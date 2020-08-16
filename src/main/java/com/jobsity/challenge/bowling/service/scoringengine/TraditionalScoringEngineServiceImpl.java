package com.jobsity.challenge.bowling.service.scoringengine;

import com.jobsity.challenge.bowling.model.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TraditionalScoringEngineServiceImpl implements ScoringEngineService {
    private final ScoringParameters scoringParameters;

    public TraditionalScoringEngineServiceImpl() {
        this.scoringParameters = new ScoringParameters(
            300,
            10,
            10
        );
    }

    @Override
    public GameResult validateResultData(List<String> result) {
        // TODO: Validar el contenido del archivo y generar datos estructurados
        return null;
    }

    @Override
    public GameScore processResultData(GameResult gameResult) {
        // TODO: Calcular los Scores con las reglas tradicionales
        GameScore gameScore = new GameScore(
            LocalDateTime.now(),
            "test.txt",
            new ArrayList<PlayerScore<Frame>>(),
            ""
        );

        return gameScore;
    }

    @Override
    public ScoringParameters getScoringParameters() {
        return this.scoringParameters;
    }
}