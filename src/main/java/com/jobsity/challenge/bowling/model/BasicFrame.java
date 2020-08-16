package com.jobsity.challenge.bowling.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class BasicFrame {
    private final Integer number;
    private final Integer firstRollScore;
    private final Integer secondRollScore;
    private final Integer thirdRollScore;

    public BasicFrame(Integer number, Integer firstRollScore, Integer secondRollScore, Integer thirdRollScore) {
        this.number = number;
        this.firstRollScore = firstRollScore;
        this.secondRollScore = secondRollScore;
        this.thirdRollScore = thirdRollScore;
    }

    public Integer currentScore() {
        return this.firstRollScore + this.secondRollScore + this.thirdRollScore;
    }

    public FrameType getFrameType(Integer maxRollScore) {
        if (maxRollScore.equals(firstRollScore)) {
            return FrameType.STRIKE;
        } else if (maxRollScore.equals(firstRollScore + secondRollScore)) {
            return FrameType.SPARE;
        } else {
            return FrameType.OPEN;
        }
    }
}