package com.jobsity.challenge.bowling.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.jobsity.challenge.bowling"})
public class BowlingScoreApp {

	public static void main(String[] args) {
		SpringApplication.run(BowlingScoreApp.class, args);
	}

}
