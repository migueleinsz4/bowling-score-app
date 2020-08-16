package com.jobsity.challenge.bowling.service.scoringengine;

import com.jobsity.challenge.bowling.model.GameResult;
import com.jobsity.challenge.bowling.model.GameScore;
import com.jobsity.challenge.bowling.model.ScoringParameters;

import java.util.List;

public interface ScoringEngineService {
    GameResult validateResultData(List<String> result);
    GameScore processResultData(GameResult gameResult);
    ScoringParameters getScoringParameters();
}