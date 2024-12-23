package com.satyam.myProfile;

import androidx.annotation.NonNull;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.AccessToken;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.makeramen.roundedimageview.RoundedImageView;
import com.satyam.MainActivity;
import com.satyam.R;
import com.satyam.baseclass.BaseClass;
import com.satyam.baseclass.Constants;
import com.satyam.controller.Controller;
import com.satyam.utils.Util;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import butterknife.ButterKnife;
import okhttp3.MultipartBody;
import retrofit2.Response;

public class UpdateProfile extends BaseClass implements IPickResult, Controller.UpdateProfile1 {

    Dialog dialog;
    Controller controller;
    TextView profile_username;
    Button updatelogout, profile_update;
    RoundedImageView profile_userimage;
    EditText profile_usernameet, profile_lastname, profile_email, profile_phnno;
    Bitmap bitmap;
    MultipartBody.Part part;
    String token;
    GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        ButterKnife.bind(this);
        Util.checkPermissions(this);
        Fresco.initialize(this);
        dialog = Util.showDialog(this);
        dialog.dismiss();
        controller = new Controller(this);
        init();
        listerners();
    }

    private void listerners() {

        profile_userimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PickImageDialog.build(new PickSetup()
                        .setButtonOrientation(LinearLayout.HORIZONTAL)).show(UpdateProfile.this);
            }
        });

        updatelogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AccessToken.getCurrentAccessToken() != null) {
                    LoginManager.getInstance().logOut();
                    Intent intent = new Intent(UpdateProfile.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("token", "2");
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                    finish();
                    clearStringVal(Constants.LOGIN_STATUS);
                } else {
                    if (getStringVal(Constants.LOGIN_STATUS).equals("login")) {
//                    textLogout.setTitle("Login");
                        dialog.dismiss();
                        Intent intent = new Intent(UpdateProfile.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("token", "2");
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        finish();
                        clearStringVal(Constants.LOGIN_STATUS);
                    } else {
                        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
                            @Override
                            public void onResult(@NonNull Status status) {
                                LoginManager.getInstance().logOut();
                                dialog.dismiss();
                                Intent intent = new Intent(UpdateProfile.this, MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.putExtra("token", "2");
                                startActivity(intent);
                                overridePendingTransition(0, 0);
                                finish();
                                clearStringVal(Constants.LOGIN_STATUS);
                            }
                        });
                    }
                }
            }
        });

        profile_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(profile_usernameet.getText().toString()) && TextUtils.isEmpty(profile_lastname.getText().toString()) &&
                        TextUtils.isEmpty(profile_email.getText().toString()) && TextUtils.isEmpty(profile_phnno.getText().toString())
                ) {
                    profile_usernameet.setFocusable(true);
                    profile_usernameet.setError("Enter First name");

                    profile_lastname.setFocusable(true);
                    profile_lastname.setError("Enter Last name");

                    profile_email.setFocusable(true);
                    profile_email.setError("Enter Email");

                    profile_phnno.setFocusable(true);
                    profile_phnno.setError("Enter Phone No.");

                } else if (TextUtils.isEmpty(profile_usernameet.getText().toString())) {
                    profile_usernameet.setFocusable(true);
                    profile_usernameet.setError("Enter First name");
                } else if (TextUtils.isEmpty(profile_lastname.getText().toString())) {
                    profile_lastname.setFocusable(true);
                    profile_lastname.setError("Enter Last name");
                } else if (TextUtils.isEmpty(profile_email.getText().toString())) {
                    profile_email.setFocusable(true);
                    profile_email.setError("Enter Email");
                } else if (TextUtils.isEmpty(profile_phnno.getText().toString())) {
                    profile_phnno.setFocusable(true);
                    profile_phnno.setError("Enter Phone No.");
                } else if (profile_phnno.getText().toString().length() < 10) {
                    profile_phnno.setFocusable(true);
                    profile_phnno.setError("Invalid number");
                } else {
                    String firstname = profile_usernameet.getText().toString();
                    String lastname = profile_lastname.getText().toString();
                    String email = profile_email.getText().toString();
                    String phone = profile_phnno.getText().toString();
                    dialog.show();
                    controller.setUpdateProfile1(getStringVal(Constants.USERTOKEN), firstname, lastname, email, "91" + phone, part);
                }
            }
        });
    }

    private void init() {
        token = getStringVal(Constants.USERTOKEN);
        profile_username = findViewById(R.id.profile_username);
        updatelogout = findViewById(R.id.updatelogout);
        profile_userimage = findViewById(R.id.profile_userimage);
        profile_usernameet = findViewById(R.id.profile_usernameet);
        profile_lastname = findViewById(R.id.profile_lastname);
        profile_email = findViewById(R.id.profile_email);
        profile_phnno = findViewById(R.id.profile_phnno);
        profile_update = findViewById(R.id.profile_update);
        profile_email.setEnabled(false);

        Glide.with(UpdateProfile.this).load(getStringVal(Constants.AVATAR)).placeholder(R.drawable.userprofileplaceholder).into(profile_userimage);
        new getImagefromURL(profile_userimage).execute(getStringVal(Constants.AVATAR));
        profile_usernameet.setText(getStringVal(Constants.FIRSTNAME));
        profile_lastname.setText(getStringVal(Constants.LASTNAME));
        profile_email.setText(getStringVal(Constants.EMAIL));
        profile_username.setText(getStringVal(Constants.FIRSTNAME) + " " + getStringVal(Constants.LASTNAME));
    }

    @Override
    public void onSuccessUpdateProfile1(Response<com.satyam.myProfile.response.UpdateProfile> response) {
        if (response.isSuccessful()) {
            dialog.dismiss();
            if (response.body().getStatus() == 200) {
                setStringVal(Constants.EMAIL, response.body().getData().getEmail());
                setStringVal(Constants.LOGIN_STATUS, "login");
                setStringVal(Constants.FIRSTNAME, response.body().getData().getFirstName());
                setStringVal(Constants.LASTNAME, response.body().getData().getLastName());
                setStringVal(Constants.AVATAR, response.body().getData().getImage());
                setStringVal(Constants.USERTOKEN, token);
                if (response.body().getData().getPhone() != null) {
                    String num = response.body().getData().getPhone().substring(2);
                    setStringVal(Constants.MOBILE, num);
                }

                Intent intent = new Intent(UpdateProfile.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Util.showToastMessage(this, response.body().getMessage(), getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
            }
        } else {
            Util.showToastMessage(this, response.body().getMessage(), getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
        }
    }

    @Override
    public void onError(String error) {
        dialog.dismiss();
        Util.showToastMessage(this, error, getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
    }

    @Override
    public void onPickResult(PickResult r) {
        if (r.getError() == null) {
            //                part.add(sendImageFileToserver(r.getBitmap()));
//                image_uris.add(r.getPath());
//                SetImage();

            profile_userimage.setImageBitmap(r.getBitmap());
            bitmap = r.getBitmap();
            try {
                part = Util.sendImageFileToserver(r.getBitmap(), "image", this);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            //Handle possible errors
            Toast.makeText(this, r.getError().getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    //Get Image from Url
    public class getImagefromURL extends AsyncTask<String, Void, Bitmap> {

        RoundedImageView imageView;

        public getImagefromURL(RoundedImageView imageView) {
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(String... url) {

            String urlimage = url[0];

            bitmap = null;


            try {
                InputStream stream = new URL(urlimage).openStream();
                bitmap = BitmapFactory.decodeStream(stream);
                part = Util.sendImageFileToserver(bitmap, "profile", UpdateProfile.this);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imageView.setImageBitmap(bitmap);
        }
    }

    @Override
    public void onStart() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        googleApiClient.connect();
        super.onStart();
    }
}
