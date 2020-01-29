package com.mandy.satyam.dashboardproducts;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mandy.satyam.R;
import com.mandy.satyam.baseclass.BaseClass;
import com.mandy.satyam.baseclass.Constants;
import com.mandy.satyam.controller.Controller;
import com.mandy.satyam.filterScreen.FilterActivity;
import com.mandy.satyam.homeFragment.response.Categoriesroducts;
import com.mandy.satyam.productList.adapter.ProductListAdapter;
import com.mandy.satyam.utils.SpacesItemDecoration;
import com.mandy.satyam.utils.Util;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

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
    ArrayList<Categoriesroducts.Datum.Image> images = new ArrayList<>();
    ArrayList<Categoriesroducts.Datum> datumArrayList = new ArrayList<Categoriesroducts.Datum>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        ButterKnife.bind(this);
        progressDialog = Util.showDialog(ProductsActivity.this);
        controller = new Controller(ProductsActivity.this);
        back.setVisibility(View.GONE);


        intent = getIntent();
        if (intent != null) {
            ProductType = intent.getStringExtra("ProductType");
            catID = intent.getStringExtra("cateID");
            textView.setText(ProductType);
            if (Util.isOnline(ProductsActivity.this) != false) {
                progressDialog.show();
                controller.setRelatedPrducts("ck_548941c93a5e2e01b319e36ec5a5c242f7e3c7a5", getStringVal(Constants.CONSUMER_SECRET), catID);
            } else {
                Util.showToastMessage(ProductsActivity.this, "No Internet connection", getResources().getDrawable(R.drawable.ic_nointernet));
            }
        }

        listerners();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);


        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerProduct.setLayoutManager(layoutManager);
        ProductListAdapter adapter = new ProductListAdapter(this, images,datumArrayList);
        recyclerProduct.setAdapter(adapter);
        recyclerProduct.addItemDecoration(new SpacesItemDecoration(5));
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

        for (int i=0;i<homePageResponseResponse.body().getData().size();i++)
        {

            if (homePageResponseResponse.body().getData().get(i).getImages().size()>=1)
            {
                Categoriesroducts.Datum.Image image = homePageResponseResponse.body().getData().get(i).getImages().get(0);
                images.add(image);

            }

            //Categoriesroducts.Datum productname = homePageResponseResponse.body().getData().get(i).getName();
            datumArrayList.add(homePageResponseResponse.body().getData().get(i));
        }


        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerProduct.setLayoutManager(layoutManager);
        ProductListAdapter adapter = new ProductListAdapter(this,images,datumArrayList);
        recyclerProduct.setAdapter(adapter);
        recyclerProduct.addItemDecoration(new SpacesItemDecoration(5));
    }

    @Override
    public void onError(String error) {
        progressDialog.dismiss();
        Util.showToastMessage(ProductsActivity.this, error, getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));

    }
}
