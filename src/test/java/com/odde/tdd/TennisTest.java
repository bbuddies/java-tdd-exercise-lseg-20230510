package com.odde.tdd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TennisTest {
    private Tennis tennis = new Tennis("Jackson", "");

    @Test
    public void init(){
        assertEquals("Love All", tennis.score());
    }

    @Test
    public void player1Win(){
        tennis.play1Score();
        assertEquals("Fifteen Love", tennis.score());
    }

    @Test
    public void player1WinTwice(){
        tennis.play1Score();
        tennis.play1Score();
        assertEquals("Thirty Love", tennis.score());
    }

    @Test
    public void player2Win(){
        tennis.play2Score();
        assertEquals("Love Fifteen", tennis.score());
    }

    @Test
    public void player1WinThreeTimes(){
        tennis.play1Score();
        tennis.play1Score();
        tennis.play1Score();
        assertEquals("Forty Love", tennis.score());
    }

    @Test
    public void player1WinFourTimes(){
        tennis = new Tennis("Jackson", "");
        tennis.play1Score();
        tennis.play1Score();
        tennis.play1Score();
        tennis.play1Score();
        assertEquals("Jackson Win", tennis.score());
    }

    @Test
    public void player1PhilipWinFourTimes(){
        tennis = new Tennis("Philip", "");
        tennis.play1Score();
        tennis.play1Score();
        tennis.play1Score();
        tennis.play1Score();
        assertEquals("Philip Win", tennis.score());
    }

    @Test
    public void player2WinFourTimes(){
        tennis = new Tennis("Jackson", "Philip");
        tennis.play2Score();
        tennis.play2Score();
        tennis.play2Score();
        tennis.play2Score();
        assertEquals("Philip Win", tennis.score());
    }
}
