package com.satyam.productDetails.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.satyam.R;

import java.util.ArrayList;

public class SizeAdapter extends RecyclerView.Adapter<SizeAdapter.ViewHolder> {

    Context context;
    ArrayList<String> attributes;
    private int selectedPosition = -1;
    // interface to refresh the list
    private OnItemClick itemClick;

    public interface OnItemClick {
        void onItemClick(int position, int id);
    }

    public void setOnItemClick(OnItemClick listener) {
        itemClick = listener;
    }

    public SizeAdapter(Context context, ArrayList<String> attributes) {
        this.context = context;
        this.attributes = attributes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.custom_size1, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        if (selectedPosition == i) {
            viewHolder.itemView.setSelected(true); //using selector drawable
            viewHolder.layoutMethods.setBackgroundResource(R.drawable.custome_theme_selected_bg);
        } else {
            viewHolder.itemView.setSelected(false);
            viewHolder.layoutMethods.setBackgroundResource(R.drawable.grey_border1);
        }


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedPosition >= 0)
                    notifyItemChanged(selectedPosition);
                selectedPosition = viewHolder.getAdapterPosition();
                notifyItemChanged(selectedPosition);
            }
        });
        viewHolder.textView.setText(attributes.get(i));
    }

    @Override
    public int getItemCount() {
        return attributes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        RelativeLayout layoutMethods;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.colortext);
            layoutMethods = itemView.findViewById(R.id.layout_methods);
        }
    }
}
