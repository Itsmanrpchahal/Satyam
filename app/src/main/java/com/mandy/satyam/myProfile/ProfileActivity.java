package com.mandy.satyam.myProfile;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.makeramen.roundedimageview.RoundedImageView;
import com.mandy.satyam.R;
import com.mandy.satyam.baseclass.BaseClass;
import com.mandy.satyam.baseclass.Constants;
import com.mandy.satyam.controller.Controller;
import com.mandy.satyam.myProfile.response.GetProfile;
import com.mandy.satyam.utils.Util;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MultipartBody;
import retrofit2.Response;

public class ProfileActivity extends BaseClass implements IPickResult , Controller.GetProfile {

    @BindView(R.id.tooolbar)
    Toolbar toolbar;
    @BindView(R.id.textView)
    TextView textView;
    protected static final int CAMERA_REQUEST = 0;
    protected static final int GALLERY_PICTURE = 1;
    private Intent pictureActionIntent = null;
    Bitmap bitmap;

    String selectedImagePath;
    Uri uri;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        Fresco.initialize(this);
        dialog = Util.showDialog(this);
        dialog.show();
        controller = new Controller(this);
        controller.setGetProfile(getStringVal(Constants.USERTOKEN));
        searchBt.setVisibility(View.GONE);
        filterBt.setVisibility(View.GONE);


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
                if (profileLogout.getText().toString().equals("Edit Profile"))
                {
                    profileUsernameet.setEnabled(true);
                    profileUsernameet.setFocusable(true);
                    profileLogout.setText("Save");
                }else {
                    profileLogout.setText("Edit Profile");
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

        } else {
            //Handle possible errors
            Toast.makeText(this, r.getError().getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onSuccessGetProfile(Response<GetProfile> response) {
        dialog.dismiss();
        if (response.isSuccessful()) {
            if (response.body().getStatus() == 200)
            {
                profileUsername.setText(response.body().getData().getFirstName()+" "+response.body().getData().getLastName());
                profileUsernameet.setText(response.body().getData().getFirstName()+" "+response.body().getData().getLastName());
                profileEmail.setText(response.body().getData().getEmail());
                profilePhnno.setText(response.body().getData().getPhone());
                Glide.with(this).load(response.body().getData().getImage()).placeholder(R.drawable.ic_satyamplaceholder).into(profileUserimage);
            }
        }
    }

    @Override
    public void onError(String error) {
        dialog.dismiss();
        Util.showToastMessage(this,error,getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));

    }
}
