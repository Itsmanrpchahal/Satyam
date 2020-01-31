package com.mandy.satyam.homeFragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mandy.satyam.R;
import com.mandy.satyam.homeFragment.response.HomePageResponse;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

public class HomeProductAdapter extends RecyclerView.Adapter<HomeProductAdapter.ViewHolder> {

    Context context;
    ArrayList<HomePageResponse.Data.Section.CategoryProduct> categoryProducts = new ArrayList<>();


    public HomeProductAdapter(Context context, ArrayList<HomePageResponse.Data.Section.CategoryProduct> categoryProducts) {
        this.context = context;
        this.categoryProducts = categoryProducts;
    }

    @NonNull
    @Override
    public HomeProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.layout_best_seller, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeProductAdapter.ViewHolder holder, int position) {
        String cat = categoryProducts.get(position).getProductName().substring(0,1);
        String small = categoryProducts.get(position).getProductName().toLowerCase().substring(1);
        Glide.with(context).load(categoryProducts.get(position).getProductImage().toString()).placeholder(R.drawable.image_d).into(holder.imageView);
        holder.textView.setText(cat+small);
    }

    @Override
    public int getItemCount() {
        return categoryProducts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.dashText);
            imageView = itemView.findViewById(R.id.dashImage);
        }
    }
}
