package com.robotack.robogamification.ui.Adapters;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.robotack.robogamification.R;
import com.robotack.robogamification.helpers.CheckTask;
import com.robotack.robogamification.models.SeasonDetailsModel;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SeasonTasksAdapter extends RecyclerView.Adapter<SeasonTasksAdapter.CustomViewHolder> {
    Activity mActivity;
    List<SeasonDetailsModel.Task> data;
    CheckTask checkTask;

    public SeasonTasksAdapter(Activity activity, List<SeasonDetailsModel.Task> data, CheckTask checkTask) {
        this.mActivity = activity;
        this.data = data;
        this.checkTask = checkTask;
    }
    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_tasks, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder holder, final int position) {
        try {
            holder.taskName.setText(data.get(position).getTitle().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Glide.with(mActivity).load(data.get(position).getTaskImage()).into(holder.taskImg);
        } catch (Exception e) {

        }
        holder.popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertPopUp(data.get(position).getTitle().toString(), data.get(position).getDescription().toString(), mActivity.getString(R.string.start_playing_gamification));
            }
        });
        if (data.get(position).getTaskStatus().toString().equalsIgnoreCase("AVAILABLE")) {
            holder.verfyButton.setText(R.string.verify_gamification);
            holder.statusImage.setVisibility(View.INVISIBLE);
            holder.verfyTxt.setVisibility(View.INVISIBLE);
        } else if (data.get(position).getTaskStatus().toString().equalsIgnoreCase("COMPLETED")) {
            holder.verfyButton.setVisibility(View.INVISIBLE);
            holder.statusImage.setVisibility(View.VISIBLE);
            holder.statusImage.setImageResource(R.drawable.ic_complete);
            holder.verfyTxt.setVisibility(View.INVISIBLE);
        } else if (data.get(position).getTaskStatus().toString().equalsIgnoreCase("VERIFYING")) {
            holder.verfyButton.setText(R.string.verifing_gamification);
            holder.statusImage.setVisibility(View.INVISIBLE);
            holder.verfyTxt.setVisibility(View.VISIBLE);
        } else if (data.get(position).getTaskStatus().toString().equalsIgnoreCase("EXPIRED")) {
            holder.verfyButton.setVisibility(View.INVISIBLE);
            holder.statusImage.setVisibility(View.VISIBLE);
            holder.statusImage.setImageResource(R.drawable.ic_expired);
            holder.verfyTxt.setVisibility(View.INVISIBLE);
        }
        try {
            if (data.get(position).getSpecial().toString().equals("true") && data.get(position).getTaskStatus().toString().equalsIgnoreCase("AVAILABLE")) {
                if (data.get(position).getRemainingTime().toString().equals("0")) {
                    holder.timerCount.setVisibility(View.INVISIBLE);
                }
                new CountDownTimer(Integer.parseInt(data.get(position).getRemainingTime().toString()) * 1000, 1000) {
                    public void onTick(long millisUntilFinished) {
                        long days = TimeUnit.MILLISECONDS.toDays(millisUntilFinished);
                        millisUntilFinished -= TimeUnit.DAYS.toMillis(days);

                        long hours = TimeUnit.MILLISECONDS.toHours(millisUntilFinished);
                        millisUntilFinished -= TimeUnit.HOURS.toMillis(hours);

                        long minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished);
                        millisUntilFinished -= TimeUnit.MINUTES.toMillis(minutes);

                        long seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished);

                        try {
                            holder.timerCount.setText(days + ":" + hours + ":" + minutes + ":" + seconds);
                        } catch (Exception e) {

                        }
                        holder.timerCount.setVisibility(View.VISIBLE);
                    }

                    public void onFinish() {
                        holder.timerCount.setVisibility(View.INVISIBLE);
                    }
                }.start();
            } else {
                holder.timerCount.setVisibility(View.INVISIBLE);
            }
        }catch (Exception e)
        {
        }
        holder.verfyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data.get(position).getTaskStatus().toString().equalsIgnoreCase("AVAILABLE")) {
                    holder.verfyButton.setText(R.string.verifing_gamification);
                    checkTask.checkTask(data.get(position).getTaskId().toString());
                    holder.verfyTxt.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    public AlertDialog alertDialog = null;

    public void showAlertPopUp(String dialogTitle, String dialogDetails, String buttonTitle) {
        try {
            View dialogView = null;
            LayoutInflater li = LayoutInflater.from(mActivity);
            dialogView = li.inflate(R.layout.dialog, null);
            final androidx.appcompat.app.AlertDialog.Builder alertDialogBuilder = new androidx.appcompat.app.AlertDialog.Builder(mActivity, R.style.MyCustomThemeDialog);
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

    @Override
    public int getItemCount() {
        if (data == null) {
            return 0;
        }
        return data.size();
    }

    static class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView taskName;
        TextView verfyTxt;
        ImageView taskImg;
        ImageView statusImage;
        ImageView popup;
        TextView verfyButton;
        TextView timerCount;

        CustomViewHolder(View itemView) {
            super(itemView);
            timerCount = (TextView) itemView.findViewById(R.id.timerCount);
            verfyTxt = (TextView) itemView.findViewById(R.id.verfyTxt);
            verfyButton = (TextView) itemView.findViewById(R.id.verfyButton);
            taskName = (TextView) itemView.findViewById(R.id.taskName);
            taskImg = (ImageView) itemView.findViewById(R.id.taskImg);
            statusImage = (ImageView) itemView.findViewById(R.id.statusImage);
            popup = (ImageView) itemView.findViewById(R.id.popup);
        }
    }
}