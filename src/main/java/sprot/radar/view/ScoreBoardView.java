package sprot.radar.view;

import sprot.radar.controller.ScoreBoardController;
import sprot.radar.model.Match;
import sprot.radar.util.AppUtil;

import java.util.List;
import java.util.Scanner;

public class ScoreBoardView {

    private final ScoreBoardController scoreBoardController;
    private final Scanner scanner;

    private final AppUtil appUtil = new AppUtil();

    public ScoreBoardView(ScoreBoardController scoreBoardController) {
        this.scoreBoardController = scoreBoardController;
        scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = readIntegerInput();
            switch (choice) {
                case 1 -> startNewGame();
                case 2 -> updateScore();
                case 3 -> finishGameInProgress();
                case 4 -> displaySummaryOfGamesInProgress();
                case 5 -> running = false;
                default -> displayMessage("Invalid choice. Please try again.");
            }
            System.out.println();
        }
    }

    private void displayMenu() {
        System.out.println("----- Scoreboard Menu -----");
        System.out.println("1. Start a new game");
        System.out.println("2. Update score");
        System.out.println("3. Finish game in progress");
        System.out.println("4. Display summary of games in progress");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private int readIntegerInput() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private String readStringInput() {
        return scanner.nextLine();
    }

    private void startNewGame() {
        System.out.print("Enter the home team: ");
        String homeTeam = readStringInput();
        homeTeam = checkTeamName(homeTeam);
        System.out.print("Enter the away team: ");
        String awayTeam = readStringInput();
        checkTeamName(awayTeam);
        scoreBoardController.startNewGame(homeTeam, awayTeam);
    }

    private String checkTeamName(String teamName) {
        if(!appUtil.isValidInput(teamName)){
            System.out.println("The entered team name is not valid.");
            return "invalid";
        }
        return teamName;
    }

    private void updateScore() {
        System.out.print("Enter the match ID: ");
        String matchId = readStringInput();
        matchId = checkId(matchId);
        System.out.print("Enter the home team score: ");
        int homeScore = readIntegerInput();
        homeScore = checkScore(homeScore);
        System.out.print("Enter the away team score: ");
        int awayScore = readIntegerInput();
        awayScore = checkScore(awayScore);
        scoreBoardController.updateScore(matchId, homeScore, awayScore);
    }

    public int checkScore(int homeScore) {
        if(appUtil.isValidScore(homeScore)){
            System.out.println("The entered team score is not valid.");
            return -1;
        }
       return homeScore;
    }

    public String checkId(String matchId) {
        if(!appUtil.isValidId(matchId)){
            System.out.println("The entered id is not valid.");
            return "invalid";
        }
        return matchId;
    }

    private void finishGameInProgress() {
        System.out.print("Enter the match ID: ");
        String matchId = readStringInput();
        matchId = checkId(matchId);
        scoreBoardController.finishGameInProgress(matchId);
    }

    private void displaySummaryOfGamesInProgress() {
        scoreBoardController.displaySummaryOfGamesInProgress();
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displaySummary(List<Match> matches) {
        System.out.println("----- Summary of Games in Progress -----");
        System.out.println("Match ID | Home Team | Home Score | Away Team | Away Score");
        System.out.println("-----------------------------------------");

        for (Match match : matches) {
            System.out.printf("%-6s | %-10s | %-2s | %-10s | %-2s%n",
                    match.getId(), match.getHomeTeam(), match.getHomeScore(),
                    match.getAwayTeam(), match.getAwayScore());
        }
    }

}
