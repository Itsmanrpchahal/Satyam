package com.mandy.satyam.productDetails.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mandy.satyam.R;
import com.mandy.satyam.productDetails.ProductDetailsActivity;
import com.mandy.satyam.productDetails.response.ProductDetailResponse;
import com.mandy.satyam.productDetails.response.VauleResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

public class VariationOptionAdapter extends RecyclerView.Adapter<VariationOptionAdapter.ViwHolder> {

    Context context;
    ArrayList<ProductDetailResponse.Data.CustomVariation.Option> optionArrayList = new ArrayList<>();
    ArrayList<ProductDetailResponse.Data.CustomVariation> customVariationArrayList = new ArrayList<>();
    private int selectedPosition = 0;
    GetOptionPos getOptionPos;
    private int positionn;
    ArrayList<String> selectedItem = new ArrayList<>();
    static ArrayList<String> selectedItem1 = new ArrayList<>();
    private int singleSelection = -1;
    JSONArray jsonArray;

    public void VariationOptionAdapter(GetOptionPos getOptionPos) {
        this.getOptionPos = getOptionPos;
    }

    public VariationOptionAdapter(Context context, ArrayList<ProductDetailResponse.Data.CustomVariation.Option> optionArrayList, ArrayList<ProductDetailResponse.Data.CustomVariation> customVariation) {
        this.context = context;
        this.optionArrayList = optionArrayList;
        this.customVariationArrayList = customVariation;
    }

    public VariationOptionAdapter(Context context, ArrayList<ProductDetailResponse.Data.CustomVariation.Option> optionArrayList, ArrayList<ProductDetailResponse.Data.CustomVariation> customVariation, int position) {
        this.context = context;
        this.optionArrayList = optionArrayList;
        this.customVariationArrayList = customVariation;
        this.positionn = position;
    }

    @NonNull
    @Override
    public VariationOptionAdapter.ViwHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.variationoptions, parent, false);
        return new ViwHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VariationOptionAdapter.ViwHolder holder, int position) {
        jsonArray = new JSONArray();
        if (selectedPosition == position) {
            holder.itemView.setSelected(false);
            holder.linearLayout.setBackgroundColor(Color.GRAY);


            String key = "'"+customVariationArrayList.get(positionn).getOptions().get(selectedPosition).getKey()+"'"+" => "+"'"+customVariationArrayList.get(positionn).getOptions().get(selectedPosition).getVp()+"'";
            selectedItem.add(key);
            JSONArray jsonArray1 = new JSONArray();
            jsonArray1.put(customVariationArrayList.get(positionn).getOptions().get(selectedPosition).getKey());
            jsonArray1.put(customVariationArrayList.get(positionn).getOptions().get(selectedPosition).getK());
            Log.d("CHECKITEMS",""+selectedItem);


        } else {
            holder.itemView.setSelected(true); //using selector drawable
            holder.linearLayout.setBackgroundColor(Color.WHITE);
        }
        selectedItem1.addAll(selectedItem);
        HashSet<String> hashSet = new HashSet<String>();
        hashSet.addAll(selectedItem1);
        selectedItem1.clear();
        selectedItem1.addAll(hashSet);
        getOptionPos.getPos(selectedItem1.size(),selectedItem);

        ProductDetailsActivity.getPosItems = String.valueOf(optionArrayList.size());
        ProductDetailsActivity.TypeVariations.addAll(selectedItem1);
        Log.d("CHECKITEMS1",""+selectedItem1);
        if (optionArrayList.get(position).getV().startsWith("#")) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(70, 70);
            holder.variationoptiostv.setLayoutParams(layoutParams);
            holder.variationoptiostv.setText("");
            holder.variationoptiostv.setBackgroundColor(Color.parseColor(optionArrayList.get(position).getV()));
            layoutParams.setMargins(10, 10, 10, 10);

        } else {
//            if (positionn != 0)
                holder.variationoptiostv.setText(optionArrayList.get(position).getV());
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = holder.getAdapterPosition();

                if (selectedPosition == pos) {
                    holder.itemView.setSelected(true); //using selector drawable
                    holder.linearLayout.setBackgroundColor(Color.GRAY);
                    selectedPosition = pos;
                    notifyDataSetChanged();

                } else {
                    holder.itemView.setSelected(false);
                    holder.linearLayout.setBackgroundColor(Color.GRAY);
                    selectedPosition = pos;
                    notifyDataSetChanged();
                }


            }
        });
    }

    @Override
    public int getItemCount() {
        return optionArrayList.size();
    }

    public class ViwHolder extends RecyclerView.ViewHolder {
        TextView variationoptiostv;
        RelativeLayout layoutMethods;
        LinearLayout linearLayout;

        public ViwHolder(@NonNull View itemView) {
            super(itemView);
            variationoptiostv = itemView.findViewById(R.id.variationoptiostv);
            layoutMethods = itemView.findViewById(R.id.layout_methods);
            linearLayout = itemView.findViewById(R.id.testing);
        }
    }
}
