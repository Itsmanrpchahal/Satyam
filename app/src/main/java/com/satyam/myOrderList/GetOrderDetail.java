package com.satyam.myOrderList;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.satyam.R;
import com.satyam.baseclass.BaseClass;
import com.satyam.baseclass.Constants;
import com.satyam.controller.Controller;
import com.satyam.myOrderList.adapter.OrderDetailAdapter;
import com.satyam.utils.Util;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

public class GetOrderDetail extends BaseClass implements Controller.GetOrderDetails {

    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    RoundedImageView orderImage;
    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.email)
    TextView email;
    @BindView(R.id.gender)
    TextView gender;
    @BindView(R.id.BName)
    TextView BName;
    @BindView(R.id.BCompanyName)
    TextView BCompanyName;
    @BindView(R.id.CAddress)
    TextView CAddress;
    @BindView(R.id.SName)
    TextView SName;
    @BindView(R.id.SCompanyName)
    TextView SCompanyName;
    @BindView(R.id.SAddress)
    TextView SAddress;
    @BindView(R.id.orderRecyclers)
    RecyclerView orderRecyclers;
    Controller controller;
    Dialog dialog;
    Intent intent;
    String orderID;
    ArrayList<com.satyam.myOrderList.response.GetOrderDetail.Data.LineItem> lineItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_order_detail);
        ButterKnife.bind(this);
        intent = getIntent();
        dialog = Util.showDialog(this);
        dialog.show();
        controller = new Controller(this);
        if (intent!=null)
        {
            orderID = intent.getStringExtra("orderID");
            controller.setGetOrderDetails(orderID,getStringVal(Constants.CONSUMER_KEY),getStringVal(Constants.CONSUMER_SECRET),"get_order");
        }
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
    public void onSuccessGetOrderDetail(Response<com.satyam.myOrderList.response.GetOrderDetail> response) {
        if (response.isSuccessful())
        {
            dialog.dismiss();
            if (response.body().getStatus()==200)
            {
                if (response.body().getData().getLineItems().get(0).getProductImage()!=null || !response.body().getData().getLineItems().get(0).getProductImage().equals(null) || !response.body().getData().getLineItems().get(0).getProductImage().equals("null"))
                {
//                    Glide.with(this).load(response.body().getData().getLineItems().get(0).getProductImage()).placeholder(R.drawable.ic_satyamplaceholder).into(orderImage);
                }

//                orderid.setText("Order #"+response.body().getData().getId());
//                paymentmethod.setText(response.body().getData().getPaymentMethodTitle());
//                statusbt.setText(response.body().getData().getStatus());
                username.setText(getStringVal(Constants.FIRSTNAME)+" "+getStringVal(Constants.LASTNAME));
                if (response.body().getData().getBilling().getPhone()!=null)
                {
                    if(response.body().getData().getBilling().getPhone().contains(" "))
                    {
                        String[] mobile =response.body().getData().getBilling().getPhone().split(" ");
                        if(mobile.length==1)
                        {
                            String mob =mobile[0];
//                            edtAlternateno.setText(mob);
                        }else {
                            String num =mobile[1];
                            phone.setText(num);
                        }
                    }
                }
                email.setText(getStringVal(Constants.EMAIL));
                BName.setText(response.body().getData().getBilling().getFirstName()+" "+response.body().getData().getBilling().getLastName());
                BCompanyName.setText(response.body().getData().getBilling().getAddress1());
                CAddress.setText(response.body().getData().getBilling().getCity()+","+response.body().getData().getBilling().getCountry());
                SName.setText(response.body().getData().getShipping().getFirstName()+" "+response.body().getData().getShipping().getLastName());
                SCompanyName.setText(response.body().getData().getShipping().getAddress1());
                SAddress.setText(response.body().getData().getShipping().getCity()+","+response.body().getData().getShipping().getCountry());


                for (int i=0;i<response.body().getData().getLineItems().size();i++)
                {
                    com.satyam.myOrderList.response.GetOrderDetail.Data.LineItem getAllOrders = response.body().getData().getLineItems().get(i);
                    lineItems.add(getAllOrders);
                    OrderDetailAdapter adapter = new OrderDetailAdapter(GetOrderDetail.this,lineItems,response.body().getData().getPaymentMethodTitle(),response.body().getData().getStatus());
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
                    orderRecyclers.setLayoutManager(linearLayoutManager);
                    orderRecyclers.setAdapter(adapter);
                }
            }else {
                Util.showToastMessage(this,response.body().getMessage(),getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
            }
        }else {
            Util.showToastMessage(this,response.message(),getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
        }
    }

    @Override
    public void onError(String error) {
        dialog.dismiss();
        Util.showToastMessage(this,error,getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
    }
}
