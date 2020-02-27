package com.mandy.satyam.addressActivity.addAddress;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.mandy.satyam.MainActivity;
import com.mandy.satyam.R;
import com.mandy.satyam.addressActivity.response.GetAddress;
import com.mandy.satyam.addressActivity.response.UpdateAddress;
import com.mandy.satyam.baseclass.BaseClass;
import com.mandy.satyam.baseclass.Constants;
import com.mandy.satyam.controller.Controller;
import com.mandy.satyam.placeorder.CreateOrder;
import com.mandy.satyam.placeorder.CreateOrderPojo;
import com.mandy.satyam.utils.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Response;

public class ADDAddressActivity extends BaseClass implements Controller.GetAddress, Controller.UpdateAddress, Controller.PlaceOrder, Controller.PlaceOrder1 {

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
    @BindView(R.id.btnAedit)
    Button btnAedit;
    @BindView(R.id.edtLName)
    EditText edtLName;
    Intent intent;
    String product_id, quantity, q;
    @BindView(R.id.edtemail)
    EditText edtemail;
    CreateOrderPojo createOrderPojo;
    String product_id_quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = getIntent();
        setContentView(R.layout.activity_addaddress);
        ButterKnife.bind(this);
        dialog = Util.showDialog(this);
        dialog.show();
        controller = new Controller((Controller.GetAddress) this, (Controller.UpdateAddress) this, (Controller.PlaceOrder) this, (Controller.PlaceOrder1) this);
        controller.setGetAddress(getStringVal(Constants.USER_ID), getStringVal(Constants.CONSUMER_KEY), getStringVal(Constants.CONSUMER_SECRET));

        if (intent != null) {

            if (intent.getStringExtra("isFrom").equals("BuyBT")) {
                product_id = intent.getStringExtra("productID");
                quantity = intent.getStringExtra("quantity");
            } else {
//                product_idA = String.valueOf(intent.getSerializableExtra("product_id"));
//                /**/product_idA.add(intent.getSerializableExtra("product_id"));
////                quantityA.add(intent.getSerializableExtra("quantity"));
//
//                addJSON(new String[]{product_idA},quantityA);
                product_id_quantity = intent.getStringExtra("product_id_quantity");
                Log.d("JSONNEW", product_id_quantity);
            }

        }
        filterBt.setVisibility(View.GONE);
        searchBt.setVisibility(View.GONE);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        textView.setText("Add Address");
        listners();
    }


