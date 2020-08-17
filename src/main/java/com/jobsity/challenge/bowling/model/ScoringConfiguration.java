package com.jobsity.challenge.bowling.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScoringConfiguration {
    private final Integer maxGameScore;
    private final Integer minRollScore;
    private final Integer maxRollScore;
    private final Integer maxFramesPerPlayer;
    private final Integer maxRollsPerPlayer;
    private final Integer minPlayers;
    private final Integer maxPlayers;
    private final String columnsSeparator;
    private final Integer columnsNumber;

    public ScoringConfiguration(Integer maxGameScore, Integer minRollScore, Integer maxRollScore, Integer maxFramesPerPlayer, Integer maxRollsPerPlayer,
                                Integer minPlayers, Integer maxPlayers, String columnsSeparator, Integer columnsNumber) {
        this.maxGameScore = maxGameScore;
        this.minRollScore = minRollScore;
        this.maxRollScore = maxRollScore;
        this.maxFramesPerPlayer = maxFramesPerPlayer;
        this.maxRollsPerPlayer = maxRollsPerPlayer;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
        this.columnsSeparator = columnsSeparator;
        this.columnsNumber = columnsNumber;
    }
}