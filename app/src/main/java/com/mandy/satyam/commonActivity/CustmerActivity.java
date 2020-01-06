package com.mandy.satyam.commonActivity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mandy.satyam.GetMeesageApi;
import com.mandy.satyam.R;
import com.mandy.satyam.controller.Controller;
import com.mandy.satyam.utils.CheckInternet;
import com.mandy.satyam.utils.ProgressBarClass;
import com.mandy.satyam.utils.SharedToken;
import com.mandy.satyam.utils.Snack;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Response;

public class CustmerActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView textView;
    TextView textEmail1, textPhone1, textPhone2;
    Dialog dialog;

    @BindView(R.id.edtComment)
    EditText editText;
    @BindView(R.id.btnMail)
    Button btnMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custmer);
        ButterKnife.bind(this);
        dialog = ProgressBarClass.showProgressDialog(this);


        init();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        textView.setText("Customer Support");

        textPhone1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + textPhone1.getText().toString()));
                startActivity(intent);
            }
        });


        textPhone2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + textPhone2.getText().toString()));
                startActivity(intent);
            }
        });

    }

    private void init() {
        toolbar = (Toolbar) findViewById(R.id.tooolbar);
        textView = (TextView) findViewById(R.id.textView);
        textPhone1 = (TextView) findViewById(R.id.txtPhone1);
        textPhone2 = (TextView) findViewById(R.id.txtPhone2);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    @OnClick(R.id.btnMail)
    public void onViewClicked() {
        finish();
    }
}
