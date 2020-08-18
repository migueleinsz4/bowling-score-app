package com.jobsity.challenge.bowling.exception;

import com.jobsity.challenge.bowling.service.printengine.PrintEngineService;
import lombok.extern.apachecommons.CommonsLog;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
@CommonsLog
public class BowlingExceptionHandler {
    final PrintEngineService printEngineService;

    @Autowired
    public BowlingExceptionHandler(PrintEngineService printEngineService) {
        this.printEngineService = printEngineService;
    }

    @AfterThrowing(pointcut="execution(* com.jobsity.challenge.bowling.service.gamescore.*.*(..))", throwing="ex")
    public void handleError(Exception ex) {
        this.printEngineService.printError(ex.getMessage());
        log.debug("Error");
        log.debug(ex.getMessage());
    }
}