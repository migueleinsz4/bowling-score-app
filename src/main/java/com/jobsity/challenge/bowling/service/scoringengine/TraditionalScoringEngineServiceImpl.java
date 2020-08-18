package com.jobsity.challenge.bowling.service.scoringengine;

import com.jobsity.challenge.bowling.exception.InvalidFormatException;
import com.jobsity.challenge.bowling.model.*;
import com.jobsity.challenge.bowling.util.Utils;
import lombok.Getter;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@CommonsLog
public class TraditionalScoringEngineServiceImpl implements ScoringEngineService {
    private final ScoringConfiguration scoringConfiguration;

    public TraditionalScoringEngineServiceImpl() {
        this.scoringConfiguration = new ScoringConfiguration(
            "Traditional Scoring",
            300,
            -1,
            10,
            10,
            12,
            21,
            1,
            10,
            "\\t",
            2,
            Pattern.compile("[a-zA-Z]+"),
            Pattern.compile("10|[0-9|F]"),
            "F",
            -1
        );
    }

    @Override
    public GameResult validateResultData(String filename, List<String> result) {
        if (result == null || result.isEmpty()) throw new InvalidFormatException("No data");

        List<PlayerRoll> playersRollsList = result.stream().map(line -> {
            log.debug("LINE READ: " + line);

            String[] columns = line.split(this.scoringConfiguration.getColumnsSeparator());

            if (columns.length != this.scoringConfiguration.getColumnsNumber()) throw new InvalidFormatException("Invalid number of columns");

            String playerName = columns[0].trim();

            Matcher nameMatcher = this.scoringConfiguration.getNameColumnPattern().matcher(playerName);

            if (playerName.isEmpty() || !nameMatcher.find()) throw new InvalidFormatException("Invalid name of player");

            String rollScoreText = columns[1].trim();

            Matcher rollScoreMatcher = this.scoringConfiguration.getRollScoreColumnPattern().matcher(rollScoreText);

            if (!rollScoreMatcher.find()) throw new InvalidFormatException("Invalid score format (not a number or Fail Symbol)");

            boolean isFail = this.scoringConfiguration.getFailSymbol().equals(rollScoreText);

            int rollScore = isFail ? this.scoringConfiguration.getFailValue() : Integer.parseInt(rollScoreText);

            log.debug("PLAYER NAME: " + playerName);
            log.debug("ROLL SCORE: " + rollScore);

            return new PlayerRoll(playerName, rollScore, isFail);
        }).collect(Collectors.toList());

        Map<String, List<PlayerRoll>> playersRollsMap =  playersRollsList.stream()
                .collect(Collectors.groupingBy(PlayerRoll::getPlayerName, LinkedHashMap::new, Collectors.toList()));

        int numberOfPlayers = playersRollsMap.size();

        if (numberOfPlayers < this.scoringConfiguration.getMinPlayers() || numberOfPlayers > this.scoringConfiguration.getMaxPlayers())
            throw new InvalidFormatException("Invalid number of players");

        playersRollsMap.forEach((name, playerRolls) -> {
            if (playerRolls.size() < this.scoringConfiguration.getMinRollsPerPlayer()
                || playerRolls.size() > this.scoringConfiguration.getMaxRollsPerPlayer()) {
                throw new InvalidFormatException("Invalid number of rolls for player: " + name);
            }

            playerRolls.forEach(playerRoll -> {
                if (playerRoll.getRollScore() < this.scoringConfiguration.getMinRollScore() ||
                    playerRoll.getRollScore() > this.scoringConfiguration.getMaxRollScore()) {
                    throw new InvalidFormatException("Invalid roll score");
                }
            });
        });

        List<String> playersList = new ArrayList<>(playersRollsMap.keySet());

        Iterator<String> playerIterator = Utils.circularIterator(playersList);
        String expectedPlayerName = playerIterator.next();
        int playerFrameScore = 0;
        int rollCount = 0;
        Map<String, List<BasicFrame>> playerFrames = new LinkedHashMap<>();

        int i = 0;
        int increment;
        while(i < playersRollsList.size())  {
            increment = 1;
            PlayerRoll playerRoll = playersRollsList.get(i);
            String actualPlayerName = playerRoll.getPlayerName();
            int actualRollScore = playerRoll.getRollScore();

            if (!actualPlayerName.equals(expectedPlayerName))
                throw new InvalidFormatException("Invalid order of players");

            playerFrameScore += actualRollScore;
            rollCount++;

            boolean isLastFrame = playerFrames.get(actualPlayerName) != null && playerFrames.get(actualPlayerName).size() >= 9;

            if (playerFrameScore > this.scoringConfiguration.getMaxRollScore() && !isLastFrame) throw new InvalidFormatException("Invalid frame score");

            if (!isLastFrame) {
                if (rollCount == 1 && actualRollScore == this.scoringConfiguration.getMaxRollScore()) {
                    expectedPlayerName = playerIterator.next();
                    playerFrameScore = 0;
                    rollCount = 0;
                    this.updatePlayerFrames(actualPlayerName, actualRollScore, null, null, playerFrames);
                } else if (rollCount == 2) {
                    PlayerRoll prev1 = playersRollsList.get(i - 1);
                    expectedPlayerName = playerIterator.next();
                    playerFrameScore = 0;
                    rollCount = 0;
                    this.updatePlayerFrames(actualPlayerName, prev1.getRollScore(), actualRollScore, null, playerFrames);
                }
            } else {
                if (rollCount == 1 && actualRollScore == this.scoringConfiguration.getMaxRollScore()) {
                    PlayerRoll next1 = playersRollsList.get(i + 1);
                    PlayerRoll next2 = playersRollsList.get(i + 2);

                    if (!actualPlayerName.equals(next1.getPlayerName()) && !actualPlayerName.equals(next2.getPlayerName())) throw new InvalidFormatException("Invalid sequence on last frame");

                    this.updatePlayerFrames(actualPlayerName, actualRollScore, next1.getRollScore(), next2.getRollScore(), playerFrames);

                    expectedPlayerName = playerIterator.next();
                    playerFrameScore = 0;
                    rollCount = 0;
                    increment = 3;
                } else if (rollCount == 2 && playerFrameScore == this.scoringConfiguration.getMaxRollScore()) {
                    PlayerRoll next1 = playersRollsList.get(i + 1);
                    PlayerRoll prev1 = playersRollsList.get(i - 1);

                    if (!actualPlayerName.equals(prev1.getPlayerName()) && !actualPlayerName.equals(next1.getPlayerName()) ) throw new InvalidFormatException("Invalid sequence on last frame");

                    this.updatePlayerFrames(actualPlayerName,  prev1.getRollScore(), actualRollScore, null, playerFrames);

                    expectedPlayerName = playerIterator.next();
                    playerFrameScore = 0;
                    rollCount = 0;
                    increment = 2;
                } else if (rollCount == 2) {
                    PlayerRoll prev1 = playersRollsList.get(i - 1);

                    if (!actualPlayerName.equals(prev1.getPlayerName())) throw new InvalidFormatException("Invalid sequence on last frame");

                    this.updatePlayerFrames(actualPlayerName, prev1.getRollScore(), actualRollScore, null, playerFrames);

                    expectedPlayerName = playerIterator.next();
                    playerFrameScore = 0;
                    rollCount = 0;
                    increment = 1;
                }
            }

            if (playerFrames.get(actualPlayerName) != null && playerFrames.get(actualPlayerName).size() > this.scoringConfiguration.getMaxFramesPerPlayer()) throw new InvalidFormatException("Invalid number of frames");

            i += increment;
        }

        List<String> playersNames = new ArrayList<>(playerFrames.keySet());
        playerFrames.forEach((s, basicFrames) -> {
            if (basicFrames.size() != this.scoringConfiguration.getMaxFramesPerPlayer())
                throw new InvalidFormatException("Invalid number of frames");
        });

        return new GameResult(
                filename,
                playerFrames.entrySet().stream()
                        .map(entry -> new PlayerScore<BasicFrame>(
                            new Player(playersNames.indexOf(entry.getKey()) + 1, entry.getKey()),
                            entry.getValue()
                        )).collect(Collectors.toList())
        );
    }

