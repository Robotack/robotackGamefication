package com.robotack.robogamification.ui.Activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.robotack.robogamification.R;
import com.robotack.robogamification.managers.ApiCallResponse;
import com.robotack.robogamification.managers.BusinessManager;
import com.robotack.robogamification.models.AlertClickListener;
import com.robotack.robogamification.models.GenderModel;
import com.robotack.robogamification.models.LoginUserModel;
import com.robotack.robogamification.models.SelectGenderListener;
import com.robotack.robogamification.ui.Adapters.ChooseGenderAdapter;
import com.robotack.robogamification.utilities.Utils;

public class ChooseGenderActivity extends AppCompatActivity implements SelectGenderListener {
    TextView tvNext;
    TextView tvTitleToolBar;
    RecyclerView genderReyclerView;
    ShimmerFrameLayout shimmer_view_container;
    SelectGenderListener selectGenderListener = (SelectGenderListener) this;
    String selectedID = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_gender);
        ImageView ivArrow;
        ivArrow = findViewById(R.id.ivArrow);
        ivArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        shimmer_view_container = findViewById(R.id.shimmer_view_container);
        tvTitleToolBar = findViewById(R.id.tvTitleToolBar);
        tvTitleToolBar.setText(R.string.chose_your_gender);
        tvNext = findViewById(R.id.tvNext);
        tvNext.setEnabled(false);
        genderReyclerView = findViewById(R.id.genderReyclerView);
        genderReyclerView.setLayoutManager(new GridLayoutManager(ChooseGenderActivity.this,2));
        genderReyclerView.setVisibility(View.GONE);
        tvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(ChooseGenderActivity.this, SetupProfileActivity.class).putExtra("id",selectedID);
                startActivity(intent);
            }
        });
        getGenderData();
    }

    private void getGenderData()
    {
        new BusinessManager().getGenderAPI(this, GamificationActivity.getTokenListener.getToken(), new ApiCallResponse() {
            @Override
            public void onSuccess(Object responseObject, String responseMessage) {
                GenderModel genderModel = (GenderModel) responseObject;
                try {
                    if (genderModel != null)
                    {
                        if (genderModel.getErrorCode().toString().equals("0"))
                        {
                            shimmer_view_container.setVisibility(View.GONE);
                            genderReyclerView.setVisibility(View.VISIBLE);
                            genderReyclerView.setAdapter(new ChooseGenderAdapter(ChooseGenderActivity.this,genderModel.getData(),selectGenderListener));
                        }else if (genderModel.getErrorCode().toString().equals("-99")){
                            finish();
                            startActivity(new Intent(ChooseGenderActivity.this, MaintancePageActivity.class));
                        }else {
                            new Utils().showSettingsAlertCustomClick(ChooseGenderActivity.this, genderModel.getDescriptionCode().toString(), new AlertClickListener() {
                                @Override
                                public void onAlertClick() {
                                    finish();
                                }
                            });
                        }
                    }
                }catch (Exception e)
                {
                    new Utils().showSettingsAlert(ChooseGenderActivity.this,getString(R.string.something_wrong));
                }
            }
            @Override
            public void onFailure(String errorResponse) {
                new Utils().showSettingsAlert(ChooseGenderActivity.this,getString(R.string.something_wrong));

            }
        });
    }

    @Override
    public void onGenderSelected(String id) {
        tvNext.setEnabled(true);
        tvNext.setBackgroundResource(R.drawable.dark_button);
        selectedID = id;

    }
}