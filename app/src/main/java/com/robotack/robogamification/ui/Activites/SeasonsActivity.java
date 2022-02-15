package com.robotack.robogamification.ui.Activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.robotack.robogamification.R;
import com.robotack.robogamification.helpers.LanguageHelper;
import com.robotack.robogamification.managers.ApiCallResponse;
import com.robotack.robogamification.managers.BusinessManager;
import com.robotack.robogamification.models.AlertClickListener;
import com.robotack.robogamification.models.SeasonModel;
import com.robotack.robogamification.ui.Adapters.SeasonHistoryAdapter;
import com.robotack.robogamification.utilities.Utils;

public class SeasonsActivity extends AppCompatActivity {

    TextView tvTitleToolBar;
    TextView completedTaskCount;
    TextView seasonName;
    TextView seasonDate;
    ProgressBar performance_progress_bar;
    RecyclerView historyList;
    ShimmerFrameLayout shimmer_view_container;
    RelativeLayout activeSeasonClick;
    String activeID = "";
    String activeName = "";
    SeasonModel.User userObject ;
    SwipeRefreshLayout swipeToRefrsh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seasons);
        userObject = new SeasonModel.User();
        ImageView ivArrow;
        ivArrow = findViewById(R.id.ivArrow);
        if (LanguageHelper.getCurrentLanguage(this).equals("ar")) {
            ivArrow.setScaleX(-1);
        }
        ivArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        shimmer_view_container = findViewById(R.id.shimmer_view_container);
        swipeToRefrsh = findViewById(R.id.swipeToRefrsh);
        activeSeasonClick = findViewById(R.id.activeSeasonClick);
        activeSeasonClick.setEnabled(false);
        historyList = findViewById(R.id.historyList);
        performance_progress_bar = findViewById(R.id.performance_progress_bar);
        completedTaskCount = findViewById(R.id.completedTaskCount);
        seasonName = findViewById(R.id.seasonName);
        seasonDate = findViewById(R.id.seasonDate);
        tvTitleToolBar = findViewById(R.id.tvTitleToolBar);
        tvTitleToolBar.setText(R.string.seasons_gamification);
        getSesonData();
        activeSeasonClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SeasonsActivity.this,SeasonDetailsActivity.class).putExtra("untiID",activeID).putExtra("activeName",activeName).putExtra("user",userObject));
            }
        });
        swipeToRefrsh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                getSesonData();
            }
        });
    }

    private void getSesonData() {
        new BusinessManager().getUntiHistory(SeasonsActivity.this, GamificationActivity.getTokenListener.getToken(), new ApiCallResponse() {
            @Override
            public void onSuccess(Object responseObject, String responseMessage) {
                SeasonModel seasonModel = (SeasonModel) responseObject;
                try {
                    if (seasonModel != null) {
                        if (seasonModel.getErrorCode().toString().equals("0")) {
                            swipeToRefrsh.setRefreshing(false);
                            userObject = seasonModel.getUser();
                            shimmer_view_container.setVisibility(View.GONE);
                            activeSeasonClick.setEnabled(true);
                            try {
                                activeID = seasonModel.getActiveUnit().getUnitId().toString();
                                activeName = seasonModel.getActiveUnit().getTitle().toString();
                                seasonName.setText(seasonModel.getActiveUnit().getTitle().toString());
                            } catch (Exception e) {

                            }
                            try {
                                seasonDate.setText(seasonModel.getActiveUnit().getStartDate().toString()+" - "+seasonModel.getActiveUnit().getEndDate().toString());
                            } catch (Exception e) {

                            }
                            try {
                                completedTaskCount.setText(seasonModel.getActiveUnit().getCompletedTaskCount().toString()+"/"+seasonModel.getActiveUnit().getTotalTaskCount().toString());
                                performance_progress_bar.setMax(seasonModel.getActiveUnit().getTotalTaskCount());
                                performance_progress_bar.setProgress(seasonModel.getActiveUnit().getCompletedTaskCount());
                            } catch (Exception e) {

                            }
                            historyList.setLayoutManager(new LinearLayoutManager(SeasonsActivity.this));
                            historyList.setAdapter(new SeasonHistoryAdapter(SeasonsActivity.this,seasonModel.getHistory(),userObject));
                        } else if (seasonModel.getErrorCode().toString().equals("-99")) {
                            finish();
                            startActivity(new Intent(SeasonsActivity.this, MaintancePageActivity.class));
                        } else {
                            new Utils().showSettingsAlertCustomClick(SeasonsActivity.this, seasonModel.getDescriptionCode().toString(), new AlertClickListener() {
                                @Override
                                public void onAlertClick() {
                                    finish();
                                }
                            });
                        }
                    }
                } catch (Exception e) {
                    new Utils().showSettingsAlert(SeasonsActivity.this, getString(R.string.something_wrong_gamification));
                }
            }

            @Override
            public void onFailure(String errorResponse) {
                new Utils().showSettingsAlert(SeasonsActivity.this, getString(R.string.something_wrong_gamification));

            }
        });
    }


}