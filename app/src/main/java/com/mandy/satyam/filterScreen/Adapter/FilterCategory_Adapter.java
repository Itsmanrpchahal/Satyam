package com.mandy.satyam.filterScreen.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mandy.satyam.R;

import java.util.ArrayList;

public class FilterCategory_Adapter extends RecyclerView.Adapter<FilterCategory_Adapter.ViewHolder> {

    Context context;
    ArrayList<String> filterCate = new ArrayList<>();

    public FilterCategory_Adapter(Context context, ArrayList<String> filterCate) {
        this.context = context;
        this.filterCate = filterCate;
    }

    @NonNull
    @Override
    public FilterCategory_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.filter_categories,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilterCategory_Adapter.ViewHolder viewHolder, int i) {
        viewHolder.filterCate.setText(filterCate.get(i));
    }

    @Override
    public int getItemCount() {
        return filterCate.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView filterCate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            filterCate = itemView.findViewById(R.id.filte_cate);
        }
    }
}
