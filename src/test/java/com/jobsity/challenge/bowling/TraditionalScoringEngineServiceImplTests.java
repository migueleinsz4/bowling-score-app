package com.jobsity.challenge.bowling;

import com.jobsity.challenge.bowling.model.*;
import com.jobsity.challenge.bowling.service.scoring.ScoringEngineService;
import com.jobsity.challenge.bowling.service.scoring.TraditionalScoringEngineServiceImpl;
import lombok.extern.apachecommons.CommonsLog;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@CommonsLog
public class TraditionalScoringEngineServiceImplTests {
    ScoringEngineService scoringEngineService = new TraditionalScoringEngineServiceImpl();

    @Test
    void processResultDataGeneralCase01() {
        PlayerScore<Frame> jonScore = new PlayerScore<>(
            new Player("Jon"),
            Arrays.asList(
                new Frame(1, 10, null, null, 10, 8, 18),
                new Frame(2, 10, null, null, 10, 8, 18),
                new Frame(3, 10, null, null, 10, 8, 18),
                new Frame(4, 10, null, null, 10, 8, 18),
                new Frame(5, 10, null, null, 10, 8, 18),
                new Frame(6, 10, null, null, 10, 8, 18),
                new Frame(7, 10, null, null, 10, 8, 18),
                new Frame(8, 10, null, null, 10, 8, 18),
                new Frame(9, 10, null, null, 10, 8, 18),
                new Frame(10, 10, null, null, 10, 8, 300)
            )
        );

        PlayerScore<Frame> sansaScore = new PlayerScore<>(
            new Player("Sansa"),
            Arrays.asList(
                new Frame(1, 5, null, null, 10, 8, 18),
                new Frame(2, 5, null, null, 10, 8, 18),
                new Frame(3, 5, null, null, 10, 8, 18),
                new Frame(4, 5, null, null, 10, 8, 18),
                new Frame(5, 5, null, null, 10, 8, 18),
                new Frame(6, 5, null, null, 10, 8, 18),
                new Frame(7, 5, null, null, 10, 8, 18),
                new Frame(8, 5, null, null, 10, 8, 18),
                new Frame(9, 5, null, null, 10, 8, 18),
                new Frame(10, 5, null, null, 10, 8, 18)
            )
        );

        List<PlayerScore<Frame>> playersScores = Arrays.asList(
            jonScore,
            sansaScore
        );

        GameScore gameScoreExpected = new GameScore(
            LocalDateTime.now(),
            "test.txt",
            playersScores,
            "Jon"
        );

        PlayerScore<BasicFrame> jonResult = new PlayerScore<>(
            new Player("Jon"),
            Arrays.asList(
                new BasicFrame(1, 5, null, null),
                new BasicFrame(2, 5, null, null),
                new BasicFrame(3, 5, null, null),
                new BasicFrame(4, 5, null, null),
                new BasicFrame(5, 5, null, null),
                new BasicFrame(6, 5, null, null),
                new BasicFrame(7, 5, null, null),
                new BasicFrame(8, 5, null, null),
                new BasicFrame(9, 5, null, null),
                new BasicFrame(10, 5, null, null)
            )
        );

        PlayerScore<BasicFrame> sansaResult = new PlayerScore<>(
            new Player("Sansa"),
            Arrays.asList(
                new BasicFrame(1, 10, null, null),
                new BasicFrame(2, 10, null, null),
                new BasicFrame(3, 10, null, null),
                new BasicFrame(4, 10, null, null),
                new BasicFrame(5, 10, null, null),
                new BasicFrame(6, 10, null, null),
                new BasicFrame(7, 10, null, null),
                new BasicFrame(8, 10, null, null),
                new BasicFrame(9, 10, null, null),
                new BasicFrame(10, 10, null, null)
            )
        );

        GameResult gameResult = new GameResult(
            "test.txt",
            Arrays.asList(jonResult, sansaResult)
        );

        GameScore gameScore = this.scoringEngineService.processResultData(gameResult);

       assertEquals(gameScoreExpected.getFileName(), gameScore.getFileName());
       assertEquals(gameScoreExpected.getWinner(), gameScore.getWinner());
    }

    @Test
    void processResultDataPerfectPuntuationOnePlayerCase() {
        log.info("My first test");

        GameResult gameResult = DataGeneratorUtils.generatePerfectResultDataOnePlayer("test.txt","Jon");
        GameScore gameScoreExpected = DataGeneratorUtils.generatePerfectScoreDataOnePlayer("test.txt", "Jon");

        GameScore gameScore = this.scoringEngineService.processResultData(gameResult);

        assertEquals(gameScoreExpected.getFileName(), gameScore.getFileName());
        assertEquals(gameScoreExpected.getWinner(), gameScore.getWinner());
        assertEquals(gameScoreExpected.getScores().size(), gameScore.getScores().size());

        for (int i = 0; i < gameScore.getScores().size(); i++) {
            assertEquals(gameScoreExpected.getScores().get(i), gameScore.getScores().get(i));
        }
    }

}