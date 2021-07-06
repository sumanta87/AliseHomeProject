package com.example.alisehomeproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.alisehomeproject.R;
import com.example.alisehomeproject.Viewsmode.HighBoldTextView;
import com.makeramen.roundedimageview.RoundedImageView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * Created by su on 8/18/17.
 */

public class PremiumLIkeAdapter extends RecyclerView.Adapter<PremiumLIkeAdapter.MyViewHolder> {
    //    ArrayList<PortFolio> portFolioArrayList;
    Context mContext;


    public PremiumLIkeAdapter(Context mContext) {
        this.mContext = mContext;

        //this.portFolioArrayList=portFolioArrayList;
    }
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.premium_like, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyViewHolder holder, int position) {
        if (position==0)
        {
            Glide.with(mContext).load(R.drawable.photo_3)
                    .apply(RequestOptions.bitmapTransform(new BlurTransformation(10, 5)))
                    .into(holder.likeorofile);
            holder.tv_name_age.setText("Beena");

        }
        else if(position==1)
        {
            Glide.with(mContext).load(R.drawable.photo_2)
                    .apply(RequestOptions.bitmapTransform(new BlurTransformation(10, 5)))
                    .into(holder.likeorofile);

            holder.tv_name_age.setText("Teena");
        }
        else if (position==2)
        {
            Glide.with(mContext).load(R.drawable.photo_3)
                    .apply(RequestOptions.bitmapTransform(new BlurTransformation(10, 5)))
                    .into(holder.likeorofile);
            holder.tv_name_age.setText("Beena");

        }
        else {
            Glide.with(mContext).load(R.drawable.photo_2)
                    .apply(RequestOptions.bitmapTransform(new BlurTransformation(10, 5)))
                    .into(holder.likeorofile);
            holder.tv_name_age.setText("Teena");

        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView likeorofile;
        HighBoldTextView tv_name_age;
        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            likeorofile=(RoundedImageView)itemView.findViewById(R.id.iv_profile);
            tv_name_age=(HighBoldTextView)itemView.findViewById(R.id.tv_name_age);

        }
    }
}
