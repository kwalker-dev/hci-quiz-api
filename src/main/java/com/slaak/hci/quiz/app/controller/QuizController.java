package com.slaak.hci.quiz.app.controller;

import com.slaak.hci.quiz.app.mapper.QuestionMapper;
import com.slaak.hci.quiz.app.service.QuestionService;
import com.slaak.quiz.api.UserApi;
import com.slaak.quiz.api.model.PostQuestion;
import com.slaak.quiz.api.model.Question;
import lombok.AllArgsConstructor;
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
    final private QuestionMapper questionMapper;

    @Override
    public ResponseEntity<List<Question>> getQuestions(String userName) {
        List<Question> questions =  questionService.getQuestions(userName);

        return ResponseEntity.of(Optional.of(questions));
    }

    @Override
    public ResponseEntity<List<Question>> postQuestions(String userName, PostQuestion postQuesti) {
        List<Question> questions = questionService.postQuestions(userName);
        return ResponseEntity.of(Optional.of(questions));
    }

    @Override
    public ResponseEntity<List<Question>> putQuestions(String userName, List<Question> question) {
        return UserApi.super.putQuestions(userName, question);
    }

}
