package com.mandy.satyam.addressActivity.addAddress;

import android.app.Dialog;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.mandy.satyam.R;
import com.mandy.satyam.addressActivity.response.GetAddress;
import com.mandy.satyam.addressActivity.response.UpdateAddress;
import com.mandy.satyam.baseclass.BaseClass;
import com.mandy.satyam.baseclass.Constants;
import com.mandy.satyam.controller.Controller;
import com.mandy.satyam.utils.SharedToken;
import com.mandy.satyam.utils.Util;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Response;

public class ADDAddressActivity extends BaseClass implements Controller.GetAddress,Controller.UpdateAddress {

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
    Controller controller;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addaddress);
        ButterKnife.bind(this);
        dialog = Util.showDialog(this);
        dialog.show();
        controller = new Controller((Controller.GetAddress)this,(Controller.UpdateAddress)this);
        controller.setGetAddress(getStringVal(Constants.USER_ID),getStringVal(Constants.CONSUMER_KEY),getStringVal(Constants.CONSUMER_SECRET));

        filterBt.setVisibility(View.GONE);
        searchBt.setVisibility(View.GONE);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        textView.setText("Add Address");
        listners();

    }

    private void listners() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edtName.getText().toString()) && TextUtils.isEmpty(edtMobile.getText().toString()) && TextUtils.isEmpty(edtPostcode.getText().toString()) &&
                        TextUtils.isEmpty(edtTown.getText().toString())  && TextUtils.isEmpty(edtState.getText().toString()) && TextUtils.isEmpty(edtFlat.getText().toString()) &&
                        TextUtils.isEmpty(edtNear.getText().toString()))
                {

                    edtName.setError("Enter Name");
                    edtMobile.setError("Enter Mobile number");
                    edtPostcode.setError("Enter Postcode");
                    edtTown.setError("Enter city");
                    edtState.setError("Enter State");
                    edtFlat.setError("Enter Address");
                    edtNear.setError("Enter Landmark");
                }else if (TextUtils.isEmpty(edtName.getText().toString()))
                {
                    edtName.setError("Enter Name");
                }else if (TextUtils.isEmpty(edtMobile.getText().toString()))
                {
                    edtMobile.setError("Enter Mobile number");
                }else if (TextUtils.isEmpty(edtPostcode.getText().toString()))
                {
                    edtPostcode.setError("Enter Postcode");
                }else if (TextUtils.isEmpty(edtTown.getText().toString()))
                {
                    edtTown.setError("Enter city");
                }else if (TextUtils.isEmpty(edtState.getText().toString()))
                {
                    edtState.setError("Enter State");
                }else if (TextUtils.isEmpty(edtFlat.getText().toString()))
                {
                    edtFlat.setError("Enter Address");
                }else if (TextUtils.isEmpty(edtNear.getText().toString()))
                {
                    edtNear.setError("Enter Landmark");
                }else {
                    dialog.show();
                    controller.setUpdateAddress(getStringVal(Constants.USER_ID),getStringVal(Constants.CONSUMER_KEY),getStringVal(Constants.CONSUMER_SECRET),
                            edtName.getText().toString(),edtFlat.getText().toString(),edtNear.getText().toString(),edtTown.getText().toString(),
                            edtPostcode.getText().toString(),edtState.getText().toString(),edtMobile.getText().toString());
                }
            }
        });
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

    @Override
    public void onSuccessGetAddress(Response<GetAddress> response) {
        dialog.dismiss();
        if (response!=null)
        {
            if (response.body().getStatus()==200)
            {
                edtName.setText(response.body().getData().getBilling().getFirstName());
                edtMobile.setText(response.body().getData().getBilling().getPhone());
                edtPostcode.setText(response.body().getData().getBilling().getPostcode());
                edtTown.setText(response.body().getData().getBilling().getCity());
                edtState.setText(response.body().getData().getBilling().getState());
                edtFlat.setText(response.body().getData().getBilling().getAddress1());
                edtNear.setText(response.body().getData().getBilling().getAddress2());
            }
        }
    }

    @Override
    public void onSuccessUpdateAddress(Response<UpdateAddress> response) {
        dialog.dismiss();
        if (response.isSuccessful())
        {
            Toast.makeText(this, "Address Updated "+response.body().getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onError(String error) {
        dialog.dismiss();
        Util.showToastMessage(this,error,getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
    }
}
