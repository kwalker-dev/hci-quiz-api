package com.slaak.hci.quiz.app.repository;

import com.slaak.hci.quiz.app.models.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Questions, Long> {
}
