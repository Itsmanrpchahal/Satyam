package com.mandy.satyam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mandy.satyam.filterScreen.response.FilterResponse;
import com.mandy.satyam.mainIF.getProductName;
import com.mandy.satyam.productList.ProductsActivity;

import java.util.ArrayList;

public class SearchItemAdapter extends RecyclerView.Adapter<SearchItemAdapter.ViewHolder> {

    Context context;
    ArrayList<String> products = new ArrayList<>();
    getProductName getProductName;
    ArrayList<FilterResponse.Datum> search = new ArrayList<>();

    public void SearchItemAdapter(com.mandy.satyam.mainIF.getProductName getProductName) {
        this.getProductName = getProductName;
    }

    public SearchItemAdapter(Context context1, ArrayList<String> productname, ArrayList<FilterResponse.Datum> searchProducts) {
        context = context1;
        products = productname;
        search = searchProducts;
    }

    @NonNull
    @Override
    public SearchItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.searchitem, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchItemAdapter.ViewHolder holder, int position) {

        holder.textView.setText(products.get(position));
//        Glide.with(context).load(search.get(position).getImages().get(0).getSrc()).placeholder(R.drawable.ic_satyamplaceholder).into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getProductName.getName(products.get(position), String.valueOf(search.get(position).getId()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.searchitemname);
            imageView = itemView.findViewById(R.id.productimage);
        }
    }
}
