package com.slaak.hci.quiz.app.models;

import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Data
@Entity
public class Options {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long option_id;
    @Column(updatable = false, insertable = false)
    private Long question_id;
    private String opt_value;
    private boolean opt_selected;
    private boolean opt_correct;
    @CreatedDate
    private LocalDateTime start_ts;
    private LocalDateTime end_ts;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Questions question;
}
