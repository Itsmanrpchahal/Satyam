package com.mandy.satyam.productList.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mandy.satyam.R;
import com.mandy.satyam.homeFragment.adapter.CategoryAdapter;
import com.mandy.satyam.productList.interface_.GetSubCate_IF;
import com.mandy.satyam.productList.response.SubCategory;

import java.util.ArrayList;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.ViewHolder> {
    Context context;
    ArrayList<SubCategory.Datum> categories = new ArrayList<>();
    private int selectedPosition = -1;
    GetSubCate_IF getSubCate_if;

    public void SubCategoryAdapter(GetSubCate_IF getSubCate_if) {
        this.getSubCate_if = getSubCate_if;
    }

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


        holder.textView.setText(categories.get(position).getCategoryName().replace("&amp;","&"));

        if (selectedPosition == position) {
            holder.itemView.setSelected(true); //using selector drawable
            holder.textView.setBackgroundResource(R.drawable.selected_theam);
            holder.textView.setTextColor(context.getResources().getColor(R.color.white));
        } else {
            holder.itemView.setSelected(false);
            holder.textView.setBackgroundResource(R.drawable.grey_border);
            holder.textView.setTextColor(context.getResources().getColor(R.color.colorAccent));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (selectedPosition >= 0)
                    notifyItemChanged(selectedPosition);
                selectedPosition = holder.getAdapterPosition();
                notifyItemChanged(selectedPosition);
                getSubCate_if.getCateID(String.valueOf(categories.get(position).getCategoryId()));
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
