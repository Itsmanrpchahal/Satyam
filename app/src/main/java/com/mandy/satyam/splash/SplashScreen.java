package com.mandy.satyam.splash;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Handler;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;
import com.mandy.satyam.KeysResponse;
import com.mandy.satyam.MainActivity;
import com.mandy.satyam.R;
import com.mandy.satyam.baseclass.BaseClass;
import com.mandy.satyam.baseclass.Constants;
import com.mandy.satyam.controller.Controller;
import com.mandy.satyam.productDetails.ProductDetailsActivity;
import com.mandy.satyam.utils.Util;
import com.mandy.satyam.welcomeScreens.WelcomeScreens;

import dots.animation.textview.TextAndAnimationView;
import retrofit2.Response;

public class SplashScreen extends BaseClass implements Controller.Keys {

    ImageView imageView;
    TextView textView;
    Controller controller;
    private NetworkChangeReceiver receiver;
    private boolean isConnected = false;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        controller = new Controller(this);
        imageView = findViewById(R.id.imageView);
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        receiver = new NetworkChangeReceiver();
        registerReceiver(receiver, filter);
//        controller.setKeys("SBWoiw9UE9qx4NVLSHC9");
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public class NetworkChangeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(final Context context, final Intent intent) {

            Log.v("STATUS", "Receieved notification about network status");
            isNetworkAvailable(context);

        }


        private boolean isNetworkAvailable(Context context) {
            ConnectivityManager connectivity = (ConnectivityManager)
                    context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                NetworkInfo[] info = connectivity.getAllNetworkInfo();
                if (info != null) {
                    for (int i = 0; i < info.length; i++) {
                        if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                            if (!isConnected) {
                                Log.v("STATUS", "Now you are connected to Internet!");
//                                networkStatus.setText("Now you are connected to Internet!");
                                isConnected = true;
                                controller.setKeys("SBWoiw9UE9qx4NVLSHC9");
                                //do your processing here ---
                                //if you need to post any data to the server or get status
                                //update from the server
                            } else {
                                Util.showToastMessage(SplashScreen.this, "You are not connected to Internet!", getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
                            }
                            return true;
                        }
                    }
                }
            }
            Util.showToastMessage(SplashScreen.this, "You are not connected to Internet!", getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
            Log.v("STATUS", "You are not connected to Internet!");
//            networkStatus.setText("You are not connected to Internet!");
            isConnected = false;
            return false;
        }
    }

    @Override
    public void onSuccess(Response<KeysResponse> keysResponseResponse) {
        if (keysResponseResponse.body().getStatus() == 200) {
            setStringVal(Constants.CONSUMER_KEY, keysResponseResponse.body().getData().getConsumerKey());
            setStringVal(Constants.CONSUMER_SECRET, keysResponseResponse.body().getData().getConsumerSecret());
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    FirebaseDeeplinking();
                }
            }, 0000);
        } else {
            Util.showToastMessage(this, keysResponseResponse.body().getMessage(), getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
        }
    }

    @Override
    public void onError(String error) {
        Util.showToastMessage(this, error, getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));

    }


    public void FirebaseDeeplinking() {
        try {
            FirebaseDynamicLinks.getInstance().getDynamicLink(getIntent()).addOnSuccessListener(this, new OnSuccessListener<PendingDynamicLinkData>() {
                @Override
                public void onSuccess(PendingDynamicLinkData pendingDynamicLinkData) {
                    Uri uri = null;
                   if (pendingDynamicLinkData != null) {
                        uri = pendingDynamicLinkData.getLink();
                        String path = uri.getPath();
                        Log.d("deeplink", path);
                        String[] product_id = path.split("/");
                        if (product_id.length == 1) {
                            id = product_id[1];
                            if (!id.equals("")) {
                                Intent intent = new Intent(SplashScreen.this, ProductDetailsActivity.class);
                                intent.putExtra("productID", id);
                                startActivity(intent);
                                finish();
                            }

                        } else {
                            id = product_id[2];
                            if (!id.equals("")) {
                                Intent intent = new Intent(SplashScreen.this, ProductDetailsActivity.class);
                                intent.putExtra("productID", id);
                                startActivity(intent);
                                finish();
                            }else{
                                if (getWelComeString(Constants.WELCOMESTATUS).equals("2")) {
                                    if (!Constants.LOGIN_STATUS.equalsIgnoreCase("")) {


                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                        intent.putExtra("token", "login");
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                        intent.putExtra("token", "logout");
                                        startActivity(intent);
                                        finish();
                                    }
                                } else {
                                    if (!Constants.LOGIN_STATUS.equalsIgnoreCase("")) {
                                        Intent intent = new Intent(getApplicationContext(), WelcomeScreens.class);
                                        intent.putExtra("token", "login");
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Intent intent = new Intent(getApplicationContext(), WelcomeScreens.class);
                                        intent.putExtra("token", "logout");
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            }

                        }
                        Log.d("deeplink1", id);
                    } else {
                         if (getWelComeString(Constants.WELCOMESTATUS).equals("2")) {
                            if (!Constants.LOGIN_STATUS.equalsIgnoreCase("")) {

                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.putExtra("token", "login");
                                startActivity(intent);
                                finish();
                            } else {
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.putExtra("token", "logout");
                                startActivity(intent);
                                finish();
                            }
                        } else {
                            if (!Constants.LOGIN_STATUS.equalsIgnoreCase("")) {
                                Intent intent = new Intent(getApplicationContext(), WelcomeScreens.class);
                                intent.putExtra("token", "login");
                                startActivity(intent);
                                finish();
                            } else {
                                Intent intent = new Intent(getApplicationContext(), WelcomeScreens.class);
                                intent.putExtra("token", "logout");
                                startActivity(intent);
                                finish();
                            }
                        }
                    }
                }
            });
        } catch (Exception e)
        {
            Log.d("link3",""+e);
        }

    }
}
