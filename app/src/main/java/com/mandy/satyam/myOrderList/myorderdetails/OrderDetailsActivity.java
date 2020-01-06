package com.mandy.satyam.myOrderList.myorderdetails;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mandy.satyam.GetMeesageApi;
import com.mandy.satyam.R;
import com.mandy.satyam.commonActivity.NoInternetActivity;
import com.mandy.satyam.controller.Controller;
import com.mandy.satyam.utils.CheckInternet;
import com.mandy.satyam.utils.Config;
import com.mandy.satyam.utils.ProgressBarClass;
import com.mandy.satyam.utils.SharedToken;
import com.mandy.satyam.utils.Snack;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Response;

public class OrderDetailsActivity extends AppCompatActivity {

    @BindView(R.id.tooolbar)
    Toolbar toolbar;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.productName)
    TextView productName;
    @BindView(R.id.productRating)
    RatingBar productRating;
    @BindView(R.id.txtDiliverData)
    TextView txtDiliverData;
    @BindView(R.id.txtPackageSigned)
    TextView txtPackageSigned;
    @BindView(R.id.ratingbar)
    RatingBar ratingbar;
    @BindView(R.id.edtComment)
    EditText edtComment;
    @BindView(R.id.btnRate)
    Button btnRate;
    Dialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        ButterKnife.bind(this);
        dialog = ProgressBarClass.showProgressDialog(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        textView.setText("My Order Details");


    }

    @OnClick(R.id.btnRate)
    public void onViewClicked() {
       finish();
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
