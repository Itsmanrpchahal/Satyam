package com.mandy.satyam.myOrderList;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mandy.satyam.R;
import com.mandy.satyam.baseclass.BaseClass;
import com.mandy.satyam.baseclass.Constants;
import com.mandy.satyam.controller.Controller;
import com.mandy.satyam.myOrderList.adapter.GetAllOrdersAdapter;
import com.mandy.satyam.myOrderList.response.GetAllOrders;
import com.mandy.satyam.utils.Util;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

public class MyOrderListActivity extends BaseClass implements Controller.GetAllOrders {

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
    Controller controller;
    Dialog progressDialog;
    ArrayList<GetAllOrders.Datum> getAllOrders  = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order_list);
        ButterKnife.bind(this);
        progressDialog = Util.showDialog(this);
        progressDialog.show();
        controller = new Controller(this);
        controller.setGetAllOrders(getStringVal(Constants.USER_ID),getStringVal(Constants.CONSUMER_KEY),getStringVal(Constants.CONSUMER_SECRET),"my_orders");

        filterBt.setVisibility(View.GONE);
        searchBt.setVisibility(View.GONE);
        back.setVisibility(View.VISIBLE);

        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        textView.setText("My Order List");
        listerners();

    }

    private void listerners() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }




    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onSuccessGetAllOrders(Response<GetAllOrders> response) {
        progressDialog.dismiss();
        if (response.isSuccessful())
        {
            for (int i=0;i<response.body().getData().size();i++)
            {
                GetAllOrders.Datum datum = response.body().getData().get(i);
                getAllOrders.add(datum);
                GetAllOrdersAdapter adapter = new GetAllOrdersAdapter(this,getAllOrders);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
                recyclerOrder.setLayoutManager(linearLayoutManager);
                recyclerOrder.setAdapter(adapter);
            }
        }
    }

    @Override
    public void onError(String error) {
        progressDialog.dismiss();
        Util.showToastMessage(this,""+error,getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
    }
}
