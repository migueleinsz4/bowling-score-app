package com.jobsity.challenge.bowling.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class GameScore {
    private final LocalDateTime processingDate;
    private final String fileName;
    private final List<PlayerScore<Frame>> scores;
    private final String winner;

    public List<String> winners() {
        return null;
    }

    public GameScore(LocalDateTime processingDate, String fileName, List<PlayerScore<Frame>> scores, String winner) {
        this.processingDate = processingDate;
        this.fileName = fileName;
        this.scores = scores;
        this.winner = winner;
    }
}