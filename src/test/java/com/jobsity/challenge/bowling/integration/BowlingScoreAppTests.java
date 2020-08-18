package com.jobsity.challenge.bowling.integration;

import com.jobsity.challenge.bowling.app.BowlingScoreApp;
import com.jobsity.challenge.bowling.model.GameResult;
import com.jobsity.challenge.bowling.model.GameScore;
import com.jobsity.challenge.bowling.service.gamescore.GameScoreService;
import com.jobsity.challenge.bowling.service.scoringengine.ScoringEngineService;
import com.jobsity.challenge.bowling.util.TestsUtils;
import lombok.extern.apachecommons.CommonsLog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.jobsity.challenge.bowling.util.TestsUtils.logGameScore;

@CommonsLog
@SpringBootTest(classes = BowlingScoreApp.class)
public class BowlingScoreAppTests {
    @Autowired
    private GameScoreService gameScoreService;

    @Autowired
    private ScoringEngineService scoringEngineService;

    @Test
    void processGameResultTes01File() {
        GameResult gameResult = this.gameScoreService.parseGameResultFile("test01.txt", true);
        GameScore gameScore = this.gameScoreService.calculateGameScore(gameResult);
        this.gameScoreService.printGameScore(gameScore);
    }

    @Test
    void processGameResultTes02File() {
        GameResult gameResult = this.gameScoreService.parseGameResultFile("test02.txt", true);
        GameScore gameScore = this.gameScoreService.calculateGameScore(gameResult);
        log.info("SCORE DETAILS");
        TestsUtils.logGameScore(gameScore);
        log.info("PRINT");
        this.gameScoreService.printGameScore(gameScore);
    }

    @Test
    void processGameResultTest03File() {
        log.info("PRINT");
        GameScore gameScore = this.gameScoreService.processGameResult("test03.txt", true);
        log.info("SCORE DETAILS");
        TestsUtils.logGameScore(gameScore);
    }

    @Test
    void processGameResultTest04File() {
        log.info("PRINT");
        GameScore gameScore = this.gameScoreService.processGameResult("test04.txt", true);
        log.info("SCORE DETAILS");
        TestsUtils.logGameScore(gameScore);
    }

    @Test
    void processGameResultTest05File() {
        log.info("PRINT");
        GameScore gameScore = this.gameScoreService.processGameResult("test05.txt", true);
        log.info("SCORE DETAILS");
        TestsUtils.logGameScore(gameScore);
    }

    @Test
    void processGameResultTest06File() {
        log.info("PRINT");
        GameScore gameScore = this.gameScoreService.processGameResult("test06.txt", true);
        log.info("SCORE DETAILS");
        TestsUtils.logGameScore(gameScore);
    }

    @Test
    void processGameResultTest07File() {
        log.info("PRINT");
        GameScore gameScore = this.gameScoreService.processGameResult("test07.txt", true);
        log.info("SCORE DETAILS");
        TestsUtils.logGameScore(gameScore);
    }

    @Test
    void processGameResultTest08File() {
        log.info("PRINT");
        GameScore gameScore = this.gameScoreService.processGameResult("test08.txt", true);
        log.info("SCORE DETAILS");
        TestsUtils.logGameScore(gameScore);
    }

    @Test
    void processResultDataPerfectPunctuationOnePlayerIntegrationCase() {
        List<String> data = TestsUtils.generatePerfectRawDataOnePlayer();
        GameResult gameResult = this.scoringEngineService.validateResultData("test-one-player.txt", data);
        GameScore gameScore = this.scoringEngineService.processResultData(gameResult);
        logGameScore(gameScore);
    }

    @Test
    void processResultDataPerfectPunctuationTwoPlayersIntegrationCase() {
        List<String> data = TestsUtils.generatePerfectRawDataTwoPlayers();
        GameResult gameResult = this.scoringEngineService.validateResultData("test-two-players.txt", data);
        GameScore gameScore = this.scoringEngineService.processResultData(gameResult);
        logGameScore(gameScore);
    }

    @Test
    void processResultDataChallengeExampleValidIntegrationCase() {
        List<String> data = TestsUtils.generateChallengeExampleValidCase();
        GameResult gameResult = this.scoringEngineService.validateResultData("test-two-players.txt", data);
        GameScore gameScore = this.scoringEngineService.processResultData(gameResult);
        logGameScore(gameScore);
    }
}