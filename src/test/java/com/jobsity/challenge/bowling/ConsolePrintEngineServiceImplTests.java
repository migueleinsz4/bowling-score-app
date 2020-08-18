package com.jobsity.challenge.bowling;

import com.jobsity.challenge.bowling.model.GameScore;
import com.jobsity.challenge.bowling.model.ScoringConfiguration;
import com.jobsity.challenge.bowling.service.printengine.ConsolePrintEngineServiceImpl;
import com.jobsity.challenge.bowling.service.printengine.PrintEngineService;
import lombok.extern.apachecommons.CommonsLog;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

@CommonsLog
public class ConsolePrintEngineServiceImplTests {
    PrintEngineService printEngineService = new ConsolePrintEngineServiceImpl();
    ScoringConfiguration scoringConfiguration = new ScoringConfiguration(
            "Traditional Scoring",
            300,
            0,
            10,
            10,
            21,
            1,
            10,
            "\\t",
            2,
            Pattern.compile("[a-zA-Z]+"),
            Pattern.compile("10|[0-9|F]"),
            "F"
    );

    /**
     * Test PerfectScore
     */
    @Test
    void printGeneralValidCase01() {

        GameScore gameScore = DataGeneratorUtils.generatePerfectScoreDataTwoPlayer("test02.txt", "Jon","Sansa",scoringConfiguration);
        this.printEngineService.print(gameScore);
    }

    /**
     * Test one player NormalScore
     */
    @Test
    void printGeneralValidCase02() {
        GameScore gameScore = DataGeneratorUtils.generateScoreDataOnePlayer("test.txt", "Jon",scoringConfiguration);
        this.printEngineService.print(gameScore);
    }

    /**
     * Test NormalScore
     */
    @Test
    void printGeneralValidCase03() {
        GameScore gameScore = DataGeneratorUtils.generateScoreDataTwoPlayers("test.txt", "Jon","Sansa",scoringConfiguration);
        this.printEngineService.print(gameScore);
    }

    /**
     * Test CeroScore
     */
    @Test
    void printGeneralValidCase04() {
        GameScore gameScore = DataGeneratorUtils.generateCeroScoreDataTwoPlayer("test01.txt", "Jon","Sansa",scoringConfiguration);
        this.printEngineService.print(gameScore);
    }

}