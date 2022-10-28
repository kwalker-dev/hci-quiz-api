package com.slaak.hci.quiz.app.service;

import com.slaak.hci.quiz.app.mapper.QuestionMapper;
import com.slaak.hci.quiz.app.models.Questions;
import com.slaak.hci.quiz.app.models.Quiz;
import com.slaak.hci.quiz.app.models.Users;
import com.slaak.hci.quiz.app.models.api.TriviaApiResponse;
import com.slaak.hci.quiz.app.repository.OptionRepository;
import com.slaak.hci.quiz.app.repository.QuestionRepository;
import com.slaak.hci.quiz.app.repository.QuizRepository;
import com.slaak.hci.quiz.app.repository.UsersRepository;
import com.slaak.quiz.api.model.Answer;
import com.slaak.quiz.api.model.Question;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepo;
    private final QuizRepository quizRepo;
    private final UsersRepository userRepo;
    private final OptionRepository optionRepo;
    private final QuestionMapper questionMapper;

    public List<Question> getQuestions(final String userName) {
        return questionRepo.getQuestionsByUserName(userName).stream()
                .map(questionMapper::toQuestionFromQuestions).collect(Collectors.toList());
    }

    public List<Question> postQuestions(final String userName) {
        final var triviaQuestions = callTriviaApi();

        if (triviaQuestions.isEmpty()) {
            return List.of();
        }

        final var user = getUser(userName);

        archivePrevQuiz(userName);

        final var quiz = new Quiz();
        quiz.setQuestionsTotal(triviaQuestions.size());
        quiz.setUser(user);
        quiz.setStart_ts(LocalDateTime.now());

        final var savedQuiz = quizRepo.save(quiz);
        int questionNum = 1;

        for (Questions question : triviaQuestions) {
            question.setQuiz(savedQuiz);
            question.setQuestionNum(questionNum);
            question.setStart_ts(LocalDateTime.now());
            questionNum++;
        }

        questionRepo.saveAll(triviaQuestions);
        return triviaQuestions.stream()
                .map(questionMapper::toQuestionFromQuestions).collect(Collectors.toList());
    }

    public void putAnswers(final String userName, final List<Answer> answers) {
        int correct = 0;
        for (Answer answer : answers) {
            final var optionOpt = optionRepo.findById(answer.getOptionId());
            if (optionOpt.isPresent()) {
                final var option = optionOpt.get();
                option.setSelected(true);
                optionRepo.save(option);
                if (option.isCorrect()) {
                    correct++;
                }
            }

        }

        final var quiz = quizRepo.findActvQuiz(userName);
        quiz.setQuestionsCorrect(correct);
        quizRepo.save(quiz);
    }

    private List<Questions> callTriviaApi() {
        RestTemplate restTemplate = new RestTemplate();
        final var resp = restTemplate
                .getForObject("https://the-trivia-api.com/api/questions?limit=20&region=US&difficulty=medium&tags"
                        + "=computing,mathematics,physics,technology", TriviaApiResponse[].class);

        if (resp == null) {
            return List.of();
        }

        final var list =  Arrays.stream(resp).map(questionMapper::toQuestionsFromTriviaQuestion)
                .collect(Collectors.toList());

        Collections.shuffle(list);

        return list;
    }

    private Users getUser(final String userName) {
        var user = userRepo.findActiveUser(userName);

        if (user == null) {
            user = new Users();
            user.setUserName(userName);
            user.setStart_ts(LocalDateTime.now());

            user = userRepo.save(user);
        }

        return user;
    }

    private void archivePrevQuiz(final String userName) {
        final var prevQuiz = quizRepo.findActvQuiz(userName);

        if (prevQuiz != null) {
            prevQuiz.setEnd_ts(LocalDateTime.now());
            quizRepo.save(prevQuiz);
        }
    }

}
