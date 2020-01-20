package com.mandy.satyam.myOrderList;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mandy.satyam.R;
import com.mandy.satyam.utils.ProgressBarClass;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyOrderListActivity extends AppCompatActivity {

    @BindView(R.id.tooolbar)
    Toolbar toolbar;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.txtTime)
    TextView txtTime;
    @BindView(R.id.txtFilter)
    TextView txtFilter;
    @BindView(R.id.rel)
    RelativeLayout rel;
    @BindView(R.id.recyclerOrder)
    RecyclerView recyclerOrder;
    Dialog dialog;
    @BindView(R.id.filter_bt)
    ImageButton filterBt;
    @BindView(R.id.search_bt)
    ImageButton searchBt;
    @BindView(R.id.back)
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order_list);
        ButterKnife.bind(this);
        dialog = ProgressBarClass.showProgressDialog(this);

        filterBt.setVisibility(View.GONE);
        searchBt.setVisibility(View.GONE);
        back.setVisibility(View.GONE);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        textView.setText("My Order List");
        setRecyclerView();

    }


    private void setRecyclerView() {
        YourOrderAdapter adapter = new YourOrderAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerOrder.setLayoutManager(linearLayoutManager);
        recyclerOrder.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
