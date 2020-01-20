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

public class PriceAdapter extends RecyclerView.Adapter<PriceAdapter.ViewHolder> {

    Context context;
    ArrayList<String> priceList = new ArrayList<>();

    public PriceAdapter(Context context, ArrayList<String> priceList) {
        this.context = context;
        this.priceList = priceList;
    }

    @NonNull
    @Override
    public PriceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.filter_price,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PriceAdapter.ViewHolder viewHolder, int i) {

        viewHolder.priceCheckBox.setText(priceList.get(i));
    }

    @Override
    public int getItemCount() {
        return priceList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CheckBox priceCheckBox;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            priceCheckBox = itemView.findViewById(R.id.filter_price_check_box);
        }
    }
}
