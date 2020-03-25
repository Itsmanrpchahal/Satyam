package com.mandy.satyam.productDetails.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mandy.satyam.R;
import com.mandy.satyam.productDetails.IF.SendItemsToActivityIF;
import com.mandy.satyam.productDetails.response.ProductDetailResponse;

import java.util.ArrayList;

public class VariationsAdapter extends RecyclerView.Adapter<VariationsAdapter.ViewHolder> {

    Context context;
    private int selectedPosition = -1;
    ArrayList<ProductDetailResponse.Data.CustomVariation.Option> optionArrayList = new ArrayList<>();
    ArrayList<ProductDetailResponse.Data.CustomVariation> customVariation = new ArrayList<>();
    ArrayList<String> selectedItems = new ArrayList<>();
    String selectedItemSize ;
    SendItemsToActivityIF sendItemsToActivityIF;

    public void VariationsAdapter(SendItemsToActivityIF sendItemsToActivityIF) {
        this.sendItemsToActivityIF = sendItemsToActivityIF;
    }

    public VariationsAdapter(Context context1, ArrayList<ProductDetailResponse.Data.CustomVariation> customVariations) {
        context = context1;
        customVariation = customVariations;
    }

    @NonNull
    @Override
    public VariationsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.variation, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VariationsAdapter.ViewHolder holder, int position) {
        holder.variationnametv.setText(customVariation.get(position).getTitle());


        LinearLayoutManager linearLayoutManager4 = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        holder.varitionotoinRecycler.setLayoutManager(linearLayoutManager4);
        optionArrayList.clear();

        if (customVariation.size()>=1)
        {
            for (int i = 0; i < customVariation.get(position).getOptions().size(); i++) {
                ProductDetailResponse.Data.CustomVariation.Option option = customVariation.get(position).getOptions().get(i);
                optionArrayList.add(option);
                VariationOptionAdapter adapter = new VariationOptionAdapter(context, optionArrayList,customVariation,position);
                holder.varitionotoinRecycler.setAdapter(adapter);
                adapter.VariationOptionAdapter(new GetOptionPos() {
                    @Override
                    public void getPos(int pos, ArrayList<String> arrayList) {
                        selectedItemSize = String.valueOf(pos);
                        selectedItems.addAll(arrayList);
                        adapter.VariationOptionAdapter(new GetOptionPos() {
                            @Override
                            public void getPos(int pos, ArrayList<String> arrayList) {
                                selectedItemSize = String.valueOf(pos);
                                selectedItems.addAll(selectedItems);
                            }
                        });
                    }
                });
            }
        }

    }

    @Override
    public int getItemCount() {
        return customVariation.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView variationnametv;
        RecyclerView varitionotoinRecycler;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            variationnametv = itemView.findViewById(R.id.variationnametv);
            varitionotoinRecycler = itemView.findViewById(R.id.varitionotoinRecycler);
        }
    }
}
