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

    public void printConsoleError(String errorMessage) {
        System.out.println("=== File Validation: Errors ===");
        System.out.format("%100s", errorMessage);
    }

    public void printConsoleScoreTabSeparated(GameScore gameScore) {
        String space="2";
        Integer maxFramesPerPlayer = gameScore.getScoringConfiguration().getMaxFramesPerPlayer();
        Integer maxRollScore = gameScore.getScoringConfiguration().getMaxRollScore();

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
                frame.getFirstRollScore().equals(maxRollScore) && !frame.getNumber().equals(maxRollScore) ?"":
                    frame.getFirstRollScore().equals(maxRollScore) && frame.getNumber().equals(maxRollScore) ?"X":
                            frame.getFirstRollScore(),
                frame.getFirstRollScore().equals(maxRollScore) && !frame.getNumber().equals(maxFramesPerPlayer) ?"X":
                    frame.getSecondRollScore().equals(maxRollScore) && frame.getNumber().equals(maxFramesPerPlayer) ?"X":
                        frame.getFrameScore().equals(maxRollScore) && !frame.getFirstRollScore().equals(maxRollScore) ?"/":
                                            frame.getSecondRollScore()));

            thirdScore= Objects.requireNonNull(CollectionUtils.lastElement(playerScore.getFrames())).getThirdRollScore();

            if(thirdScore!=null)
                System.out.format(formatSpaceRoll, thirdScore.equals(maxRollScore) ?"X":thirdScore);

            System.out.println();

            System.out.format(formatLabel, "Score");
            playerScore.getFrames().forEach(frame->System.out.format(formatSpaceFrame,frame.getAggregateScore()));
            System.out.println();
        }
    }
}