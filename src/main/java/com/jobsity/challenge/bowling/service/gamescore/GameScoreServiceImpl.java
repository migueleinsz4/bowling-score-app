package com.jobsity.challenge.bowling.service.gamescore;

import com.jobsity.challenge.bowling.model.GameResult;
import com.jobsity.challenge.bowling.model.GameScore;
import com.jobsity.challenge.bowling.service.printengine.PrintEngineService;
import com.jobsity.challenge.bowling.service.scoringengine.ScoringEngineService;
import com.jobsity.challenge.bowling.service.sourcereader.SourceReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameScoreServiceImpl implements GameScoreService {
    private final SourceReaderService sourceReaderService;
    private final ScoringEngineService scoringEngineService;
    private final PrintEngineService printEngineService;

    @Autowired
    public GameScoreServiceImpl(SourceReaderService sourceReaderService,
                                ScoringEngineService scoringEngineService,
                                PrintEngineService printEngineService) {
        this.sourceReaderService = sourceReaderService;
        this.scoringEngineService = scoringEngineService;
        this.printEngineService = printEngineService;
    }

    @Override
    public GameResult parseGameResultFile(String source) {
        List<String> fileContent = this.sourceReaderService.readSource(source);
        return this.scoringEngineService.validateResultData(source, fileContent);
    }

    @Override
    public GameScore calculateGameScore(GameResult gameResult) {
        return this.scoringEngineService.processResultData(gameResult);
    }

    @Override
    public void printGameScore(GameScore gameScore) {
        this.printEngineService.print(gameScore);
    }
}
