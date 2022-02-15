package com.robotack.robogamification.ui.Activites;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.robotack.robogamification.R;
import com.robotack.robogamification.helpers.GameficationGetTokenListener;

public class Splash extends AppCompatActivity {

    EditText customerID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        customerID = (EditText) findViewById(R.id.customerID);

        findViewById(R.id.startBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GamificationActivity.init(Splash.this,customerID.getText().toString() , "en", new GameficationGetTokenListener() {
                    @Override
                    public String getToken() {
                        return "test";
                    }
                });
            }
        });

    }
}