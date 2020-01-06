package com.mandy.satyam.myOrderList;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.mandy.satyam.myOrderList.myorderdetails.OrderDetailsActivity;
import com.mandy.satyam.R;
import com.mandy.satyam.utils.Config;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

public class YourOrderAdapter extends RecyclerView.Adapter<YourOrderAdapter.ViewHolder> {

    Context context;
    ArrayList<OrderListApi.Datum> arrayList = new ArrayList<>();
    String des;

    public YourOrderAdapter(Context context) {
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.custom_your_order, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {



        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OrderDetailsActivity.class);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textDispatch, textDate;
        ImageView imageView;
        AVLoadingIndicatorView avLoadingIndicatorView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textDispatch = itemView.findViewById(R.id.txtDispatch);
            textDate = itemView.findViewById(R.id.txtData);
            imageView = itemView.findViewById(R.id.custom_image);
            avLoadingIndicatorView = itemView.findViewById(R.id.avi);

        }

    }
}
