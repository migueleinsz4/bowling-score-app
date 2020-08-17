package com.jobsity.challenge.bowling.service.scoringengine;

import com.jobsity.challenge.bowling.exception.InvalidFormatException;
import com.jobsity.challenge.bowling.model.*;
import lombok.Getter;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    }

    @Override
    public GameResult validateResultData(List<String> result) {
        // TODO: Validar el contenido del archivo y generar datos estructurados

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

            int rollScore = this.scoringConfiguration.getFailSymbol().equals(rollScoreText) ? 0 : Integer.parseInt(rollScoreText);

            log.debug("PLAYER NAME: " + playerName);
            log.debug("ROLL SCORE: " + rollScore);

            return new PlayerRoll(playerName, rollScore);
        }).collect(Collectors.toList());

        Map<String, List<PlayerRoll>> playersRollsMap =  playersRollsList.stream().collect(Collectors.groupingBy(PlayerRoll::getName));

        int numberOfPlayers = playersRollsMap.size();

        if (numberOfPlayers < this.scoringConfiguration.getMinPlayers() || numberOfPlayers > this.scoringConfiguration.getMaxPlayers())
            throw new InvalidFormatException("Invalid number of players");

        if (numberOfPlayers == 1) { // One Player
            if (playersRollsList.size() != this.scoringConfiguration.getMaxRollsPerPlayer()
                    && playersRollsList.size() != this.scoringConfiguration.getMaxRollsPerPlayer() - 1) {
                throw new InvalidFormatException("Invalid number of rolls for player " + playersRollsList.get(0).getName());
            }

            playersRollsList.forEach(playerRoll -> {
                if (playerRoll.getRollScore() < this.scoringConfiguration.getMinRollScore() ||
                        playerRoll.getRollScore() > this.scoringConfiguration.getMaxRollScore()) {
                    throw new InvalidFormatException("Invalid roll score");
                }
            });

            return this.createGameResult(playersRollsMap);
        } else { // Many Players
            playersRollsMap.forEach((name, playerRolls) -> {
                if (playerRolls.size() != this.scoringConfiguration.getMaxRollsPerPlayer()
                    && playerRolls.size() != this.scoringConfiguration.getMaxRollsPerPlayer() - 1) {
                    throw new InvalidFormatException("Invalid number of rolls for player: " + playerRolls.get(0).getName());
                }

                playerRolls.forEach(playerRoll -> {
                    if (playerRoll.getRollScore() < this.scoringConfiguration.getMinRollScore() ||
                        playerRoll.getRollScore() > this.scoringConfiguration.getMaxRollScore()) {
                        throw new InvalidFormatException("Invalid roll score");
                    }
                });
            });


            //IntStream
             //   .range(0, playersRollsList.size())
             //   .mapToObj()

        }

        return null;
    }

    private GameResult createGameResult(Map<String, List<PlayerRoll>> playersRollsMap) {

        return null;
    }

    @Override
    public GameScore processResultData(GameResult gameResult) {
        // TODO: Calcular los Scores con las reglas tradicionales
        GameScore gameScore = new GameScore(
            LocalDateTime.now(),
            "test.txt",
            new ArrayList<PlayerScore<Frame>>(),
            "",
            null
        );

        return gameScore;
    }

    @Override
    public ScoringConfiguration getScoringConfiguration() {
        return this.scoringConfiguration;
    }

    @Getter
    private class PlayerRoll {
        private final String name;
        private final Integer rollScore;

        public PlayerRoll(String name, Integer rollScore) {
            this.name = name;
            this.rollScore = rollScore;
        }
    }
}