package com.jobsity.challenge.bowling.service.printengine;

import com.jobsity.challenge.bowling.model.Frame;
import com.jobsity.challenge.bowling.model.GameScore;
import com.jobsity.challenge.bowling.model.PlayerScore;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.util.Objects;

@CommonsLog
@Service
public class ConsolePrintEngineServiceImpl implements PrintEngineService {
    @Override
    public void print(GameScore gameScore) {
        log.debug("***** Print Game Score *****");
        this.printConsoleScoreTabSeparated(gameScore);
    }

    @Override
    public void printError(String errorMessage) {
        System.out.println();
        System.out.println("<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>");
        System.out.println("<<<<< FILE VALIDATION: Error(s) >>>>>     *******  "+errorMessage+"  *******");
        System.out.println("<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>");
        System.out.println();
    }

    public void printConsoleScoreTabSeparated(GameScore gameScore) {
        String space="2";
        Integer maxFramesPerPlayer = gameScore.getScoringConfiguration().getMaxFramesPerPlayer();
        Integer maxRollScore = gameScore.getScoringConfiguration().getMaxRollScore();

        String formatLabel ="%s\t\t";
        String formatSpaceFrame ="%-"+space+"s\t\t";
        String formatSpaceRoll ="%-"+(Integer.parseInt(space) / 2)+"s\t";

        System.out.format(formatLabel, "Frames");
        gameScore.getScores().get(0).getFrames().forEach(
                frame->System.out.format(formatSpaceFrame,frame.getNumber()));
        System.out.println();

        Frame lastFrame;
        for(PlayerScore<Frame> playerScore: gameScore.getScores()){
            System.out.println(playerScore.getPlayer().getName());
            System.out.format("%s\t", "Pinfalls");


            playerScore.getFrames().forEach(frame->
                            System.out.format(formatSpaceRoll+formatSpaceRoll,
                    frame.getFirstRollScoreSymbol(maxRollScore).equals("X") && !frame.getNumber().equals(maxFramesPerPlayer)?"":frame.getFirstRollScoreSymbol(maxRollScore),
                                    frame.getFirstRollScoreSymbol(maxRollScore).equals("X") && !frame.getNumber().equals(maxFramesPerPlayer)?frame.getFirstRollScoreSymbol(maxRollScore):frame.getSecondRollScoreSymbol(maxRollScore,maxFramesPerPlayer)));

            lastFrame= CollectionUtils.lastElement(playerScore.getFrames());

            if(lastFrame != null && lastFrame.getThirdRollScore()!=null)
                System.out.format(formatSpaceRoll, lastFrame.getThirdRollScoreSymbol(maxRollScore,maxFramesPerPlayer));

            System.out.println();

            System.out.format(formatLabel, "Score");
            playerScore.getFrames().forEach(frame->System.out.format(formatSpaceFrame,frame.getAggregateScore()));
            System.out.println();
        }
    }
}