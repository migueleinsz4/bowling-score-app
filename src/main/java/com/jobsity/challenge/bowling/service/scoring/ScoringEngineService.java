package com.jobsity.challenge.bowling.service.scoring;

import com.jobsity.challenge.bowling.model.GameResult;
import com.jobsity.challenge.bowling.model.GameScore;
import com.jobsity.challenge.bowling.model.ScoringParameters;

public interface ScoringEngineService {
    GameResult validateResultData(String result);
    GameScore processResultData(GameResult gameResult);
    ScoringParameters getScoringParameters();
}