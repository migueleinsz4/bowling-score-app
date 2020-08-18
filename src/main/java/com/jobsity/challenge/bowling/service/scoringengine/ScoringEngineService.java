package com.jobsity.challenge.bowling.service.scoringengine;

import com.jobsity.challenge.bowling.model.GameResult;
import com.jobsity.challenge.bowling.model.GameScore;
import com.jobsity.challenge.bowling.model.ScoringConfiguration;

import java.util.List;

public interface ScoringEngineService {
    GameResult validateResultData(String filename, List<String> result);
    GameScore processResultData(GameResult gameResult);
    ScoringConfiguration getScoringConfiguration();
}