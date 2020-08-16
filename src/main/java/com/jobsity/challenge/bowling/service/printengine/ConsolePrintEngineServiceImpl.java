package com.jobsity.challenge.bowling.service.printengine;

import com.jobsity.challenge.bowling.model.GameScore;
import org.springframework.stereotype.Service;

@Service
public class ConsolePrintEngineServiceImpl implements PrintEngineService {
    @Override
    public void print(GameScore gameScore) {
        System.out.println("***** Print test *****");
    }
}