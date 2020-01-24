package com.mandy.satyam.opt;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.chaos.view.PinView;
import com.mandy.satyam.MainActivity;
import com.mandy.satyam.R;
import com.mandy.satyam.baseclass.BaseClass;
import com.mandy.satyam.baseclass.Constants;
import com.mandy.satyam.login.LoginActivity;
import com.stfalcon.smsverifycatcher.OnSmsCatchListener;
import com.stfalcon.smsverifycatcher.SmsVerifyCatcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OTP_verify extends BaseClass {


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

    @BindView(R.id.resendotp_tv)
    TextView resendotpTv;
    @BindView(R.id.verify_otp_button)
    Button verifyOtpButton;
    Intent intent;
    String phonenumber, otp;
    @BindView(R.id.otpView)
    PinView otpView;
    SmsVerifyCatcher smsVerifyCatcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verify);
        ButterKnife.bind(this);
        intent = getIntent();
        if (intent != null) {
            phonenumber = intent.getStringExtra("phonenumber");
            otp = intent.getStringExtra("OTP");
            userNumber.setText("+" + phonenumber);
        }
        lisenters();
    }

    private void lisenters() {

        smsVerifyCatcher = new SmsVerifyCatcher(OTP_verify.this, new OnSmsCatchListener<String>() {
            @Override
            public void onSmsCatch(String message) {

                String code = parseCode(message);
                Log.d("code", code);
                otpView.setText(code);
            }
        });

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

        editnumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OTP_verify.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        verifyOtpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (otp.equals(otpView.getText().toString()))
                {
                    setStringVal(Constants.LOGIN_STATUS,"login");
                    Intent intent = new Intent(OTP_verify.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    otpView.setLineColor(getResources().getColor(R.color.red));
                    otpView.setText("");
                }
            }
        });


    }

    private String parseCode(String message) {
        Pattern p = Pattern.compile("\\b\\d{6}\\b");
        Matcher m = p.matcher(message);
        String code = "";
        while (m.find()) {
            code = m.group(0);
        }
        return code;
    }
}
