package sprot.radar.controller;

import sprot.radar.model.Match;
import sprot.radar.model.ScoreBoardRegistry;
import sprot.radar.view.ScoreBoardView;

public class ScoreBoardController {

    private final ScoreBoardRegistry scoreBoardRegistry;
    private final ScoreBoardView scoreBoardView;

    public ScoreBoardController() {
        scoreBoardRegistry = ScoreBoardRegistry.getInstance();
        scoreBoardView = new ScoreBoardView(this);
    }

    public void start() {
        scoreBoardView.start();
    }

    public void startNewGame(String homeTeam, String awayTeam) {
        if (!homeTeam.equals("invalid") && !awayTeam.equals("invalid")) {
            Match match = new Match(homeTeam, awayTeam);
            scoreBoardRegistry.startGame(match);
            scoreBoardView.displayMessage("New game started successfully.");
        }
        else {
            scoreBoardView.displayMessage("\nThere's an error during the match adding");
        }
    }

    public void updateScore(String matchId, int homeScore, int awayScore) {
        boolean exitIdStage = scoreBoardView.checkId(matchId).equals("Exit");
        boolean scoreStageHome = scoreBoardView.checkScore(homeScore) == -1;
        boolean scoreStageAway = scoreBoardView.checkScore(awayScore) == -1;
        if (!exitIdStage && !scoreStageAway && !scoreStageHome) {
            scoreBoardRegistry.updateScore(matchId, homeScore, awayScore);
            scoreBoardView.displayMessage("Score updated successfully.");
        } else {
            scoreBoardView.displayMessage("Match not found. Score update failed.");
        }
    }

    public void finishGameInProgress(String matchId) {
        boolean success = scoreBoardRegistry.removeMatchFromTheBoard(matchId);
        if (success) {
            scoreBoardView.displayMessage("Game finished and removed from the scoreboard.");
        } else {
            scoreBoardView.displayMessage("Match not found. Game finish failed.");
        }
    }

    public void displaySummaryOfGamesInProgress() {
        scoreBoardView.displaySummary(scoreBoardRegistry.getSummaryOfGamesInProgress());
    }
}
