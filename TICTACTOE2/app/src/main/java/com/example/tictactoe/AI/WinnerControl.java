package com.example.tictactoe.AI;

import java.util.ArrayList;

public class WinnerControl {

    protected int matrixGround;
    private ArrayList<Move> moveArrayList = new ArrayList<>();

    public WinnerControl(int matrixGround) {
        this.matrixGround = matrixGround;
    }

    //the play ground is checking by system for a winner
    public boolean winnerControl(int row, int column, int playerType, int[][] playGround) {
        int winCondition = matrixGround;
        boolean result;

        //dikey kontrol
        int winVertical = 0;
        for (int i = 0; i < matrixGround; i++) {
            if (playGround[i][column] == playerType) {
                Move move = new Move(i, column, 0);
                moveArrayList.add(move);
                winVertical++;
            }
        }

        if (winVertical == winCondition) {
            return true;
        }
        moveArrayList.clear();

        //yatay kontrol
        int winHorizontal = 0;
        for (int i = 0; i < matrixGround; i++) {
            if (playGround[row][i] == playerType) {
                Move move = new Move(row, i, 0);
                moveArrayList.add(move);
                winHorizontal++;
            }
        }

        if (winHorizontal == winCondition) {
            return true;
        }
        moveArrayList.clear();

        //çapraz sol kontrol
        int crossLeft = 0;
        int horizontalLeft = 0;
        for (int i = 0; i < matrixGround; i++) {
            if (playGround[i][horizontalLeft] == playerType) {
                Move move = new Move(i, horizontalLeft, 0);
                moveArrayList.add(move);
                crossLeft++;
            }
            horizontalLeft++;
        }

        if (crossLeft == winCondition) {
            return true;
        }
        moveArrayList.clear();

        //çapraz sağ kontrol
        int crossRight = 0;
        int horizonRight = matrixGround - 1;
        for (int i = 0; i < matrixGround; i++) {
            if (playGround[i][horizonRight] == playerType) {
                Move move = new Move(i, horizonRight, 0);
                moveArrayList.add(move);
                crossRight++;
            }
            horizonRight--;
        }

        if (crossRight == winCondition) {
            return true;
        }
        moveArrayList.clear();

        return false;
    }

    public ArrayList<Move> getMoveArrayList() {
        return moveArrayList;
    }

    public void setMoveArrayList(ArrayList<Move> moveArrayList){
        this.moveArrayList = moveArrayList;
    }
}