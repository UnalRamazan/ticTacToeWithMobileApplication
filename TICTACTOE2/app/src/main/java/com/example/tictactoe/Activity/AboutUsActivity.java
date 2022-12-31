package com.example.tictactoe.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.tictactoe.R;

public class AboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        TextView slogan_text_view = findViewById(R.id.slogan_text_view);

        String slogan = "<" + getResources().getString(R.string.slogan) + ">";

        slogan_text_view.setText(slogan);
    }
}