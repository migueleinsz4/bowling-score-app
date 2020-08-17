package com.jobsity.challenge.bowling;

import com.jobsity.challenge.bowling.model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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

    public static GameScore generateScoreDataOnePlayer(String fileName, String playerName) {
        List<Frame> frames = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            frames.add(
                    new Frame(i, 2, 5, null, 7, 0, 7*i)
            );
        }

        PlayerScore<Frame> normalScore = new PlayerScore<>(
                new Player(playerName),
                frames
        );

        return new GameScore(
                LocalDateTime.now(),
                fileName,
                Collections.singletonList(normalScore),
                playerName
        );
    }

    public static GameScore generateScoreDataTwoPlayers(String fileName, String playerOneName, String playerTwoName) {
        PlayerScore<Frame> jonScore = new PlayerScore<>(
                new Player(playerOneName),
                Arrays.asList(
                        new Frame(1, 10, null, null, 10, 20, 30),
                        new Frame(2, 10, null, null, 10, 20, 60),
                        new Frame(3, 7, 3, null, 15, 5, 75),
                        new Frame(4, 5, 2, null, 7, 0, 82),
                        new Frame(5, 8, 0, null, 8, 0, 90),
                        new Frame(6, 4, 6, null, 10, 10, 110),
                        new Frame(7, 10, null, null, 10, 17, 127),
                        new Frame(8, 3, 4, null, 7, 0, 134),
                        new Frame(9, 0, 0, null, 0, 0, 134),
                        new Frame(10, 10, 4, 10, 10, 14, 158)
                )
        );
        PlayerScore<Frame> sansaScore = new PlayerScore<>(
                new Player(playerTwoName),
                Arrays.asList(
                        new Frame(1, 0, 4, null, 4, 0, 4),
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

        return new GameScore(
                LocalDateTime.now(),
                fileName,
                playersScores,
                playerOneName
        );

    }
}