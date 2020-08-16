package com.jobsity.challenge.bowling;

import com.jobsity.challenge.bowling.model.*;
import com.jobsity.challenge.bowling.service.scoringengine.ScoringEngineService;
import com.jobsity.challenge.bowling.service.scoringengine.TraditionalScoringEngineServiceImpl;
import lombok.extern.apachecommons.CommonsLog;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@CommonsLog
public class TraditionalScoringEngineServiceImplTests {
    ScoringEngineService scoringEngineService = new TraditionalScoringEngineServiceImpl();

    @Test
    void processResultDataGeneralValidCase01() {
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

        GameScore expectedGameScore = new GameScore(
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

        GameScore actualGameScore = this.scoringEngineService.processResultData(gameResult);

       assertEquals(expectedGameScore.getFileName(), actualGameScore.getFileName());
       assertEquals(expectedGameScore.getWinner(), actualGameScore.getWinner());
    }

    @Test
    void processResultDataPerfectPuntuationOnePlayerCase() {
        log.info("My first test");

        GameResult gameResult = DataGeneratorUtils.generatePerfectResultDataOnePlayer("test.txt","Jon");
        GameScore expectedGameScore = DataGeneratorUtils.generatePerfectScoreDataOnePlayer("test.txt", "Jon");

        GameScore actualGameScore = this.scoringEngineService.processResultData(gameResult);

        assertEquals(expectedGameScore.getFileName(), actualGameScore.getFileName());
        assertEquals(expectedGameScore.getWinner(), actualGameScore.getWinner());
        assertEquals(expectedGameScore.getScores().size(), actualGameScore.getScores().size());

        for (int i = 0; i < actualGameScore.getScores().size(); i++) {
            assertEquals(expectedGameScore.getScores().get(i), actualGameScore.getScores().get(i));
        }
    }

}