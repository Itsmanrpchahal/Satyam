package com.mandy.satyam.myCart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.mandy.satyam.R;
import com.mandy.satyam.utils.Util;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    Context context;
    int count1 ;


    public CartAdapter(Context context) {
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.cart_items, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.add_item_from_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (1<=15)
                {
                    count1 =0;
                    count1 = 1+1;
                    viewHolder.count_tv.setText(String.valueOf(count1));
                }else {
                    Util.showToastMessage(context,"15 is maximum",context.getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
                }
            }
        });


        viewHolder.remove_item_from_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (10 >= 2) {
                    count1 = 0;
                    count1 = 10 - 1;
                    viewHolder.count_tv.setText(String.valueOf(count1));

                } else {
                    Util.showToastMessage(context,"1 is minimum",context.getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageButton remove_item_from_cart,add_item_from_cart,cart_item_cancel;
        TextView count_tv,product_name,product_category,product_price,product_discount;
        RoundedImageView cart_product_image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            remove_item_from_cart = itemView.findViewById(R.id.remove_item_from_cart);
            count_tv = itemView.findViewById(R.id.count_tv);
            add_item_from_cart = itemView.findViewById(R.id.add_item_from_cart);
            cart_product_image = itemView.findViewById(R.id.cart_product_image);
            product_name = itemView.findViewById(R.id.product_name);
            product_category = itemView.findViewById(R.id.product_category);
            product_price = itemView.findViewById(R.id.product_price);
            product_discount = itemView.findViewById(R.id.product_discount);
            cart_item_cancel = itemView.findViewById(R.id.cart_item_cancel);
        }
    }
}
