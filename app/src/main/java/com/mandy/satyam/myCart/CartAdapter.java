package com.mandy.satyam.myCart;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mandy.satyam.R;
import com.mandy.satyam.utils.Config;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    Context context;

    public CartAdapter(Context context) {
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.custom_cart, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Spinner spinner;
        Button btnDelete;
        ImageView imageView;
        TextView txtName, txtPrice, txtStock, txtColor, txtSize;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            spinner = itemView.findViewById(R.id.spinnner);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            txtName = itemView.findViewById(R.id.txtName);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtStock = itemView.findViewById(R.id.txtStock);
            imageView = itemView.findViewById(R.id.imageView);
            txtColor = itemView.findViewById(R.id.txtColor);
            txtSize = itemView.findViewById(R.id.txtSize);
        }
    }
}
