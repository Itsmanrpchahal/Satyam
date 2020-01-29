package com.mandy.satyam.productList;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mandy.satyam.R;
import com.mandy.satyam.productList.adapter.ProductListAdapter;
import com.mandy.satyam.utils.SpacesItemDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductListActivity extends AppCompatActivity {

    @BindView(R.id.tooolbar)
    Toolbar toolbar;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.recyclerProduct)
    RecyclerView recyclerProduct;
    Dialog dialog;
    @BindView(R.id.search_bt)
    ImageButton searchBt;
    @BindView(R.id.filter_bt)
    ImageButton filterBt;
    @BindView(R.id.back)
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        ButterKnife.bind(this);

        back.setVisibility(View.GONE);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        textView.setText("Product list");


        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerProduct.setLayoutManager(layoutManager);
//        ProductListAdapter adapter = new ProductListAdapter(this, images);
//        recyclerProduct.setAdapter(adapter);
//        recyclerProduct.addItemDecoration(new SpacesItemDecoration(10));


    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
