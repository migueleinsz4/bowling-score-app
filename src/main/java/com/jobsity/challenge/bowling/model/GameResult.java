package com.jobsity.challenge.bowling.model;

import lombok.Getter;

import java.util.List;

@Getter
public class GameResult {
    private final String fileName;
    private final List<PlayerScore<BasicFrame>> results;

    public GameResult(String fileName, List<PlayerScore<BasicFrame>> results) {
        this.fileName = fileName;
        this.results = results;
    }
}