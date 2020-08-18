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
import java.util.regex.Pattern;

@CommonsLog
public class TraditionalScoringEngineServiceImplTests {
    ScoringEngineService scoringEngineService = new TraditionalScoringEngineServiceImpl();
    ScoringConfiguration scoringConfiguration = new ScoringConfiguration(
            "Traditional Scoring",
            300,
            0,
            10,
            10,
            21,
            1,
            10,
            "\\t",
            2,
            Pattern.compile("[a-zA-Z]+"),
            Pattern.compile("10|[0-9|F]"),
            "F"
    );

    /**
     * Test Jon and Sansa: the DataGeneratorUtils, this method and testJonSansa.txt have same data
     * */
    @Test
    void processResultDataGeneralValidCase01() {
        GameScore expectedGameScore =
                DataGeneratorUtils.generateScoreDataTwoPlayers("testJonSansa.txt", "Jon", "Sansa", scoringConfiguration);

        PlayerScore<BasicFrame> jonResult = new PlayerScore<>(
                new Player("Jon"),
                Arrays.asList(
                        new BasicFrame(1, 10, null, null),
                        new BasicFrame(2, 10, null, null),
                        new BasicFrame(3, 7, 3, null),
                        new BasicFrame(4, 5, 2, null),
                        new BasicFrame(5, 8, 0, null),
                        new BasicFrame(6, 4, 6, null),
                        new BasicFrame(7, 10, null, null),
                        new BasicFrame(8, 3, 4, null),
                        new BasicFrame(9, 0, 0, null),
                        new BasicFrame(10, 10, 4, 10)
                )
        );

        PlayerScore<BasicFrame> sansaResult = new PlayerScore<>(
                new Player("Sansa"),
                Arrays.asList(
                        new BasicFrame(1, 0, 4, null),
                        new BasicFrame(2, 5, 0, null),
                        new BasicFrame(3, 0, 2, null),
                        new BasicFrame(4, 5, 5, null),
                        new BasicFrame(5, 10, null, null),
                        new BasicFrame(6, 10, null, null),
                        new BasicFrame(7, 10, null, null),
                        new BasicFrame(8, 5, 5, null),
                        new BasicFrame(9, 3, 7, null),
                        new BasicFrame(10, 9, 0, null)
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

        GameResult gameResult = DataGeneratorUtils.generatePerfectResultDataTwoPlayer("test02.txt", "Jon", "Sansa");
        GameScore expectedGameScore = DataGeneratorUtils.generatePerfectScoreDataTwoPlayer("test02.txt", "Jon","Sansa", scoringConfiguration);

        GameScore actualGameScore = this.scoringEngineService.processResultData(gameResult);

        assertEquals(expectedGameScore.getFileName(), actualGameScore.getFileName());
        assertEquals(expectedGameScore.getWinner(), actualGameScore.getWinner());
        assertEquals(expectedGameScore.getScores().size(), actualGameScore.getScores().size());

        for (int i = 0; i < actualGameScore.getScores().size(); i++) {
            assertEquals(expectedGameScore.getScores().get(i), actualGameScore.getScores().get(i));
        }
    }

    @Test
    void processResultDataCeroPuntuationOnePlayerCase() {
        log.info("My first test");

        GameResult gameResult = DataGeneratorUtils.generateCeroResultDataTwoPlayer("test01.txt", "Jon","Sansa");
        GameScore expectedGameScore = DataGeneratorUtils.generateCeroScoreDataTwoPlayer("test01.txt", "Jon","Sansa", scoringConfiguration);

        GameScore actualGameScore = this.scoringEngineService.processResultData(gameResult);

        assertEquals(expectedGameScore.getFileName(), actualGameScore.getFileName());
        assertEquals(expectedGameScore.getWinner(), actualGameScore.getWinner());
        assertEquals(expectedGameScore.getScores().size(), actualGameScore.getScores().size());

        for (int i = 0; i < actualGameScore.getScores().size(); i++) {
            assertEquals(expectedGameScore.getScores().get(i), actualGameScore.getScores().get(i));
        }
    }

}