//[{"product_id":"18969","quantity":1},
// {"product_id":"18973","quantity":1}]


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
                if (intent.getStringExtra("isFrom").equals("BuyBT")) {
                    if (TextUtils.isEmpty(edtName.getText().toString()) && TextUtils.isEmpty(edtLName.getText().toString()) && TextUtils.isEmpty(edtMobile.getText().toString()) &&
                            TextUtils.isEmpty(edtemail.getText().toString()) && TextUtils.isEmpty(edtPostcode.getText().toString()) && TextUtils.isEmpty(edtFlat.getText().toString()) &&
                            TextUtils.isEmpty(edtNear.getText().toString()) && TextUtils.isEmpty(edtState.getText().toString()) && TextUtils.isEmpty(edtTown.getText().toString())) {
                        edtName.setError("");
                        edtLName.setError("");
                        edtMobile.setError("");
                        edtemail.setError("");
                        edtPostcode.setError("");
                        edtFlat.setError("");
                        edtNear.setError("");
                        edtState.setError("");
                        edtTown.setError("");
                    } else if (TextUtils.isEmpty(edtName.getText().toString())) {
                        edtName.setError("");
                    } else if (TextUtils.isEmpty(edtLName.getText().toString())) {
                        edtLName.setError("");
                    } else if (TextUtils.isEmpty(edtMobile.getText().toString())) {
                        edtMobile.setError("");
                    } else if (TextUtils.isEmpty(edtemail.getText().toString())) {
                        edtemail.setError("");
                    } else if (TextUtils.isEmpty(edtPostcode.getText().toString())) {
                        edtPostcode.setError("");
                    } else if (TextUtils.isEmpty(edtFlat.getText().toString())) {
                        edtFlat.setError("");
                    } else if (TextUtils.isEmpty(edtNear.getText().toString())) {
                        edtNear.setError("");
                    } else if (TextUtils.isEmpty(edtState.getText().toString())) {
                        edtState.setError("");
                    } else if (TextUtils.isEmpty(edtTown.getText().toString())) {
                        edtTown.setError("");
                    } else {
                        if (Util.isOnline(ADDAddressActivity.this) != false) {
                            dialog.show();
                            controller.setPlaceOrder("bacs", "Direct Bank Transfer", "true", edtName.getText().toString(), edtLName.getText().toString(),
                                    edtFlat.getText().toString(), "", edtTown.getText().toString(), edtState.getText().toString(), edtPostcode.getText().toString(),
                                    "India", edtemail.getText().toString(), edtMobile.getText().toString(), edtName.getText().toString(), edtLName.getText().toString(),
                                    edtFlat.getText().toString(), "", edtTown.getText().toString(), edtState.getText().toString(), edtPostcode.getText().toString(),
                                    "India", product_id, quantity, getStringVal(Constants.CONSUMER_KEY), getStringVal(Constants.CONSUMER_SECRET),
                                    getStringVal(Constants.USER_ID), "create_order");
                        } else {
                            Util.showToastMessage(ADDAddressActivity.this, "No Internet connection", getResources().getDrawable(R.drawable.ic_nointernet));
                        }
                    }
                } else {
                    dialog.dismiss();
                    if (TextUtils.isEmpty(edtName.getText().toString()) && TextUtils.isEmpty(edtLName.getText().toString()) && TextUtils.isEmpty(edtMobile.getText().toString()) &&
                            TextUtils.isEmpty(edtemail.getText().toString()) && TextUtils.isEmpty(edtPostcode.getText().toString()) && TextUtils.isEmpty(edtFlat.getText().toString()) &&
                            TextUtils.isEmpty(edtNear.getText().toString()) && TextUtils.isEmpty(edtState.getText().toString()) && TextUtils.isEmpty(edtTown.getText().toString())) {
                        edtName.setError("");
                        edtLName.setError("");
                        edtMobile.setError("");
                        edtemail.setError("");
                        edtPostcode.setError("");
                        edtFlat.setError("");
                        edtNear.setError("");
                        edtState.setError("");
                        edtTown.setError("");
                    } else if (TextUtils.isEmpty(edtName.getText().toString())) {
                        edtName.setError("");
                    } else if (TextUtils.isEmpty(edtLName.getText().toString())) {
                        edtLName.setError("");
                    } else if (TextUtils.isEmpty(edtMobile.getText().toString())) {
                        edtMobile.setError("");
                    } else if (TextUtils.isEmpty(edtemail.getText().toString())) {
                        edtemail.setError("");
                    } else if (TextUtils.isEmpty(edtPostcode.getText().toString())) {
                        edtPostcode.setError("");
                    } else if (TextUtils.isEmpty(edtFlat.getText().toString())) {
                        edtFlat.setError("");
                    } else if (TextUtils.isEmpty(edtNear.getText().toString())) {
                        edtNear.setError("");
                    } else if (TextUtils.isEmpty(edtState.getText().toString())) {
                        edtState.setError("");
                    } else if (TextUtils.isEmpty(edtTown.getText().toString())) {
                        edtTown.setError("");
                    } else {
                        if (Util.isOnline(ADDAddressActivity.this) != false) {
                            dialog.show();
                            controller.setPlaceOrder1_("bacs", "Direct Bank Transfer", true, edtName.getText().toString(), edtLName.getText().toString(),
                                    edtFlat.getText().toString(), "", edtTown.getText().toString(), edtState.getText().toString(), edtPostcode.getText().toString(),
                                    "India", edtemail.getText().toString(), edtMobile.getText().toString(), edtName.getText().toString(), edtLName.getText().toString(),
                                    edtFlat.getText().toString(), "", edtTown.getText().toString(), edtState.getText().toString(), edtPostcode.getText().toString(),
                                    "India", product_id_quantity, getStringVal(Constants.CONSUMER_KEY), getStringVal(Constants.CONSUMER_SECRET),
                                    getStringVal(Constants.USER_ID), "create_order");
                        } else {
                            dialog.dismiss();
                            Util.showToastMessage(ADDAddressActivity.this, "No Internet connection", getResources().getDrawable(R.drawable.ic_nointernet));
                        }
                    }

                }
            }
        });

        btnAedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Util.isOnline(ADDAddressActivity.this) != false) {

                    if (btnAedit.getText().toString().equals("Change Address")) {
                        btnAedit.setText("Save");
                        edtName.setEnabled(true);
                        edtLName.setEnabled(true);
                        edtemail.setEnabled(true);
                        edtMobile.setEnabled(true);
                        edtPostcode.setEnabled(true);
                        edtTown.setEnabled(true);
                        edtState.setEnabled(true);
                        edtFlat.setEnabled(true);
                        edtNear.setEnabled(true);
                    } else {
                        dialog.show();
                        if (TextUtils.isEmpty(edtName.getText().toString()) && TextUtils.isEmpty(edtLName.getText().toString()) && TextUtils.isEmpty(edtMobile.getText().toString()) && TextUtils.isEmpty(edtPostcode.getText().toString()) &&
                                TextUtils.isEmpty(edtTown.getText().toString()) && TextUtils.isEmpty(edtState.getText().toString()) && TextUtils.isEmpty(edtFlat.getText().toString()) &&
                                TextUtils.isEmpty(edtNear.getText().toString())) {

                            edtName.setError("Enter Firstname");
                            edtLName.setError("Enter Lastname");
                            edtMobile.setError("Enter Mobile number");
                            edtPostcode.setError("Enter Postcode");
                            edtTown.setError("Enter city");
                            edtState.setError("Enter State");
                            edtFlat.setError("Enter Address");
                            edtNear.setError("Enter Landmark");
                        } else if (TextUtils.isEmpty(edtName.getText().toString())) {
                            edtName.setError("Enter Name");
                        } else if (TextUtils.isEmpty(edtLName.getText().toString())) {
                            edtLName.setError("Enter Lastname");
                        } else if (TextUtils.isEmpty(edtMobile.getText().toString())) {
                            edtMobile.setError("Enter Mobile number");
                        } else if (TextUtils.isEmpty(edtPostcode.getText().toString())) {
                            edtPostcode.setError("Enter Postcode");
                        } else if (TextUtils.isEmpty(edtTown.getText().toString())) {
                            edtTown.setError("Enter city");
                        } else if (TextUtils.isEmpty(edtState.getText().toString())) {
                            edtState.setError("Enter State");
                        } else if (TextUtils.isEmpty(edtFlat.getText().toString())) {
                            edtFlat.setError("Enter Address");
                        } else if (TextUtils.isEmpty(edtNear.getText().toString())) {
                            edtNear.setError("Enter Landmark");
                        } else {
                            dialog.show();
                            controller.setUpdateAddress(getStringVal(Constants.USER_ID), getStringVal(Constants.CONSUMER_KEY), getStringVal(Constants.CONSUMER_SECRET),
                                    edtName.getText().toString() + " " + edtLName.getText().toString(), edtFlat.getText().toString(), edtNear.getText().toString(), edtTown.getText().toString(),
                                    edtPostcode.getText().toString(), edtState.getText().toString(), edtMobile.getText().toString());
                        }
                    }
                } else {
                    Util.showToastMessage(ADDAddressActivity.this, "No Internet connection", getResources().getDrawable(R.drawable.ic_nointernet));
                }
            }
        });
    }

    @OnClick(R.id.btnAdd)
    public void onViewClicked() {

        finish();

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
        if (response != null) {
            if (response.body().getStatus() == 200) {

                edtName.setText(response.body().getData().getBilling().getFirstName());
                edtLName.setText(response.body().getData().getLastName());
                edtMobile.setText(response.body().getData().getBilling().getPhone());
                edtPostcode.setText(response.body().getData().getBilling().getPostcode());
                edtTown.setText(response.body().getData().getBilling().getCity());
                edtState.setText(response.body().getData().getBilling().getState());
                edtFlat.setText(response.body().getData().getBilling().getAddress1());
                edtNear.setText(response.body().getData().getBilling().getAddress2());
                edtemail.setText(response.body().getData().getEmail());
            }
        }
    }

    @Override
    public void onSuccessUpdateAddress(Response<UpdateAddress> response) {
        dialog.dismiss();
        if (response.isSuccessful()) {
            btnAedit.setText("Change Address");
            Util.showToastMessage(this, "Address Updated", getResources().getDrawable(R.drawable.app_icon));
        }
    }

    @Override
    public void onSuccessPlaceOrder(Response<CreateOrder> response) {
        dialog.dismiss();
        if (response.isSuccessful()) {
            if (response.body().getStatus() == 200) {
                Util.showToastMessage(this, "Order Placed", getResources().getDrawable(R.drawable.app_icon));
                Intent intent = new Intent(ADDAddressActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Util.showToastMessage(this, response.body().getMessage(), getResources().getDrawable(R.drawable.app_icon));
            }

        }
    }


    @Override
    public void onSuccessPlaceOrder1(Response<CreateOrder> response) {
        dialog.dismiss();
        if (response.isSuccessful()) {
            if (response.body().getStatus() == 200) {
                Intent intent = new Intent(ADDAddressActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                Util.showToastMessage(this, "Order Placed", getResources().getDrawable(R.drawable.app_icon));
            } else {
                Util.showToastMessage(this, response.body().getMessage(), getResources().getDrawable(R.drawable.app_icon));
            }
        }
    }

    @Override
    public void onError(String error) {
        dialog.dismiss();
        Util.showToastMessage(this, error, getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
    }
}
