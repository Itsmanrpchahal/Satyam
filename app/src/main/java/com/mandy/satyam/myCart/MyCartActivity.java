package com.mandy.satyam.myCart;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mandy.satyam.commonActivity.NoInternetActivity;
import com.mandy.satyam.addressActivity.AddressActivity;
import com.mandy.satyam.controller.Controller;
import com.mandy.satyam.GetMeesageApi;
import com.mandy.satyam.myCart.exploremore.CartMoreAdapter;
import com.mandy.satyam.myCart.exploremore.GetExploreMoreData;
import com.mandy.satyam.myCart.topitems.GetTopDataApi;
import com.mandy.satyam.myCart.topitems.TopItemsAdapter;
import com.mandy.satyam.productDetails.apis.GetAddToCart;
import com.mandy.satyam.R;
import com.mandy.satyam.utils.SpacesItemDecoration;
import com.mandy.satyam.utils.CheckInternet;
import com.mandy.satyam.utils.Config;
import com.mandy.satyam.utils.ProgressBarClass;
import com.mandy.satyam.utils.SharedToken;
import com.mandy.satyam.utils.Snack;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Response;

public class MyCartActivity extends AppCompatActivity {

    @BindView(R.id.tooolbar)
    Toolbar toolbar;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.txtItems)
    TextView txtItems;
    @BindView(R.id.txtPrice)
    TextView txtPrice;
    @BindView(R.id.btnProced)
    Button btnProced;
    @BindView(R.id.recyclerCart)
    RecyclerView recyclerCart;
    @BindView(R.id.recyclerMore)
    RecyclerView recyclerMore;
    @BindView(R.id.recyclerTopPickup)
    RecyclerView recyclerTopPickup;
    @BindView(R.id.btnProced2)
    Button btnProced2;
    @BindView(R.id.scrool_view)
    NestedScrollView scroolView;

    Dialog dialog;
    FragmentManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);
        ButterKnife.bind(this);
        dialog = ProgressBarClass.showProgressDialog(this);
        manager = getSupportFragmentManager();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        textView.setText("My Cart");

        ((NestedScrollView) findViewById(R.id.scrool_view)).post(new Runnable() {
            public void run() {
                ((NestedScrollView) findViewById(R.id.scrool_view)).fullScroll(View.FOCUS_UP);
            }
        });


        CartMoreAdapter adapter = new CartMoreAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerMore.setLayoutManager(linearLayoutManager);
        recyclerMore.setAdapter(adapter);
        recyclerMore.addItemDecoration(new SpacesItemDecoration(5));


        //set top liste items

        TopItemsAdapter adapter2 = new TopItemsAdapter(this);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerTopPickup.setLayoutManager(linearLayoutManager2);
        recyclerTopPickup.setAdapter(adapter2);
        recyclerTopPickup.addItemDecoration(new SpacesItemDecoration(5));


        // set data into cart

        CartAdapter adapter3 = new CartAdapter(this);
        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerCart.setLayoutManager(linearLayoutManager3);
        recyclerCart.setAdapter(adapter3);
        recyclerCart.addItemDecoration(new SpacesItemDecoration(10));


    }

    @OnClick({R.id.btnProced, R.id.btnProced2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnProced:
                sendData();
                break;
            case R.id.btnProced2:
                sendData();
                break;
        }
    }


    private void sendData() {
        Intent intent = new Intent(this, AddressActivity.class);
        intent.putExtra("Cid", "0");
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
