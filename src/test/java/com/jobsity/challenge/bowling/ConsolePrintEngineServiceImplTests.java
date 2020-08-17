package com.jobsity.challenge.bowling;

import com.jobsity.challenge.bowling.model.GameScore;
import com.jobsity.challenge.bowling.service.printengine.ConsolePrintEngineServiceImpl;
import com.jobsity.challenge.bowling.service.printengine.PrintEngineService;
import lombok.extern.apachecommons.CommonsLog;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

@CommonsLog
public class ConsolePrintEngineServiceImplTests {
    PrintEngineService printEngineService = new ConsolePrintEngineServiceImpl();

    @Test
    void printGeneralValidCase01() {
        GameScore gameScore = DataGeneratorUtils.generatePerfectScoreDataOnePlayer("test.txt", "Jon");
        this.printEngineService.print(gameScore);
    }

    @Test
    void printGeneralValidCase02() {
        GameScore gameScore = DataGeneratorUtils.generateScoreDataOnePlayer("test.txt", "Jon");
        this.printEngineService.print(gameScore);
    }

    @Test
    void printGeneralValidCase03() {
        GameScore gameScore = DataGeneratorUtils.generateScoreDataTwoPlayers("test.txt", "Jon","Sansa");
        this.printEngineService.print(gameScore);
    }

}