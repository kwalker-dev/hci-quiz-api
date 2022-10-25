package com.slaak.hci.quiz.app.controller;

import com.slaak.quiz.api.QuizApi;
import com.slaak.quiz.api.model.Option;
import com.slaak.quiz.api.model.Question;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class QuizController implements QuizApi {

    @Override
    public ResponseEntity<List<Question>> getQuestions() {
        List<Question> questions = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            final Question question = new Question();
            question.setQuestionId((long) i+1);
            question.setQuestionNum(i+1);
            question.setQuestion("This is a test question.");
            Option option = new Option();
            option.setValue("Answer 1");
            Option option2 = new Option();
            option2.setValue("Answer 2");
            Option option3 = new Option();
            option3.setValue("Answer 3");
            Option option4 = new Option();
            option4.setValue("Answer 4");
            question.setOptions(List.of(option,option2,option3,option4));
            questions.add(question);
        }


        return ResponseEntity.of(Optional.of(questions));
    }

    @Override
    public ResponseEntity<String> putQuestions(List<Question> questions) {
        return QuizApi.super.putQuestions(questions);
    }

    @Override
    public ResponseEntity<String> postQuestions(List<Question> questions) {
        return null;
    }
}
