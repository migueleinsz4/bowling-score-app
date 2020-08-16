package com.jobsity.challenge.bowling.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScoringConfiguration {
    private final Integer maxGameScore;
    private final Integer maxRollScore;
    private final Integer maxFrames;
    private final Integer minPlayers;
    private final Integer maxPlayers;
    private final String columnsSeparator;
    private final Integer columnsNumber;

    public ScoringConfiguration(Integer maxGameScore, Integer maxRollScore, Integer maxFrames, Integer minPlayers, Integer maxPlayers, String columnsSeparator, Integer columnsNumber) {
        this.maxGameScore = maxGameScore;
        this.maxRollScore = maxRollScore;
        this.maxFrames = maxFrames;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
        this.columnsSeparator = columnsSeparator;
        this.columnsNumber = columnsNumber;
    }
}