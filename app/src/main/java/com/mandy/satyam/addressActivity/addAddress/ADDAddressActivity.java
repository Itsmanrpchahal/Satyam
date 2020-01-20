package com.mandy.satyam.addressActivity.addAddress;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mandy.satyam.R;
import com.mandy.satyam.controller.Controller;
import com.mandy.satyam.utils.ProgressBarClass;
import com.mandy.satyam.utils.SharedToken;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ADDAddressActivity extends AppCompatActivity {

    SharedToken sharedToken;
    String name, mobile, postcode, town, state, flat, near;
    Controller controller;
    Dialog dialog;

    @BindView(R.id.tooolbar)
    Toolbar toolbar;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.edtName)
    EditText edtName;
    @BindView(R.id.edtMobile)
    EditText edtMobile;
    @BindView(R.id.edtPostcode)
    EditText edtPostcode;
    @BindView(R.id.edtTown)
    EditText edtTown;
    @BindView(R.id.edtState)
    EditText edtState;
    @BindView(R.id.edtFlat)
    EditText edtFlat;
    @BindView(R.id.edtNear)
    EditText edtNear;
    @BindView(R.id.btnAdd)
    Button btnAdd;
    String id = "", size, color;
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.search_bt)
    ImageButton searchBt;
    @BindView(R.id.filter_bt)
    ImageButton filterBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addaddress);
        ButterKnife.bind(this);
        dialog = ProgressBarClass.showProgressDialog(this);

        filterBt.setVisibility(View.GONE);
        searchBt.setVisibility(View.GONE);
        back.setVisibility(View.GONE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        textView.setText("Add Address");

    }

    @OnClick(R.id.btnAdd)
    public void onViewClicked() {

        finish();

        /*name = edtName.getText().toString();
        mobile = edtMobile.getText().toString();
        postcode = edtPostcode.getText().toString();
        town = edtTown.getText().toString();
        state = edtState.getText().toString();
        flat = edtFlat.getText().toString();
        near = edtNear.getText().toString();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(mobile) || TextUtils.isEmpty(postcode) || TextUtils.isEmpty(town) || TextUtils.isEmpty(state) || TextUtils.isEmpty(flat)
                || TextUtils.isEmpty(near)) {
            getTextError(edtName, 20);
            getTextError(edtMobile, 10);
            getTextError(edtPostcode, 6);
            getTextError(edtTown, 20);
            getTextError(edtState, 20);
            getTextError(edtFlat, 30);
            getTextError(edtNear, 50);
        } else if (mobile.length() != 10) {
            edtMobile.setError("Enter 10 digit mobile number");
            edtMobile.requestFocus();
        } else {
            if (CheckInternet.isInternetAvailable(this)) {
                dialog.show();
                controller.setAddAddress("Bearer " + sharedToken.getShared(), name, mobile, postcode, town, state, flat, near);
            } else {
                startActivity(new Intent(this, NoInternetActivity.class));
            }
        }
*/
    }

    //check validation for edit text
    private void getTextError(final EditText editText, int number) {
        if (TextUtils.isEmpty(editText.getText().toString()) || editText.getText().toString().length() > number) {
            editText.setError("Enter this field");
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
