package com.jobsity.challenge.bowling;

import com.jobsity.challenge.bowling.model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DataGeneratorUtils {
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
            playerName,
            null
        );
    }
}