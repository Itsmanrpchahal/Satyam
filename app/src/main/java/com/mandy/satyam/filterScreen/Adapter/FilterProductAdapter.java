package com.mandy.satyam.filterScreen.Adapter;

import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mandy.satyam.R;
import com.mandy.satyam.filterScreen.response.FilterResponse;
import com.mandy.satyam.homeFragment.response.Categoriesroducts;
import com.mandy.satyam.productDetails.IF.product_id_IF;
import com.mandy.satyam.productList.ProductsActivity;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

public class FilterProductAdapter extends RecyclerView.Adapter<FilterProductAdapter.ViewHolder> {

    Context context;
    ArrayList<FilterResponse.Datum> arrayList = new ArrayList<>();
    ArrayList<FilterResponse.Datum.Image> filterImages = new ArrayList<>();
    product_id_IF product_id_if;

    public void FilterProductAdapter(product_id_IF product_id_if) {
        this.product_id_if = product_id_if;
    }

    public FilterProductAdapter(Context context, ArrayList<FilterResponse.Datum.Image> images, ArrayList<FilterResponse.Datum> filterDatumArraylist) {
        this.context = context;
        this.filterImages = images;
        this.arrayList = filterDatumArraylist;
    }

    @NonNull
    @Override
    public FilterProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.custom_products, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilterProductAdapter.ViewHolder holder, int position) {

        try {
            holder.custom_actaulPrice.setPaintFlags(holder.custom_actaulPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.custom_actaulPrice.setText("₹" + arrayList.get(position).getRegularPrice());
            Glide.with(context).load(arrayList.get(position).getImages().get(0).getSrc().toString()).placeholder(R.drawable.ic_satyamplaceholder).into(holder.imageView);
            String cat = arrayList.get(position).getName().substring(0, 1);
            String small = arrayList.get(position).getName().toLowerCase().substring(1);
            holder.txtProductName.setText(cat + small);
            holder.txtPrice.setText("₹" + arrayList.get(position).getPrice());
            holder.ratingBar.setRating(Float.parseFloat(arrayList.get(position).getAverageRating()));
            holder.txtRatingUser.setText("(" + arrayList.get(position).getAverageRating() + ")");

            if (arrayList.get(position).getRegularPrice()==null || arrayList.get(position).getPrice()==null || arrayList.get(position).getRegularPrice().equals("") || arrayList.get(position).getPrice().equals(""))
            {
                holder.discount.setText("-0%");
            }else {
                Float discount = Float.valueOf(arrayList.get(position).getRegularPrice()) -  Float.valueOf(arrayList.get(position).getPrice());
                Float getDiscount = discount/Float.valueOf(arrayList.get(position).getRegularPrice());


                if (String.valueOf(discount).length()>=4)
                {
                    holder.savetext.setText("You save ₹"+String.valueOf(discount).substring(0,4));
                }else if (String.valueOf(discount).length()==1)
                {
                    holder.savetext.setText("You save ₹"+discount.toString().substring(0,1));
                }else if (String.valueOf(discount).length()<=2)
                {
                    holder.savetext.setText("You save ₹"+discount.toString().substring(0,2)+".00");
                }else {
                    holder.savetext.setText("You save ₹"+discount.toString().substring(0,2));
                }

                Float getFinalDiscount = getDiscount*100;
                if (String.valueOf(getFinalDiscount).length()>2)
                {
                    holder.discount.setText("-"+String.valueOf(getFinalDiscount).substring(0,2)+"%");
                }else {
                    holder.discount.setText("-"+String.valueOf(getFinalDiscount) +"%");
                }
            }
        }catch (Exception e){

        }



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product_id_if.getProductID(String.valueOf(arrayList.get(position).getId()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView txtProductName, txtRatingUser, txtPrice,custom_actaulPrice,savetext,discount;
        RatingBar ratingBar;
        AVLoadingIndicatorView avLoadingIndicatorView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.custom_productImage);
            txtProductName = itemView.findViewById(R.id.custom_productName);
            txtRatingUser = itemView.findViewById(R.id.custom_productRating_number);
            txtPrice = itemView.findViewById(R.id.custom_productPrice);
            avLoadingIndicatorView = itemView.findViewById(R.id.avi);
            ratingBar = itemView.findViewById(R.id.custom_productRating);
            custom_actaulPrice = itemView.findViewById(R.id.custom_actaulPrice);
            savetext = itemView.findViewById(R.id.savetext);
            discount = itemView.findViewById(R.id.discount);
        }
    }
}
