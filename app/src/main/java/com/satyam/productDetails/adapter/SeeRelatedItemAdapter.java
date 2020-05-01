package com.satyam.productDetails.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.satyam.productDetails.IF.product_id_IF;
import com.satyam.productDetails.onClick;
import com.satyam.productDetails.response.ProductDetailResponse;
import com.satyam.R;
import com.wang.avi.AVLoadingIndicatorView;
import java.util.ArrayList;

public class SeeRelatedItemAdapter extends RecyclerView.Adapter<SeeRelatedItemAdapter.ViewHolder> {

    Context context;

    ArrayList<ProductDetailResponse.Data.RelatedId> relatedID = new ArrayList<>();
    product_id_IF product_id_if;
    public onClick clike;

    public void ProductListAdapter(product_id_IF product_id_if) {
        this.product_id_if = product_id_if;
    }


    public SeeRelatedItemAdapter(Context context, ArrayList<ProductDetailResponse.Data.RelatedId> relatedIDs) {
        this.context = context;
        this.relatedID = relatedIDs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.custom_simlar, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {

        Glide.with(context).load(relatedID.get(i).getProductImage().toString()).placeholder(R.drawable.ic_satyamplaceholder).into(viewHolder.imageView);
        viewHolder.txtName.setText(relatedID.get(i).getProductName());
        viewHolder.txtPrice.setText("₹"+relatedID.get(i).getProductPrice());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product_id_if.getProductID(String.valueOf(relatedID.get(i).getProductId()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return relatedID.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        AVLoadingIndicatorView avLoadingIndicatorView;
        TextView txtName, txtPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            avLoadingIndicatorView = itemView.findViewById(R.id.avi);
            txtName = itemView.findViewById(R.id.productName);
            txtPrice = itemView.findViewById(R.id.productPrice);
        }
    }
}
