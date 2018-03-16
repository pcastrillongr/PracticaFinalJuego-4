package com.mygdx.game.Database;

import java.sql.Timestamp;

/**
 * Created by admincenec on 05/03/2018.
 */

public class PuntuacionJuego {

    private int score;
    private Timestamp gameStartDate;
    private Timestamp gameEndDate;

    public PuntuacionJuego(Timestamp startDate, int score, Timestamp endDate) {
        this.score = score;
        this.gameStartDate = startDate;
        this.gameEndDate = endDate;
    }

    public int getScore() {
        return score;
    }

    public Timestamp getGameStartDate() {
        return gameStartDate;
    }

    public Timestamp getGameEndDate() {
        return gameEndDate;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setGameEndDate(Timestamp endDate) {
        this.gameEndDate = endDate;
    }

    @Override
    public String toString() {
        return "RECORD TORTILLERO\n" +
                "   "+score;

    }
}