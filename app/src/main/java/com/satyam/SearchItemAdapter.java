package com.satyam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.satyam.mainIF.getProductName;
import com.satyam.productList.response.GetSearchProductsResponse;

import java.util.ArrayList;

public class SearchItemAdapter extends RecyclerView.Adapter<SearchItemAdapter.ViewHolder> {

    Context context;
    ArrayList<String> products = new ArrayList<>();
    getProductName getProductName;
    ArrayList<GetSearchProductsResponse.Datum> search = new ArrayList<>();
//    ArrayList<FilterResponse.Datum> search = new ArrayList<>();

    public void SearchItemAdapter(com.satyam.mainIF.getProductName getProductName) {
        this.getProductName = getProductName;
    }

//    public SearchItemAdapter(Context context1, ArrayList<String> productname, ArrayList<FilterResponse.Datum> searchProducts) {
//        context = context1;
//        products = productname;
//        search = searchProducts;
//    }


    public SearchItemAdapter(Context context, ArrayList<GetSearchProductsResponse.Datum> search) {
        this.context = context;
        this.search = search;
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


        holder.textView.setText(search.get(position).getName());
        if (!search.get(position).getCategory().equals(""))
        {
            holder.searchitemcategory.setText("in "+search.get(position).getCategory());
        }

//        if (search.get(position).getImages().size()>0)
//        {
            Glide.with(context).load(search.get(position).getImage()).placeholder(R.drawable.ic_satyamplaceholder).into(holder.imageView);
//        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getProductName.getName(String.valueOf(search.get(position)), String.valueOf(search.get(position).getId()));
//                Toast.makeText(context, ""+products.get(position)+"  "+search.get(position).getId(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return search.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView,searchitemcategory;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.searchitemname);
            imageView = itemView.findViewById(R.id.productimage);
            searchitemcategory = itemView.findViewById(R.id.searchitemcategory);
        }
    }
}
