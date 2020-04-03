package com.mandy.satyam.homeFragment.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mandy.satyam.R;
import com.mandy.satyam.productList.ProductsActivity;
import com.mandy.satyam.homeFragment.interfaces.IF_getCategoryID;
import com.mandy.satyam.homeFragment.response.HomePageResponse;

import java.util.ArrayList;

public class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.ViewHolder> {

    Context context;
    ArrayList<HomePageResponse.Data.Section> sections = new ArrayList<>();
    ArrayList<HomePageResponse.Data.Section.CategoryProduct> categoryProducts = new ArrayList<>();
    IF_getCategoryID if_getCategoryID;

    public void SectionAdapter(IF_getCategoryID if_getCategoryID) {
        this.if_getCategoryID = if_getCategoryID;
    }

    public SectionAdapter(Context context, ArrayList<HomePageResponse.Data.Section> sections) {
        this.context = context;
        this.sections = sections;
    }

    @NonNull
    @Override
    public SectionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.cutsomdashboardproduct, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onBindViewHolder(@NonNull SectionAdapter.ViewHolder holder, int position) {


        String cat = sections.get(position).getCategoryTitle().substring(0,1);
        String small = sections.get(position).getCategoryTitle().toLowerCase().substring(1);
        holder.categorynametext.setText(cat+small);


        GridLayoutManager linearLayout = new GridLayoutManager(context, 2);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        holder.products.setLayoutManager(linearLayout);
        categoryProducts.clear();

        for (int i=0;i<sections.get(position).getCategoryProducts().size();i++)
        {
            HomePageResponse.Data.Section.CategoryProduct categoryProduct = sections.get(position).getCategoryProducts().get(i);
            categoryProducts.add(categoryProduct);
            HomeProductAdapter adapter =new HomeProductAdapter(context,categoryProducts,sections.get(position).getCategoryId().toString(),sections.get(position).getCategoryTitle());
            holder.products.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            Log.d("Category",categoryProducts.get(0).getProductName());
        }



        holder.txtSeeNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductsActivity.class);
                intent.putExtra("isFrom","HomePage");
                intent.putExtra("cateID",sections.get(position).getCategoryId().toString());
                intent.putExtra("ProductType",sections.get(position).getCategoryTitle());
                context.startActivity(intent);
//               if_getCategoryID.catID(sections.get(position).getCategoryId().toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return sections.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView categorynametext;
        RecyclerView products;
        TextView txtSeeNew;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtSeeNew = itemView.findViewById(R.id.txtSeeNew);
            categorynametext = itemView.findViewById(R.id.categorynametext);
            products = itemView.findViewById(R.id.products);
        }
    }
}
