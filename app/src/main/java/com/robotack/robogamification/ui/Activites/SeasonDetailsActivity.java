package com.robotack.robogamification.ui.Activites;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.robotack.robogamification.R;
import com.robotack.robogamification.helpers.CheckTask;
import com.robotack.robogamification.helpers.LanguageHelper;
import com.robotack.robogamification.managers.ApiCallResponse;
import com.robotack.robogamification.managers.BusinessManager;
import com.robotack.robogamification.models.AlertClickListener;
import com.robotack.robogamification.models.CheckTaskModel;
import com.robotack.robogamification.models.SeasonDetailsModel;
import com.robotack.robogamification.models.SeasonModel;
import com.robotack.robogamification.ui.Adapters.SeasonTasksAdapter;
import com.robotack.robogamification.utilities.Utils;

public class SeasonDetailsActivity extends AppCompatActivity implements CheckTask {
    TextView tvTitleToolBar;
    TextView point;
    TextView tasksProgress;
    SeasonModel.User userObject ;
    ImageView avatarImage;
    String activeID = "";
    String activeName = "";
    String howToplayText= "";
    ImageView howToplay;
    ImageView ivArrow;
    RecyclerView tasksReyclerView;
    ShimmerFrameLayout shimmer_view_container;
    CheckTask checkTask = (CheckTask) this;
    SwipeRefreshLayout swipeToRefesh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_august_season);
        userObject = (SeasonModel.User) getIntent().getSerializableExtra("user");
        activeID = getIntent().getStringExtra("untiID");
        activeName = getIntent().getStringExtra("activeName");
        setupView();
    }
    private void setupView()
    {
        swipeToRefesh = findViewById(R.id.swipeToRefesh);
        shimmer_view_container = findViewById(R.id.shimmer_view_container);
        tvTitleToolBar = findViewById(R.id.tvTitleToolBar);
        tvTitleToolBar.setText(activeName);
        avatarImage = findViewById(R.id.avatarImage);
        point = findViewById(R.id.point);
        tasksProgress = findViewById(R.id.tasksProgress);
        tasksReyclerView = findViewById(R.id.tasksReyclerView);
        tasksReyclerView.setLayoutManager(new GridLayoutManager(SeasonDetailsActivity.this,2));
        try {
            Glide.with(this).load(userObject.getAvatar().toString()).into(avatarImage);
        } catch (Exception e) {

        }
        ivArrow = findViewById(R.id.ivArrow);
        ivArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (LanguageHelper.getCurrentLanguage(this).equals("ar")) {
            ivArrow.setScaleX(-1);
        }
        howToplay = findViewById(R.id.howToplay);
        howToplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertPopUp(getString(R.string.how_toplay_gamification),howToplayText,getString(R.string.start_playing_gamification));
            }
        });
        swipeToRefesh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getSesonData();
            }
        });

        getSesonData();
    }

    public AlertDialog alertDialog = null;

    public void showAlertPopUp(String dialogTitle,String dialogDetails,String buttonTitle) {
        try {
            View dialogView = null;
            LayoutInflater li = LayoutInflater.from(this);
            dialogView = li.inflate(R.layout.dialog, null);
            final androidx.appcompat.app.AlertDialog.Builder alertDialogBuilder = new androidx.appcompat.app.AlertDialog.Builder(this, R.style.MyCustomThemeDialog);
            alertDialogBuilder.setView(dialogView);

            alertDialog = alertDialogBuilder.create();
            TextView title = dialogView.findViewById(R.id.title);
            TextView details = dialogView.findViewById(R.id.details);
            TextView buttonClick = dialogView.findViewById(R.id.buttonClick);
            title.setText(dialogTitle);
            details.setText(dialogDetails);
            buttonClick.setText(buttonTitle);
            buttonClick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (alertDialog.isShowing()) {
                        alertDialog.dismiss();
                    }
                }
            });
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            if (alertDialog.isShowing()) {
                alertDialog.dismiss();
            }
            alertDialog.show();
        } catch (Exception e) {

        }
    }

    private void getSesonData() {
        new BusinessManager().getUnitDetailsAPI(SeasonDetailsActivity.this,activeID,GamificationActivity.getTokenListener.getToken(), new ApiCallResponse() {
            @Override
            public void onSuccess(Object responseObject, String responseMessage) {
                SeasonDetailsModel seasonModel = (SeasonDetailsModel) responseObject;
                try {
                    if (seasonModel != null) {
                        if (seasonModel.getErrorCode().toString().equals("0")) {
                            swipeToRefesh.setRefreshing(false);
                            shimmer_view_container.setVisibility(View.GONE);
                            tasksReyclerView.setAdapter(new SeasonTasksAdapter(SeasonDetailsActivity.this,seasonModel.getTasks(),checkTask));
                            howToplayText = seasonModel.getDescription().toString();
                            try {
                                point.setText(seasonModel.getGainedPoints().toString().toString());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            try {
                                tasksProgress.setText(seasonModel.getCompletedTaskCount().toString() +"/"+seasonModel.getTotalTaskCount().toString() );
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else if (seasonModel.getErrorCode().toString().equals("-99")) {
                            finish();
                            startActivity(new Intent(SeasonDetailsActivity.this, MaintancePageActivity.class));
                        } else {
                            new Utils().showSettingsAlertCustomClick(SeasonDetailsActivity.this, seasonModel.getDescriptionCode().toString(), new AlertClickListener() {
                                @Override
                                public void onAlertClick() {
                                    finish();
                                }
                            });
                        }
                    }
                } catch (Exception e) {
                    new Utils().showSettingsAlert(SeasonDetailsActivity.this, getString(R.string.something_wrong_gamification));
                }
            }

            @Override
            public void onFailure(String errorResponse) {
                new Utils().showSettingsAlert(SeasonDetailsActivity.this, getString(R.string.something_wrong_gamification));

            }
        });
    }


    @Override
    public void checkTask(String id) {
        checkTaskApi(id);
    }

    private void checkTaskApi(String id)
    {
        new BusinessManager().checkTaskAPI(SeasonDetailsActivity.this, id, GamificationActivity.getTokenListener.getToken(), new ApiCallResponse() {
            @Override
            public void onSuccess(Object responseObject, String responseMessage) {
                CheckTaskModel checkTaskModel = (CheckTaskModel) responseObject;
                if (checkTaskModel.getErrorCode().toString().equals("0"))
                {
                    getSesonData();
                }
            }

            @Override
            public void onFailure(String errorResponse) {

            }
        });
    }
}