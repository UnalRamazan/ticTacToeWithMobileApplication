package com.example.tictactoe.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.tictactoe.AI.MinimaxForAI;
import com.example.tictactoe.AI.Move;
import com.example.tictactoe.AI.WinnerControl;
import com.example.tictactoe.Node.ButtonNode;
import com.example.tictactoe.R;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button btn_1;
    private Button btn_2;
    private Button btn_3;
    private Button btn_4;
    private Button btn_5;
    private Button btn_6;
    private Button btn_7;
    private Button btn_8;
    private Button btn_9;
    private TextView x_text_view;
    private TextView o_text_view;
    private TextView you_text_view;
    private TextView ai_text_view;

    private int[][] playGround;//player board
    private int matrixGround = 3;//size of player board
    private String buttonTextForPlayer;//when the player1 click button, the text on the button that will appear
    private String buttonTextForAI;//when the player2 or AI click button, the text on the button that will appear
    private int orderOfMoves = 0;//order of moves
    private ArrayList<ButtonNode> buttonNodeArrayList = new ArrayList<>();//to keep buttons' information

    Handler handler = new Handler();
    private Dialog resultDialog;
    WinnerControl winnerControl;
    MinimaxForAI minimaxForAI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        checkLanguage();

        setContentView(R.layout.activity_main);

        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        btn_3 = findViewById(R.id.btn_3);
        btn_4 = findViewById(R.id.btn_4);
        btn_5 = findViewById(R.id.btn_5);
        btn_6 = findViewById(R.id.btn_6);
        btn_7 = findViewById(R.id.btn_7);
        btn_8 = findViewById(R.id.btn_8);
        btn_9 = findViewById(R.id.btn_9);
        x_text_view = findViewById(R.id.x_text_view);
        o_text_view = findViewById(R.id.o_text_view);
        you_text_view = findViewById(R.id.you_text_view);
        ai_text_view = findViewById(R.id.ai_text_view);
        ImageButton restarting_game_image_button = findViewById(R.id.restarting_game_image_button);
        ImageButton settings_image_button = findViewById(R.id.settings_image_button);

        buttonTextForPlayer = getResources().getString(R.string.x);
        buttonTextForAI = getResources().getString(R.string.o);
        minimaxForAI = new MinimaxForAI(matrixGround);
        winnerControl = new WinnerControl(matrixGround);
        playGround = new int[matrixGround][matrixGround];//create player ground
        createPlayerGround();//throwing 0 on the play board
        setButtonsToArrayList();//creating an arraylist structure

        x_text_view.setTextColor(Color.parseColor("#01579b"));
        you_text_view.setTextColor(Color.parseColor("#01579b"));
        o_text_view.setTextColor(Color.parseColor("#F4F3F3"));
        ai_text_view.setTextColor(Color.parseColor("#F4F3F3"));

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player(0, 0);
            }
        });

        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player(0, 1);
            }
        });

        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player(0, 2);
            }
        });

        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player(1, 0);
            }
        });

        btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player(1, 1);
            }
        });

        btn_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player(1, 2);
            }
        });

        btn_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player(2, 0);
            }
        });

        btn_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player(2, 1);
            }
        });

        btn_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player(2, 2);
            }
        });

        restarting_game_image_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                restart();
            }
        });

        settings_image_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void restart() {
        createPlayerGround();
        orderOfMoves = 0;
        setButtonsToArrayList();

        x_text_view.setTextColor(Color.parseColor("#01579b"));
        you_text_view.setTextColor(Color.parseColor("#01579b"));
        o_text_view.setTextColor(Color.parseColor("#F4F3F3"));
        ai_text_view.setTextColor(Color.parseColor("#F4F3F3"));
    }

    //throwing 0 on the play board
    private void createPlayerGround() {
        for (int i = 0; i < matrixGround; i++) {
            for (int j = 0; j < matrixGround; j++) {
                playGround[i][j] = 0;
            }
        }
    }

    //creating an arraylist structure
    private void setButtonsToArrayList() {

        if (!buttonNodeArrayList.isEmpty()) {
            clearButtonFeatures();
            buttonNodeArrayList.clear();
        }

        ButtonNode btnNode1 = new ButtonNode(btn_1);
        buttonNodeArrayList.add(btnNode1);
        ButtonNode btnNode2 = new ButtonNode(btn_2);
        buttonNodeArrayList.add(btnNode2);
        ButtonNode btnNode3 = new ButtonNode(btn_3);
        buttonNodeArrayList.add(btnNode3);
        ButtonNode btnNode4 = new ButtonNode(btn_4);
        buttonNodeArrayList.add(btnNode4);
        ButtonNode btnNode5 = new ButtonNode(btn_5);
        buttonNodeArrayList.add(btnNode5);
        ButtonNode btnNode6 = new ButtonNode(btn_6);
        buttonNodeArrayList.add(btnNode6);
        ButtonNode btnNode7 = new ButtonNode(btn_7);
        buttonNodeArrayList.add(btnNode7);
        ButtonNode btnNode8 = new ButtonNode(btn_8);
        buttonNodeArrayList.add(btnNode8);
        ButtonNode btnNode9 = new ButtonNode(btn_9);
        buttonNodeArrayList.add(btnNode9);
    }

    private void clearButtonFeatures() {

        for (ButtonNode node : buttonNodeArrayList) {

            node.getButton().setBackgroundColor(Color.parseColor("#4f83cc"));
            node.getButton().setText("");
            node.getButton().setEnabled(true);
        }
    }

    private void showWinner(int playerType) {

        ArrayList<Move> moveArrayList = winnerControl.getMoveArrayList();

        Button btn1 = getButton(moveArrayList.get(0).getRow(), moveArrayList.get(0).getColumn());
        Button btn2 = getButton(moveArrayList.get(1).getRow(), moveArrayList.get(1).getColumn());
        Button btn3 = getButton(moveArrayList.get(2).getRow(), moveArrayList.get(2).getColumn());

        if (playerType == 1) {
            btn1.setBackgroundColor(Color.parseColor("#7cb342"));
            btn2.setBackgroundColor(Color.parseColor("#7cb342"));
            btn3.setBackgroundColor(Color.parseColor("#7cb342"));
        } else {
            btn1.setBackgroundColor(Color.parseColor("#e53935"));
            btn2.setBackgroundColor(Color.parseColor("#e53935"));
            btn3.setBackgroundColor(Color.parseColor("#e53935"));
        }
    }

    private void player(int locationRow, int locationColumn) {

        lockThePlayGround();//lock the play ground
        x_text_view.setTextColor(Color.parseColor("#F4F3F3"));
        you_text_view.setTextColor(Color.parseColor("#F4F3F3"));
        o_text_view.setTextColor(Color.parseColor("#01579b"));
        ai_text_view.setTextColor(Color.parseColor("#01579b"));

        updateButton(locationRow, locationColumn, buttonTextForPlayer, "Player");

        playGround[locationRow][locationColumn] = 1;

        winnerControlCenterForPlayer(locationRow, locationColumn);
    }

    private void winnerControlCenterForPlayer(int row, int column) {
        if (winnerControl.winnerControl(row, column, 1, playGround)) {
            showWinner(1);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    finishTheGame(getResources().getString(R.string.winner), getResources().getString(R.string.x));
                }
            }, 1000);
        } else {

            if (orderOfMoves < 9) {
                selectAIType();
            } else {
                finishTheGame(getResources().getString(R.string.draw), getResources().getString(R.string.xo));
            }
        }
    }

    private void selectAIType() {

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (getAIStatusFromLocalDataSource()) {
                    AI();
                } else {
                    randomMove();
                }
            }
        }, 500);
    }

    private void AI() {
        Move move = minimaxForAI.findBestMoveForAI(playGround);
        int row = move.getRow();
        int column = move.getColumn();

        updateButton(row, column, buttonTextForAI, "AI");

        playGround[row][column] = 2;

        winnerControlCenterForAI(row, column);
    }

    private void randomMove() {

        Random random = new Random();
        int randomRow, randomColumn;

        boolean control;
        do {
            randomRow = random.nextInt(matrixGround);
            randomColumn = random.nextInt(matrixGround);

            control = controlBoard(randomRow, randomColumn);
        } while (!control);

        updateButton(randomRow, randomColumn, buttonTextForAI, "RandomSystem");

        playGround[randomRow][randomColumn] = 2;

        winnerControlCenterForAI(randomRow, randomColumn);
    }

    private boolean controlBoard(int row, int column) {
        return playGround[row][column] == 0;
    }

    private void winnerControlCenterForAI(int row, int column) {
        if (winnerControl.winnerControl(row, column, 2, playGround)) {
            showWinner(2);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    finishTheGame(getResources().getString(R.string.winner), getResources().getString(R.string.o));
                }
            }, 1000);
        } else {

            if (orderOfMoves < 9) {
                unLockThePlayGround();// unlock the play ground
                x_text_view.setTextColor(Color.parseColor("#01579b"));
                you_text_view.setTextColor(Color.parseColor("#01579b"));
                o_text_view.setTextColor(Color.parseColor("#F4F3F3"));
                ai_text_view.setTextColor(Color.parseColor("#F4F3F3"));
            } else {
                finishTheGame(getResources().getString(R.string.draw), getResources().getString(R.string.xo));
            }
        }
    }

    private void updateButton(int row, int column, String textType, String playerType) {
        Button button = getButton(row, column);
        for (ButtonNode node : buttonNodeArrayList) {

            if (node.getButton() == button) {
                orderOfMoves++;
                node.setButtonText(textType);
                node.setActive(false);
                node.setPlayer(playerType);
                node.setOrderOfMoves(orderOfMoves);
                break;
            }
        }

        button.setBackgroundColor(Color.parseColor("#01579b"));
        button.setText(textType);
    }

    private Button getButton(int row, int column) {

        if (row == 0 && column == 0) {
            return btn_1;
        } else if (row == 0 && column == 1) {
            return btn_2;
        } else if (row == 0 && column == 2) {
            return btn_3;
        } else if (row == 1 && column == 0) {
            return btn_4;
        } else if (row == 1 && column == 1) {
            return btn_5;
        } else if (row == 1 && column == 2) {
            return btn_6;
        } else if (row == 2 && column == 0) {
            return btn_7;
        } else if (row == 2 && column == 1) {
            return btn_8;
        } else {
            return btn_9;
        }
    }

    //turn off button click
    private void lockThePlayGround() {

        for (ButtonNode node : buttonNodeArrayList) {

            if (node.isActive()) {
                node.getButton().setEnabled(false);
            }
        }
    }

    //turn on button click
    private void unLockThePlayGround() {

        for (ButtonNode node : buttonNodeArrayList) {

            if (node.isActive()) {
                node.getButton().setEnabled(true);
            }
        }
    }

    private void finishTheGame(String result, String resultType) {
        restart();
        resultPopup(result, resultType);
    }

    private void resultPopup(String result, String resultType) {

        resultDialog = new Dialog(MainActivity.this, android.R.style.Theme_Light_NoTitleBar_Fullscreen);
        resultDialog.setContentView(R.layout.result_popup);
        resultDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        resultDialog.getWindow().setGravity(Gravity.HORIZONTAL_GRAVITY_MASK);

        TextView result_1_text_view = resultDialog.findViewById(R.id.result_1_text_view);
        TextView result_2_text_view = resultDialog.findViewById(R.id.result_2_text_view);
        ImageButton restart_the_game_image_button = resultDialog.findViewById(R.id.restart_the_game_image_button);

        result_1_text_view.setText(result);
        result_2_text_view.setText(resultType);

        restart_the_game_image_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                resultDialog.dismiss();
            }
        });

        resultDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
            }
        });

        // Geri tuşuyla kapatılması engelleniyor
        //resultDialog.setCancelable(false);

        // Popup dışına tıklanarak kapatılması engelleniyor
        //loginDialog.setCanceledOnTouchOutside(false);

        resultDialog.show();
    }


    //uygulamanin dilini kontrol etmek icin
    private void checkLanguage() {
        Locale locale;

        if (getLanguageFromLocalDataSource().equals("")) {
            locale = new Locale("en");
        } else {
            locale = new Locale(getLanguageFromLocalDataSource());
        }

        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        this.getResources().updateConfiguration(config, this.getResources().getDisplayMetrics());
    }

    private String getLanguageFromLocalDataSource() {

        String result;
        String CONST_DATA = "LANGUAGE";
        SharedPreferences preferences = this.getSharedPreferences(CONST_DATA, getApplicationContext().MODE_PRIVATE);
        result = preferences.getString(CONST_DATA, "");

        return result;
    }

    private boolean getAIStatusFromLocalDataSource() {

        String result;
        String CONST_DATA = "AI";
        SharedPreferences preferences = this.getSharedPreferences(CONST_DATA, getApplicationContext().MODE_PRIVATE);
        result = preferences.getString(CONST_DATA, "");

        return result.equals("AI_ON") || result.equals("");
    }

    public String getButtonTextForPlayer() {
        return buttonTextForPlayer;
    }

    public void setButtonTextForPlayer(String buttonTextForPlayer) {
        this.buttonTextForPlayer = buttonTextForPlayer;
    }

    public String getButtonTextForAI() {
        return buttonTextForAI;
    }

    public void setButtonTextForAI(String buttonTextForAI) {
        this.buttonTextForAI = buttonTextForAI;
    }

    public int getOrderOfMoves() {
        return orderOfMoves;
    }

    public void setOrderOfMoves(int orderOfMoves) {
        this.orderOfMoves = orderOfMoves;
    }

    public ArrayList<ButtonNode> getButtonNodeArrayList() {
        return buttonNodeArrayList;
    }

    public void setButtonNodeArrayList(ArrayList<ButtonNode> buttonNodeArrayList) {
        this.buttonNodeArrayList = buttonNodeArrayList;
    }

    public int getMatrixGround() {
        return matrixGround;
    }

    public void setMatrixGround(int matrixGround) {
        this.matrixGround = matrixGround;
    }
}