    private void updatePlayerFrames(String playerName,
                                    Integer playerFirstRollScore,
                                    Integer playerSecondRollScore,
                                    Integer playerThirdRollScore,
                                    Map<String, List<BasicFrame>> playerFrames) {
        if (playerFrames.get(playerName) == null) {
            BasicFrame basicFrame = new BasicFrame(
                1,
                playerFirstRollScore,
                playerSecondRollScore,
                playerThirdRollScore
            );
            List<BasicFrame> basicFrames = new ArrayList<>();
            basicFrames.add(basicFrame);
            playerFrames.put(playerName, basicFrames);
        } else {
            BasicFrame basicFrame = new BasicFrame(
                playerFrames.get(playerName).size() + 1,
                playerFirstRollScore,
                playerSecondRollScore,
                playerThirdRollScore
            );
            playerFrames.get(playerName).add(basicFrame);
        }
    }

    @Override
    public GameScore processResultData(GameResult gameResult) {
        final Integer maxRollScore = this.scoringConfiguration.getMaxRollScore();
        final int lastFrameIndex = this.scoringConfiguration.getMaxFramesPerPlayer() - 1;
        final List<PlayerScore<Frame>> playersScores = new ArrayList<>();
        List<Frame> frames;
        Integer bonus;

        for (PlayerScore<BasicFrame> inputPlayerScore : gameResult.getResults()) {
            List<BasicFrame> basicFrames = inputPlayerScore.getFrames();
            frames = new ArrayList<>();

            for (int i = 0; i < basicFrames.size(); i++) {
                BasicFrame basicFrame = basicFrames.get(i);
                Integer frameScore = basicFrame.currentScore();
                bonus = 0;

                if (i < lastFrameIndex) {
                    BasicFrame next1 = basicFrames.get(i + 1);

                    if (basicFrame.getFrameType(maxRollScore).equals(FrameType.STRIKE)) {
                        bonus += next1.getFirstRollScoreValue();

                        if (next1.getFrameType(maxRollScore).equals(FrameType.STRIKE) && (i < lastFrameIndex - 1)) {
                            BasicFrame next2 = basicFrames.get(i + 2);
                            bonus += next2.getFirstRollScoreValue();
                        } else {
                            bonus += next1.getSecondRollScoreValue();
                        }
                    } else if (basicFrame.getFrameType(maxRollScore).equals(FrameType.SPARE)) {
                        bonus += next1.getFirstRollScoreValue();
                    }

                    frames.add(
                        new Frame(
                            basicFrame.getNumber(),
                            basicFrame.getFirstRollScore(),
                            basicFrame.getSecondRollScore(),
                            basicFrame.getThirdRollScore(),
                            frameScore,
                            bonus,
                            i == 0 ? frameScore + bonus : frameScore + bonus + frames.get(i - 1).getAggregateScore())
                    );
                } else {
                    Integer previousAggregateScore = frames.get(i - 1).getAggregateScore();

                    if (basicFrame.getFrameType(maxRollScore).equals(FrameType.STRIKE)) {
                        bonus = basicFrame.getSecondRollScoreValue() + basicFrame.getThirdRollScoreValue();
                    } else if (basicFrame.getFrameType(maxRollScore).equals(FrameType.SPARE)) {
                        bonus = basicFrame.getThirdRollScoreValue();
                    }

                    frames.add(
                        new Frame(
                            basicFrame.getNumber(),
                            basicFrame.getFirstRollScore(),
                            basicFrame.getSecondRollScore(),
                            basicFrame.getThirdRollScore(),
                            frameScore - bonus,
                            bonus,
                            frameScore + previousAggregateScore)
                    );
                }
            }

            playersScores.add(
                new PlayerScore<>(
                    new Player(inputPlayerScore.getPlayer().getNumber(), inputPlayerScore.getPlayer().getName()),
                    frames
                )
            );
        }

        return new GameScore(
            LocalDateTime.now(),
            gameResult.getFileName(),
            playersScores,
            this.scoringConfiguration
        );
    }

    @Override
    public ScoringConfiguration getScoringConfiguration() {
        return this.scoringConfiguration;
    }

    @Getter
    private class PlayerRoll {
        private final String playerName;
        private final int rollScore;
        private final boolean fail;

        public PlayerRoll(String playerName, int rollScore, boolean fail) {
            this.playerName = playerName;
            this.rollScore = rollScore;
            this.fail = fail;
        }
    }
}