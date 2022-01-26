package com.robotack.robogamification.ui.Adapters;
import android.app.Activity;
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

import com.bumptech.glide.Glide;
import com.robotack.robogamification.R;
import com.robotack.robogamification.models.GenderModel;
import com.robotack.robogamification.models.SelectGenderListener;

import java.util.List;

public class ChooseGenderAdapter extends RecyclerView.Adapter<ChooseGenderAdapter.CustomViewHolder> {
    Activity mActivity;
    List<GenderModel.Datum> data;
    SelectGenderListener selectGenderListener;
    public ChooseGenderAdapter(Activity activity, List<GenderModel.Datum> data, SelectGenderListener selectGenderListener) {
        this.mActivity = activity;
        this.data = data;
        this.selectGenderListener = selectGenderListener;
    }
    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_choose_gender, parent, false);
        return new CustomViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final CustomViewHolder holder, final int position) {
        if (data.get(position).isCheck())
        {
            holder.genderLinear.setBackgroundResource(R.drawable.select_red_outline);
        }else {
            holder.genderLinear.setBackgroundResource(0);
        }
        try {
            holder.genderTitle.setText(data.get(position).getTitle());
        } catch (Exception e) {
        }

        try {
            Glide.with(mActivity).load(data.get(position).getImage()).into(holder.genderImage);
        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int counter = 0 ; counter < data.size() ; counter ++)
                {
                    data.get(counter).setCheck(false);
                }
                data.get(position).setCheck(true);
                selectGenderListener.onGenderSelected(data.get(position).getId().toString());
                notifyDataSetChanged();

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
        TextView genderTitle;
        ImageView genderImage;
        LinearLayout genderLinear;
        CustomViewHolder(View itemView) {
            super(itemView);
            genderTitle = (TextView) itemView.findViewById(R.id.genderTitle);
            genderImage = (ImageView) itemView.findViewById(R.id.genderImage);
            genderLinear = (LinearLayout) itemView.findViewById(R.id.genderLinear);
        }
    }
}