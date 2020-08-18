package com.jobsity.challenge.bowling.model;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class GameScore {
    private final LocalDateTime processingDate;
    private final String fileName;
    private final List<PlayerScore<Frame>> scores;
    private final ScoringConfiguration scoringConfiguration;

    public GameScore(LocalDateTime processingDate, String fileName, List<PlayerScore<Frame>> scores, ScoringConfiguration scoringConfiguration) {
        this.processingDate = processingDate;
        this.fileName = fileName;
        this.scores = scores;
        this.scoringConfiguration = scoringConfiguration;
    }
}