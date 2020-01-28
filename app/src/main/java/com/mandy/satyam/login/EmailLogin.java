package com.mandy.satyam.login;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.mandy.satyam.MainActivity;
import com.mandy.satyam.R;
import com.mandy.satyam.baseclass.BaseClass;
import com.mandy.satyam.baseclass.Constants;
import com.mandy.satyam.controller.Controller;
import com.mandy.satyam.login.model.Login;
import com.mandy.satyam.login.model.LoginCheck;
import com.mandy.satyam.utils.Util;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

public class EmailLogin extends BaseClass implements Controller.LoginCheck ,Controller.Login{

    @BindView(R.id.loginclose)
    ImageButton loginclose;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.logintext)
    TextView logintext;
    @BindView(R.id.phone_number_et)
    EditText phoneNumberEt;
    @BindView(R.id.phn_no_layout)
    LinearLayout phnNoLayout;
    @BindView(R.id.loginwithnumber_tv)
    TextView loginwithnumber_tv;
    @BindView(R.id.continue_bt)
    Button continueBt;
    String type = "email";
    Dialog dialog;
    Controller controller;
    String email;
    @BindView(R.id.password_et)
    EditText passwordEt;
    @BindView(R.id.pass_layout)
    LinearLayout passLayout;
    @BindView(R.id.password_layout)
    RelativeLayout passwordLayout;
    @BindView(R.id.login_bt)
    Button loginBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_login);
        ButterKnife.bind(this);
        controller = new Controller((Controller.LoginCheck)this,(Controller.Login)this);
        dialog = Util.showDialog(EmailLogin.this);
        lisenters();
    }

    private void lisenters() {

        phoneNumberEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passLayout.setVisibility(View.GONE);
                passwordLayout.setVisibility(View.GONE);
                loginBt.setVisibility(View.GONE);
                continueBt.setVisibility(View.VISIBLE);
            }
        });

        loginclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        loginwithnumber_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmailLogin.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        continueBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = phoneNumberEt.getText().toString();
                if (!email.equals("")) {

                    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        phoneNumberEt.setFocusable(true);
                        phoneNumberEt.setError("Enter valid email");
                    } else if (Util.isOnline(EmailLogin.this) != false) {
                        dialog.show();
                        controller.setLoginCheck(email, type);
                    } else {
                        Util.showToastMessage(EmailLogin.this, "No Internet connection", getResources().getDrawable(R.drawable.ic_nointernet));
                    }
                } else {
                    phoneNumberEt.setFocusable(true);
                    phoneNumberEt.setError("Enter email");
                }
            }
        });

        loginBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass = passwordEt.getText().toString();

                if (TextUtils.isEmpty(passwordEt.getText().toString()))
                {
                    passwordEt.setError("Enter Password");
                    passwordEt.setFocusable(true);
                }else if (Util.isOnline(EmailLogin.this) != false) {
                    dialog.show();
                    controller.setLogin(email,"email",pass);
                }else {
                    Util.showToastMessage(EmailLogin.this, "No Internet connection", getResources().getDrawable(R.drawable.ic_nointernet));
                }
            }
        });
    }

    @Override
    public void onSuccessLoginCheck(Response<LoginCheck> loginCheckResponse) {
        dialog.dismiss();
        if (loginCheckResponse.body().getStatus() == 200) {
            passwordLayout.setVisibility(View.VISIBLE);
            passLayout.setVisibility(View.VISIBLE);
            continueBt.setVisibility(View.GONE);
            loginBt.setVisibility(View.VISIBLE);
        } else if (loginCheckResponse.body().getStatus() == 401) {
            phoneNumberEt.setFocusable(true);
            phoneNumberEt.setError("Email not exist");
        } else {
            Util.showToastMessage(EmailLogin.this, loginCheckResponse.body().getMessage(), getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
        }

    }

    @Override
    public void onsetLogin(Response<Login> loginResponse) {
        dialog.dismiss();
        if (loginResponse.body().getStatus()==200)
        {
            setStringVal(Constants.LOGIN_STATUS,"login");
            setStringVal(Constants.EMAIL,loginResponse.body().getData().getEmail());
            setStringVal(Constants.FIRSTNAME,loginResponse.body().getData().getFirstname());
            setStringVal(Constants.LASTNAME,loginResponse.body().getData().getLastname());
            setStringVal(Constants.USER_ID,loginResponse.body().getData().getUserId().toString());
            setStringVal(Constants.AVATAR,loginResponse.body().getData().getAvatar());
            setStringVal(Constants.USERTOKEN,loginResponse.body().getData().getToken());
            Intent intent = new Intent(EmailLogin.this, MainActivity.class);
            startActivity(intent);
            finish();
        }else {
            Util.showToastMessage(EmailLogin.this, loginResponse.body().getMessage(), getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
        }
    }

    @Override
    public void onError(String error) {
        dialog.dismiss();
        Util.showToastMessage(EmailLogin.this, error, getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
    }
}
