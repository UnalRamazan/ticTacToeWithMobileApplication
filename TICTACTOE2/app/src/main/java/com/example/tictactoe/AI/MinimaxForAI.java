package com.example.tictactoe.AI;

import java.util.ArrayList;

public class MinimaxForAI {

    protected int matrixGround;//oyun tahtasının boyutu
    protected int player = 1;//oyuncu
    protected int computer = 2;//bilgisayar
    protected int counter = 0;

    public MinimaxForAI(int matrixGround) {
        this.matrixGround = matrixGround;
    }

    public Move findBestMoveForAI(int[][] playGround) {
        return minimaxForAI(playGround, 2, computer, counter);
    }

    private Move minimaxForAI(int[][] playGround, int dept, int playerType, int counter) {

        ArrayList<Move> moveArrayList = generatedCurrentMoves(playGround);

        int bestScore;
        int tempScore;
        Move moveTemp = new Move();

        if (playerType == computer) {
            bestScore = Integer.MIN_VALUE;
        } else {
            bestScore = Integer.MAX_VALUE;
        }

        if (moveArrayList.isEmpty() || dept == 0) {

            moveTemp.setScore(evaluate(playGround) - counter);
        } else {

            for (Move move : moveArrayList) {
                playGround[move.getRow()][move.getColumn()] = playerType;

                if (playerType == computer) {

                    tempScore = minimaxForAI(playGround, dept - 1, player, (counter + 1)).getScore();
                    if (tempScore > bestScore) {
                        bestScore = tempScore;

                        moveTemp = move;
                        moveTemp.setScore(bestScore);
                    }
                } else {

                    tempScore = minimaxForAI(playGround, dept - 1, computer, (counter + 1)).getScore();
                    if (tempScore < bestScore) {
                        bestScore = tempScore;

                        moveTemp = move;
                        moveTemp.setScore(bestScore);
                    }
                }

                playGround[move.getRow()][move.getColumn()] = 0;
            }
        }

        return moveTemp;
    }

    //mevcut olan tüm boş hücreleri bulup arraylist' e ekledik
    private ArrayList<Move> generatedCurrentMoves(int[][] playGround) {
        ArrayList<Move> tempArrayList = new ArrayList<>();

        for (int i = 0; i < matrixGround; i++) {
            for (int j = 0; j < matrixGround; j++) {

                if (playGround[i][j] == 0) {
                    Move move = new Move(i, j, 0);
                    tempArrayList.add(move);
                }
            }
        }

        return tempArrayList;
    }

    private int evaluate(int[][] playGround) {

        int score = 0;
        score += evaluateType(playGround, 0, 0, 0, 1, 0, 2);// row 0
        score += evaluateType(playGround, 1, 0, 1, 1, 1, 2);// row 1
        score += evaluateType(playGround, 2, 0, 2, 1, 2, 2);// row 2
        score += evaluateType(playGround, 0, 0, 1, 0, 2, 0);// col 0
        score += evaluateType(playGround, 0, 1, 1, 1, 2, 1);// col 1
        score += evaluateType(playGround, 0, 2, 1, 2, 2, 2);// col 2
        score += evaluateType(playGround, 0, 0, 1, 1, 2, 2);// diagonal
        score += evaluateType(playGround, 0, 2, 1, 1, 2, 0);// alternate diagonal
        return score;
    }

    private int evaluateType(int[][] playGround, int row01, int column01, int row02, int column02, int row03, int column03) {

        int tempScore = 0;

        if (playGround[row01][column01] == computer) {
            tempScore = 1;
        } else if (playGround[row01][column01] == player) {
            tempScore = -1;
        }

        if (playGround[row02][column02] == computer) {
            if (tempScore == 1) {
                tempScore = 10;
            } else if (tempScore == -1) {
                return 0;
            } else {
                tempScore = 1;
            }
        } else if (playGround[row02][column02] == player) {
            if (tempScore == -1) {
                tempScore = -10;
            } else if (tempScore == 1) {
                return 0;
            } else {
                tempScore = -1;
            }
        }

        if (playGround[row03][column03] == computer) {
            if (tempScore > 0) {
                tempScore *= 20;
            } else if (tempScore < 0) {
                return 0;
            } else {
                tempScore = 1;
            }
        } else if (playGround[row03][column03] == player) {
            if (tempScore < 0) {
                tempScore *= 10;
            } else if (tempScore > 1) {
                return 0;
            } else {
                tempScore = -1;
            }
        }
        return tempScore;
    }
}