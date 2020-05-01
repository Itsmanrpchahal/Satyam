package com.satyam.opt;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.chaos.view.PinView;
import com.satyam.MainActivity;
import com.satyam.R;
import com.satyam.baseclass.BaseClass;
import com.satyam.baseclass.Constants;
import com.satyam.controller.Controller;
import com.satyam.login.LoginActivity;
import com.satyam.login.model.ClearCart;
import com.satyam.login.model.Login;
import com.satyam.login.model.LoginCheck;
import com.satyam.login.model.SocialLoginResponse;
import com.satyam.productDetails.ProductDetailsActivity;
import com.satyam.utils.Util;
import com.stfalcon.smsverifycatcher.OnSmsCatchListener;
import com.stfalcon.smsverifycatcher.SmsVerifyCatcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

public class OTP_verify extends BaseClass implements Controller.LoginCheck, Controller.Login ,Controller.ClearCart,Controller.SocailLogin{


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
    Controller controller;
    Dialog dialog;
    String productID,isFrom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verify);
        ButterKnife.bind(this);
        dialog = Util.showDialog(OTP_verify.this);
        intent = getIntent();
        if (intent != null) {

            if (intent.getStringExtra("isFrom")!=null)
            {
                if (intent.getStringExtra("isFrom").equals("ProductDetail"))
                {
                    productID = intent.getStringExtra("productID");
                    isFrom = intent.getStringExtra("isFrom");
                    phonenumber = intent.getStringExtra("phonenumber");
                    otp = intent.getStringExtra("OTP");
                    userNumber.setText("+" + phonenumber);
                }
            }
            else {
                phonenumber = intent.getStringExtra("phonenumber");
                otp = intent.getStringExtra("OTP");

                userNumber.setText("+" + phonenumber);
            }

        }
        controller = new Controller((Controller.LoginCheck) this, (Controller.Login) OTP_verify.this,(Controller.ClearCart)this,(Controller.SocailLogin)this);
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
                if (Util.isOnline(OTP_verify.this) != false) {
                    dialog.show();
                    controller.setLoginCheck(phonenumber, "phone");
                    new CountDownTimer(30000, 1000) {

                        public void onTick(long millisUntilFinished) {
                            resendotpTv.setText("seconds remaining: " + millisUntilFinished / 1000);
                            resendotpTv.setClickable(false);
                            //here you can have your logic to set text to edittext
                        }

                        public void onFinish() {
                            resendotpTv.setText("Resend OTP");
                        }

                    }.start();

                } else {
                    Util.showToastMessage(OTP_verify.this, "No Internet connection", getResources().getDrawable(R.drawable.ic_nointernet));
                }
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

                if (otp.equals(otpView.getText().toString())) {
                    dialog.show();
                    controller.setLogin(phonenumber,"phone",otp);
                } else {
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

    @Override
    public void onSuccessLoginCheck(Response<LoginCheck> loginCheckResponse) {
        if (loginCheckResponse.isSuccessful())
        {
            if (loginCheckResponse.body().getStatus() == 200) {
                dialog.dismiss();
                otp = String.valueOf(loginCheckResponse.body().getData().getOtp());
                Util.showToastMessage(OTP_verify.this, "OTP sent", getResources().getDrawable(R.drawable.ic_present_to_all_black_24dp));
            }
        }else {
            Util.showToastMessage(OTP_verify.this,loginCheckResponse.body().getMessage(),getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
        }

    }

    @Override
    public void onsetLogin(Response<Login> loginResponse) {
        dialog.dismiss();
        if (loginResponse != null) {
            if (loginResponse.body().getStatus() == 200) {
                setStringVal(Constants.LOGIN_STATUS,"login");
                setStringVal(Constants.CONSUMER_KEY_LOGIN,loginResponse.body().getData().getConsumerKey());
                setStringVal(Constants.CONSUMER_SECRET_LOGIN,loginResponse.body().getData().getConsumerSecret());
                setStringVal(Constants.AVATAR,loginResponse.body().getData().getAvatar());
                setStringVal(Constants.FIRSTNAME,loginResponse.body().getData().getFirstname());
                setStringVal(Constants.LASTNAME,loginResponse.body().getData().getLastname());
                setStringVal(Constants.MOBILE,loginResponse.body().getData().getPhone());
                setStringVal(Constants.USER_ID, String.valueOf(loginResponse.body().getData().getUserId()));
                setStringVal(Constants.USERTOKEN,loginResponse.body().getData().getToken());
                setStringVal(Constants.LOGIN_STATUS, "login");
                if (intent.getStringExtra("isFrom")!=null)
                {
                    if (intent.getStringExtra("isFrom").equals("ProductDetail"))
                    {
                        Intent intent = new Intent(OTP_verify.this, ProductDetailsActivity.class);
                        intent.putExtra("productID", productID);
                        startActivity(intent);
                        finish();
                    }
                }
                else {
                    Intent intent = new Intent(OTP_verify.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }else {
            Util.showToastMessage(this,loginResponse.body().getMessage(),getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
        }
    }

    @Override
    public void onSuccessClearCart(Response<ClearCart> response) {
        if (response.isSuccessful())
        {
            if (response.body().getStatus()==200)
            {

                if (intent.getStringExtra("isFrom")!=null)
                {
                    if (intent.getStringExtra("isFrom").equals("ProductDetail"))
                    {
                        Intent intent = new Intent(OTP_verify.this, ProductDetailsActivity.class);
                        intent.putExtra("productID", productID);
                        startActivity(intent);
                        finish();
                    }
                }
                else {
                    Intent intent = new Intent(OTP_verify.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }

//            controller.setLogin(phonenumber, "phone", otp);
            }else {
                Log.d("clearcarterror",response.body().getMessage());
                Util.showToastMessage(OTP_verify.this,response.body().getMessage(),getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
            }
        }

    }

    @Override
    public void onSuccessSocailLogin(Response<SocialLoginResponse> socialLoginResponse) {

    }

    @Override
    public void onError(String error) {
        dialog.dismiss();
        Util.showToastMessage(OTP_verify.this, error, getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
    }
}
