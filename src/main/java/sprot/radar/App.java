package sprot.radar;

import sprot.radar.controller.ScoreBoardController;

public class App {
    public static void main(String[] args) {
        ScoreBoardController scoreBoardController = new ScoreBoardController();
        scoreBoardController.start();
    }
}
