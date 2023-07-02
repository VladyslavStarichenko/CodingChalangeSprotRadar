package sprot.radar;

import sprot.radar.model.Match;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ScoreBoardRegistry {

    private static ScoreBoardRegistry scoreBoardRegistry;

    private List<Match> matches;

    private static ScoreBoardRegistry createInstance() {
        return new ScoreBoardRegistry();
    }

    public static ScoreBoardRegistry getInstance() {
        if (scoreBoardRegistry == null) {
            scoreBoardRegistry = createInstance();
        }
        return scoreBoardRegistry;
    }

    /**
     * No param constructor to init storage.
     */
    private ScoreBoardRegistry() {
        matches = new ArrayList<>();
    }

    public List<Match> getMatches() {
        return new ArrayList<>(matches);
    }

    public void setMatches(List<Match> matches) {
        this.matches = new ArrayList<>(matches);
    }

    public boolean startGame(Match match) {
        return matches.add(match);
    }

    public boolean removeMatchFromTheBoard(String id) {
        return getMatchById(id)
                .map(match -> matches.remove(match))
                .orElse(false);
    }

    private Optional<Match> getMatchById(String id) {
        return matches.stream()
                .filter(match -> match.getId().equals(id))
                .findAny();
    }

    public boolean updateScore(String matchId, int homeScore, int awayScore) {
        return getMatchById(matchId).map(match -> {
            match.setHomeScore(homeScore);
            match.setAwayScore(awayScore);
            return true;
        }).orElse(false);
    }

    public List<Match> getSummaryOfGamesInProgress() {
        return matches.stream()
                .sorted(Comparator.comparingInt(Match::getTotalScore).reversed()
                        .thenComparing(Comparator.comparing(Match::getDateStart).reversed()))
                .collect(Collectors.toList());
    }



}
