package com.satyam.termsandcondition;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.satyam.R;
import com.satyam.controller.Controller;
import com.satyam.utils.Util;

import retrofit2.Response;

public class PrivacyPolicay extends AppCompatActivity implements Controller.PrivacyTD {

    ImageButton backprivacy;
    TextView privacytext;
    Dialog dialog;
    Controller controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policay);
        dialog = Util.showDialog(this);
        dialog.show();
        controller = new Controller(this);
        controller.setPrivacyTD();
        backprivacy = findViewById(R.id.backprivacy);
        backprivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        privacytext = findViewById(R.id.privacytext);

    }

    @Override
    public void onSuccess(Response<PrivacyTD> privacyTDResponse) {
        dialog.dismiss();
        if (privacyTDResponse.isSuccessful())
        {
            String privacy = privacyTDResponse.body().getData().getPrivacyPolicy();
            privacytext.setText(Html.fromHtml(privacy));
        }
    }

    @Override
    public void onError(String error) {
dialog.dismiss();
        Toast.makeText(this, ""+error, Toast.LENGTH_SHORT).show();
    }
}
