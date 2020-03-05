package com.mandy.satyam.addressActivity.addAddress;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.mandy.satyam.MainActivity;
import com.mandy.satyam.R;
import com.mandy.satyam.addressActivity.AddressActivity;
import com.mandy.satyam.addressActivity.response.GetAddress;
import com.mandy.satyam.addressActivity.response.GetCities;
import com.mandy.satyam.addressActivity.response.GetZones;
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
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Response;

public class ADDAddressActivity extends BaseClass implements Controller.GetAddress, Controller.UpdateAddress, Controller.PlaceOrder, Controller.PlaceOrder1, Controller.GetZone,Controller.GetCities, AdapterView.OnItemSelectedListener {

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
    @BindView(R.id.spinnerCity)
    Spinner spinnerCity;
    @BindView(R.id.spinnerState)
    Spinner spinnerState;
    @BindView(R.id.edtFlat)
    EditText edtFlat;
    @BindView(R.id.edtNear)
    EditText edtNear;
    @BindView(R.id.btnAdd)
    Button btnAdd;
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.search_bt)
    ImageButton searchBt;
    @BindView(R.id.stateTV)
    TextView stateTV;
    @BindView(R.id.cityTV)
    TextView cityTV;
    @BindView(R.id.filter_bt)
    ImageButton filterBt;
    Controller controller;
    Dialog dialog;
    @BindView(R.id.btnAedit)
    Button btnAedit;
    @BindView(R.id.radio_group)
    RadioGroup radio_Group;
    @BindView(R.id.home_radio)
    RadioButton home_radio;
    @BindView(R.id.office_radio)
    RadioButton office_radio;
    @BindView(R.id.shop_radio)
    RadioButton shop_radio;
    @BindView(R.id.edtLName)
    EditText edtLName;
    Intent intent;
    String product_id, quantity, q;
    @BindView(R.id.edtemail)
    EditText edtemail;
    ArrayList<String> getZonesArrayList = new ArrayList<>();
    ArrayList<String> getZonesID = new ArrayList<>();
    ArrayList<String> getCities = new ArrayList<>();
    ArrayList<GetZones.Datum> getZoneData = new ArrayList<>();
    String product_id_quantity;
    String state,city,addressType="home",is_address_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = getIntent();
        setContentView(R.layout.activity_addaddress);
        ButterKnife.bind(this);
        dialog = Util.showDialog(this);
        dialog.show();

        controller = new Controller((Controller.GetAddress) this, (Controller.UpdateAddress) this, (Controller.PlaceOrder) this, (Controller.PlaceOrder1) this, (Controller.GetZone) this,(Controller.GetCities)this);
        controller.setGetAddress(getStringVal(Constants.USER_ID),"get_address", getStringVal(Constants.CONSUMER_KEY), getStringVal(Constants.CONSUMER_SECRET));
        controller.setGetZone();
        if (intent != null) {

            if (intent.getStringExtra("isFrom").equals("BuyBT")) {
                product_id = intent.getStringExtra("productID");
                is_address_update = intent.getStringExtra("is_address_update");
                quantity = intent.getStringExtra("quantity");
            } else {

                product_id_quantity = intent.getStringExtra("product_id_quantity");
                is_address_update = intent.getStringExtra("is_address_update");
                Log.d("JSONNEW", product_id_quantity);
            }

        }
        filterBt.setVisibility(View.GONE);
        searchBt.setVisibility(View.GONE);
        setSupportActionBar(toolbar);
        textView.setText("Add Address");
        listners();
        setDataAddress();
    }

    private void setDataAddress() {
        if (!is_address_update.equalsIgnoreCase("true"))
        {
            edtName.setEnabled(true);
            edtLName.setEnabled(true);
            edtMobile.setEnabled(true);
            edtemail.setEnabled(true);
            btnAdd.setVisibility(View.GONE);
            btnAedit.setText("Save");
        }
    }


