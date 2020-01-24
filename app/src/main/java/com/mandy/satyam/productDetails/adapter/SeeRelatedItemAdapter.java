package com.mandy.satyam.productDetails.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.mandy.satyam.productDetails.onClick;
import com.mandy.satyam.productList.GetProductList;
import com.mandy.satyam.R;
import com.wang.avi.AVLoadingIndicatorView;
import java.util.ArrayList;

public class SeeRelatedItemAdapter extends RecyclerView.Adapter<SeeRelatedItemAdapter.ViewHolder> {

    Context context;

    ArrayList<GetProductList.Datum> arrayList = new ArrayList<>();

    public onClick clike;

    public void setOnClick(onClick clik) {
        clike = clik;
    }

    public SeeRelatedItemAdapter(Context context) {
        this.context = context;
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

        /*viewHolder.txtName.setText(arrayList.get(i).getTitle());
        viewHolder.txtPrice.setText(Config.GET_RUPPESS_SYMBOL + " " + arrayList.get(i).getSpecialPrice());

        Glide.with(context).load(Config.GET_PRODUCT_IMAGE + arrayList.get(i).getImage()).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                viewHolder.avLoadingIndicatorView.setVisibility(View.GONE);
                return false;
            }
        }).into(viewHolder.imageView);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clike.GetId(arrayList.get(i).getId().toString());
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return 5;
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
