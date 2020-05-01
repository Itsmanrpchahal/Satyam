package com.satyam.myOrderList.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.satyam.R;
import com.satyam.myOrderList.response.GetOrderDetail;

import java.util.ArrayList;

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.ViewHolder> {
    Context context;
    ArrayList<com.satyam.myOrderList.response.GetOrderDetail.Data.LineItem> arrayList = new ArrayList<>();
    String getPayment,stataus;


    public OrderDetailAdapter(Context context, ArrayList<GetOrderDetail.Data.LineItem> lineItems, String paymentMethodTitle, String status) {
        this.context = context;
        this.arrayList = lineItems;
        this.getPayment = paymentMethodTitle;
        this.stataus = status;
    }

    @NonNull
    @Override
    public OrderDetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.custom_your_order1, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailAdapter.ViewHolder holder, int position) {
        holder.orderid.setText(arrayList.get(position).getName());
        holder.paymentmethod.setText(getPayment);
        holder.statusbt.setText(stataus);
        Glide.with(context).load(arrayList.get(position).getProductImage()).placeholder(R.drawable.ic_satyamplaceholder).into(holder.orderImage);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView orderImage;
        TextView orderid,paymentmethod;
        Button statusbt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            orderImage = itemView.findViewById(R.id.orderImage);
            orderid = itemView.findViewById(R.id.orderid);
            paymentmethod = itemView.findViewById(R.id.paymentmethod);
            statusbt =itemView.findViewById(R.id.statusbt);
        }
    }
}
