package com.mandy.satyam.termsandcondition;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.widget.TextView;

import com.mandy.satyam.controller.Controller;
import com.mandy.satyam.R;
import com.mandy.satyam.utils.ProgressBarClass;
import com.mandy.satyam.utils.SharedToken;
import com.mandy.satyam.utils.Snack;

import retrofit2.Response;

public class TermsActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView textView, txterms;

    Dialog dialog;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);
        dialog = ProgressBarClass.showProgressDialog(this);
        type = getIntent().getStringExtra("T");

        init();


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);

        if (type.equalsIgnoreCase("T")) {
            textView.setText("Privacy policy");
        } else {
            textView.setText("Terms & Condition");
        }


    }

    private void init() {
        toolbar = (Toolbar) findViewById(R.id.tooolbar);
        textView = (TextView) findViewById(R.id.textView);
        txterms = (TextView) findViewById(R.id.textTerms);
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}
