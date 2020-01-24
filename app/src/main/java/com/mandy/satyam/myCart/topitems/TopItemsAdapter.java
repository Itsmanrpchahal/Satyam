package com.mandy.satyam.myCart.topitems;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.mandy.satyam.R;
import com.wang.avi.AVLoadingIndicatorView;


public class TopItemsAdapter extends RecyclerView.Adapter<TopItemsAdapter.ViewHolder> {

    Context context;


    public TopItemsAdapter(Context context) {
        this.context = context;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.layout_top_item, viewGroup, false);
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
