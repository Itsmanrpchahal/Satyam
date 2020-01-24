package com.mandy.satyam.signup;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.mandy.satyam.MainActivity;
import com.mandy.satyam.R;
import com.mandy.satyam.login.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreatePasswordScreen extends AppCompatActivity {

    @BindView(R.id.back_password)
    ImageButton backPassword;
    @BindView(R.id.passowrd_et)
    EditText passowrdEt;
    @BindView(R.id.Cpassowrd_et)
    EditText CpassowrdEt;
    @BindView(R.id.submit)
    Button submit;
    @BindView(R.id.linear)
    LinearLayout linear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_password_screen);
        ButterKnife.bind(this);

        listeners();
    }

    private void listeners() {

        backPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreatePasswordScreen.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
