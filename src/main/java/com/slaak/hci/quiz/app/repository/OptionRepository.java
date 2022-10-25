package com.slaak.hci.quiz.app.repository;

import com.slaak.hci.quiz.app.models.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository extends JpaRepository<Option, Long> {
}
