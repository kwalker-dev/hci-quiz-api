package com.slaak.hci.quiz.app.repository;

import com.slaak.hci.quiz.app.models.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Questions, Long> {
    @Query("SELECT distinct q from Questions q "
            + "JOIN FETCH q.options o "
            + "JOIN q.quiz qz "
            + "JOIN qz.user u "
            + "WHERE qz.quizId = :quizId")
    List<Questions> getQuestionsByQuizId(Long quizId);
}
