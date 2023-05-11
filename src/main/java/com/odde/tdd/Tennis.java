package com.odde.tdd;

import java.util.HashMap;
import java.util.Map;

public class Tennis {
    private final String play1name;
    private final String play2name;
    private int play1Scores;
    private int play2Scores;

    String[] scoreMap = new String[]{"Love", "Fifteen", "Thirty", "Forty"};

    public Tennis(String name1, String name2) {
        this.play1name = name1;
        this.play2name = name2;
    }

    public String score() {
        if (isWin(play1Scores)) {
            return play1name + " Win";
        }
        if (isWin(play2Scores)) {
            return play2name + " Win";
        }
        if (play1Scores != play2Scores) {
            return scoreMap[play1Scores] + " " + scoreMap[play2Scores];
        }
        return scoreMap[play1Scores] + " All";
    }

    public void play1Score() {
        play1Scores += 1;
    }

    public void play2Score() {
        play2Scores +=1;
    }

    private boolean isWin(int score){
        return score == 4;
    }
}
