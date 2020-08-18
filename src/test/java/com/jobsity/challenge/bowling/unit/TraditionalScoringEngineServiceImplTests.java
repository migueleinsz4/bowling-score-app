package com.jobsity.challenge.bowling.unit;

import com.jobsity.challenge.bowling.model.*;
import com.jobsity.challenge.bowling.service.scoringengine.ScoringEngineService;
import com.jobsity.challenge.bowling.service.scoringengine.TraditionalScoringEngineServiceImpl;
import com.jobsity.challenge.bowling.util.TestsUtils;
import lombok.extern.apachecommons.CommonsLog;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static com.jobsity.challenge.bowling.util.TestsUtils.logGameResult;
import static com.jobsity.challenge.bowling.util.TestsUtils.logGameScore;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@CommonsLog
public class TraditionalScoringEngineServiceImplTests {
    ScoringEngineService scoringEngineService = new TraditionalScoringEngineServiceImpl();

    //@Test
    void processResultDataGeneralValidCase01() {
        PlayerScore<Frame> jonScore = new PlayerScore<>(
            new Player(1, "Jon"),
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
            new Player(2, "Sansa"),
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
            null
        );

        PlayerScore<BasicFrame> jonResult = new PlayerScore<>(
            new Player(1, "Jon"),
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
            new Player(2, "Sansa"),
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
    }

    //@Test
    void processResultDataPerfectPunctuationOnePlayerCase() {
        GameResult gameResult = TestsUtils.generatePerfectResultDataOnePlayer("test.txt","Jon");
        GameScore expectedGameScore = TestsUtils.generatePerfectScoreDataOnePlayer("test.txt", "Jon");

        GameScore actualGameScore = this.scoringEngineService.processResultData(gameResult);

        assertEquals(expectedGameScore.getFileName(), actualGameScore.getFileName());
        assertEquals(expectedGameScore.getScores().size(), actualGameScore.getScores().size());

        for (int i = 0; i < actualGameScore.getScores().size(); i++) {
            assertEquals(expectedGameScore.getScores().get(i), actualGameScore.getScores().get(i));
        }
    }

    @Test
    void validateResultDataPerfectPunctuationOnePlayerCase() {
        List<String> data = TestsUtils.generatePerfectRawDataOnePlayer();
        GameResult gameResult = this.scoringEngineService.validateResultData("test-one-player.txt", data);
        logGameResult(gameResult);
    }

    @Test
    void processResultDataPerfectPunctuationOnePlayerIntegrationCase() {
        List<String> data = TestsUtils.generatePerfectRawDataOnePlayer();
        GameResult gameResult = this.scoringEngineService.validateResultData("test-one-player.txt", data);
        GameScore gameScore = this.scoringEngineService.processResultData(gameResult);
        logGameScore(gameScore);
    }

    @Test
    void validateResultDataPerfectPunctuationTwoPlayersCase() {
        List<String> data = TestsUtils.generatePerfectRawDataTwoPlayers();
        GameResult gameResult = this.scoringEngineService.validateResultData("test-two-players.txt", data);
        logGameResult(gameResult);
    }

    @Test
    void processResultDataPerfectPunctuationTwoPlayersIntegrationCase() {
        List<String> data = TestsUtils.generatePerfectRawDataTwoPlayers();
        GameResult gameResult = this.scoringEngineService.validateResultData("test-two-players.txt", data);
        GameScore gameScore = this.scoringEngineService.processResultData(gameResult);
        logGameScore(gameScore);
    }

    @Test
    void validateResultDataInvalidNumberOfFramesOnePlayerCase() {
        List<String> data = TestsUtils.generateInvalidNumberOfFramesOnePlayer();
        GameResult gameResult = this.scoringEngineService.validateResultData("test-one-player.txt", data);
        logGameResult(gameResult);
    }

    @Test
    void validateResultDataWorstPunctuationOnePlayerCase() {
        List<String> data = TestsUtils.generateWorstResultRawDataOnePlayer();
        GameResult gameResult = this.scoringEngineService.validateResultData("test-one-player.txt", data);
        logGameResult(gameResult);
    }

    @Test
    void validateResultDataOtherWorstPunctuationOnePlayerCase() {
        List<String> data = TestsUtils.generateOtherWorstResultRawDataOnePlayer();
        GameResult gameResult = this.scoringEngineService.validateResultData("test-one-player.txt", data);
        logGameResult(gameResult);
    }

    @Test
    void validateResultDataWorstPunctuationTwoPlayerCase() {
        List<String> data = TestsUtils.generateWorstResultRawDataTwoPlayers();
        GameResult gameResult = this.scoringEngineService.validateResultData("test-two-players.txt", data);
        logGameResult(gameResult);
    }

    @Test
    void validateResultDataInvalidOrderTwoPlayersCase() {
        List<String> data = TestsUtils.generateInvalidOrderRawDataTwoPlayers();
        GameResult gameResult = this.scoringEngineService.validateResultData("test-two-players.txt", data);
        logGameResult(gameResult);
    }

    @Test
    void processResultDataChallengeExampleValidIntegrationCase() {
        List<String> data = TestsUtils.generateChallengeExampleValidCase();
        GameResult gameResult = this.scoringEngineService.validateResultData("test-two-players.txt", data);
        GameScore gameScore = this.scoringEngineService.processResultData(gameResult);
        logGameScore(gameScore);
    }

}