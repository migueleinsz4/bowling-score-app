package com.jobsity.challenge.bowling.integration;

import com.jobsity.challenge.bowling.app.BowlingScoreApp;
import com.jobsity.challenge.bowling.model.GameResult;
import com.jobsity.challenge.bowling.service.gamescore.GameScoreService;
import com.jobsity.challenge.bowling.util.TestsUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = BowlingScoreApp.class)
public class GameScoreServiceImplTests {
    @Autowired
    private GameScoreService gameScoreService;

    @Test
    void parseGameResultFilePerfectPunctuationOnePlayerCase() {
        List<String> data = TestsUtils.generatePerfectRawDataOnePlayer();
        //GameResult gameResult = this.gameScoreService.calculateGameScore();
    }

}