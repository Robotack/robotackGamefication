package com.robotack.robogamification.ui.Activites;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.robotack.robogamification.R;
import com.robotack.robogamification.helpers.GameficationGetTokenListener;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        GamificationActivity.init(Splash.this, "123123", "en", "https://robotack.au.ngrok.io/AdminPortal/api/v1.3/", new GameficationGetTokenListener() {
            @Override
            public String getToken() {
                return "test";
            }
        });
    }
}