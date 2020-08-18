package com.jobsity.challenge.bowling.unit;

import com.jobsity.challenge.bowling.model.GameScore;
import com.jobsity.challenge.bowling.model.ScoringConfiguration;
import com.jobsity.challenge.bowling.service.printengine.ConsolePrintEngineServiceImpl;
import com.jobsity.challenge.bowling.service.printengine.PrintEngineService;
import com.jobsity.challenge.bowling.service.scoringengine.TraditionalScoringEngineServiceImpl;
import com.jobsity.challenge.bowling.util.TestsUtils;
import lombok.extern.apachecommons.CommonsLog;
import org.junit.jupiter.api.Test;

@CommonsLog
public class ConsolePrintEngineServiceImplTests {
    PrintEngineService printEngineService = new ConsolePrintEngineServiceImpl();
    ScoringConfiguration scoringConfiguration = new TraditionalScoringEngineServiceImpl().getScoringConfiguration();

    /**
     * Test PerfectScore
     */
    @Test
    void printGeneralValidCase01() {
        GameScore gameScore = TestsUtils.generatePerfectScoreDataTwoPlayer("test02.txt", "Jon","Sansa",scoringConfiguration);
        this.printEngineService.print(gameScore);
    }

    /**
     * Test one player NormalScore
     */
    @Test
    void printGeneralValidCase02() {
        GameScore gameScore = TestsUtils.generateScoreDataOnePlayer("test.txt", "Jon",scoringConfiguration);
        this.printEngineService.print(gameScore);
    }

    /**
     * Test NormalScore
     */
    @Test
    void printGeneralValidCase03() {
        GameScore gameScore = TestsUtils.generateScoreDataTwoPlayers("test.txt", "Jon","Sansa",scoringConfiguration);
        this.printEngineService.print(gameScore);
    }

    /**
     * Test ZeroScore
     */
    @Test
    void printGeneralValidCase04() {
        GameScore gameScore = TestsUtils.generateZeroScoreDataTwoPlayer("test01.txt", "Jon","Sansa",scoringConfiguration);
        this.printEngineService.print(gameScore);
    }
}