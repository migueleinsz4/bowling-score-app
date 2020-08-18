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
            frames.add(
                new BasicFrame(i, 10, null, null)
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