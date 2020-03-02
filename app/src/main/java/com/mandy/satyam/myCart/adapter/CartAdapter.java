package com.mandy.satyam.myCart.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.mandy.satyam.R;
import com.mandy.satyam.controller.Controller;
import com.mandy.satyam.myCart.IF.AddCartQuantity;
import com.mandy.satyam.myCart.IF.RemoveCartIF;
import com.mandy.satyam.myCart.IF.RemoveCartItem;
import com.mandy.satyam.myCart.response.GetCartProducts;
import com.mandy.satyam.utils.Util;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    Context context;
    int count1 ;
    ArrayList<GetCartProducts.Datum> arrayList =new ArrayList<>();
    AddCartQuantity addCartQuantity;
    RemoveCartItem removeCartItem;
    RemoveCartIF removeCartIF;
    Controller controller;
    String token;

    public void CartAdapter(RemoveCartIF removeCartIF) {
        this.removeCartIF = removeCartIF;
    }

    public void CartAdapter(AddCartQuantity addCartQuantity) {
        this.addCartQuantity = addCartQuantity;
    }

    public void CartAdapter(RemoveCartItem removeCartItem) {
        this.removeCartItem = removeCartItem;
    }

    public CartAdapter(Context context, ArrayList<GetCartProducts.Datum> getCartProductsArrayList) {
        this.context = context;
        this.arrayList = getCartProductsArrayList;

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

        GetCartProducts.Datum datum = arrayList.get(i);
        Glide.with(context).load(datum.getProductImage()).placeholder(R.drawable.ic_satyamplaceholder).into(viewHolder.cart_product_image);
        viewHolder.product_name.setText(datum.getProductName());
        viewHolder.count_tv.setText(String.valueOf(datum.getQuantity()));
        viewHolder.product_category.setVisibility(View.GONE);
        viewHolder.product_price.setText("₹"+datum.getProductPrice());

        viewHolder.add_item_from_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        count1 = 0;
                        count1 = 1+arrayList.get(i).getQuantity();
                        arrayList.get(i).setQuantity(count1);
                        if (arrayList.get(i).getProduct_pending_qty()>1)
                        {
                            addCartQuantity.onSuccess(String.valueOf(datum.getQuantity()),datum.getCartId());
                            viewHolder.count_tv.setText(String.valueOf(datum.getQuantity()));
                        }else {
                            Util.showToastMessage(context,"Out of Stock",context.getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
                        }


            }
        });


        viewHolder.remove_item_from_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (datum.getQuantity() >= 2) {
                    count1 = 0;
                    count1 = arrayList.get(i).getQuantity()-1;
                    arrayList.get(i).setQuantity(count1);
                    addCartQuantity.onSuccess(String.valueOf(datum.getQuantity()),datum.getCartId());
                    viewHolder.count_tv.setText(String.valueOf(datum.getQuantity()));

                } else {
                    Util.showToastMessage(context,"1 is minimum",context.getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
                }
            }
        });

        viewHolder.cart_item_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeCartIF.cartID(datum.getCartId(),i,datum.getQuantity(), Float.parseFloat(datum.getProductPrice()));
            }
        });

    }

    public int calculateTotal(){
        int total = 0;
        for(GetCartProducts.Datum vanzator: arrayList){
            total+=  vanzator.getQuantity();
        }
        return total;
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
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
