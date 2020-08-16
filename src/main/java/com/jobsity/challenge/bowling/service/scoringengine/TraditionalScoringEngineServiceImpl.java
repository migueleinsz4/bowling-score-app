package com.jobsity.challenge.bowling.service.scoringengine;

import com.jobsity.challenge.bowling.model.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class TraditionalScoringEngineServiceImpl implements ScoringEngineService {
    private final ScoringConfiguration scoringConfiguration;

    public TraditionalScoringEngineServiceImpl() {
        this.scoringConfiguration = new ScoringConfiguration(
            300,
            10,
            10,
            1,
            10,
            "\t",
            2
        );
    }

    @Override
    public GameResult validateResultData(List<String> result) {
        result.stream().map(line -> {
            Scanner scanner = new Scanner(line).useDelimiter(this.scoringConfiguration.getColumnsSeparator());
            return null;
        });
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
    public ScoringConfiguration getScoringConfiguration() {
        return this.scoringConfiguration;
    }
}