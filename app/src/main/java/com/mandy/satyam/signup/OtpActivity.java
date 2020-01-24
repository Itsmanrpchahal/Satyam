package com.mandy.satyam.signup;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.mandy.satyam.R;

public class OtpActivity extends AppCompatActivity {

    Button btnVerify;
    EditText edtOtp;
    ImageButton back;
    String phone;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        btnVerify = findViewById(R.id.btnVerify);
        edtOtp = findViewById(R.id.edtOtp);
        back = findViewById(R.id.back_otp);

        listerners();

    }

    private void listerners() {

        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OtpActivity.this, CreatePasswordScreen.class));

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OtpActivity.this,SignupActivity.class));
                finish();
            }
        });
    }


}
