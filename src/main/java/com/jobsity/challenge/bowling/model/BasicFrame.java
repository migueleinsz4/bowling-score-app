package com.jobsity.challenge.bowling.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
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

    public Integer getFirstRollScoreValue() {
        return this.validateFailOrEmpty(this.firstRollScore);
    }

    public Integer getSecondRollScoreValue() {
        return this.validateFailOrEmpty(this.secondRollScore);
    }

    public Integer getThirdRollScoreValue() {
        return this.validateFailOrEmpty(this.thirdRollScore);
    }

    public String getFirstRollScoreSymbol(Integer maxRollScore) {
        if (this.firstRollScore == null) {
            return "";
        } else if (this.getFrameType(maxRollScore).equals(FrameType.STRIKE)) {
            return RollSymbol.STRIKE.getSymbol();
        } else if (new Integer(-1).equals(this.firstRollScore)) {
            return RollSymbol.FAIL.getSymbol();
        } else {
            return String.valueOf(this.firstRollScore);
        }
    }

    public String getSecondRollScoreSymbol(Integer maxRollScore, Integer maxFramesPerPlayer) {
        if (this.secondRollScore == null) {
            return "";
        } else if (this.getFrameType(maxRollScore).equals(FrameType.SPARE)) {
            return RollSymbol.SPARE.getSymbol();
        } else if (new Integer(-1).equals(this.secondRollScore)) {
            return RollSymbol.FAIL.getSymbol();
        } else if (this.isLastFrame(maxFramesPerPlayer) && this.secondRollScore.equals(maxRollScore)) {
            return RollSymbol.STRIKE.getSymbol();
        } else {
            return String.valueOf(this.secondRollScore);
        }
    }

    public String getThirdRollScoreSymbol(Integer maxRollScore, Integer maxFramesPerPlayer) {
        if (this.thirdRollScore == null) {
            return "";
        } else if (new Integer(-1).equals(this.thirdRollScore)) {
            return RollSymbol.FAIL.getSymbol();
        } else if (this.isLastFrame(maxFramesPerPlayer) && this.thirdRollScore.equals(maxRollScore)) {
            return RollSymbol.STRIKE.getSymbol();
        } else {
            return String.valueOf(this.thirdRollScore);
        }
    }

    private Integer validateFailOrEmpty(Integer value) {
        return (value == null || new Integer(-1).equals(value)) ? 0 : value;
    }

    public Integer currentScore() {
        return this.getFirstRollScoreValue() + this.getSecondRollScoreValue() + this.getThirdRollScoreValue();
    }

    public FrameType getFrameType(Integer maxRollScore) {
        if (maxRollScore.equals(this.getFirstRollScoreValue())) {
            return FrameType.STRIKE;
        } else if (maxRollScore.equals(this.getFirstRollScoreValue() + this.getSecondRollScoreValue())) {
            return FrameType.SPARE;
        } else {
            return FrameType.OPEN;
        }
    }

    private boolean isLastFrame(Integer maxFramesPerPlayer) {
        return maxFramesPerPlayer.equals(this.number);
    }
}