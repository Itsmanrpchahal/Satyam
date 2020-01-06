package com.mandy.satyam.searchActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.mandy.satyam.R;
import com.mandy.satyam.commonActivity.NoInternetActivity;
import com.mandy.satyam.controller.Controller;
import com.mandy.satyam.dashboardproducts.ProductsActivity;
import com.mandy.satyam.utils.CheckInternet;
import com.mandy.satyam.utils.ProgressBarClass;
import com.mandy.satyam.utils.SharedToken;
import com.mandy.satyam.utils.Snack;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.tooolbar)
    Toolbar toolbar;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.autoComplete)
    AutoCompleteTextView autoComplete;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        dialog = ProgressBarClass.showProgressDialog(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        textView.setText("Search items");

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
