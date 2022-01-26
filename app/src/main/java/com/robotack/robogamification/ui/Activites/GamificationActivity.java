package com.robotack.robogamification.ui.Activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.robotack.robogamification.helpers.GameficationGetTokenListener;
import com.robotack.robogamification.helpers.PrefConstant;
import com.robotack.robogamification.managers.ApiCallResponse;
import com.robotack.robogamification.managers.BusinessManager;
import com.robotack.robogamification.models.AlertClickListener;
import com.robotack.robogamification.models.InfoModel;
import com.robotack.robogamification.models.LoginUserModel;
import com.robotack.robogamification.utilities.Utils;
import com.robotack.robogamification.R;

import static com.robotack.robogamification.helpers.PrefConstant.sdkLanguage;
import static com.robotack.robogamification.helpers.SharedPrefConstants.Language;

public class GamificationActivity extends AppCompatActivity {
    ImageView arrow;
    String LanguageValue = "en";
    String userID = null;
    public  static GameficationGetTokenListener getTokenListener ;
    ShimmerFrameLayout shimmer_view_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            try {
                LanguageValue = getIntent().getStringExtra(sdkLanguage);
            } catch (Exception e) {
                LanguageValue = "en";
            }
            if (LanguageValue == null) {
                LanguageValue = "en";
            }
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor edit = preferences.edit();
            edit.putString(Language, LanguageValue);
            edit.commit();
        } catch (Exception e) {
            LanguageValue = "en";
        }
        new Utils().updateLangauge(this);
        setContentView(R.layout.activity_loyatli);
        shimmer_view_container = findViewById(R.id.shimmer_view_container);
        new Utils().setUserID(userID, this);
        try {
            userID = getIntent().getStringExtra(PrefConstant.custumerID);
            if (userID == null) {
                new Utils().showSettingsAlertCustomClick(GamificationActivity.this, getString(R.string.no_user), new AlertClickListener() {
                    @Override
                    public void onAlertClick() {
                        finish();
                    }
                });

            }else {
                new Utils().setUserID(userID, this);
                getLoginUser();
                getGameInfo();
            }
        } catch (Exception e) {
            new Utils().showSettingsAlertCustomClick(GamificationActivity.this, getString(R.string.no_user), new AlertClickListener() {
                @Override
                public void onAlertClick() {
                    finish();
                }
            });
        }

    }



    private void getLoginUser() {
        new BusinessManager().getUserInfoApiCall(this, getTokenListener.getToken(), new ApiCallResponse() {
            @Override
            public void onSuccess(Object responseObject, String responseMessage) {

                LoginUserModel loginUserModel = (LoginUserModel) responseObject;
                try {
                    if (loginUserModel != null) {
                        if (loginUserModel.getErrorCode().toString().equals("0")) {
                            shimmer_view_container.setVisibility(View.GONE);
                            finish();
                            startActivity(new Intent(GamificationActivity.this, SeasonsActivity.class));

                        } else if (loginUserModel.getErrorCode().toString().equals("-99")) {
                            finish();
                            startActivity(new Intent(GamificationActivity.this, MaintancePageActivity.class));
                        } else if (loginUserModel.getErrorCode().toString().equals("-100")) {
                            finish();
                            startActivity(new Intent(GamificationActivity.this, ChooseGenderActivity.class));
                        } else {
                            new Utils().showSettingsAlertCustomClick(GamificationActivity.this, loginUserModel.getDescriptionCode().toString(), new AlertClickListener() {
                                @Override
                                public void onAlertClick() {
                                    finish();
                                }
                            });
                        }
                    }
                } catch (Exception e) {
                    new Utils().showSettingsAlert(GamificationActivity.this, getString(R.string.something_wrong));
                }
            }

            @Override
            public void onFailure(String errorResponse) {
                new Utils().showSettingsAlert(GamificationActivity.this, getString(R.string.something_wrong));

            }
        });
    }

    public static void init(Context context , String customerID , String languageValue , GameficationGetTokenListener listener)
    {
        setTokenListner(listener);
        Intent in = new Intent(context, GamificationActivity.class);

        in.putExtra("custumerID", customerID);
        in.putExtra("sdkLanguage", languageValue);
        context.startActivity(in);
    }
    public static void setTokenListner(GameficationGetTokenListener listener)
    {
        if (listener == null)
        {
            return;
        }
        getTokenListener = listener;
    }
    private void getGameInfo() {
        new BusinessManager().getInfoApiCall(this, getTokenListener.getToken(), new ApiCallResponse() {
            @Override
            public void onSuccess(Object responseObject, String responseMessage) {

                InfoModel infoModel = (InfoModel) responseObject;
                try {
                    if (infoModel != null) {
                        if (infoModel.getErrorCode().toString().equals("0")) {

                            Utils.putSharedPreferencesObject(GamificationActivity.this, "data", infoModel);
                        }
                    }
                } catch (Exception e) {
                }
            }

            @Override
            public void onFailure(String errorResponse) {

            }
        });
    }


}