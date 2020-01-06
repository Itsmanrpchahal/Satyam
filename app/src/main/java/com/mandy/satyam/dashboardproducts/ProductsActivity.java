package com.mandy.satyam.dashboardproducts;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.mandy.satyam.R;
import com.mandy.satyam.utils.SharedToken;
import com.mandy.satyam.utils.SpacesItemDecoration;
import com.mandy.satyam.commonActivity.NoInternetActivity;
import com.mandy.satyam.controller.Controller;
import com.mandy.satyam.productList.GetProductList;
import com.mandy.satyam.productList.adapter.ProductListAdapter;
import com.mandy.satyam.utils.CheckInternet;
import com.mandy.satyam.utils.ProgressBarClass;
import com.mandy.satyam.utils.Snack;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

public class ProductsActivity extends AppCompatActivity  {
    @BindView(R.id.tooolbar)
    Toolbar toolbar;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.recyclerProduct)
    RecyclerView recyclerProduct;
    Dialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        ButterKnife.bind(this);
        dialog = ProgressBarClass.showProgressDialog(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);


        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerProduct.setLayoutManager(layoutManager);
        ProductListAdapter adapter = new ProductListAdapter(this);
        recyclerProduct.setAdapter(adapter);
        recyclerProduct.addItemDecoration(new SpacesItemDecoration(5));
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
