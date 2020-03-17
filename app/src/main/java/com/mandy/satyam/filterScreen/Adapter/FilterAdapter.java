package com.mandy.satyam.filterScreen.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mandy.satyam.R;
import com.mandy.satyam.filterScreen.filterIF.FilterCateidIF;
import com.mandy.satyam.productList.response.SubCategory;

import java.util.ArrayList;

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.ViewHolder> {
    Context context;
    int checkedPosition = 0;
    ArrayList<SubCategory.Datum> arrayList = new ArrayList<>();
    FilterCateidIF filterCateidIF;

    public void FilterAdapter(FilterCateidIF filterCateidIF) {
        this.filterCateidIF = filterCateidIF;
    }

    public FilterAdapter(Context context, ArrayList<SubCategory.Datum> subcate) {
        this.context = context;
        this.arrayList = subcate;
    }

    @NonNull
    @Override
    public FilterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.custom_filter_design, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilterAdapter.ViewHolder holder, int position) {

        holder.brandname.setText(arrayList.get(position).getCategoryName());
        if (checkedPosition == position) {
            holder.checkBox.setChecked(true);
        } else {
            holder.checkBox.setChecked(false);
        }

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkedPosition >= 0)
                    notifyItemChanged(checkedPosition);
                checkedPosition = holder.getAdapterPosition();
                notifyItemChanged(checkedPosition);
                filterCateidIF.onSuccess(String.valueOf(arrayList.get(position).getCategoryId()));

            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView brandname;
        CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            brandname = itemView.findViewById(R.id.brandname);
            checkBox = itemView.findViewById(R.id.brandCheckBox);
        }
    }
}
