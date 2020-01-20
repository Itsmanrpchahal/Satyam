package com.mandy.satyam.termsandcondition;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mandy.satyam.R;
import com.mandy.satyam.utils.ProgressBarClass;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TermsActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView txterms;

    Dialog dialog;
    String type;
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.search_bt)
    ImageButton searchBt;
    @BindView(R.id.filter_bt)
    ImageButton filterBt;
    @BindView(R.id.textTerms)
    TextView textTerms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);
        ButterKnife.bind(this);
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
        back.setVisibility(View.GONE);
        filterBt.setVisibility(View.GONE);
        searchBt.setVisibility(View.GONE);
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
