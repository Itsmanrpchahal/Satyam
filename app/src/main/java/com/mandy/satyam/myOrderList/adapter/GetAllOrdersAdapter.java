package com.mandy.satyam.myOrderList.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mandy.satyam.R;
import com.mandy.satyam.myOrderList.GetOrderDetail;
import com.mandy.satyam.myOrderList.response.GetAllOrders;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

public class GetAllOrdersAdapter extends RecyclerView.Adapter<GetAllOrdersAdapter.ViewHolder> {

    Context context;
    ArrayList<GetAllOrders.Datum> arrayList = new ArrayList<>();

    public GetAllOrdersAdapter(Context context, ArrayList<GetAllOrders.Datum> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public GetAllOrdersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.custom_your_order, parent, false);



        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GetAllOrdersAdapter.ViewHolder holder, int position) {

        Log.d("IMAGE",arrayList.get(position).getLineItems().get(0).getProductImage());
        Glide.with(context).load(arrayList.get(position).getLineItems().get(0).getProductImage().toString()).placeholder(R.drawable.ic_satyamplaceholder).into(holder.imageView);
        holder.textDispatch.setText(arrayList.get(position).getStatus());
        holder.textDate.setText(arrayList.get(position).getCurrencySymbol()+arrayList.get(position).getTotal());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GetOrderDetail.class);
                intent.putExtra("orderID",arrayList.get(position).getId().toString());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textDispatch, textDate;
        ImageView imageView;
        AVLoadingIndicatorView avLoadingIndicatorView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textDispatch = itemView.findViewById(R.id.txtDispatch);
            textDate = itemView.findViewById(R.id.txtData);
            imageView = itemView.findViewById(R.id.custom_image);
            avLoadingIndicatorView = itemView.findViewById(R.id.avi);

        }
    }
}
