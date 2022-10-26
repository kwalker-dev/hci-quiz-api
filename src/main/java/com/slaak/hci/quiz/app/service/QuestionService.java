package com.slaak.hci.quiz.app.service;

import com.slaak.hci.quiz.app.models.Questions;
import com.slaak.hci.quiz.app.repository.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepo;

    public List<Questions> getQuestions(final Long quizId) {
        return questionRepo.getQuestionsByQuizId(quizId);
    }
}
