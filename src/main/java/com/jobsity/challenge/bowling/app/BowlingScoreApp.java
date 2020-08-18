package com.jobsity.challenge.bowling.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.annotation.PreDestroy;

@SpringBootApplication(scanBasePackages = {"com.jobsity.challenge.bowling"})
@EnableAspectJAutoProxy
public class BowlingScoreApp implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BowlingScoreApp.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Starting Bowling Score App...");
		for (String arg : args) {
			System.out.println(arg);
		}
	}

	@PreDestroy
	public void onExit() {
		System.out.println("Exit Bowling Score App");
	}
}