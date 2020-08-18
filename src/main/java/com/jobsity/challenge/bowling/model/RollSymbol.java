package com.jobsity.challenge.bowling.model;

public enum RollSymbol {
    STRIKE("X"),
    SPARE("/"),
    FAIL("F");

    private String symbol;

    RollSymbol(String symbol) {
        this.symbol = symbol;
    }

    String getSymbol() {
        return this.symbol;
    }
}