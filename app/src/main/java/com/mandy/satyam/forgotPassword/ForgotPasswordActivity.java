package com.mandy.satyam.forgotPassword;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mandy.satyam.login.LoginActivity;
import com.mandy.satyam.R;

public class ForgotPasswordActivity extends AppCompatActivity {

    Button button;
    EditText edtPhone, edtOtp, edtPassword, edtCPassword;
    CheckBox checkBox;
    RelativeLayout relativeLayout;
    TextView txtLogin;
    String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        init();

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });


        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
    }

    private void init() {
        button = (Button) findViewById(R.id.btnLogin);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        edtCPassword = (EditText) findViewById(R.id.edtCPassword);
        edtPhone = (EditText) findViewById(R.id.edtPhone);
        edtOtp = (EditText) findViewById(R.id.edtOTp);
        checkBox = (CheckBox) findViewById(R.id.passwordshow);
        relativeLayout = (RelativeLayout) findViewById(R.id.relative);
        txtLogin = (TextView) findViewById(R.id.txtLogin);
    }


}
