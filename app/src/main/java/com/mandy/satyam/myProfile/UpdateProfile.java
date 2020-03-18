package com.mandy.satyam.myProfile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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

import com.bumptech.glide.Glide;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.makeramen.roundedimageview.RoundedImageView;
import com.mandy.satyam.MainActivity;
import com.mandy.satyam.R;
import com.mandy.satyam.baseclass.BaseClass;
import com.mandy.satyam.baseclass.Constants;
import com.mandy.satyam.controller.Controller;
import com.mandy.satyam.utils.Util;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MultipartBody;
import retrofit2.Response;

public class UpdateProfile extends BaseClass implements IPickResult, Controller.UpdateProfile {

    Dialog dialog;
    Controller controller;
    Button updatelogout,profile_update;
    RoundedImageView profile_userimage;
    EditText profile_usernameet,profile_lastname,profile_email,profile_phnno,password,confirmpassword;
    Bitmap bitmap;
    MultipartBody.Part part;

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
                setStringVal(Constants.LOGIN_STATUS,"logout");
                Intent intent = new Intent(UpdateProfile.this, MainActivity.class);
                startActivity(intent);
            }
        });

        profile_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(profile_usernameet.getText().toString()) && TextUtils.isEmpty(profile_lastname.getText().toString()) &&
                        TextUtils.isEmpty(profile_email.getText().toString()) && TextUtils.isEmpty(profile_phnno.getText().toString()) &&
                TextUtils.isEmpty(password.getText().toString()) && TextUtils.isEmpty(confirmpassword.getText().toString()))
                {
                    profile_usernameet.setFocusable(true);
                    profile_usernameet.setError("Enter First name");

                    profile_lastname.setFocusable(true);
                    profile_lastname.setError("Enter Last name");

                    profile_email.setFocusable(true);
                    profile_email.setError("Enter Email");

                    profile_phnno.setFocusable(true);
                    profile_phnno.setError("Enter Phone No.");

                    password.setFocusable(true);
                    password.setError("Enter Password");

                    confirmpassword.setFocusable(true);
                    confirmpassword.setError("Enter Confirm Password");
                }else if (TextUtils.isEmpty(profile_usernameet.getText().toString()) )
                {
                    profile_usernameet.setFocusable(true);
                    profile_usernameet.setError("Enter First name");
                }else if (TextUtils.isEmpty(profile_lastname.getText().toString()))
                {
                    profile_lastname.setFocusable(true);
                    profile_lastname.setError("Enter Last name");
                }else if (TextUtils.isEmpty(profile_email.getText().toString()))
                {
                    profile_email.setFocusable(true);
                    profile_email.setError("Enter Email");
                }else if (TextUtils.isEmpty(profile_phnno.getText().toString()))
                {
                    profile_phnno.setFocusable(true);
                    profile_phnno.setError("Enter Phone No.");
                }else if (profile_phnno.getText().toString().length()<10)
                {
                    profile_phnno.setFocusable(true);
                    profile_phnno.setError("Invalid number");
                }else if (TextUtils.isEmpty(password.getText().toString()))
                {
                    password.setFocusable(true);
                    password.setError("Enter Password");
                }else if (TextUtils.isEmpty(confirmpassword.getText().toString()))
                {
                    confirmpassword.setFocusable(true);
                    confirmpassword.setError("Enter Confirm Password");
                }else if (!password.getText().toString().matches(confirmpassword.getText().toString()))
                {
                    confirmpassword.setFocusable(true);
                    confirmpassword.setError("Password not matched");
                }else {
                    String firstname = profile_usernameet.getText().toString();
                    String lastname = profile_lastname.getText().toString();
                    String email = profile_email.getText().toString();
                    String phone = profile_phnno.getText().toString();
                    String pass = password.getText().toString();
                    String c_pass = confirmpassword.getText().toString();
                    dialog.show();
                    controller.setUpdateProfile(getStringVal(Constants.USERTOKEN),firstname,lastname,email,c_pass,part);
                }
            }
        });
    }

    private void init() {
        updatelogout = findViewById(R.id.updatelogout);
        profile_userimage = findViewById(R.id.profile_userimage);
        profile_usernameet = findViewById(R.id.profile_usernameet);
        profile_lastname = findViewById(R.id.profile_lastname);
        profile_email = findViewById(R.id.profile_email);
        profile_phnno = findViewById(R.id.profile_phnno);
        password = findViewById(R.id.password);
        confirmpassword = findViewById(R.id.confirmpassword);
        profile_update = findViewById(R.id.profile_update);
        profile_email.setEnabled(false);

        Glide.with(UpdateProfile.this).load(getStringVal(Constants.AVATAR)).placeholder(R.drawable.userprofileplaceholder).into(profile_userimage);
        new getImagefromURL(profile_userimage).execute(getStringVal(Constants.AVATAR));
        profile_usernameet.setText(getStringVal(Constants.FIRSTNAME));
        profile_lastname.setText(getStringVal(Constants.LASTNAME));
        profile_email.setText(getStringVal(Constants.EMAIL));
    }

    @Override
    public void onSuccessUpdateProfile(Response<com.mandy.satyam.myProfile.response.UpdateProfile> response) {
        if (response.isSuccessful())
        {
            dialog.dismiss();
            if (response.body().getStatus()==200)
            {
                setStringVal(Constants.EMAIL,response.body().getData().getEmail());
                setStringVal(Constants.LOGIN_STATUS,"login");
                setStringVal(Constants.FIRSTNAME,response.body().getData().getFirstName());
                setStringVal(Constants.LASTNAME,response.body().getData().getLastName());
                setStringVal(Constants.AVATAR,response.body().getData().getImage());
                setStringVal(Constants.MOBILE,response.body().getData().getPhone());
                Intent intent = new Intent(UpdateProfile.this,MainActivity.class);
                startActivity(intent);
                finish();
            }else {
                Util.showToastMessage(this,response.body().getMessage(),getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
            }
        }
    }

    @Override
    public void onError(String error) {
        dialog.dismiss();
        Util.showToastMessage(this,error,getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
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
                part = Util.sendImageFileToserver(r.getBitmap(), "profile", this);
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

        ImageView imageView;

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
}
