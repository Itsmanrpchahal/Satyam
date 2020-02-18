package com.mandy.satyam;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.crashlytics.android.Crashlytics;
import com.google.android.material.navigation.NavigationView;
import com.mandy.satyam.baseclass.BaseClass;
import com.mandy.satyam.baseclass.Constants;
import com.mandy.satyam.commonActivity.CustmerActivity;
import com.mandy.satyam.controller.Controller;
import com.mandy.satyam.filterScreen.response.FilterResponse;
import com.mandy.satyam.homeFragment.HomeFragment;
import com.mandy.satyam.login.LoginActivity;
import com.mandy.satyam.myCart.MyCartActivity;
import com.mandy.satyam.myOrderList.MyOrderListActivity;
import com.mandy.satyam.myProfile.ProfileActivity;
import com.mandy.satyam.myProfile.ProfileApi;
import com.mandy.satyam.productList.ProductsActivity;
import com.mandy.satyam.searchActivity.SearchActivity;
import com.mandy.satyam.termsandcondition.TermsActivity;
import com.mandy.satyam.utils.Util;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.fabric.sdk.android.Fabric;
import retrofit2.Response;

public class MainActivity extends BaseClass implements Controller.Keys {

    public static ProfileApi.Data data;

    ActionBarDrawerToggle mToggle;
    FragmentManager manager;
    FragmentTransaction transaction;


    public static final int MULTIPLE_PERMISSIONS = 10;
    String[] permissions = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA};
    @BindView(R.id.txtToolbar)
    TextView txtToolbar;
    @BindView(R.id.product_cart)
    ImageButton productCart;
    @BindView(R.id.cart_count)
    TextView cartCount;
    @BindView(R.id.cartlayout)
    RelativeLayout cartlayout;
    @BindView(R.id.toolbarSearch)
    ImageView toolbarSearch;
    @BindView(R.id.tooolbar)
    Toolbar tooolbar;
    @BindView(R.id.framelayout)
    FrameLayout framelayout;
    @BindView(R.id.Drawer_navigation)
    NavigationView DrawerNavigation;
    @BindView(R.id.drawerNavigation)
    DrawerLayout drawerNavigation;
    Dialog exit_dialog;
    Button OK, CANCEL;

    String typeIntent = "";
    @BindView(R.id.loginmain)
    TextView loginmain;
    Controller controller;
    @BindView(R.id.searchProduct)
    AutoCompleteTextView searchProduct;
    @BindView(R.id.searchProducts)
    ImageButton searchProducts;
    ArrayList<FilterResponse.Datum.Image> filterImages = new ArrayList<>();
    ArrayList<FilterResponse.Datum> filterDatumArraylist = new ArrayList<FilterResponse.Datum>();

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(MainActivity.this, new Crashlytics());
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        controller = new Controller((Controller.Keys)this);
        controller.setKeys("SBWoiw9UE9qx4NVLSHC9");

        View hView = DrawerNavigation.inflateHeaderView(R.layout.header);
        ImageView imgvw = (ImageView) hView.findViewById(R.id.imageView);
        TextView tv = (TextView) hView.findViewById(R.id.textView);

        tv.setText(getStringVal(Constants.FIRSTNAME) + " " + getStringVal(Constants.LASTNAME));
        Glide.with(this).load(getStringVal(Constants.AVATAR)).placeholder(R.drawable.ic_satyamplaceholder).into(imgvw);

        //checkPermissions();
