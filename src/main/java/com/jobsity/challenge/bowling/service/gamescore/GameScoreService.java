package com.jobsity.challenge.bowling.service.gamescore;

import com.jobsity.challenge.bowling.model.GameResult;
import com.jobsity.challenge.bowling.model.GameScore;

public interface GameScoreService {
    GameResult parseGameResultFile(String source);

    GameScore calculateGameScore(GameResult gameResult);

    void printGameScore(GameScore gameScore);

    default GameScore processGameResult(String source) {
        GameResult gameResult = this.parseGameResultFile(source);
        GameScore gameScore = this.calculateGameScore(gameResult);
        this.printGameScore(gameScore);
        return gameScore;
    }
}
