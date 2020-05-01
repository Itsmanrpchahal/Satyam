package com.satyam.homeFragment.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.satyam.R;
import com.satyam.homeFragment.response.HomePageResponse;
import com.satyam.productList.ProductsActivity;
import com.satyam.productList.interface_.GetSubCate_IF;

import java.util.ArrayList;

public class CateAdapterMain extends RecyclerView.Adapter<CateAdapterMain.ViewHolder> {
    Context context;
    ArrayList<HomePageResponse.Data.Category> categories = new ArrayList<>();
    private int selectedPosition = -1;
    GetSubCate_IF getSubCate_if;

    public CateAdapterMain(Context context, ArrayList<HomePageResponse.Data.Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public CateAdapterMain.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.custom_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CateAdapterMain.ViewHolder holder, int position) {
        holder.textView.setText(categories.get(position).getCategoryName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (selectedPosition >= 0)
                    notifyItemChanged(selectedPosition);
                selectedPosition = holder.getAdapterPosition();
                notifyItemChanged(selectedPosition);
                Intent intent = new Intent(context, ProductsActivity.class);
                intent.putExtra("isFrom","main");
                intent.putExtra("cateID",categories.get(position).getCategoryId().toString());
                intent.putExtra("ProductType",categories.get(position).getCategoryName());
                context.startActivity(intent);
            }
        });
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