//        Toast.makeText(this, ""+getStringVal(Constants.FIRSTNAME)+" "+getStringVal(Constants.LASTNAME), Toast.LENGTH_SHORT).show();
        mToggle = new ActionBarDrawerToggle(this, drawerNavigation, R.string.open, R.string.close);

        drawerNavigation.addDrawerListener(mToggle);

        mToggle.syncState();
        setSupportActionBar(tooolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_drawer);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        txtToolbar.setText("Satyam");

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();

        transaction = manager.beginTransaction();
        transaction.replace(R.id.framelayout, new HomeFragment());
        transaction.commit();
        // framelayout.setVisibility(View.VISIBLE);


        lisenters();

    }

    private void lisenters() {

        toolbarSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //done
               toolbarSearch.setVisibility(View.GONE);
               productCart.setVisibility(View.GONE);
               cartCount.setVisibility(View.GONE);
               cartlayout.setVisibility(View.GONE);
                txtToolbar.setVisibility(View.GONE);
               searchProduct.setVisibility(View.VISIBLE);
                searchProducts.setVisibility(View.VISIBLE);
            }
        });


        productCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myCartIntent = new Intent(MainActivity.this, MyCartActivity.class);
                startActivity(myCartIntent);
            }
        });

        loginmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        searchProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ProductsActivity.class);
                intent.putExtra("isFrom","main");
                intent.putExtra("search",searchProduct.getText().toString());
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        onNavigationClick();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    // navigation item click
    private void onNavigationClick() {

        // get menu from navigationView
        final Menu menu = DrawerNavigation.getMenu();
        // do the same for other MenuItems
        final MenuItem textLogout = menu.findItem(R.id.my_logout);
        MenuItem myorderlist = menu.findItem(R.id.myorderlist);
        MenuItem mycart = menu.findItem(R.id.cart);
        MenuItem profile = menu.findItem(R.id.my_profile);
        if (getStringVal(Constants.LOGIN_STATUS).equals("login")) {
            textLogout.setTitle("Logout");
        } else {
            String logutText = (String) textLogout.getTitle();
            menu.findItem(R.id.my_logout).setIcon(R.drawable.ic_login);
            textLogout.setTitle("Login");
            loginmain.setVisibility(View.VISIBLE);
            mycart.setVisible(false);
            myorderlist.setVisible(false);
            profile.setVisible(false);
            productCart.setVisibility(View.GONE);
            cartCount.setVisibility(View.GONE);
        }

        DrawerNavigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();


                switch (id) {
                    case R.id.home:
                        drawerNavigation.closeDrawers();
                        Intent inten = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(inten);
                        break;

                    case R.id.myorderlist:
                        drawerNavigation.closeDrawers();
                        Intent intent = new Intent(MainActivity.this, MyOrderListActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.cart:
                        drawerNavigation.closeDrawers();
                        Intent myCartIntent = new Intent(MainActivity.this, MyCartActivity.class);
                        startActivity(myCartIntent);
                        break;
                    case R.id.my_profile:
                        drawerNavigation.closeDrawers();
                        Intent intent1 = new Intent(MainActivity.this, ProfileActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.my_logout:
                        drawerNavigation.closeDrawers();
                        String logutText = (String) textLogout.getTitle();

                        if (logutText.equals("Login")) {
                            Intent intent2 = new Intent(MainActivity.this, LoginActivity.class);

                            startActivity(intent2);
                        } else {
                            logutText = "Login";
                            textLogout.setTitle("Login");
                            logout();

                        }
                        break;
                    case R.id.term:
                        drawerNavigation.closeDrawers();
                        Intent intent2 = new Intent(MainActivity.this, TermsActivity.class);
                        intent2.putExtra("T", "T");
                        startActivity(intent2);
                        break;
                    case R.id.customer:
                        drawerNavigation.closeDrawers();
                        startActivity(new Intent(getApplicationContext(), CustmerActivity.class));
                        break;
                }
                return false;
            }
        });
    }


    private boolean checkPermissions() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p : permissions) {
            result = ContextCompat.checkSelfPermission(this, p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        /*if (getFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        } else {
            super.onBackPressed();
        }*/
        dialog();
    }

    private void dialog() {
        exit_dialog = new Dialog(MainActivity.this);
        Window window = exit_dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        exit_dialog.setContentView(R.layout.exit_dialog);
        exit_dialog.setCancelable(true);
        exit_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        exit_dialog.show();

        OK = exit_dialog.findViewById(R.id.ok_exit);
        CANCEL = exit_dialog.findViewById(R.id.cancel_exit);

        OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });

        CANCEL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exit_dialog.dismiss();
            }
        });
    }

    public void logout() {
        final Dialog dialog = new Dialog(MainActivity.this);

        dialog.setContentView(R.layout.custom_dialog);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        TextView textView;
        final Button btnCancel, btnLogout;

        textView = dialog.findViewById(R.id.txtTitle);
        btnCancel = dialog.findViewById(R.id.btnCancel);
        btnLogout = dialog.findViewById(R.id.btnLogout);

        //set the title
        textView.setText("Are you sure to logout");

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        btnLogout.setText("Logout");

        if (btnLogout.getText().equals("Logout")) {
            btnLogout.setBackgroundColor(this.getResources().getColor(R.color.yellow));
        } else {
            btnLogout.setBackgroundColor(this.getResources().getColor(R.color.red));
        }

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("token", "2");
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
                clearStringVal(Constants.LOGIN_STATUS);
            }
        });
    }


    @Override
    public void onSuccess(Response<KeysResponse> keysResponseResponse) {
        if (keysResponseResponse.isSuccessful()) {
            setStringVal(Constants.CONSUMER_SECRET, keysResponseResponse.body().getData().getConsumerSecret());
            setStringVal(Constants.CONSUMER_KEY, keysResponseResponse.body().getData().getConsumerKey());
        }
    }

    @Override
    public void onError(String error) {

    }
}
