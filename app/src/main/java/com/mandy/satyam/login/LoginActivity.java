package com.mandy.satyam.login;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.mandy.satyam.R;
import com.mandy.satyam.baseclass.BaseClass;
import com.mandy.satyam.baseclass.Constants;
import com.mandy.satyam.controller.Controller;
import com.mandy.satyam.login.model.LoginCheck;
import com.mandy.satyam.opt.OTP_verify;
import com.mandy.satyam.utils.Util;
import com.rilixtech.widget.countrycodepicker.CountryCodePicker;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.michaelrocks.libphonenumber.android.NumberParseException;
import io.michaelrocks.libphonenumber.android.PhoneNumberUtil;
import io.michaelrocks.libphonenumber.android.Phonenumber;
import retrofit2.Response;

public class LoginActivity extends BaseClass implements Controller.LoginCheck {
    Button btnLogin;

    String token = "";
    @BindView(R.id.loginclose)
    ImageButton loginclose;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.logintext)
    TextView logintext;
    @BindView(R.id.ccp)
    CountryCodePicker ccp;
    @BindView(R.id.phone_number_et)
    EditText phoneNumberEt;
    @BindView(R.id.phn_no_layout)
    LinearLayout phnNoLayout;
    @BindView(R.id.set_otp_button)
    Button set_otp_button;
    @BindView(R.id.loginwith_email_tv)
    TextView loginwith_email_tv;
    String type = "phone";
    Dialog dialog;
    Controller controller;
    String phone, countrycode;
    String productID,isFrom,is_address_update;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        controller = new Controller(this);
        dialog = Util.showDialog(LoginActivity.this);
        intent = getIntent();
        if (intent!=null)
        {
            productID = intent.getStringExtra("productID");
            isFrom = intent.getStringExtra("isFrom");
            is_address_update = intent.getStringExtra("is_address_update");
        }

        listeners();

    }

    private void listeners() {
        loginclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        loginwith_email_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (intent.getStringExtra("isFrom")!=null)
                {
                    if (intent.getStringExtra("isFrom").equals("ProductDetail"))
                    {
                        Intent intent = new Intent(LoginActivity.this, EmailLogin.class);
                        intent.putExtra("productID",productID);
                        intent.putExtra("is_address_update",is_address_update);
                        intent.putExtra("isFrom","ProductDetail");
                        startActivity(intent);
                        finish();
                    }
                }
                else {
                    Intent intent = new Intent(LoginActivity.this, EmailLogin.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        set_otp_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                phone = phoneNumberEt.getText().toString();
                countrycode = ccp.getSelectedCountryCode();

                if (!phone.equals("")) {

                    if (isValidPhoneNumber(phone) == true)
                    {
                        if (validateUsing_libphonenumber(countrycode,phone)==true)
                        {
                            if (Util.isOnline(LoginActivity.this) != false) {
                                dialog.show();
                                controller.setLoginCheck(countrycode+""+phone, type);

                            } else {
                                Util.showToastMessage(LoginActivity.this, "No Internet connection", getResources().getDrawable(R.drawable.ic_nointernet));
                            }
                        }else {
                            Util.showToastMessage(LoginActivity.this,"Invalid Phone Number",getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
                        }
                    }
                    
                } else {
                    phoneNumberEt.setFocusable(true);
                    phoneNumberEt.setError("Enter Phone number");
                }
            }
        });
    }

    private boolean isValidPhoneNumber(CharSequence phoneNumber) {
        if (!TextUtils.isEmpty(phoneNumber)) {
            return Patterns.PHONE.matcher(phoneNumber).matches();
        }
        return false;
    }

    private boolean validateUsing_libphonenumber(String countryCode, String phNumber) {
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.createInstance(this);
        String isoCode = phoneNumberUtil.getRegionCodeForCountryCode(Integer.parseInt(countryCode));
        Phonenumber.PhoneNumber phoneNumber = null;
        try {
            //phoneNumber = phoneNumberUtil.parse(phNumber, "IN");  //if you want to pass region code
            phoneNumber = phoneNumberUtil.parse(phNumber, isoCode);
        } catch (NumberParseException e) {
            System.err.println(e);
        }

        boolean isValid = phoneNumberUtil.isValidNumber(phoneNumber);
        if (isValid) {
            String internationalFormat = phoneNumberUtil.format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL);
//            Toast.makeText(this, "Phone Number is Valid" + internationalFormat, Toast.LENGTH_LONG).show();
            return true;
        } else {
//            Toast.makeText(this, "Phone Number is Invalid" + phoneNumber, Toast.LENGTH_LONG).show();
            return false;
        }
    }

    @Override
    public void onSuccessLoginCheck(Response<LoginCheck> loginCheckResponse) {
        dialog.dismiss();
        if (loginCheckResponse.body().getStatus() == 200) {
//            setStringVal(Constants.LOGIN_STATUS,"login");
            if (intent.getStringExtra("isFrom")!=null)
            {
                if (intent.getStringExtra("isFrom").equals("ProductDetail"))
                {
                    Intent intent = new Intent(LoginActivity.this, OTP_verify.class);
                    intent.putExtra("productID",productID);
                    intent.putExtra("isFrom","ProductDetail");
                    intent.putExtra("OTP", loginCheckResponse.body().getData().getOtp().toString());
                    intent.putExtra("phonenumber",countrycode+""+phone);
                    startActivity(intent);
                    finish();
                }
            }
            else {
                Intent intent = new Intent(LoginActivity.this, OTP_verify.class);
                intent.putExtra("OTP", loginCheckResponse.body().getData().getOtp().toString());
                intent.putExtra("phonenumber",countrycode+""+phone);
                startActivity(intent);
            }

        } else {
            Util.showToastMessage(LoginActivity.this, loginCheckResponse.body().getMessage(), getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
        }
    }

    @Override
    public void onError(String error) {
        dialog.dismiss();
        Util.showToastMessage(LoginActivity.this, error, getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
    }
}
