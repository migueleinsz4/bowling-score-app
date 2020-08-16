package com.jobsity.challenge.bowling;

import com.jobsity.challenge.bowling.model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataGeneratorUtils {
    public static GameResult generatePerfectResultDataOnePlayer(String fileName, String playerName) {
        List<BasicFrame> frames = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            frames.add(
                new BasicFrame(i, 10, null, null)
            );
        }

        PlayerScore<BasicFrame> perfectResult = new PlayerScore<>(
            new Player(playerName),
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
            new Player(playerName),
            frames
        );

        return new GameScore(
            LocalDateTime.now(),
            fileName,
            Collections.singletonList(perfectScore),
            playerName
        );
    }
}