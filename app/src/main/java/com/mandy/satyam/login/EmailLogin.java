package com.mandy.satyam.login;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.vision.L;
import com.mandy.satyam.MainActivity;
import com.mandy.satyam.R;
import com.mandy.satyam.baseclass.BaseClass;
import com.mandy.satyam.baseclass.Constants;
import com.mandy.satyam.controller.Controller;
import com.mandy.satyam.login.model.ClearCart;
import com.mandy.satyam.login.model.Login;
import com.mandy.satyam.login.model.LoginCheck;
import com.mandy.satyam.login.model.SocialLoginResponse;
import com.mandy.satyam.opt.OTP_verify;
import com.mandy.satyam.productDetails.ProductDetailsActivity;
import com.mandy.satyam.utils.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

public class EmailLogin extends BaseClass implements Controller.LoginCheck ,Controller.Login,Controller.ClearCart,Controller.SocailLogin {

    public static int RC_SIGN_IN = 101;
    @BindView(R.id.loginclose)
    ImageButton loginclose;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.logintext)
    TextView logintext;
    @BindView(R.id.phone_number_et)
    EditText phoneNumberEt;
    @BindView(R.id.phn_no_layout)
    LinearLayout phnNoLayout;
    @BindView(R.id.loginwithnumber_tv)
    TextView loginwithnumber_tv;
    @BindView(R.id.continue_bt)
    Button continueBt;
    String type = "email";
    Dialog dialog;
    Controller controller;
    String email;
    @BindView(R.id.password_et)
    EditText passwordEt;
    @BindView(R.id.pass_layout)
    LinearLayout passLayout;
    @BindView(R.id.password_layout)
    RelativeLayout passwordLayout;
    @BindView(R.id.login_bt)
    Button loginBt;
    String pass;
    Intent intent;
    String productID, isFrom;
    LoginButton facebooklogin;
    CallbackManager callbackManager;
    GoogleSignInClient googleSignInClient;
    SignInButton signInButton;
    GoogleApiClient googleApiClient;
    Button facebookBT, googleBT;
    String Fname,Lname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_login);
        ButterKnife.bind(this);
        facebookBT = findViewById(R.id.login_facebook);
        googleBT = findViewById(R.id.login_gmail);
        signInButton = findViewById(R.id.google_bt);
        facebooklogin = findViewById(R.id.facebook_bt);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        final GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        facebooklogin.setReadPermissions(Arrays.asList("email", "public_profile"));
        facebookLogin();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);

        getHashKey();
        intent = getIntent();
        controller = new Controller((Controller.LoginCheck) this, (Controller.Login) this, (Controller.ClearCart) this,(Controller.SocailLogin)this);
        dialog = Util.showDialog(EmailLogin.this);

        if (intent != null) {
            productID = intent.getStringExtra("productID");
            isFrom = intent.getStringExtra("isFrom");
        }
        lisenters();
    }

    public void onClick(View view)
    {
        if (view == facebookBT)
        {
            if (Util.isOnline(EmailLogin.this) != false) {
                facebooklogin.performClick();
            }else {
                Util.showToastMessage(EmailLogin.this, "No Internet connection", getResources().getDrawable(R.drawable.ic_nointernet));
            }
        }

        if (view == googleBT)
        {
            if (Util.isOnline(EmailLogin.this) != false) {
                signInButton.performClick();
                googleSignIn();
            }else {
                Util.showToastMessage(EmailLogin.this, "No Internet connection", getResources().getDrawable(R.drawable.ic_nointernet));
            }

        }
    }

    private void lisenters() {

        phoneNumberEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passLayout.setVisibility(View.GONE);
                passwordLayout.setVisibility(View.GONE);
                loginBt.setVisibility(View.GONE);
                continueBt.setVisibility(View.VISIBLE);
            }
        });

        loginclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        loginwithnumber_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmailLogin.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        continueBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = phoneNumberEt.getText().toString();
                if (!email.equals("")) {

                    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        phoneNumberEt.setFocusable(true);
                        phoneNumberEt.setError("Enter valid email");
                    } else if (Util.isOnline(EmailLogin.this) != false) {
                        dialog.show();
                        controller.setLoginCheck(email, type);
                    } else {
                        Util.showToastMessage(EmailLogin.this, "No Internet connection", getResources().getDrawable(R.drawable.ic_nointernet));
                    }
                } else {
                    phoneNumberEt.setFocusable(true);
                    phoneNumberEt.setError("Enter email");
                }
            }
        });

        loginBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 pass = passwordEt.getText().toString();

                if (TextUtils.isEmpty(passwordEt.getText().toString()))
                {
                    passwordEt.setError("Enter Password");
                    passwordEt.setFocusable(true);
                }else if (Util.isOnline(EmailLogin.this) != false) {
                    dialog.show();
                    controller.setLogin(email,"email",pass);
                }else {
                    Util.showToastMessage(EmailLogin.this, "No Internet connection", getResources().getDrawable(R.drawable.ic_nointernet));
                }
            }
        });
    }

    @Override
    public void onSuccessLoginCheck(Response<LoginCheck> loginCheckResponse) {
        dialog.dismiss();
        if (loginCheckResponse.body().getStatus() == 200) {
            passwordLayout.setVisibility(View.VISIBLE);
            passLayout.setVisibility(View.VISIBLE);
            continueBt.setVisibility(View.GONE);
            loginBt.setVisibility(View.VISIBLE);
        } else if (loginCheckResponse.body().getStatus() == 401) {
            phoneNumberEt.setFocusable(true);
            phoneNumberEt.setError("Email not exist");
        } else {
            Util.showToastMessage(EmailLogin.this, loginCheckResponse.body().getMessage(), getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
        }
    }

    @Override
    public void onsetLogin(Response<Login> loginResponse) {
        dialog.dismiss();
        if (loginResponse.body().getStatus()==200)
        {
            setStringVal(Constants.LOGIN_STATUS,"login");
            setStringVal(Constants.CONSUMER_KEY_LOGIN,loginResponse.body().getData().getConsumerKey());
            setStringVal(Constants.CONSUMER_SECRET_LOGIN,loginResponse.body().getData().getConsumerSecret());
            setStringVal(Constants.EMAIL,loginResponse.body().getData().getEmail());
            setStringVal(Constants.FIRSTNAME,loginResponse.body().getData().getFirstname());
            setStringVal(Constants.LASTNAME,loginResponse.body().getData().getLastname());
            setStringVal(Constants.USER_ID,loginResponse.body().getData().getUserId().toString());
            setStringVal(Constants.AVATAR,loginResponse.body().getData().getAvatar());
            setStringVal(Constants.USERTOKEN,loginResponse.body().getData().getToken());
            if (loginResponse.body().getData().getPhone()!=null)
            {
                setStringVal(Constants.MOBILE,loginResponse.body().getData().getPhone().toString());
            }else {
                setStringVal(Constants.MOBILE,"");
            }
            controller.setClearCart();

        }else {
            Util.showToastMessage(EmailLogin.this, loginResponse.body().getMessage(), getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
        }
    }

    @Override
    public void onSuccessClearCart(Response<ClearCart> response) {
        if (response.body().getStatus()==200)
        {
            if (intent.getStringExtra("isFrom")!=null)
            {
                if (intent.getStringExtra("isFrom").equals("ProductDetail"))
                {
                    Intent intent = new Intent(EmailLogin.this, ProductDetailsActivity.class);
                    intent.putExtra("productID", productID);
                    startActivity(intent);
                    finish();
                }
            }
            else {
                Intent intent = new Intent(EmailLogin.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

        }
    }


    @Override
    public void onError(String error) {
        dialog.dismiss();
        Util.showToastMessage(EmailLogin.this, error, getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
    }

    private void facebookLogin() {

        facebooklogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                boolean loggedIn = AccessToken.getCurrentAccessToken() == null;
                Log.d("API123", loggedIn + " ??");

                //getFaceBookUserProfile(AccessToken.getCurrentAccessToken());

                //==============================get Facebook Information============================

                GraphRequest request = GraphRequest.newMeRequest(
                        AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {

                                if (AccessToken.getCurrentAccessToken() != null) {
                                    try {
                                        String first_name = object.getString("first_name");
                                        String last_name = object.getString("last_name");
                                        String email = "";
                                        if (object.getString("email")!=null)
                                        {
                                            email = object.getString("email");
                                            String id = object.getString("id");

                                            Log.d("fbUsername",first_name + " " + last_name);

                                            final String fbToken1 = AccessToken.getCurrentAccessToken().getToken();

                                            String image_url = "https://graph.facebook.com/" + id + "/picture?type=normal";
                                            Log.d("first_name", first_name + " " + fbToken1 + " " + email + " " + image_url);

                                            if (first_name != null) {
                                                String[] name = first_name.split(" ");
                                                if (name.length == 1) {
                                                    Fname = name[0];
                                                    dialog.show();
                                                    controller.setSocailLogin(fbToken1,"facebook",email,image_url,Fname, "");
                                                } else {
                                                    Fname = name[0];
                                                    Lname = name[1];
                                                    dialog.show();
                                                    controller.setSocailLogin(fbToken1,"facebook",email,image_url,Fname, Lname);
                                                }


                                            }


                                        }else {
                                            Toast.makeText(EmailLogin.this, "We Didn't recog", Toast.LENGTH_SHORT).show();
                                        }


//                                        NormalLogin("normal",fbToken,fbEmail,fbUsername,"facebook");


                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                        Toast.makeText(EmailLogin.this, "No Email found", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "first_name,last_name,email,id");
                request.setParameters(parameters);
                request.executeAsync();

            }

            @Override
            public void onCancel() {
                Toast.makeText(EmailLogin.this, "CANCEL", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(EmailLogin.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //ToDo: Gmail login
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            updateUI(account);

        } catch (ApiException e) {
            updateUI(null);
        }
    }

    private void updateUI(GoogleSignInAccount account) {

        if (account != null) {
            dialog.show();
            final String email = account.getEmail();
            final String username = account.getDisplayName();

            if (username != null) {
                String[] name = username.split(" ");
                if (name.length == 1) {
                     Fname = name[0];

                } else {
                     Fname = name[0];
                     Lname = name[1];

                }

                if (account.getPhotoUrl()!=null) {
                    final String image = account.getPhotoUrl().toString();
                    final String token = account.getId();

                    controller.setSocailLogin(token,"gmail",email,image,Fname, Lname);

                } else if (account.getPhotoUrl()==null){
                    final String token = account.getId();
                    final String image = "data:image/png;base64,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";
                    controller.setSocailLogin(token,"gmail",email,image,Fname, Lname);
                }
            }
        }
    }

    private void googleSignIn() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onSuccessSocailLogin(Response<SocialLoginResponse> socialLoginResponse) {
        dialog.dismiss();
        if (socialLoginResponse.isSuccessful())
        {
            if (socialLoginResponse.body().getStatus()==200)
            {
                setStringVal(Constants.LOGIN_STATUS,"login");
                setStringVal(Constants.CONSUMER_KEY_LOGIN,socialLoginResponse.body().getData().getConsumerKey());
                setStringVal(Constants.CONSUMER_SECRET_LOGIN,socialLoginResponse.body().getData().getConsumerSecret());

                if (socialLoginResponse.body().getData().getAvatar()!=null)
                {
                    setStringVal(Constants.AVATAR,socialLoginResponse.body().getData().getAvatar().toString());
                }else {
                    setStringVal(Constants.AVATAR,"data:image/png;base64,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");
                }

                if (socialLoginResponse.body().getData().getPhone()!=null)
                {
                    setStringVal(Constants.MOBILE,socialLoginResponse.body().getData().getPhone().toString());
                }else {
                    setStringVal(Constants.MOBILE,"");
                }

                setStringVal(Constants.FIRSTNAME,socialLoginResponse.body().getData().getFirstname());
                setStringVal(Constants.LASTNAME,socialLoginResponse.body().getData().getLastname());
                setStringVal(Constants.USER_ID, String.valueOf(socialLoginResponse.body().getData().getUserId()));
                setStringVal(Constants.USERTOKEN,socialLoginResponse.body().getData().getToken());
                setStringVal(Constants.EMAIL,socialLoginResponse.body().getData().getEmail());
                setStringVal(Constants.LOGIN_STATUS, "login");
                Intent intent = new Intent(EmailLogin.this, MainActivity.class);
                intent.putExtra("isFrom","SocailLogin");
                startActivity(intent);
                finish();
            }
        }
    }

    //Genrate HashKey here...
    public void getHashKey() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.mandy.satyam",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }
}
