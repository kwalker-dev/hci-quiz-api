package com.slaak.hci.quiz.app;

import com.slaak.hci.quiz.app.controller.QuizController;
import com.slaak.hci.quiz.app.mapper.QuestionMapper;
import com.slaak.hci.quiz.app.service.QuestionService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
