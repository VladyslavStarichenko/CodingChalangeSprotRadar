import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sprot.radar.controller.ScoreBoardController;
import sprot.radar.model.Match;
import sprot.radar.model.ScoreBoardRegistry;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreBoardTest {

    private ScoreBoardController scoreBoardController;
    private ScoreBoardRegistry scoreBoardRegistry;


    @BeforeEach
    void setUp() {
        scoreBoardRegistry = ScoreBoardRegistry.getInstance();
        scoreBoardController = new ScoreBoardController();
    }




    @Test
    void testStartNewGame() {
        scoreBoardController.startNewGame("HomeTeam", "AwayTeam");
        List<Match> matches = scoreBoardRegistry.getMatches();
        assertEquals(1, matches.size());
        Match match = matches.get(0);
        assertEquals("HomeTeam", match.getHomeTeam());
        assertEquals("AwayTeam", match.getAwayTeam());
        assertEquals(0, match.getHomeScore());
        assertEquals(0, match.getAwayScore());
    }

    @Test
    public void testUpdateScore_ExistingMatch() {
        scoreBoardRegistry = ScoreBoardRegistry.getInstance();
        Match match = new Match("Team A", "Team B");
        scoreBoardRegistry.startGame(match);
        scoreBoardRegistry.updateScore(match.getId(), 2, 1);
        List<Match> matches = scoreBoardRegistry.getMatches();
        Match updatedMatch = matches.stream()
                .filter(m -> m.getId().equals(match.getId()))
                .findFirst()
                .orElse(null);
        assertNotNull(updatedMatch);
        assertEquals(2, updatedMatch.getHomeScore());
        assertEquals(1, updatedMatch.getAwayScore());
    }

    @Test
    public void testUpdateScore_NonExistingMatch() {
        scoreBoardRegistry = ScoreBoardRegistry.getInstance();
        Match match = new Match("Team A", "Team B");
        scoreBoardRegistry.updateScore(match.getId(), 2, 1);
        List<Match> matches = scoreBoardRegistry.getMatches();
        Match updatedMatch = matches.stream()
                .filter(m -> m.getId().equals(match.getId()))
                .findFirst()
                .orElse(null);
        assertNull(updatedMatch);
    }

    @Test
    void testFinishGameInProgress() {
        scoreBoardRegistry.removeAllMatches();
        Match match = new Match("HomeTeam", "AwayTeam");
        scoreBoardRegistry.startGame(match);
        String matchId = match.getId();
        scoreBoardController.finishGameInProgress(matchId);
        List<Match> matches = scoreBoardRegistry.getMatches();
        assertTrue(matches.isEmpty());
    }

    @Test
    void testGetSummaryOfGamesInProgress() {
        scoreBoardRegistry.removeAllMatches();
        Match match1 = new Match("HomeTeam1", "AwayTeam1");
        Match match2 = new Match("HomeTeam2", "AwayTeam2");
        Match match3 = new Match("HomeTeam3", "AwayTeam3");

        match1.setHomeScore(2);
        match1.setAwayScore(1);
        match2.setHomeScore(1);
        match2.setAwayScore(1);
        match3.setHomeScore(0);
        match3.setAwayScore(2);

        scoreBoardRegistry.startGame(match1);
        scoreBoardRegistry.startGame(match2);
        scoreBoardRegistry.startGame(match3);

        List<Match> summary = scoreBoardRegistry.getSummaryOfGamesInProgress();
        assertEquals(3, summary.size());
        assertEquals(match1, summary.get(0));
        assertEquals(match2, summary.get(1));
        assertEquals(match3, summary.get(2));
    }
}
