package com.robotack.robogamification.ui.Activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.robotack.robogamification.R;
import com.robotack.robogamification.managers.ApiCallResponse;
import com.robotack.robogamification.managers.BusinessManager;
import com.robotack.robogamification.models.AlertClickListener;
import com.robotack.robogamification.models.InfoModel;
import com.robotack.robogamification.models.LoginUserModel;
import com.robotack.robogamification.models.RegisterSenderClass;
import com.robotack.robogamification.utilities.Utils;

public class WelcomeAbroadActivity extends AppCompatActivity {
    TextView tvNext;
    TextView titleWelcome;
    TextView descWelcome;
    TextView tvTitleToolBar;
    String avatarID;
    ProgressBar progressBar;
    InfoModel infoModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_abroad);
        avatarID = getIntent().getStringExtra("avatarID");
        ImageView ivArrow;
        ivArrow = findViewById(R.id.ivArrow);
        progressBar = findViewById(R.id.progressBar);
        ivArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        infoModel = (InfoModel) Utils.getSharedPreferencesObject(WelcomeAbroadActivity.this, "data", InfoModel.class);
        titleWelcome = findViewById(R.id.titleWelcome);
        descWelcome = findViewById(R.id.descWelcome);
        try {
            titleWelcome.setText(infoModel.getTitle().toString());
        } catch (Exception e) {
        }
        try {
            descWelcome.setText(infoModel.getDescription().toString());
        } catch (Exception e) {
        }
        tvTitleToolBar = findViewById(R.id.tvTitleToolBar);
        try {
            tvTitleToolBar.setText(infoModel.getTitle().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        ;
        tvNext = findViewById(R.id.tvNext);
        tvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });


    }

    private void registerUser() {
        progressBar.setVisibility(View.VISIBLE);
        RegisterSenderClass senderClass = new RegisterSenderClass();
        senderClass.avatarId = avatarID;
        Gson gson = new Gson();
        String json = gson.toJson(senderClass);
        JsonObject gsonObject = new JsonObject();
        JsonParser jsonParser = new JsonParser();
        gsonObject = (JsonObject) jsonParser.parse(json.toString());
        new BusinessManager().registerAPI(WelcomeAbroadActivity.this, gsonObject, GamificationActivity.getTokenListener.getToken(), new ApiCallResponse() {
            public void onSuccess(Object responseObject, String responseMessage) {
                progressBar.setVisibility(View.GONE);
                LoginUserModel loginUserModel = (LoginUserModel) responseObject;
                try {
                    if (loginUserModel != null) {
                        if (loginUserModel.getErrorCode().toString().equals("0")) {
                            finish();
                            Intent intent = new Intent(WelcomeAbroadActivity.this, SeasonsActivity.class);
                            startActivity(intent);

                        } else if (loginUserModel.getErrorCode().toString().equals("-99")) {
                            finish();
                            startActivity(new Intent(WelcomeAbroadActivity.this, MaintancePageActivity.class));
                        } else if (loginUserModel.getErrorCode().toString().equals("-100")) {
                            finish();
                            startActivity(new Intent(WelcomeAbroadActivity.this, ChooseGenderActivity.class));
                        } else {
                            new Utils().showSettingsAlertCustomClick(WelcomeAbroadActivity.this, loginUserModel.getDescriptionCode().toString(), new AlertClickListener() {
                                @Override
                                public void onAlertClick() {
                                    finish();
                                }
                            });
                        }
                    }
                } catch (Exception e) {
                    progressBar.setVisibility(View.GONE);
                    new Utils().showSettingsAlert(WelcomeAbroadActivity.this, getString(R.string.something_wrong));
                }
            }

            @Override
            public void onFailure(String errorResponse) {
                progressBar.setVisibility(View.GONE);
                new Utils().showSettingsAlert(WelcomeAbroadActivity.this, getString(R.string.something_wrong));

            }
        });
    }

}