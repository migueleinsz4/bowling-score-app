package com.jobsity.challenge.bowling.app;

import com.jobsity.challenge.bowling.service.gamescore.GameScoreService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.annotation.PreDestroy;

@SpringBootApplication(scanBasePackages = {"com.jobsity.challenge.bowling"})
@EnableAspectJAutoProxy
@CommonsLog
public class BowlingScoreApp implements CommandLineRunner {
	@Value("${file:}")
	private String fileName;

	@Value("${scoring-engine:traditional}")
	private String scoringEngineName;

	@Value("${print-engine:console}")
	private String printEngineName;

	private final GameScoreService gameScoreService;

	@Autowired
	public BowlingScoreApp(GameScoreService gameScoreService) {
		this.gameScoreService = gameScoreService;
	}

	public static void main(String[] args) {
		SpringApplication.run(BowlingScoreApp.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("\n********************************");
		System.out.println("\nStarting Bowling Score App...");
		System.out.println("File: " + this.fileName);
		System.out.println("Scoring Engine: " + this.scoringEngineName);
		System.out.println("Print Engine: " + this.printEngineName);

		if (this.fileName.isEmpty()) {
			System.out.println("Empty filename");
			System.out.println("Usage: java -jar bowling-score-app-1.0.0.jar --file=filename.txt");
		} else {
			try {
				this.gameScoreService.processGameResult(this.fileName, false);
			} catch (Exception e) {
				log.debug(e.getMessage());
			}
		}
	}

	@PreDestroy
	public void onExit() {
		System.out.println("\nExit Bowling Score App");
		System.out.println("\n********************************");
	}
}