//[{"product_id":"18969","quantity":1},
// {"product_id":"18973","quantity":1}]


    private void listners() {
        edtName.setEnabled(false);
        edtLName.setEnabled(false);
        edtemail.setEnabled(false);
        edtMobile.setEnabled(false);
        edtPostcode.setEnabled(false);
//        spinnerCity.setEnabled(false);
        edtFlat.setEnabled(false);
        edtNear.setEnabled(false);
        cityTV.setVisibility(View.VISIBLE);
        spinnerCity.setVisibility(View.GONE);
        stateTV.setVisibility(View.VISIBLE);
        spinnerState.setVisibility(View.GONE);


        radio_Group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId)
                {
                    case R.id.home_radio:
                    addressType = "home";
                    break;

                    case R.id.office_radio:
                        addressType = "office";
                        break;

                    case R.id.shop_radio:
                        addressType = "shop";
                        break;
                }
            }
        });


        spinnerState.setOnItemSelectedListener(this);
        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                city = parent.getSelectedItem().toString();
                cityTV.setText(city);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



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
                            TextUtils.isEmpty(edtNear.getText().toString())) {
                        edtName.setError("Enter Field");
                        edtName.setFocusable(true);
                        edtLName.setError("Enter Field");
                        edtLName.setFocusable(true);
                        edtMobile.setError("Enter Field");
                        edtMobile.setFocusable(true);
                        edtemail.setError("Enter Field");
                        edtemail.setFocusable(true);
                        edtPostcode.setError("Enter Field");
                        edtPostcode.setFocusable(true);
                        edtFlat.setError("Enter Field");
                        edtFlat.setFocusable(true);
                        edtNear.setError("Enter Field");
                        edtNear.setFocusable(true);
//                        edtTown.setError("Enter Field");
                        Util.showToastMessage(ADDAddressActivity.this,"Add Complete Address to place order",getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
                        btnAdd.setEnabled(false);
                    } else if (TextUtils.isEmpty(edtName.getText().toString())) {
                        Util.showToastMessage(ADDAddressActivity.this,"Add Complete Address to place order",getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
                        btnAdd.setEnabled(false);
                    } else if (TextUtils.isEmpty(edtLName.getText().toString())) {
                        Util.showToastMessage(ADDAddressActivity.this,"Add Complete Address to place order",getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
                        btnAdd.setEnabled(false);
                    } else if (TextUtils.isEmpty(edtMobile.getText().toString())) {
                        Util.showToastMessage(ADDAddressActivity.this,"Add Complete Address to place order",getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
                        btnAdd.setEnabled(false);
                    } else if (TextUtils.isEmpty(edtemail.getText().toString())) {
                        Util.showToastMessage(ADDAddressActivity.this,"Add Complete Address to place order",getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
                        btnAdd.setEnabled(false);
                    } else if (TextUtils.isEmpty(edtPostcode.getText().toString())) {
                        Util.showToastMessage(ADDAddressActivity.this,"Add Complete Address to place order",getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
                        btnAdd.setEnabled(false);
                    } else if (TextUtils.isEmpty(edtFlat.getText().toString())) {
                        Util.showToastMessage(ADDAddressActivity.this,"Add Complete Address to place order",getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
                        btnAdd.setEnabled(false);
                    } else if (TextUtils.isEmpty(edtNear.getText().toString())) {
                        Util.showToastMessage(ADDAddressActivity.this,"Add Complete Address to place order",getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
                        btnAdd.setEnabled(false);
                    }  else {
                        if (Util.isOnline(ADDAddressActivity.this) != false) {
                            dialog.show();
                            controller.setPlaceOrder("bacs", "Direct Bank Transfer", "true", edtName.getText().toString(), edtLName.getText().toString(),
                                    edtFlat.getText().toString(), "",cityTV.getText().toString(), stateTV.getText().toString(), edtPostcode.getText().toString(),
                                    "India", edtemail.getText().toString(), edtMobile.getText().toString(), edtName.getText().toString(), edtLName.getText().toString(),
                                    edtFlat.getText().toString(), "",cityTV.getText().toString(), stateTV.getText().toString(), edtPostcode.getText().toString(),
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
                            TextUtils.isEmpty(edtNear.getText().toString()) ) {
                        edtName.setError("Enter Field");
                        edtLName.setError("Enter Field");
                        edtMobile.setError("Enter Field");
                        edtemail.setError("Enter Field");
                        edtPostcode.setError("Enter Field");
                        edtFlat.setError("Enter Field");
                        edtNear.setError("Enter Field");
                        Util.showToastMessage(ADDAddressActivity.this,"Add Complete Address to place order",getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
//                        edtTown.setError("Enter Field");
                        btnAdd.setEnabled(false);
                    } else if (TextUtils.isEmpty(edtName.getText().toString())) {
                        Util.showToastMessage(ADDAddressActivity.this,"Add Complete Address to place order",getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
                        btnAdd.setEnabled(false);
                    } else if (TextUtils.isEmpty(edtLName.getText().toString())) {
                        Util.showToastMessage(ADDAddressActivity.this,"Add Complete Address to place order",getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
                        btnAdd.setEnabled(false);
                    } else if (TextUtils.isEmpty(edtMobile.getText().toString())) {
                        Util.showToastMessage(ADDAddressActivity.this,"Add Complete Address to place order",getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
                        btnAdd.setEnabled(false);
                    } else if (TextUtils.isEmpty(edtemail.getText().toString())) {
                        Util.showToastMessage(ADDAddressActivity.this,"Add Complete Address to place order",getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
                        btnAdd.setEnabled(false);
                    } else if (TextUtils.isEmpty(edtPostcode.getText().toString())) {
                        Util.showToastMessage(ADDAddressActivity.this,"Add Complete Address to place order",getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
                        btnAdd.setEnabled(false);
                    } else if (TextUtils.isEmpty(edtFlat.getText().toString())) {
                        Util.showToastMessage(ADDAddressActivity.this,"Add Complete Address to place order",getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
                        btnAdd.setEnabled(false);
                    } else if (TextUtils.isEmpty(edtNear.getText().toString())) {
                        Util.showToastMessage(ADDAddressActivity.this,"Add Complete Address to place order",getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
                        btnAdd.setEnabled(false);
                    }  else {
                        if (Util.isOnline(ADDAddressActivity.this) != false) {
                            dialog.show();
                            controller.setPlaceOrder1_("bacs", "Direct Bank Transfer", true, edtName.getText().toString(), edtLName.getText().toString(),
                                    edtFlat.getText().toString(), "", cityTV.getText().toString(), stateTV.getText().toString(), edtPostcode.getText().toString(),
                                    "India", edtemail.getText().toString(), edtMobile.getText().toString(), edtName.getText().toString(), edtLName.getText().toString(),
                                    edtFlat.getText().toString(), "", cityTV.getText().toString(), stateTV.getText().toString(), edtPostcode.getText().toString(),
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

                        home_radio.setVisibility(View.VISIBLE);
                        office_radio.setVisibility(View.VISIBLE);
                        shop_radio.setVisibility(View.VISIBLE);
                        btnAedit.setText("Save");
                        edtName.setEnabled(true);
                        edtLName.setEnabled(true);
                        edtemail.setEnabled(true);
                        edtMobile.setEnabled(true);
                        edtPostcode.setEnabled(true);
//                        edtTown.setEnabled(true);
                        cityTV.setVisibility(View.GONE);
                        spinnerCity.setVisibility(View.VISIBLE);
                        stateTV.setVisibility(View.GONE);
                        spinnerState.setVisibility(View.VISIBLE);
                        edtFlat.setEnabled(true);
                        edtNear.setEnabled(true);
                        btnAdd.setEnabled(false);
                    }else if (TextUtils.isEmpty(edtName.getText().toString()) && TextUtils.isEmpty(edtLName.getText().toString()) && TextUtils.isEmpty(edtMobile.getText().toString()) &&
                            TextUtils.isEmpty(edtemail.getText().toString()) && TextUtils.isEmpty(edtFlat.getText().toString()) && TextUtils.isEmpty(edtNear.getText().toString()) &&
                           TextUtils.isEmpty(edtPostcode.getText().toString()))
                    {

                        btnAedit.setText("Save");
                        edtName.setEnabled(true);
                        edtLName.setEnabled(true);
                        edtemail.setEnabled(true);
                        edtMobile.setEnabled(true);
                        edtPostcode.setEnabled(true);
                        edtFlat.setEnabled(true);
                        edtNear.setEnabled(true);
                        btnAdd.setEnabled(false);
                        edtName.setError("Enter Firstname");
                        edtLName.setError("Enter Lastname");
                        edtMobile.setError("Enter Mobile number");
                        edtPostcode.setError("Enter Postcode");
                        edtemail.setError("Enter Email");
//                            edtTown.setError("Enter city");
                        edtFlat.setError("Enter Address");
                        edtNear.setError("Enter Landmark");
                        edtName.setFocusable(true);
                    }else if (TextUtils.isEmpty(edtName.getText().toString()))
                    { home_radio.setClickable(true);

                        btnAedit.setText("Save");
                        edtName.setEnabled(true);
                        edtLName.setEnabled(true);
                        edtemail.setEnabled(true);
                        edtMobile.setEnabled(true);
                        edtPostcode.setEnabled(true);
                        edtFlat.setEnabled(true);
                        edtNear.setEnabled(true);
                        btnAdd.setEnabled(false);
                        edtName.setFocusable(true);
                        edtName.setError("Enter Firstname");
                    }else if (TextUtils.isEmpty(edtLName.getText().toString()))
                    {
                        btnAedit.setText("Save");
                        edtName.setEnabled(true);
                        edtLName.setEnabled(true);
                        edtemail.setEnabled(true);
                        edtMobile.setEnabled(true);
                        edtPostcode.setEnabled(true);
                        edtFlat.setEnabled(true);
                        edtNear.setEnabled(true);
                        btnAdd.setEnabled(false);
                        edtLName.setError("Enter Lastname");
                        edtLName.setFocusable(true);

                    }else if (TextUtils.isEmpty(edtMobile.getText().toString()))
                    {
                        btnAedit.setText("Save");
                        edtName.setEnabled(true);
                        edtLName.setEnabled(true);
                        edtemail.setEnabled(true);
                        edtMobile.setEnabled(true);
                        edtPostcode.setEnabled(true);
                        edtFlat.setEnabled(true);
                        edtNear.setEnabled(true);
                        btnAdd.setEnabled(false);

                        edtMobile.setFocusable(true);
                        edtMobile.setError("Enter Mobile number");

                    }else if (TextUtils.isEmpty(edtemail.getText().toString()))
                    {
                        btnAedit.setText("Save");
                        edtName.setEnabled(true);
                        edtLName.setEnabled(true);
                        edtemail.setEnabled(true);
                        edtMobile.setEnabled(true);
                        edtPostcode.setEnabled(true);
                        edtFlat.setEnabled(true);
                        edtNear.setEnabled(true);
                        btnAdd.setEnabled(false);
                        edtemail.setFocusable(true);
                        edtemail.setError("Enter email");
                    }else if (TextUtils.isEmpty(edtFlat.getText().toString()))
                    {
                        btnAedit.setText("Save");
                        edtName.setEnabled(true);
                        edtLName.setEnabled(true);
                        edtemail.setEnabled(true);
                        edtMobile.setEnabled(true);
                        edtPostcode.setEnabled(true);
                        edtFlat.setEnabled(true);
                        edtNear.setEnabled(true);
                        btnAdd.setEnabled(false);
                        edtFlat.setFocusable(true);
                        edtFlat.setError("Enter Address");
                    }else if ( TextUtils.isEmpty(edtNear.getText().toString()))
                    {
                        btnAedit.setText("Save");
                        edtName.setEnabled(true);
                        edtLName.setEnabled(true);
                        edtemail.setEnabled(true);
                        edtMobile.setEnabled(true);
                        edtPostcode.setEnabled(true);
                        edtFlat.setEnabled(true);
                        edtNear.setEnabled(true);
                        btnAdd.setEnabled(false);
                        edtNear.setFocusable(true);
                        edtNear.setError("Enter Landmark");
                    }else if (TextUtils.isEmpty(edtPostcode.getText().toString()))
                    {
                        btnAedit.setText("Save");
                        edtName.setEnabled(true);
                        edtLName.setEnabled(true);
                        edtemail.setEnabled(true);
                        edtMobile.setEnabled(true);
                        edtPostcode.setEnabled(true);
                        edtFlat.setEnabled(true);
                        edtNear.setEnabled(true);
                        btnAdd.setEnabled(false);
                        edtPostcode.setFocusable(true);
                        edtPostcode.setError("Enter Postcode");
                    }else {

                        edtName.setEnabled(false);
                        edtLName.setEnabled(false);
                        edtemail.setEnabled(false);
                        edtMobile.setEnabled(false);
                        edtPostcode.setEnabled(false);
                        cityTV.setVisibility(View.VISIBLE);
                        spinnerCity.setVisibility(View.GONE);
                        stateTV.setVisibility(View.VISIBLE);
                        spinnerState.setVisibility(View.GONE);
//                        edtTown.setEnabled(false);

                        edtFlat.setEnabled(false);
                        edtNear.setEnabled(false);
                        btnAdd.setEnabled(true);
                        btnAdd.setVisibility(View.VISIBLE);

                        /*if (TextUtils.isEmpty(edtName.getText().toString()) && TextUtils.isEmpty(edtLName.getText().toString()) && TextUtils.isEmpty(edtMobile.getText().toString()) && TextUtils.isEmpty(edtPostcode.getText().toString()) &&
                                 TextUtils.isEmpty(edtFlat.getText().toString()) &&
                                TextUtils.isEmpty(edtNear.getText().toString())) {

                            edtName.setError("Enter Firstname");
                            edtLName.setError("Enter Lastname");
                            edtMobile.setError("Enter Mobile number");
                            edtPostcode.setError("Enter Postcode");
                            edtFlat.setError("Enter Address");
                            edtNear.setError("Enter Landmark");
                            edtName.setFocusable(true);
                        } else if (TextUtils.isEmpty(edtName.getText().toString())) {
                            edtName.setError("Enter Name");
                        } else if (TextUtils.isEmpty(edtLName.getText().toString())) {
                            edtLName.setError("Enter Lastname");
                        } else if (TextUtils.isEmpty(edtMobile.getText().toString())) {
                            edtMobile.setError("Enter Mobile number");
                        } else if (TextUtils.isEmpty(edtPostcode.getText().toString())) {
                            edtPostcode.setError("Enter Postcode");
                        }  else if (TextUtils.isEmpty(edtFlat.getText().toString())) {
                            edtFlat.setError("Enter Address");
                        } else if (TextUtils.isEmpty(edtNear.getText().toString())) {
                            edtNear.setError("Enter Landmark");
                        } else {*/
                            dialog.show();
                            controller.setUpdateAddress(getStringVal(Constants.USER_ID),"update_address", getStringVal(Constants.CONSUMER_KEY), getStringVal(Constants.CONSUMER_SECRET),
                                    edtName.getText().toString() ,edtLName.getText().toString(), edtFlat.getText().toString(), edtNear.getText().toString(), cityTV.getText().toString(),
                                    edtPostcode.getText().toString(), stateTV.getText().toString(), edtMobile.getText().toString(),edtemail.getText().toString(),addressType);
//                        }
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
                edtLName.setText(response.body().getData().getBilling().getLastName());
                edtMobile.setText(response.body().getData().getBilling().getPhone());
                edtPostcode.setText(response.body().getData().getBilling().getPostcode());
                stateTV.setText(response.body().getData().getBilling().getState());
                cityTV.setText(response.body().getData().getBilling().getCity());
                if (response.body().getData().getBilling().getAddress_type().equalsIgnoreCase("Home"))
                {
                    home_radio.setChecked(true);
                    office_radio.setVisibility(View.GONE);
                    shop_radio.setVisibility(View.GONE);
                }else if (response.body().getData().getBilling().getAddress_type().equalsIgnoreCase("Office"))
                {
                    home_radio.setVisibility(View.GONE);
                    office_radio.setChecked(true);
                    shop_radio.setVisibility(View.GONE);
                }else if (response.body().getData().getBilling().getAddress_type().equalsIgnoreCase("Shop"))
                {
                    home_radio.setVisibility(View.GONE);
                    office_radio.setVisibility(View.GONE);
                    shop_radio.setChecked(true);
                }

//                edtTown.setText(response.body().getData().getBilling().getCity());
                edtFlat.setText(response.body().getData().getBilling().getAddress1());
                edtNear.setText(response.body().getData().getBilling().getAddress2());
                edtemail.setText(response.body().getData().getBilling().getEmail());
            }
        }
    }

    @Override
    public void onSuccessUpdateAddress(Response<UpdateAddress> response) {
        dialog.dismiss();
        if (response.isSuccessful()) {
            btnAedit.setText("Change Address");
            if (addressType.equalsIgnoreCase("Home"))
            {
                home_radio.setChecked(true);
                office_radio.setVisibility(View.GONE);
                shop_radio.setVisibility(View.GONE);
            }else if (addressType.equalsIgnoreCase("Office"))
            {
                home_radio.setVisibility(View.GONE);
                office_radio.setChecked(true);
                shop_radio.setVisibility(View.GONE);
            }else if (addressType.equalsIgnoreCase("Shop"))
            {
                home_radio.setVisibility(View.GONE);
                office_radio.setVisibility(View.GONE);
                shop_radio.setChecked(true);
            }
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
    public void onSuccessZones(Response<GetZones> getZonesResponse) {
        dialog.dismiss();
        if (getZonesResponse.isSuccessful()) {
            if (getZonesResponse.body().getStatus() == 200) {
                for (int i = 0; i < getZonesResponse.body().getData().size(); i++) {
                    GetZones.Datum state = getZonesResponse.body().getData().get(i);
                    getZoneData.add(state);

                getZonesArrayList.add(getZonesResponse.body().getData().get(i).getState());
//                getZonesID.add(String.valueOf(getZonesResponse.body().getData().getState().get(i).getId()));
                }
                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                        (ADDAddressActivity.this, android.R.layout.simple_spinner_item, getZonesArrayList); //selected item will look like a spinner set from XML
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                        .simple_spinner_dropdown_item);
                spinnerState.setAdapter(spinnerArrayAdapter);

            }


        }

    }


    @Override
    public void onSuccessCities(Response<GetCities> getCitiesResponse) {
        dialog.dismiss();
        if (getCitiesResponse.isSuccessful())
        {
            if (getCitiesResponse.body().getStatus()==200)
            {
                getCities.clear();
                for (int i=0;i<getCitiesResponse.body().getData().size();i++)
                {
                    getCities.add(getCitiesResponse.body().getData().get(i));
                    String getCities = getCitiesResponse.body().getData().get(i);

                }
                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                        (ADDAddressActivity.this, android.R.layout.simple_spinner_item, getCities); //selected item will look like a spinner set from XML
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                        .simple_spinner_dropdown_item);
                spinnerCity.setAdapter(spinnerArrayAdapter);
            }
        }
    }

    @Override
    public void onError(String error) {
        dialog.dismiss();
        Util.showToastMessage(this, error, getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        dialog.show();
        stateTV.setText(parent.getSelectedItem().toString());
       controller.setGetCities(String.valueOf(getZoneData.get(position).getId()));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
