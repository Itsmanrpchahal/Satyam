package com.satyam.myOrderList.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.satyam.R;
import com.satyam.myOrderList.GetOrderDetail;
import com.satyam.myOrderList.response.GetAllOrders;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.Calendar;

public class GetAllOrdersAdapter extends RecyclerView.Adapter<GetAllOrdersAdapter.ViewHolder> {

    Context context;
    ArrayList<GetAllOrders.Datum> arrayList = new ArrayList<>();
    Calendar cl;

    public GetAllOrdersAdapter(Context context, ArrayList<GetAllOrders.Datum> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public GetAllOrdersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.custom_your_order, parent, false);

cl =  Calendar.getInstance();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GetAllOrdersAdapter.ViewHolder holder, int position) {

        Log.d("IMAGE", arrayList.get(position).getLineItems().get(0).getProductImage());
        Glide.with(context).load(arrayList.get(position).getLineItems().get(0).getProductImage().toString()).placeholder(R.drawable.ic_satyamplaceholder).into(holder.imageView);
        holder.textDispatch.setText(arrayList.get(position).getStatus());


        holder.textDate.setText(arrayList.get(position).getCurrencySymbol() + arrayList.get(position).getTotal());

        holder.orderID.setText(arrayList.get(position).getStatus().toString());

        cl.setTimeInMillis(Long.parseLong(arrayList.get(position).getDate_created_miliseconds()));

        holder.orderdate.setText(cl.get(Calendar.DAY_OF_MONTH)+"/"+cl.get(Calendar.MONTH)+"/"+cl.get(Calendar.YEAR));
        holder.orderprice.setText("₹"+arrayList.get(position).getTotal());

        holder.viewbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GetOrderDetail.class);
                intent.putExtra("orderID", arrayList.get(position).getId().toString());
                context.startActivity(intent);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GetOrderDetail.class);
                intent.putExtra("orderID", arrayList.get(position).getId().toString());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textDispatch, textDate, orderID, orderdate,orderprice;
        ImageView imageView;
        AVLoadingIndicatorView avLoadingIndicatorView;
        Button viewbt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textDispatch = itemView.findViewById(R.id.txtDispatch);
            textDate = itemView.findViewById(R.id.txtData);
            imageView = itemView.findViewById(R.id.custom_image);
            avLoadingIndicatorView = itemView.findViewById(R.id.avi);
            orderID = itemView.findViewById(R.id.orderID);
            orderdate = itemView.findViewById(R.id.orderDate);
            orderprice = itemView.findViewById(R.id.orderprice);
            viewbt = itemView.findViewById(R.id.viewbt);

        }
    }
}
