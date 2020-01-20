package com.mandy.satyam.filterScreen.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.mandy.satyam.R;
import com.mandy.satyam.filterScreen.FilterActivity;

import java.util.ArrayList;

public class BrandAdapter  extends RecyclerView.Adapter<BrandAdapter.ViewHolder> {

    Context context;
    ArrayList<String> brandlist = new ArrayList<>();

    public BrandAdapter(Context context, ArrayList<String> brandlist) {
        this.context = context;
        this.brandlist = brandlist;
    }

    @NonNull
    @Override
    public BrandAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.filter_brand,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BrandAdapter.ViewHolder viewHolder, int i) {
        viewHolder.brandcheckbox.setText(brandlist.get(i));
    }

    @Override
    public int getItemCount() {
        return brandlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox brandcheckbox;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            brandcheckbox = itemView.findViewById(R.id.filter_brand_check_box);
        }
    }
}
