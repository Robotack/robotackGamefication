package com.robotack.robogamification.ui.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.robotack.robogamification.R;
import com.robotack.robogamification.models.GenderModel;
import com.robotack.robogamification.models.SeasonModel;
import com.robotack.robogamification.models.SelectGenderListener;
import com.robotack.robogamification.ui.Activites.SeasonDetailsActivity;
import com.robotack.robogamification.ui.Activites.SeasonsActivity;

import java.util.List;

public class SeasonHistoryAdapter extends RecyclerView.Adapter<SeasonHistoryAdapter.CustomViewHolder> {
    Activity mActivity;
    List<SeasonModel.History> data;
    SeasonModel.User userObject;
    public SeasonHistoryAdapter(Activity activity, List<SeasonModel.History> data, SeasonModel.User userObject) {
        this.mActivity = activity;
        this.data = data;
        this.userObject = userObject;

    }
    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_season, parent, false);
        return new CustomViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final CustomViewHolder holder, final int position) {
        try {
            holder.seaseonDate.setText(data.get(position).getStartDate().toString()+" - "+data.get(position).getEndDate().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            holder.seasoneTitle.setText(data.get(position).getTitle().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            holder.seasonReward.setText(data.get(position).getGainedPoints().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.startActivity(new Intent(mActivity, SeasonDetailsActivity.class).putExtra("untiID",data.get(position).getUnitId().toString()).putExtra("activeName",data.get(position).getTitle().toString()).putExtra("user",userObject));
            }
        });

    }

    @Override
    public int getItemCount() {
        if (data == null) {
            return 0;
        }
        return data.size();
    }

    static class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView seasoneTitle;
        TextView seaseonDate;
        TextView seasonReward;
        CustomViewHolder(View itemView) {
            super(itemView);

            seasoneTitle = (TextView) itemView.findViewById(R.id.seasoneTitle);
            seaseonDate = (TextView) itemView.findViewById(R.id.seaseonDate);
            seasonReward = (TextView) itemView.findViewById(R.id.seasonReward);
        }
    }
}