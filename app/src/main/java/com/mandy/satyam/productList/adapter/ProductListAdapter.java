package com.mandy.satyam.productList.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mandy.satyam.homeFragment.response.Categoriesroducts;
import com.mandy.satyam.productDetails.ProductDetailsActivity;
import com.mandy.satyam.R;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;


public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

    Context context;
    String tok;
    ArrayList<Categoriesroducts.Datum.Image> imageArrayList;
    ArrayList<Categoriesroducts.Datum> datumArrayList;


    public ProductListAdapter(Context context, ArrayList<Categoriesroducts.Datum.Image> images,ArrayList<Categoriesroducts.Datum> datumArrayList1) {
        this.context = context;
        this.imageArrayList = images;
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

        Glide.with(context).load(imageArrayList.get(i).getSrc().toString()).placeholder(R.drawable.image_d).into(viewHolder.imageView);
        viewHolder.txtProductName.setText(datumArrayList.get(i).getName());
        viewHolder.txtPrice.setText("$"+datumArrayList.get(i).getPrice());
        viewHolder.ratingBar.setRating(Float.parseFloat(datumArrayList.get(i).getAverageRating()));
        viewHolder.txtRatingUser.setText("("+datumArrayList.get(i).getAverageRating());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    /*Intent intent = new Intent(context, ProductDetailsActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);*/

            }
        });

    }

    @Override
    public int getItemCount() {
        return imageArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView txtProductName, txtRatingUser, txtPrice;
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
        }
    }
}
