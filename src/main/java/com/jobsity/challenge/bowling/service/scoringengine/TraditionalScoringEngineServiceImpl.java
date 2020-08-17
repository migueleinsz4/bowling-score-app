package com.jobsity.challenge.bowling.service.scoringengine;

import com.jobsity.challenge.bowling.exception.InvalidFormatException;
import com.jobsity.challenge.bowling.model.*;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TraditionalScoringEngineServiceImpl implements ScoringEngineService {
    private final ScoringConfiguration scoringConfiguration;

    public TraditionalScoringEngineServiceImpl() {
        this.scoringConfiguration = new ScoringConfiguration(
            300,
            0,
            10,
            10,
            21,
            1,
            10,
            "\\t",
            2
        );
    }

    @Override
    public GameResult validateResultData(List<String> result) {
        // TODO: Validar el contenido del archivo y generar datos estructurados

        if (result == null || result.isEmpty()) throw new InvalidFormatException("No data");

        List<PlayerRoll> playersRollsList = result.stream().map(line -> {
            String[] columns = line.split(this.scoringConfiguration.getColumnsSeparator());

            if (columns.length != this.scoringConfiguration.getColumnsNumber()) throw new InvalidFormatException("Invalid number of columns");

            if (columns[0].isEmpty()) throw new InvalidFormatException("Invalid name of player");

            String playerName = columns[0].trim();

            int rollScore;

            try {
                rollScore = Integer.parseInt(columns[1]);
            } catch (NumberFormatException e) {
                throw new InvalidFormatException("Invalid score format (not a number)");
            }

            return new PlayerRoll(playerName, rollScore);
        }).collect(Collectors.toList());

        Map<String, List<PlayerRoll>> playersRollsMap =  playersRollsList.stream().collect(Collectors.groupingBy(PlayerRoll::getName));

        int numberOfPlayers = playersRollsMap.size();

        if (numberOfPlayers < this.scoringConfiguration.getMinPlayers() || numberOfPlayers > this.scoringConfiguration.getMaxPlayers())
            throw new InvalidFormatException("Invalid number of players");

        if (numberOfPlayers == 1) { // One Player
            if (playersRollsList.size() != this.scoringConfiguration.getMaxRollsPerPlayer()
                    && playersRollsList.size() != this.scoringConfiguration.getMaxRollsPerPlayer() - 1) {
                throw new InvalidFormatException("Invalid number of rolls");
            }

            playersRollsList.forEach(playerRoll -> {
                if (playerRoll.getRollScore() < this.scoringConfiguration.getMinRollScore() ||
                        playerRoll.getRollScore() > this.scoringConfiguration.getMaxRollScore()) {
                    throw new InvalidFormatException("Invalid roll score");
                }
            });

            return this.createGameResult(playersRollsMap);
        } else { // Many Players

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
            ""
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