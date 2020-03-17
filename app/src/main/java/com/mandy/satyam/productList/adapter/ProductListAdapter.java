package com.mandy.satyam.productList.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.mandy.satyam.homeFragment.response.Categoriesroducts;
import com.mandy.satyam.productDetails.IF.product_id_IF;
import com.mandy.satyam.productDetails.ProductDetailsActivity;
import com.mandy.satyam.R;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;


public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

    Context context;
    String tok;
    ArrayList<Categoriesroducts.Datum.Image> imageArrayList;
    ArrayList<Categoriesroducts.Datum> datumArrayList;
    product_id_IF product_id_if;

    public void ProductListAdapter(product_id_IF product_id_if) {
        this.product_id_if = product_id_if;
    }

    public ProductListAdapter(Context context, ArrayList<Categoriesroducts.Datum> datumArrayList1) {
        this.context = context;
        this.datumArrayList = datumArrayList1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.custom_products, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {

        if (datumArrayList.get(i).getImages().size()>=1)
        {
            Glide.with(context).load(datumArrayList.get(i).getImages().get(0).getSrc()).placeholder(R.drawable.ic_satyamplaceholder).into(viewHolder.imageView);
        }else {
            Glide.with(context).load("").placeholder(R.drawable.ic_satyamplaceholder).into(viewHolder.imageView);
        }
        String cat = datumArrayList.get(i).getName().substring(0, 1);
        String small = datumArrayList.get(i).getName().toLowerCase().substring(1);
        viewHolder.txtProductName.setText(cat + small);
        viewHolder.txtPrice.setText("₹" + datumArrayList.get(i).getPrice());
        viewHolder.custom_actaulPrice.setPaintFlags(viewHolder.custom_actaulPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        viewHolder.custom_actaulPrice.setText("₹" + datumArrayList.get(i).getRegularPrice());
        viewHolder.ratingBar.setRating(Float.parseFloat(datumArrayList.get(i).getAverageRating()));
        viewHolder.txtRatingUser.setText("(" + datumArrayList.get(i).getAverageRating() + ")");
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product_id_if.getProductID(String.valueOf(datumArrayList.get(i).getId()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return datumArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView txtProductName, txtRatingUser, txtPrice,custom_actaulPrice;
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
        }
    }
}
