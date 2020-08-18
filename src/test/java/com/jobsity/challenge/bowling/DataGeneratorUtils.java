package com.jobsity.challenge.bowling;

import com.jobsity.challenge.bowling.model.*;
import com.jobsity.challenge.bowling.service.scoringengine.ScoringEngineService;
import com.jobsity.challenge.bowling.service.scoringengine.TraditionalScoringEngineServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class DataGeneratorUtils {
    public static GameResult generatePerfectResultDataTwoPlayer(String fileName, String playerOneName, String playerTwoName) {
        List<BasicFrame> frames = new ArrayList<>();

        for (int i = 1; i < 10; i++) {
            frames.add(
                new BasicFrame(i, 10, null, null)
            );
        }
        frames.add(new BasicFrame(10, 10, 10, 10));


        PlayerScore<BasicFrame> playerOneScoreResult = new PlayerScore<>(
                new Player(playerOneName),
                frames
        );

        PlayerScore<BasicFrame> playerTwoScoreResult = new PlayerScore<>(
                new Player(playerTwoName),
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
                new Player(playerOneName),
                frames
        );

        PlayerScore<Frame> sansaScore = new PlayerScore<>(
                new Player(playerTwoName),
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
                playerOneName,
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
                new Player(playerName),
                frames
        );

        return new GameScore(
                LocalDateTime.now(),
                fileName,
                Collections.singletonList(normalScore),
                playerName,
                scoringConfiguration
        );
    }

    /**
     * Test Jon and Sansa: this method and testJonSansa.txt have same data
     * */
    public static GameScore generateScoreDataTwoPlayers(String fileName, String playerOneName, String playerTwoName, ScoringConfiguration scoringConfiguration) {
        PlayerScore<Frame> jonScore = new PlayerScore<>(
                new Player(playerOneName),
                Arrays.asList(
                        new Frame(1, 10, null, null, 10, 20, 30),
                        new Frame(2, 10, null, null, 10, 20, 60),
                        new Frame(3, 7, 3, null, 10, 5, 75),
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
                playerOneName,
                scoringConfiguration
        );

    }


    public static GameResult generateCeroResultDataTwoPlayer(String fileName, String playerOneName, String playerTwoName) {
        List<BasicFrame> frames = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            frames.add(
                    new BasicFrame(i, 0, 0, null)
            );
        }


        PlayerScore<BasicFrame> playerOneScoreResult = new PlayerScore<>(
                new Player(playerOneName),
                frames
        );

        PlayerScore<BasicFrame> playerTwoScoreResult = new PlayerScore<>(
                new Player(playerTwoName),
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

    public static GameScore generateCeroScoreDataTwoPlayer(String fileName, String playerOneName,String playerTwoName, ScoringConfiguration scoringConfiguration) {

        List<Frame> frames = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            frames.add(
                    new Frame(i, 0, 0, null, 0, 0, 0)
            );
        }

        PlayerScore<Frame> playerOneScore = new PlayerScore<>(
                new Player(playerOneName),
                frames
        );

        PlayerScore<Frame> playerTwoScore = new PlayerScore<>(
                new Player(playerTwoName),
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
                playerOneName,
                scoringConfiguration
        );
    }

    //TODO the FRAME class just allow integer in first, second and third roll, there are cases when rollScore value is "F" (fault)
}