package com.jobsity.challenge.bowling.unit;

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
        GameScore gameScore = new GameScore(
            LocalDateTime.now(),
            "test.txt",
            null,
            null
        );

        this.printEngineService.print(gameScore);
    }

}