package com.mandy.satyam.myOrderList.myorderdetails;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mandy.satyam.R;
import com.mandy.satyam.myOrderList.TotoalProductAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderDetailsActivity extends AppCompatActivity {

    Dialog dialog;
    @BindView(R.id.tooolbar)
    Toolbar tooolbar;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.search_bt)
    ImageButton searchBt;
    @BindView(R.id.filter_bt)
    ImageButton filterBt;
    @BindView(R.id.numberoforders_tv)
    TextView numberofordersTv;
    @BindView(R.id.totalorders)
    TextView totalorders;
    @BindView(R.id.orderRecycler)
    RecyclerView orderRecycler;
    TotoalProductAdapter adapter;
    @BindView(R.id.tv_OrderName)
    TextView tvOrderName;
    @BindView(R.id.tv_SubPrice)
    TextView tvSubPrice;
    @BindView(R.id.tv_tax)
    TextView tvTax;
    @BindView(R.id.tv_tax1)
    TextView tvTax1;
    @BindView(R.id.tv_devlerStatus)
    TextView tvDevlerStatus;
    @BindView(R.id.tv_delivery1)
    TextView tvDelivery1;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    @BindView(R.id.tv_totalPrice)
    TextView tvTotalPrice;
    @BindView(R.id.layout_main)
    RelativeLayout layoutMain;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.ordernumber)
    TextView ordernumber;
    @BindView(R.id.productRating)
    RatingBar productRating;
    @BindView(R.id.deliveredtext)
    TextView deliveredtext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        ButterKnife.bind(this);
        tvSubPrice.setFocusable(true);

        filterBt.setVisibility(View.GONE);
        searchBt.setVisibility(View.GONE);
        back.setVisibility(View.GONE);
        setSupportActionBar(tooolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        textView.setText("My Order Details");

        setData();
    }

    private void setData() {
        adapter = new TotoalProductAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        orderRecycler.setLayoutManager(linearLayoutManager);
        orderRecycler.setAdapter(adapter);
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
