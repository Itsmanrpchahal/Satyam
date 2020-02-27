package com.mandy.satyam.homeFragment.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.mandy.satyam.R;
import com.mandy.satyam.homeFragment.response.HomePageResponse;
import com.mandy.satyam.productList.ProductsActivity;
import com.mandy.satyam.productList.interface_.GetSubCate_IF;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    Context context;
    ArrayList<HomePageResponse.Data.Category> categories = new ArrayList<>();
    private int selectedPosition = -1;
    GetSubCate_IF getSubCate_if;

    public void SubCategoryAdapter(GetSubCate_IF getSubCate_if) {
        this.getSubCate_if = getSubCate_if;
    }

    public CategoryAdapter(Context context, ArrayList<HomePageResponse.Data.Category> categories) {
        this.context = context;
        this.categories = categories;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.custom_category, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        String cat = categories.get(i).getCategoryName().substring(0,1);
        String small = categories.get(i).getCategoryName().toLowerCase().substring(1);
        viewHolder.textView.setText(cat+small);

      /*  if (selectedPosition == i) {
            viewHolder.itemView.setSelected(true); //using selector drawable
            viewHolder.textView.setBackgroundResource(R.drawable.selected_theam);
            viewHolder.textView.setTextColor(context.getResources().getColor(R.color.white));
        } else {
            viewHolder.itemView.setSelected(false);
            viewHolder.textView.setBackgroundResource(R.drawable.grey_border);
            viewHolder.textView.setTextColor(context.getResources().getColor(R.color.colorAccent));
        }*/

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (selectedPosition >= 0)
                    notifyItemChanged(selectedPosition);
                selectedPosition = viewHolder.getAdapterPosition();
                notifyItemChanged(selectedPosition);
                Intent intent = new Intent(context, ProductsActivity.class);
                intent.putExtra("isFrom","HomePage");
                intent.putExtra("cateID",categories.get(i).getCategoryId().toString());
                intent.putExtra("ProductType",categories.get(i).getCategoryName());
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
