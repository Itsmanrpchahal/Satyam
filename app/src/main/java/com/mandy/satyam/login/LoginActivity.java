package com.mandy.satyam.login;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mandy.satyam.signup.SignupActivity;
import com.mandy.satyam.forgotPassword.ForgotPasswordActivity;
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

public class LoginActivity extends AppCompatActivity {
    EditText edtPhone, edtPassword;
    Button btnLogin;
    TextView txtSignup;
    CheckBox checkBox;
    TextView textForgot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();


            }
        });

        textForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });


        txtSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignupActivity.class));
                finish();
            }
        });



    }


    // method for get all id's
    private void init() {
        btnLogin = (Button) findViewById(R.id.btnLogin);
        txtSignup = (TextView) findViewById(R.id.txtSignup);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        edtPhone = (EditText) findViewById(R.id.edtPhone);
        checkBox = (CheckBox) findViewById(R.id.passwordshow);
        textForgot = (TextView) findViewById(R.id.txtForgot);
    }

}
