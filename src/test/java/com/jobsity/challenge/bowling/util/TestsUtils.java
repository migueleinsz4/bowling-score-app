package com.jobsity.challenge.bowling.util;

import com.jobsity.challenge.bowling.model.*;
import lombok.extern.apachecommons.CommonsLog;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@CommonsLog
public class TestsUtils {
    public static List<String> generatePerfectRawDataOnePlayer() {
        List<String> data = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            data.add("Jeff\t10");
        }
        return data;
    }

    public static List<String> generateInvalidNumberOfFramesOnePlayer() {
        List<String> data = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            data.add("Jeff\t10");
        }
        return data;
    }

    public static List<String> generateWorstResultRawDataOnePlayer() {
        List<String> data = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            data.add("Jeff\tF");
        }
        return data;
    }

    public static List<String> generateOtherWorstResultRawDataOnePlayer() {
        List<String> data = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            data.add("Jeff\t0");
        }
        return data;
    }

    public static List<String> generateWorstResultRawDataTwoPlayers() {
        List<String> data = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            data.add("Jeff\tF");
            data.add("Jeff\tF");
            data.add("Carl\tF");
            data.add("Carl\tF");
        }
        return data;
    }

    public static List<String> generateInvalidOrderRawDataTwoPlayers() {
        return Arrays.asList(
            "Jeff\t10",
            "Jeff\t10",
            "Jeff\t10",
            "Jeff\t10",
            "Jeff\t10",
            "Jeff\t10",
            "Jeff\t10",
            "Jeff\t10",
            "Jeff\t10",
            "Jeff\t10",
            "Jeff\t10",
            "Jeff\t10",
            "Carl\t10",
            "Carl\t10",
            "Carl\t10",
            "Carl\t10",
            "Carl\t10",
            "Carl\t10",
            "Carl\t10",
            "Carl\t10",
            "Carl\t10",
            "Carl\t10",
            "Carl\t10",
            "Carl\t10"
        );
    }

    public static List<String> generatePerfectRawDataTwoPlayers() {
        return Arrays.asList(
            "Jeff\t10",
            "Carl\t10",
            "Jeff\t10",
            "Carl\t10",
            "Jeff\t10",
            "Carl\t10",
            "Jeff\t10",
            "Carl\t10",
            "Jeff\t10",
            "Carl\t10",
            "Jeff\t10",
            "Carl\t10",
            "Jeff\t10",
            "Carl\t10",
            "Jeff\t10",
            "Carl\t10",
            "Jeff\t10",
            "Carl\t10",
            "Jeff\t10",
            "Carl\t10",
            "Jeff\t10",
            "Carl\t10",
            "Jeff\t10",
            "Carl\t10"
        );
    }

    public static List<String> generateChallengeExampleValidCase() {
        return Arrays.asList(
            "Jeff\t10",
            "John\t3",
            "John\t7",
            "Jeff\t7",
            "Jeff\t3",
            "John\t6",
            "John\t3",
            "Jeff\t9",
            "Jeff\t0",
            "John\t10",
            "Jeff\t10",
            "John\t8",
            "John\t1",
            "Jeff\t0",
            "Jeff\t8",
            "John\t10",
            "Jeff\t8",
            "Jeff\t2",
            "John\t10",
            "Jeff\tF",
            "Jeff\t6",
            "John\t9",
            "John\t0",
            "Jeff\t10",
            "John\t7",
            "John\t3",
            "Jeff\t10",
            "John\t4",
            "John\t4",
            "Jeff\t10",
            "Jeff\t8",
            "Jeff\t1",
            "John\t10",
            "John\t9",
            "John\t0"
        );
    }

    public static GameResult generatePerfectResultDataOnePlayer(String fileName, String playerName) {
        List<BasicFrame> frames = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            Integer sr = null;
            Integer tr = null;
            if (i == 10) {
                sr = 10;
                tr = 10;
            }
            frames.add(
                new BasicFrame(i, 10, sr, tr)
            );
        }

        PlayerScore<BasicFrame> perfectResult = new PlayerScore<>(
            new Player(1, playerName),
            frames
        );

        return new GameResult(
            fileName,
            Collections.singletonList(perfectResult)
        );
    }

    public static GameScore generatePerfectScoreDataOnePlayer(String fileName, String playerName) {
        List<Frame> frames = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            frames.add(
                new Frame(i, 10, null, null, 10, 20, 30*i)
            );
        }

        PlayerScore<Frame> perfectScore = new PlayerScore<>(
            new Player(1, playerName),
            frames
        );

        return new GameScore(
            LocalDateTime.now(),
            fileName,
            Collections.singletonList(perfectScore),
            null
        );
    }

    public static GameScore generatePerfectScoreDataTwoPlayer(String fileName, String playerOneName, String playerTwoName, ScoringConfiguration scoringConfiguration) {

        List<Frame> frames = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            frames.add(
                new Frame(i, 10, null, null, 10, 20, 30*i)
            );
        }
        frames.add(
            new Frame(10, 10, 10, 10, 10, 20, 30*10)
        );

        PlayerScore<Frame> jonScore = new PlayerScore<>(
            new Player(1, playerOneName),
            frames
        );

        PlayerScore<Frame> sansaScore = new PlayerScore<>(
            new Player(2, playerTwoName),
            frames
        );

        List<PlayerScore<Frame>> playersScores = Arrays.asList(
            jonScore,
            sansaScore
        );

        return new GameScore(
            LocalDateTime.now(),
            fileName,
            playersScores,
            scoringConfiguration
        );
    }

    public static GameScore generateScoreDataOnePlayer(String fileName, String playerName, ScoringConfiguration scoringConfiguration) {
        List<Frame> frames = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            frames.add(
                new Frame(i, 2, 5, null, 7, 0, 7*i)
            );
        }

        PlayerScore<Frame> normalScore = new PlayerScore<>(
            new Player(1, playerName),
            frames
        );

        return new GameScore(
            LocalDateTime.now(),
            fileName,
            Collections.singletonList(normalScore),
            scoringConfiguration
        );
    }

    /**
     * Test Jon and Sansa: this method and testJonSansa.txt have same data
     * */
    public static GameScore generateScoreDataTwoPlayers(String fileName, String playerOneName, String playerTwoName, ScoringConfiguration scoringConfiguration) {
        PlayerScore<Frame> jonScore = new PlayerScore<>(
            new Player(1, playerOneName),
            Arrays.asList(
                new Frame(1, -1, null, null, 10, 17, 27),
                new Frame(2, 10, null, null, 10, 10, 47),
                new Frame(3, 7, 3, null, 10, 5, 62),
                new Frame(4, 5, 2, null, 7, 0, 69),
                new Frame(5, 8, 0, null, 8, 0, 77),
                new Frame(6, 4, 6, null, 10, 10, 97),
                new Frame(7, 10, null, null, 10, 7, 114),
                new Frame(8, 3, 4, null, 7, 0, 121),
                new Frame(9, 0, 0, null, 0, 0, 121),
                new Frame(10, 10, 4, 10, 10, 14, 135)
            )
        );
        PlayerScore<Frame> sansaScore = new PlayerScore<>(
            new Player(2, playerTwoName),
            Arrays.asList(
                new Frame(1, 0, 4, null, 4, 0, 4),
                new Frame(2, 5, 0, null, 5, 0, 9),
                new Frame(3, 0, 2, null, 2, 0, 11),
                new Frame(4, 5, 5, null, 10, 10, 31),
                new Frame(5, 10, null, null, 10, 20, 61),
                new Frame(6, 10, null, null, 10, 15, 86),
                new Frame(7, 10, null, null, 10, 10, 106),
                new Frame(8, 5, 5, null, 10, 3, 119),
                new Frame(9, 3, 7, null, 10, 9, 138),
                new Frame(10, 9, 0, null, 9, 0, 147)
            )
        );

        List<PlayerScore<Frame>> playersScores = Arrays.asList(
            jonScore,
            sansaScore
        );

        return new GameScore(
            LocalDateTime.now(),
            fileName,
            playersScores,
            scoringConfiguration
        );

    }


    public static GameResult generateZeroResultDataTwoPlayer(String fileName, String playerOneName, String playerTwoName) {
        List<BasicFrame> frames = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            frames.add(
                new BasicFrame(i, 0, 0, null)
            );
        }


        PlayerScore<BasicFrame> playerOneScoreResult = new PlayerScore<>(
            new Player(1, playerOneName),
            frames
        );

        PlayerScore<BasicFrame> playerTwoScoreResult = new PlayerScore<>(
            new Player(2, playerTwoName),
            frames
        );

        List<PlayerScore<BasicFrame>> playersScores = Arrays.asList(
            playerOneScoreResult,
            playerTwoScoreResult
        );

        return new GameResult(
            fileName, playersScores
        );
    }

    public static GameScore generateZeroScoreDataTwoPlayer(String fileName, String playerOneName,String playerTwoName, ScoringConfiguration scoringConfiguration) {
        List<Frame> frames = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            frames.add(
                new Frame(i, 0, 0, null, 0, 0, 0)
            );
        }

        PlayerScore<Frame> playerOneScore = new PlayerScore<>(
            new Player(1, playerOneName),
            frames
        );

        PlayerScore<Frame> playerTwoScore = new PlayerScore<>(
            new Player(2, playerTwoName),
            frames
        );

        List<PlayerScore<Frame>> playersScores = Arrays.asList(
            playerOneScore,
            playerTwoScore
        );


        return new GameScore(
            LocalDateTime.now(),
            fileName,
            playersScores,
            scoringConfiguration
        );
    }

    public static void logGameResult(GameResult gameResult) {
        log.info("Filename: " + gameResult.getFileName());
        log.info("Results:");

        List<PlayerScore<BasicFrame>> results = gameResult.getResults();

        results.forEach(playerScore -> {
            log.info("Player Name: " + playerScore.getPlayer().getName());
            log.info("Last Score:" + playerScore.lastScore());

            playerScore.getFrames().forEach(basicFrame -> {
                log.info("Number: " + basicFrame.getNumber());
                log.info(basicFrame.getFirstRollScoreValue() + " -> " + basicFrame.getFirstRollScoreSymbol(10));
                log.info(basicFrame.getSecondRollScoreValue() + " -> " + basicFrame.getSecondRollScoreSymbol(10, 10));
                log.info(basicFrame.getThirdRollScoreValue() + " -> " + basicFrame.getThirdRollScoreSymbol(10, 10));
            });
        });
    }

    public static void logGameScore(GameScore gameScore) {
        log.info("Filename: " + gameScore.getFileName());
        log.info("Processing Date: " + gameScore.getProcessingDate());

        List<PlayerScore<Frame>> scores = gameScore.getScores();

        scores.forEach(playerScore -> {
            log.info("Player Name: " + playerScore.getPlayer().getName());
            log.info("Player Number: " + playerScore.getPlayer().getNumber());
            log.info("Last Score:" + playerScore.lastScore());

            playerScore.getFrames().forEach(frame -> {
                log.info("Number: " + frame.getNumber());
                log.info(frame.getFirstRollScoreValue() + " -> " + frame.getFirstRollScoreSymbol(10));
                log.info(frame.getSecondRollScoreValue() + " -> " + frame.getSecondRollScoreSymbol(10, 10));
                log.info(frame.getThirdRollScoreValue() + " -> " + frame.getThirdRollScoreSymbol(10, 10));
                log.info("Frame Score: " + frame.getFrameScore());
                log.info("Bonus: " + frame.getBonus());
                log.info("Aggregate Score: " + frame.getAggregateScore());
            });
        });
    }
}