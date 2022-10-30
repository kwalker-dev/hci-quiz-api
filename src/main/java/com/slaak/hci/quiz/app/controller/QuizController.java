package com.slaak.hci.quiz.app.controller;

import com.slaak.hci.quiz.app.service.QuestionService;
import com.slaak.quiz.api.UserApi;
import com.slaak.quiz.api.model.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class QuizController implements UserApi {
    final private QuestionService questionService;
    @Override
    public ResponseEntity<QuizResult> getQuizResult(String userName) {
        final var result =  questionService.getQuizResult(userName);
        return ResponseEntity.of(Optional.of(result));
    }

    @Override
    public ResponseEntity<List<Question>> postQuestions(PostQuestion postQuestion) {
        List<Question> questions = questionService.postQuestions(postQuestion.getUserName());
        return ResponseEntity.of(Optional.of(questions));
    }

    @Override
    public ResponseEntity<Void> putAnswers(String userName, SubmitAnswers answers) {
        questionService.putAnswers(userName,answers);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
