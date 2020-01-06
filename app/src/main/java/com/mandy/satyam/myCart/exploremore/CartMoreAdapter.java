package com.mandy.satyam.myCart.exploremore;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.mandy.satyam.R;
import com.mandy.satyam.myCart.MyCartActivity;
import com.mandy.satyam.utils.Config;
import com.mandy.satyam.productList.ProductListActivity;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

public class CartMoreAdapter extends RecyclerView.Adapter<CartMoreAdapter.ViewHolder> {

    Context context;
    public CartMoreAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.custom_cart_more, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        AVLoadingIndicatorView avLoadingIndicatorView;
        TextView txtProducName, txtProductRatingNumber, txtPrice;
        Button btnAddtoCart;
        RatingBar ratingBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.custom_productImage);
            avLoadingIndicatorView = itemView.findViewById(R.id.avi);
            txtProducName = itemView.findViewById(R.id.custom_producName);
            txtProductRatingNumber = itemView.findViewById(R.id.custom_productRating_number);
            txtPrice = itemView.findViewById(R.id.custom_productPrice);
            btnAddtoCart = itemView.findViewById(R.id.custom_productDilvery);
            ratingBar = itemView.findViewById(R.id.custom_productRating);
        }
    }
}
