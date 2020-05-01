package com.satyam.termsandcondition;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.satyam.R;
import com.satyam.controller.Controller;
import com.satyam.utils.Util;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

public class TermsActivity extends AppCompatActivity implements Controller.PrivacyTD {

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
    Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);
        ButterKnife.bind(this);
        dialog = Util.showDialog(this);
        dialog.show();
        controller = new Controller(this);
        controller.setPrivacyTD();
        type = getIntent().getStringExtra("T");

        init();


//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);

        textView.setText("Terms & Condition");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void init() {
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


    @Override
    public void onSuccess(Response<PrivacyTD> privacyTDResponse) {
        if (privacyTDResponse.isSuccessful())
        {
            dialog.dismiss();
            String td = privacyTDResponse.body().getData().getTermsAndConditions();
            textTerms.setText(Html.fromHtml(td));
        }
    }

    @Override
    public void onError(String error) {
        dialog.dismiss();
        Toast.makeText(this, ""+error, Toast.LENGTH_SHORT).show();
    }
}
