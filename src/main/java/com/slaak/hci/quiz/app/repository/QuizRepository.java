package com.slaak.hci.quiz.app.repository;

import com.slaak.hci.quiz.app.models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    @Query("select q from Quiz q "
    + "JOIN q.user u "
    + "WHERE q.end_ts is null "
    + "AND u.end_ts is null")
    Quiz findActvQuiz(String userName);
}
