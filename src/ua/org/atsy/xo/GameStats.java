/*
Copyright (c) 2013 Evgeniy Dolgikh

See the file LICENSE for copying permission.
*/
package ua.org.atsy.xo;

public class GameStats {
    private int gamesFinished = 0;
    private int turn = 1;
    private int[] scores = new int[2];

    public int getGamesFinished() {
        return gamesFinished;
    }

    public int getTurn() {
        return turn;
    }

    public int[] getScores() {
        return scores;
    }

    public void increaseTurn() {
        turn++;
    }
    public void resetTurns() {
        turn = 1;
    }
    public void resetScores() {
        scores[0] = 0;
        scores[1] = 1;
    }
    public void increaseScores(int playerNum) {
        scores[playerNum]++;
    }
    public void printStats() {
        String out = "Games played: " + gamesFinished + "\n";
        out += "Draw in " + (gamesFinished - scores[0] + scores[1]) + " games.\n";
        out += "Player scores: " + scores[0] + ":" + scores[1];
        System.out.println(out);
    }
}
