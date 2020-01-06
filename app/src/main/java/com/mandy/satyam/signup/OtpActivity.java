package com.mandy.satyam.signup;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mandy.satyam.retrofit.ApiInterface;
import com.mandy.satyam.MainActivity;
import com.mandy.satyam.utils.CheckInternet;
import com.mandy.satyam.utils.ProgressBarClass;
import com.mandy.satyam.R;
import com.mandy.satyam.retrofit.ServiceGenerator;
import com.mandy.satyam.utils.SharedToken;
import com.mandy.satyam.utils.Snack;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtpActivity extends AppCompatActivity {

    Button button;
    EditText edtOtp;
    String phone;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        button = findViewById(R.id.btnVerify);
        edtOtp = findViewById(R.id.edtOtp);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OtpActivity.this, MainActivity.class));
                finish();
            }
        });

    }


}
