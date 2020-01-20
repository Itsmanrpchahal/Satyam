package com.mandy.satyam.opt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mandy.satyam.MainActivity;
import com.mandy.satyam.R;
import com.mukesh.OtpView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OTP_verify extends AppCompatActivity {


    @BindView(R.id.loginclose)
    ImageButton loginclose;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.logintext)
    TextView logintext;
    @BindView(R.id.user_number)
    TextView userNumber;
    @BindView(R.id.editnumber)
    TextView editnumber;
    @BindView(R.id.otp_view)
    OtpView otpView;
    @BindView(R.id.resendotp_tv)
    TextView resendotpTv;
    @BindView(R.id.verify_otp_button)
    Button verifyOtpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verify);
        ButterKnife.bind(this);

        lisenters();
    }

    private void lisenters() {
        loginclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        resendotpTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        verifyOtpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OTP_verify.this, MainActivity.class);
                intent.putExtra("token","1");
                startActivity(intent);
                finish();
            }
        });
    }
}
