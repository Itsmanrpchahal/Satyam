package com.mandy.satyam.myProfile;

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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.makeramen.roundedimageview.RoundedImageView;
import com.mandy.satyam.R;
import com.mandy.satyam.baseclass.BaseClass;
import com.mandy.satyam.baseclass.Constants;
import com.mandy.satyam.controller.Controller;
import com.mandy.satyam.login.LoginActivity;
import com.mandy.satyam.myProfile.response.GetProfile;
import com.mandy.satyam.myProfile.response.UpdateProfile;
import com.mandy.satyam.utils.Util;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MultipartBody;
import retrofit2.Response;

public class ProfileActivity extends BaseClass implements IPickResult, Controller.GetProfile, Controller.UpdateProfile {

    @BindView(R.id.tooolbar)
    Toolbar toolbar;
    @BindView(R.id.textView)
    TextView textView;
    protected static final int CAMERA_REQUEST = 0;
    protected static final int GALLERY_PICTURE = 1;
    MultipartBody.Part part;
    File filesDir;
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.search_bt)
    ImageButton searchBt;
    @BindView(R.id.filter_bt)
    ImageButton filterBt;
    @BindView(R.id.profile_userimage)
    RoundedImageView profileUserimage;
    @BindView(R.id.profile_username)
    TextView profileUsername;
    @BindView(R.id.profile_view1)
    View profileView1;
    @BindView(R.id.profile_usernameet)
    EditText profileUsernameet;
    @BindView(R.id.profile_view11)
    View profileView11;
    @BindView(R.id.profile_email)
    EditText profileEmail;
    @BindView(R.id.profile_view2)
    View profileView2;
    @BindView(R.id.profile_phnno)
    EditText profilePhnno;
    @BindView(R.id.profile_view3)
    View profileView3;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.profile_view4)
    View profileView4;
    @BindView(R.id.confirmpassword)
    EditText confirmpassword;
    @BindView(R.id.setting_view6)
    View settingView6;
    @BindView(R.id.profile_logout)
    Button profileLogout;
    Controller controller;
    Dialog dialog;
    @BindView(R.id.profile_lastname)
    EditText profileLastname;
    @BindView(R.id.usernamelayut)
    LinearLayout usernamelayut;
    Bitmap bitmap;
    @BindView(R.id.passwordtv)
    TextView passwordtv;
    @BindView(R.id.cpasswordtv)
    TextView cpasswordtv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        Util.checkPermissions(this);
        Fresco.initialize(this);
        dialog = Util.showDialog(this);
        dialog.show();
        controller = new Controller((Controller.GetProfile) this, (Controller.UpdateProfile) this);
        controller.setGetProfile(getStringVal(Constants.USERTOKEN));
        searchBt.setVisibility(View.GONE);
        filterBt.setVisibility(View.GONE);
        password.setVisibility(View.GONE);
        passwordtv.setVisibility(View.GONE);
        cpasswordtv.setVisibility(View.GONE);
        confirmpassword.setVisibility(View.GONE);
        profileUserimage.setEnabled(false);

        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        textView.setText("My Profile");
        listerners();


    }

    private void listerners() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        profileUserimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PickImageDialog.build(new PickSetup()
                        .setButtonOrientation(LinearLayout.HORIZONTAL)).show(ProfileActivity.this);
            }
        });

        profileLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (profileLogout.getText().toString().equals("Edit Profile")) {
                    profileLogout.setText("Save");
                    profileUserimage.setEnabled(true);
                    profileUsernameet.setEnabled(true);
                    profileLastname.setEnabled(true);
                    password.setVisibility(View.VISIBLE);
                    confirmpassword.setVisibility(View.VISIBLE);
                    passwordtv.setVisibility(View.VISIBLE);
                    cpasswordtv.setVisibility(View.VISIBLE);
                    profileEmail.setEnabled(true);
                } else if (profileLogout.getText().toString().equals("Save")) {
                    if (TextUtils.isEmpty(profileUsernameet.getText().toString()) && TextUtils.isEmpty(profileLastname.getText().toString()) &&
                            TextUtils.isEmpty(profileEmail.getText().toString()) ) {
                        profileUsernameet.setError("Enter Firstname");
                        profileLastname.setError("Enter Lastname");
                        profileEmail.setError("Enter Email");
                    } else if (TextUtils.isEmpty(profileUsernameet.getText().toString())) {
                        profileUsernameet.setError("Enter Firstname");
                    } else if (TextUtils.isEmpty(profileLastname.getText().toString())) {
                        profileLastname.setError("Enter Lastname");
                    } else if (TextUtils.isEmpty(profileEmail.getText().toString())) {
                        profileEmail.setError("Enter Email");
                    } else if (!password.getText().toString().matches(confirmpassword.getText().toString())) {
                        confirmpassword.setError("Password not matched");
                    } else {
                        controller.setUpdateProfile(getStringVal(Constants.USERTOKEN), profileUsernameet.getText().toString(), profileLastname.getText().toString(), profileEmail.getText().toString(), password.getText().toString(), part);
                        dialog.show();
                    }
                }
            }
        });
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onPickResult(PickResult r) {
        if (r.getError() == null) {
            //                part.add(sendImageFileToserver(r.getBitmap()));
//                image_uris.add(r.getPath());
//                SetImage();

            profileUserimage.setImageBitmap(r.getBitmap());
            bitmap = r.getBitmap();
            try {
                part = Util.sendImageFileToserver(r.getBitmap(), "profile", this);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            //Handle possible errors
            Toast.makeText(this, r.getError().getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onSuccessGetProfile(Response<GetProfile> response) {
        dialog.dismiss();
        if (response.isSuccessful()) {
            if (response.body().getStatus() == 200) {
                profileUsername.setText(response.body().getData().getFirstName() + " " + response.body().getData().getLastName());
                profileLastname.setText(response.body().getData().getLastName());
                profileUsernameet.setText(response.body().getData().getFirstName());
                profileEmail.setText(response.body().getData().getEmail());
                profilePhnno.setText(response.body().getData().getPhone());
                Glide.with(this).load(response.body().getData().getImage()).placeholder(R.drawable.ic_satyamplaceholder).into(profileUserimage);
                new getImagefromURL(profileUserimage).execute(response.body().getData().getImage());
            }
            else if (response.body().getStatus() == 400){
                clearStringVal(Constants.LOGIN_STATUS);
                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                intent.putExtra("token", "logout");
                startActivity(intent);
                finish();
            }
        }
    }

    @Override
    public void onSuccessUpdateProfile(Response<UpdateProfile> response) {
        dialog.dismiss();
        if (response.isSuccessful()) {
            if (response.body().getStatus()==200)
            {
                profileLogout.setText("Edit Profile");
                passwordtv.setVisibility(View.GONE);
                password.setVisibility(View.GONE);
                confirmpassword.setVisibility(View.GONE);
                cpasswordtv.setVisibility(View.GONE);
                recreate();
            } else if (response.body().getStatus() == 400){
                clearStringVal(Constants.LOGIN_STATUS);
                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                intent.putExtra("token", "logout");
                startActivity(intent);
                finish();
            }
        }
    }

    @Override
    public void onError(String error) {
        dialog.dismiss();
        Util.showToastMessage(this, error, getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));

    }

    //Get Image from Url
    public class getImagefromURL extends AsyncTask<String, Void, Bitmap> {

        ImageView imageView;

        public getImagefromURL(ImageView imageView) {
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(String... url) {

            String urlimage = url[0];

            bitmap = null;


            try {
                InputStream stream = new URL(urlimage).openStream();
                bitmap = BitmapFactory.decodeStream(stream);
                part = Util.sendImageFileToserver(bitmap, "profile", ProfileActivity.this);

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

}
