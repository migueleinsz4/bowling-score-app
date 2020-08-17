package com.jobsity.challenge.bowling.service.printengine;

import com.jobsity.challenge.bowling.model.Frame;
import com.jobsity.challenge.bowling.model.GameScore;
import com.jobsity.challenge.bowling.model.PlayerScore;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.util.Objects;


@Service
public class ConsolePrintEngineServiceImpl implements PrintEngineService {
    @Override
    public void print(GameScore gameScore) {

        System.out.println("***** Print test *****");
        this.printConsoleTabSeparated(gameScore);
    }


    public void printConsoleTabSeparated(GameScore gameScore) {

        String space="2";
        //Integer maxFrames = 10;
        Integer maxRollScore=10;

        String formatLabel ="%s\t\t";
        String formatSpaceFrame ="%-"+space+"s\t\t";
        String formatSpaceRoll ="%-"+(Integer.parseInt(space) / 2)+"s\t";


        System.out.format(formatLabel, "Frames");
        gameScore.getScores().get(0).getFrames().forEach(frame->System.out.format(formatSpaceFrame,frame.getNumber()));
        System.out.println();

        Integer thirdScore;
        for(PlayerScore<Frame> playerScore: gameScore.getScores()){

            System.out.println(playerScore.getPlayer().getName());

            System.out.format("%s\t", "Pinfalls");
            playerScore.getFrames().forEach(frame->System.out.format(formatSpaceRoll+formatSpaceRoll,
                    frame.getFirstRollScore()==maxRollScore && frame.getNumber()!=maxRollScore?"":
                            frame.getFirstRollScore()==maxRollScore && frame.getNumber()==maxRollScore?"X":
                            frame.getFirstRollScore(),
                    frame.getFirstRollScore()==maxRollScore && frame.getNumber()!=maxRollScore?"X":
                            frame.getFrameScore()==maxRollScore && frame.getFirstRollScore()!=maxRollScore?"/":
                                    frame.getSecondRollScore()));

            thirdScore= Objects.requireNonNull(CollectionUtils.lastElement(playerScore.getFrames())).getThirdRollScore();
            if(thirdScore!=null)
                System.out.format(formatSpaceRoll,thirdScore==maxRollScore?"X":thirdScore);

            System.out.println();

            System.out.format(formatLabel, "Score");
            playerScore.getFrames().forEach(frame->System.out.format(formatSpaceFrame,frame.getAggregateScore()));
            System.out.println();

        }
    }
}