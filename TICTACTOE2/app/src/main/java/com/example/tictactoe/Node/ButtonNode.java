package com.example.tictactoe.Node;

import android.widget.Button;

public class ButtonNode {

    private Button button;
    private String buttonText;
    private boolean isActive;
    private String player;
    private int orderOfMoves;

    public ButtonNode(Button button) {
        this.button = button;
        buttonText = "";
        isActive = true;
        player = "";
        orderOfMoves = -1;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public String getButtonText() {
        return buttonText;
    }

    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public int getOrderOfMoves() {
        return orderOfMoves;
    }

    public void setOrderOfMoves(int orderOfMoves) {
        this.orderOfMoves = orderOfMoves;
    }
}