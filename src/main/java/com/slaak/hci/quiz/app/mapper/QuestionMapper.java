package com.slaak.hci.quiz.app.mapper;

import com.slaak.hci.quiz.app.models.Questions;
import com.slaak.quiz.api.model.Question;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface QuestionMapper {

    Question toQuestionFromQuestions(Questions questions);
}
