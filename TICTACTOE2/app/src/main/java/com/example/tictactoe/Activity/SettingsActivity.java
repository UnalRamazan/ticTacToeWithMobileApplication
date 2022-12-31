package com.example.tictactoe.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tictactoe.R;

import java.util.Locale;

public class SettingsActivity extends AppCompatActivity {

    private ImageButton turkish_language_button;
    private ImageButton english_language_button;
    private TextView open_close_ai_text_view;
    private SwitchCompat ai_switch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        checkLanguage();

        setContentView(R.layout.activity_settings);

        turkish_language_button = findViewById(R.id.turkish_language_button);
        english_language_button = findViewById(R.id.english_language_button);
        open_close_ai_text_view = findViewById(R.id.open_close_ai_text_view);
        ai_switch = findViewById(R.id.ai_switch);
        Button aboutUSButton = findViewById(R.id.about_us_button);
        ImageButton instagramButton = findViewById(R.id.instagramButton);
        ImageButton linkedinButton = findViewById(R.id.linkedinButton);
        ImageButton facebookButton = findViewById(R.id.facebookButton);
        ImageButton twitterButton = findViewById(R.id.twitterButton);
        ImageButton youTubeButton = findViewById(R.id.youTubeButton);
        ImageButton yaayButton = findViewById(R.id.yaayButton);
        ImageButton doftdare_web_site = findViewById(R.id.doftdare_web_site);

        checkLanguageButton();
        checkAIStatus();

        turkish_language_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocale("tr");
                english_language_button.setImageResource(R.drawable.ic_baseline_radio_button_unchecked);
                turkish_language_button.setImageResource(R.drawable.ic_baseline_radio_button_checked);
            }
        });

        english_language_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocale("en");
                turkish_language_button.setImageResource(R.drawable.ic_baseline_radio_button_unchecked);
                english_language_button.setImageResource(R.drawable.ic_baseline_radio_button_checked);
            }
        });

        ai_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changeAIStatus();
            }
        });

        aboutUSButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this, AboutUsActivity.class);
                startActivity(intent);
            }
        });

        doftdare_web_site.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.doftdare.com/"));
                startActivity(i);
            }
        });

        instagramButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/doftdare/"));
                startActivity(i);
            }
        });

        linkedinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/company/doftdare/"));
                startActivity(i);
            }
        });

        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/doftdareglobal/"));
                startActivity(i);
            }
        });

        twitterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/doftdare"));
                startActivity(i);
            }
        });

        youTubeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UCixMiP1j5NL9J2zQwePenkA"));
                startActivity(i);
            }
        });

        yaayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://yeni.yaay.com.tr/doftdare"));
                startActivity(i);
            }
        });
    }

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

    public void setLocale(String selectedLocale) {
        Locale locale = new Locale(selectedLocale);//butonlar içerisinden fonksiyonumuz çağırılırken, gönderdiğimiz parametreye göre lokalimizi ayarladık.
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getApplicationContext().getResources().updateConfiguration(config,
                getApplicationContext().getResources().getDisplayMetrics());
        saveLanguageToLocalDataSource(selectedLocale);//dil seçimini cihaza kaydedecek fonksiyonu çağırıyoruz.
        this.finish();
        startActivity(this.getIntent());
    }

    private String getLanguageFromLocalDataSource() {

        String result;
        String CONST_DATA = "LANGUAGE";
        SharedPreferences preferences = this.getSharedPreferences(CONST_DATA, getApplicationContext().MODE_PRIVATE);
        result = preferences.getString(CONST_DATA, "");

        return result;
    }

    private void saveLanguageToLocalDataSource(String language) {

        String CONST_DATA = "LANGUAGE";
        SharedPreferences preferences = this.getSharedPreferences(CONST_DATA, getApplicationContext().MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(CONST_DATA, String.valueOf(language));
        editor.apply();
    }

    private void checkLanguageButton() {

        if (getLanguageFromLocalDataSource().equals("en")) {
            english_language_button.setImageResource(R.drawable.ic_baseline_radio_button_checked);
        } else if (getLanguageFromLocalDataSource().equals("tr")) {
            turkish_language_button.setImageResource(R.drawable.ic_baseline_radio_button_checked);
        } else {
            english_language_button.setImageResource(R.drawable.ic_baseline_radio_button_checked);
        }
    }

    private void checkAIStatus() {
        if (getAIStatusFromLocalDataSource()) {

            ai_switch.setChecked(true);
            open_close_ai_text_view.setText(getResources().getString(R.string.ai_on));
        } else {
            ai_switch.setChecked(false);
            open_close_ai_text_view.setText(getResources().getString(R.string.ai_off));
        }
    }

    private void changeAIStatus() {

        String str = "";
        if (getAIStatusFromLocalDataSource()) {

            ai_switch.setChecked(false);
            open_close_ai_text_view.setText(getResources().getString(R.string.ai_off));
            saveAIStatusToLocalDataSource("AI_OFF");
            str = getResources().getString(R.string.ai_off_text);
        } else {
            ai_switch.setChecked(true);
            open_close_ai_text_view.setText(getResources().getString(R.string.ai_on));
            saveAIStatusToLocalDataSource("AI_ON");
            str = getResources().getString(R.string.ai_on_text);
        }

        Toast toast = Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    private boolean getAIStatusFromLocalDataSource() {

        String result;
        String CONST_DATA = "AI";
        SharedPreferences preferences = this.getSharedPreferences(CONST_DATA, getApplicationContext().MODE_PRIVATE);
        result = preferences.getString(CONST_DATA, "");

        return result.equals("AI_ON") || result.equals("");
    }

    private void saveAIStatusToLocalDataSource(String ai) {

        String CONST_DATA = "AI";
        SharedPreferences preferences = this.getSharedPreferences(CONST_DATA, getApplicationContext().MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(CONST_DATA, String.valueOf(ai));
        editor.apply();
    }
}