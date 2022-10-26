package com.slaak.hci.quiz.app.controller;

import com.slaak.hci.quiz.app.mapper.QuestionMapper;
import com.slaak.hci.quiz.app.service.QuestionService;
import com.slaak.quiz.api.QuizApi;
import com.slaak.quiz.api.model.Option;
import com.slaak.quiz.api.model.Question;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api")
@AllArgsConstructor
public class QuizController implements QuizApi {
    final private QuestionService questionService;
    final private QuestionMapper questionMapper;

    @Override
    public ResponseEntity<List<Question>> getQuestions() {
        List<Question> questions =  questionService.getQuestions(1L).stream()
                .map(questionMapper::toQuestionFromQuestions).collect(Collectors.toList());

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
