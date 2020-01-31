package com.mandy.satyam.productList.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mandy.satyam.R;
import com.mandy.satyam.homeFragment.adapter.CategoryAdapter;
import com.mandy.satyam.productList.response.SubCategory;

import java.util.ArrayList;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.ViewHolder> {
    Context context;
    ArrayList<SubCategory.Datum> categories = new ArrayList<>();

    public SubCategoryAdapter(Context context, ArrayList<SubCategory.Datum> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public SubCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.custom_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubCategoryAdapter.ViewHolder holder, int position) {
        holder.textView.setText(categories.get(position).getCategoryName());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.custom_cat);
        }
    }
}
