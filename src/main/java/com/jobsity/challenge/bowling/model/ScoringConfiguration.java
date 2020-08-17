package com.jobsity.challenge.bowling.model;

import lombok.Getter;

import java.util.regex.Pattern;

@Getter
public class ScoringConfiguration {
    private final String engineName;
    private final Integer maxGameScore;
    private final Integer minRollScore;
    private final Integer maxRollScore;
    private final Integer maxFramesPerPlayer;
    private final Integer maxRollsPerPlayer;
    private final Integer minPlayers;
    private final Integer maxPlayers;
    private final String columnsSeparator;
    private final Integer columnsNumber;
    private final Pattern nameColumnPattern;
    private final Pattern rollScoreColumnPattern;
    private final String failSymbol;

    public ScoringConfiguration(String engineName, Integer maxGameScore, Integer minRollScore, Integer maxRollScore, Integer maxFramesPerPlayer,
                                Integer maxRollsPerPlayer, Integer minPlayers, Integer maxPlayers, String columnsSeparator,
                                Integer columnsNumber, Pattern nameColumnPattern, Pattern rollScoreColumnPattern, String failSymbol) {
        this.engineName = engineName;
        this.maxGameScore = maxGameScore;
        this.minRollScore = minRollScore;
        this.maxRollScore = maxRollScore;
        this.maxFramesPerPlayer = maxFramesPerPlayer;
        this.maxRollsPerPlayer = maxRollsPerPlayer;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
        this.columnsSeparator = columnsSeparator;
        this.columnsNumber = columnsNumber;
        this.nameColumnPattern = nameColumnPattern;
        this.rollScoreColumnPattern = rollScoreColumnPattern;
        this.failSymbol = failSymbol;
    }
}