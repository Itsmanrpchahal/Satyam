package com.mandy.satyam.productDetails.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mandy.satyam.R;
import com.mandy.satyam.productDetails.response.ProductDetailResponse;

import java.util.ArrayList;
import java.util.List;

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ViewHolder> {
    Context context;
    ArrayList<String> attributes;

    int pos;

    // interface to refresh the list
    private OnItemClick itemClick;

    public interface OnItemClick {
        void onItemClick(int position, int id);
    }

    public void setOnItemClick(OnItemClick listener) {
        itemClick = listener;
    }


    public ColorAdapter(Context context, ArrayList<String> array_color) {
        this.context = context;
        this.attributes = array_color;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.custom_size, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

      /*  viewHolder.textView.setText(arrayList.get(i).getSize());

        if (pos == i) {
            viewHolder.textView.setBackgroundResource(R.drawable.custom_size);
            viewHolder.textView.setTextColor(context.getResources().getColor(R.color.black));
            if (itemClick != null) {
                int po = i;
                if (po != RecyclerView.NO_POSITION) {
                    itemClick.onItemClick(po, arrayList.get(i).getId());
                }
            }
        } else {
            viewHolder.textView.setBackgroundResource(R.drawable.custom_size_close);
        }

        viewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pos = i;
                notifyDataSetChanged();

            }
        });*/

        viewHolder.textView.setBackgroundColor(Color.parseColor("#"+attributes.get(i)));
    }

    @Override
    public int getItemCount() {
        return attributes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textView);
        }
    }
}
