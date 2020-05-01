package com.satyam.allCategory;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.satyam.R;
import com.satyam.homeFragment.response.HomePageResponse;
import com.satyam.productList.ProductsActivity;

import java.util.ArrayList;

public class AllCategoryAdapter extends RecyclerView.Adapter<AllCategoryAdapter.ViewHolder> {

    Context context;
    ArrayList<HomePageResponse.Data.Category> categoryArrayList = new ArrayList<>();

    public AllCategoryAdapter(Context context1, ArrayList<HomePageResponse.Data.Category> categories) {

        this.context = context1;
        this.categoryArrayList = categories;
    }

    @NonNull
    @Override
    public AllCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.allcategorycustom, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllCategoryAdapter.ViewHolder holder, int position) {
            holder.ProductNameTv.setText(categoryArrayList.get(position).getCategoryName());
        Glide.with(context).load(categoryArrayList.get(position).getCategory_image()).placeholder(R.drawable.ic_satyamplaceholder).into(holder.allcategoryImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductsActivity.class);
                intent.putExtra("isFrom","HomePage");
                intent.putExtra("cateID",categoryArrayList.get(position).getCategoryId().toString());
                intent.putExtra("ProductType",categoryArrayList.get(position).getCategoryName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView allcategoryImage;
        TextView ProductNameTv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            allcategoryImage = itemView.findViewById(R.id.allcategoryImage);
            ProductNameTv = itemView.findViewById(R.id.ProductNameTv);
        }
    }
}
