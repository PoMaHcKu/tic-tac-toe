package com.herokuapp.crosses.model;

import lombok.Data;

@Data
public class Game {
    private final int id;
    private Field field;
    private Gamer cross;
    private Gamer zero;

    public void setCross(Gamer cross) {
        cross.setGame(this);
        cross.setMark(PState.CROSS);
        this.cross = cross;
    }

    public void setZero(Gamer zero) {
        zero.setGame(this);
        zero.setMark(PState.ZERO);
        this.zero = zero;
    }
}