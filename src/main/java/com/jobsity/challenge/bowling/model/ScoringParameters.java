package com.jobsity.challenge.bowling.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScoringParameters {
    private final Integer maxGameScore;
    private final Integer maxRollScore;
    private final Integer maxFrames;

    public ScoringParameters(Integer maxGameScore, Integer maxRollScore, Integer maxFrames) {
        this.maxGameScore = maxGameScore;
        this.maxRollScore = maxRollScore;
        this.maxFrames = maxFrames;
    }
}