package com.mandy.satyam.dashboardproducts;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mandy.satyam.R;
import com.mandy.satyam.baseclass.BaseClass;
import com.mandy.satyam.baseclass.Constants;
import com.mandy.satyam.controller.Controller;
import com.mandy.satyam.filterScreen.FilterActivity;
import com.mandy.satyam.homeFragment.response.Categoriesroducts;
import com.mandy.satyam.productDetails.IF.product_id_IF;
import com.mandy.satyam.productDetails.ProductDetailsActivity;
import com.mandy.satyam.productDetails.response.ProductDetailResponse;
import com.mandy.satyam.productList.adapter.ProductListAdapter;
import com.mandy.satyam.utils.SpacesItemDecoration;
import com.mandy.satyam.utils.Util;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

//Manpreet Work
public class ProductsActivity extends BaseClass implements Controller.RelatedPrducts {
    @BindView(R.id.tooolbar)
    Toolbar toolbar;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.recyclerProduct)
    RecyclerView recyclerProduct;
    Dialog dialog;
    Intent intent;
    String ProductType;
    @BindView(R.id.filter_bt)
    ImageButton filterBt;
    @BindView(R.id.search_bt)
    ImageButton searchBt;
    @BindView(R.id.back)
    ImageButton back;
    String token, catID;
    Controller controller;
    Dialog progressDialog;
    int pageCount = 1;
    int headerList;
    ArrayList<Categoriesroducts.Datum.Image> images = new ArrayList<>();
    ArrayList<Categoriesroducts.Datum> datumArrayList = new ArrayList<Categoriesroducts.Datum>();
    @BindView(R.id.seemorebt)
    Button seemorebt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        ButterKnife.bind(this);
        progressDialog = Util.showDialog(ProductsActivity.this);
        controller = new Controller((Controller.RelatedPrducts) ProductsActivity.this);
        back.setVisibility(View.GONE);


        listerners();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        intent = getIntent();
        if (intent != null) {
            ProductType = intent.getStringExtra("ProductType");
            catID = intent.getStringExtra("cateID");
            String cat = ProductType.substring(0, 1);
            String small = ProductType.toLowerCase().substring(1);
            textView.setText(cat + small);
            if (Util.isOnline(ProductsActivity.this) != false) {
                progressDialog.show();
                controller.setRelatedPrducts(getStringVal(Constants.CONSUMER_KEY), getStringVal(Constants.CONSUMER_SECRET), catID, String.valueOf(pageCount));
            } else {
                Util.showToastMessage(ProductsActivity.this, "No Internet connection", getResources().getDrawable(R.drawable.ic_nointernet));
            }
        }

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerProduct.setLayoutManager(layoutManager);
        ProductListAdapter adapter = new ProductListAdapter(this, images, datumArrayList);
        recyclerProduct.setAdapter(adapter);
        recyclerProduct.addItemDecoration(new SpacesItemDecoration(5));
        adapter.ProductListAdapter(new product_id_IF() {
            @Override
            public void getProductID(String id) {
                Intent intent = new Intent(ProductsActivity.this, ProductDetailsActivity.class);
                intent.putExtra("productID", id);
                startActivity(intent);
            }
        });
    }


    private void listerners() {
        filterBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductsActivity.this, FilterActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onSucessRelated(Response<Categoriesroducts> homePageResponseResponse) {
        progressDialog.dismiss();
        seemorebt.setVisibility(View.VISIBLE);
        headerList = Integer.parseInt(homePageResponseResponse.headers().get("X-WP-TotalPages"));


        for (int i = 0; i < homePageResponseResponse.body().getData().size(); i++) {

            if (homePageResponseResponse.body().getData().get(i).getImages().size() >= 1) {
                Categoriesroducts.Datum.Image image = homePageResponseResponse.body().getData().get(i).getImages().get(0);
                images.add(image);
            }
            //Categoriesroducts.Datum productname = homePageResponseResponse.body().getData().get(i).getName();
            datumArrayList.add(homePageResponseResponse.body().getData().get(i));
        }


        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerProduct.setLayoutManager(layoutManager);
        ProductListAdapter adapter = new ProductListAdapter(this, images, datumArrayList);
        recyclerProduct.setAdapter(adapter);
        adapter.ProductListAdapter(new product_id_IF() {
            @Override
            public void getProductID(String id) {
                Intent intent = new Intent(ProductsActivity.this, ProductDetailsActivity.class);
                intent.putExtra("productID", id);
                startActivity(intent);
            }
        });

        seemorebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                if (pageCount < headerList) {
                    pageCount = 1 + pageCount;
                    if (Util.isOnline(ProductsActivity.this) != false) {
                        progressDialog.show();
                        controller.setRelatedPrducts(getStringVal(Constants.CONSUMER_KEY), getStringVal(Constants.CONSUMER_SECRET), catID, String.valueOf(pageCount));
                    } else {
                        Util.showToastMessage(ProductsActivity.this, "No Internet connection", getResources().getDrawable(R.drawable.ic_nointernet));
                    }
                } else {
                    seemorebt.setVisibility(View.GONE);
                }

            }
        });

        if (pageCount == headerList) {
            seemorebt.setVisibility(View.GONE);
        } else {
            seemorebt.setVisibility(View.VISIBLE);
        }


    }


    @Override
    public void onError(String error) {
        progressDialog.dismiss();
        Util.showToastMessage(ProductsActivity.this, error, getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));

    }
}
