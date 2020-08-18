package com.jobsity.challenge.bowling.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public class Frame extends BasicFrame {
    private final Integer frameScore;
    private final Integer bonus;
    private final Integer aggregateScore;

    public Frame(Integer number,
                 Integer firstRollScore,
                 Integer secondRollScore,
                 Integer thirdRollScore,
                 Integer frameScore,
                 Integer bonus,
                 Integer aggregateScore) {
        super(number, firstRollScore, secondRollScore, thirdRollScore);
        this.frameScore = frameScore;
        this.bonus = bonus;
        this.aggregateScore = aggregateScore;
    }

    @Override
    public Integer currentScore() {
        return this.aggregateScore;
    }
}