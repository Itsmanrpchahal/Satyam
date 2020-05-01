package com.satyam.homeFragment.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.satyam.R;
import com.satyam.homeFragment.response.HomePageResponse;
import com.satyam.productList.ProductsActivity;

import java.util.ArrayList;

public class HomeProductAdapter extends RecyclerView.Adapter<HomeProductAdapter.ViewHolder> {

    Context context;
    ArrayList<HomePageResponse.Data.Section.CategoryProduct> categoryProducts = new ArrayList<>();
    String catID;
    String productType;


    public HomeProductAdapter(Context context, ArrayList<HomePageResponse.Data.Section.CategoryProduct> categoryProducts,String catID,String productType) {
        this.context = context;
        this.categoryProducts = categoryProducts;
        this.catID = catID;
        this.productType = productType;
    }

    @NonNull
    @Override
    public HomeProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.custom_products, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeProductAdapter.ViewHolder holder, int position) {
        String cat = categoryProducts.get(position).getProductName().substring(0,1);
        String small = categoryProducts.get(position).getProductName().toLowerCase().substring(1);
        Glide.with(context).load(categoryProducts.get(position).getProductImage().toString()).placeholder(R.drawable.ic_satyamplaceholder).into(holder.imageView);
        holder.textView.setText(cat+small);
        holder.ratingBar.setVisibility(View.GONE);
        holder.custom_productRating_number.setVisibility(View.GONE);

        if (categoryProducts.get(position).getProductRegularPrice()==null || categoryProducts.get(position).getProductPrice()==null || categoryProducts.get(position).getProductRegularPrice().equals("") || categoryProducts.get(position).getProductPrice().equals("") || categoryProducts.get(position).getProductRegularPrice().equals("null") || categoryProducts.get(position).getProductPrice().equals("null"))
        {
            holder.discount.setText("-0%");
            holder.custom_actaulPrice.setPaintFlags(holder.custom_actaulPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.custom_actaulPrice.setText("₹" + categoryProducts.get(position).getProductPrice());
            holder.savetext.setText("You save ₹0.0");
        }else {

            holder.custom_actaulPrice.setPaintFlags(holder.custom_actaulPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.custom_actaulPrice.setText("₹" + categoryProducts.get(position).getProductRegularPrice());
                Float discount = Float.valueOf(categoryProducts.get(position).getProductRegularPrice()) -  Float.valueOf(categoryProducts.get(position).getProductPrice());
                Float getDiscount = discount/Float.valueOf(categoryProducts.get(position).getProductRegularPrice());

                if (String.valueOf(discount).length()>=3)
                {
                    holder.savetext.setText("You save ₹"+String.valueOf(discount).substring(0,3));
                }else if (String.valueOf(discount).length()==1)
                {
                    holder.savetext.setText("You save ₹"+discount.toString().substring(0,1));
                }else if (String.valueOf(discount).length()<=2)
                {
                    holder.savetext.setText("You save ₹"+discount.toString().substring(0,2)+".00");
                }
                Float getFinalDiscount = getDiscount*100;
                if (String.valueOf(getFinalDiscount).length()>2)
                {
                    holder.discount.setText("-"+String.valueOf(getFinalDiscount).substring(0,2)+"%");
                }else {
                    holder.discount.setText("-"+String.valueOf(getFinalDiscount) +"%");
                }

        }


        if (!categoryProducts.get(position).getProductPrice().equals("") || categoryProducts.get(position).getProductPrice()!=null || !categoryProducts.get(position).getProductPrice().equals("null"))
        {
            holder.txtPrice.setText("₹" + categoryProducts.get(position).getProductPrice().toString());
        }

//        Collections.reverse(categoryProducts);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, ProductsActivity.class);
                intent.putExtra("isFrom","HomePage");
                intent.putExtra("cateID",catID);
                intent.putExtra("ProductType",productType);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryProducts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView,discount,custom_productRating_number,txtPrice,custom_actaulPrice,savetext;
        ImageView imageView;
        RatingBar ratingBar;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.custom_productName);
            discount = itemView.findViewById(R.id.discount);
            ratingBar = itemView.findViewById(R.id.custom_productRating);
            custom_productRating_number = itemView.findViewById(R.id.custom_productRating_number);
            imageView = itemView.findViewById(R.id.custom_productImage);
            txtPrice = itemView.findViewById(R.id.custom_productPrice);
            custom_actaulPrice = itemView.findViewById(R.id.custom_actaulPrice);
            savetext = itemView.findViewById(R.id.savetext);
        }
    }
}
