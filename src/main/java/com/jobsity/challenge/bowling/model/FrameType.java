package com.jobsity.challenge.bowling.model;

public enum FrameType {
    STRIKE("X"),
    SPARE("/"),
    OPEN("-");

    private String symbol;

    FrameType(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return this.symbol;
    }
}
