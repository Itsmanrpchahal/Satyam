package com.mandy.satyam.myOrderList.myorderdetails;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mandy.satyam.R;
import com.mandy.satyam.myOrderList.myorderdetails.returnProduct.ReturnProduct;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SubOrderDeatial extends AppCompatActivity {

    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.search_bt)
    ImageButton searchBt;
    @BindView(R.id.filter_bt)
    ImageButton filterBt;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.productName)
    TextView productName;
    @BindView(R.id.productRating)
    RatingBar productRating;
    @BindView(R.id.deliveredtext)
    TextView deliveredtext;
    @BindView(R.id.product_detial)
    TextView productDetial;
    @BindView(R.id.txtDiliverData)
    TextView txtDiliverData;
    @BindView(R.id.txtPackageSigned)
    TextView txtPackageSigned;
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
    @BindView(R.id.ratethisproduct_tv)
    Button ratethisproductTv;
    @BindView(R.id.returnthisproduct_tv)
    Button returnthisproductTv;
    Dialog dialog, rateDailog;
    @BindView(R.id.back)
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_order_deatial);
        ButterKnife.bind(this);

        filterBt.setVisibility(View.GONE);
        searchBt.setVisibility(View.GONE);
        textView.setText("My Order Detail");

        listerners();
    }

    private void listerners() {
        ratethisproductTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rateDailog = new Dialog(SubOrderDeatial.this);
                Window window = rateDailog.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                rateDailog.setContentView(R.layout.rating_dialog);
                rateDailog.setCancelable(true);
                rateDailog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                rateDailog.show();
            }
        });
        returnthisproductTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubOrderDeatial.this, ReturnProduct.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


}
