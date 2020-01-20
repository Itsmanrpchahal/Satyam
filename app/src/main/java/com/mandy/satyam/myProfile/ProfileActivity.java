package com.mandy.satyam.myProfile;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.makeramen.roundedimageview.RoundedImageView;
import com.mandy.satyam.R;
import com.mandy.satyam.controller.Controller;
import com.mandy.satyam.utils.ProgressBarClass;
import com.mandy.satyam.utils.SharedToken;
import com.zfdang.multiple_images_selector.ImagesSelectorActivity;
import com.zfdang.multiple_images_selector.SelectorSettings;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MultipartBody;

public class ProfileActivity extends AppCompatActivity {

    @BindView(R.id.tooolbar)
    Toolbar toolbar;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.imageView)
    RoundedImageView imageView;
    @BindView(R.id.edtName)
    EditText edtName;
    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.edtPhone)
    EditText edtPhone;
    @BindView(R.id.btnProfile)
    Button btnProfile;
    @BindView(R.id.btnEdit)
    Button btnEdit;

    Controller controller;
    ArrayList<String> image_uris = new ArrayList<>();
    String token;
    SharedToken sharedToken;
    MultipartBody.Part part;
    private final int RESULT_LOAD_IMAGE = 12;
    Dialog dialog;
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.search_bt)
    ImageButton searchBt;
    @BindView(R.id.filter_bt)
    ImageButton filterBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        Fresco.initialize(this);

        back.setVisibility(View.GONE);
        searchBt.setVisibility(View.GONE);
        filterBt.setVisibility(View.GONE);
        dialog = ProgressBarClass.showProgressDialog(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        textView.setText("My Profile");


    }

    @OnClick({R.id.imageView, R.id.btnProfile, R.id.btnEdit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView:
                // start multiple photos selector
                Intent intent = new Intent(this, ImagesSelectorActivity.class);
                intent.putExtra(SelectorSettings.SELECTOR_MAX_IMAGE_NUMBER, 1);
                //  intent.putExtra(SelectorSettings.SELECTOR_MIN_IMAGE_SIZE, 100000);

                intent.putExtra(SelectorSettings.SELECTOR_SHOW_CAMERA, true);
                intent.putStringArrayListExtra(SelectorSettings.SELECTOR_INITIAL_SELECTED_LIST, image_uris);
                // start the selector
                startActivityForResult(intent, RESULT_LOAD_IMAGE);

                break;
            case R.id.btnProfile:
                edtEmail.setFocusable(false);
                edtEmail.setClickable(false);
                edtEmail.setFocusableInTouchMode(false);

                edtName.setFocusable(false);
                edtName.setClickable(false);
                edtName.setFocusableInTouchMode(false);
                edtPhone.setFocusable(false);

                btnEdit.setVisibility(View.VISIBLE);
                btnProfile.setVisibility(View.GONE);

               /* if (CheckInternet.isInternetAvailable(this)) {
                    dialog.show();
                    controller.setEditProfile(token, edtName.getText().toString(), edtEmail.getText().toString());
                } else {
                    startActivity(new Intent(this, NoInternetActivity.class));
                }*/

                break;
            case R.id.btnEdit:
                edtEmail.setFocusable(true);
                edtEmail.setClickable(true);
                edtEmail.setFocusableInTouchMode(true);
                edtName.setFocusable(true);
                edtName.setClickable(true);
                edtName.setFocusableInTouchMode(true);

                btnProfile.setVisibility(View.VISIBLE);
                btnEdit.setVisibility(View.GONE);

                edtName.requestFocus();
                break;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
