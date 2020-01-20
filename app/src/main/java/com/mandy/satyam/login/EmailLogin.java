package com.mandy.satyam.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mandy.satyam.MainActivity;
import com.mandy.satyam.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EmailLogin extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_login);
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
                Intent intent = new Intent(EmailLogin.this, MainActivity.class);
                intent.putExtra("token", "1");
                startActivity(intent);
                finish();
            }
        });


    }
}
