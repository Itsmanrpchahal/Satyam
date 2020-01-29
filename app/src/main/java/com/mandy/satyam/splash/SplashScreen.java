package com.mandy.satyam.splash;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mandy.satyam.KeysResponse;
import com.mandy.satyam.MainActivity;
import com.mandy.satyam.R;
import com.mandy.satyam.baseclass.BaseClass;
import com.mandy.satyam.baseclass.Constants;
import com.mandy.satyam.controller.Controller;
import com.mandy.satyam.utils.Util;

import retrofit2.Response;

public class SplashScreen extends BaseClass implements Controller.Keys {

    ImageView imageView;
    TextView textView;
    Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        controller = new Controller(this);
        controller.setKeys();
        imageView = findViewById(R.id.imageView);


    }

    @Override
    public void onSuccess(Response<KeysResponse> keysResponseResponse) {
            if (keysResponseResponse.body().getStatus()==200)
            {
                setStringVal(Constants.CONSUMER_KEY,keysResponseResponse.body().getData().getConsumerKey());
                setStringVal(Constants.CONSUMER_SECRET,keysResponseResponse.body().getData().getConsumerSecret());

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (!Constants.LOGIN_STATUS.equalsIgnoreCase(""))
                        {
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.putExtra("token", "login");
                            startActivity(intent);
                            finish();
                        }else {
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.putExtra("token", "logout");
                            startActivity(intent);
                            finish();
                        }
                    }
                }, 3000);
            }else {
                Util.showToastMessage(this,keysResponseResponse.body().getMessage(),getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
            }
    }

    @Override
    public void onError(String error) {
        Util.showToastMessage(this,error,getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));

    }
}
