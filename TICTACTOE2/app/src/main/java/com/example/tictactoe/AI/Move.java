package com.example.tictactoe.AI;

public class Move {

    private int row;
    private int column;
    private int score;

    public Move() {
        row = -1;
        column = -1;
        score = 0;
    }

    public Move(int row, int column, int score) {
        this.row = row;
        this.column = column;
        this.score = score;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}