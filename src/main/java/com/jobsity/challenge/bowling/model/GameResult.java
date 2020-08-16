package com.jobsity.challenge.bowling.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GameResult {
    private final String fileName;
    private final List<PlayerScore<BasicFrame>> results;

    public GameResult(String fileName, List<PlayerScore<BasicFrame>> results) {
        this.fileName = fileName;
        this.results = results;
    }
}