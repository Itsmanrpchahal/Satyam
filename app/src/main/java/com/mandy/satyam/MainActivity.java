package com.mandy.satyam;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.crashlytics.android.Crashlytics;
import com.mandy.satyam.commonActivity.CustmerActivity;
import com.mandy.satyam.commonActivity.NoInternetActivity;
import com.mandy.satyam.myCart.MyCartActivity;
import com.mandy.satyam.productDetails.apis.GetAddToCart;
import com.mandy.satyam.myProfile.ProfileActivity;
import com.mandy.satyam.myProfile.ProfileApi;
import com.mandy.satyam.myOrderList.MyOrderListActivity;
import com.mandy.satyam.retrofit.ApiInterface;
import com.mandy.satyam.retrofit.ServiceGenerator;
import com.mandy.satyam.searchActivity.SearchActivity;
import com.mandy.satyam.termsandcondition.TermsActivity;
import com.mandy.satyam.utils.CheckInternet;
import com.mandy.satyam.utils.SharedToken;
import com.mandy.satyam.utils.Snack;
import com.mandy.satyam.utils.UtilDialog;
import com.mandy.satyam.homeFragment.HomeFragment;

import java.util.ArrayList;
import java.util.List;

import io.fabric.sdk.android.Fabric;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public static TextView textView;
    public static TextView txtcartNumber;
    public static ProfileApi.Data data;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ImageView toolbarSearch, toolbarCart;
    FrameLayout framelayout;
    ActionBarDrawerToggle mToggle;
    FragmentManager manager;
    FragmentTransaction transaction;


    public static final int MULTIPLE_PERMISSIONS = 10;
    String[] permissions = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA};

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(MainActivity.this, new Crashlytics());
        setContentView(R.layout.activity_main);
        navigationView = (NavigationView) findViewById(R.id.Drawer_navigation);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerNavigation);
        toolbar = (Toolbar) findViewById(R.id.tooolbar);
        toolbarSearch = (ImageView) findViewById(R.id.toolbarSearch);
        toolbarCart = (ImageView) findViewById(R.id.toolbarCart);
        txtcartNumber = (TextView) findViewById(R.id.toolbarCartNumber);
        textView = (TextView) findViewById(R.id.txtToolbar);
        framelayout = (FrameLayout) findViewById(R.id.framelayout);

        View hView = navigationView.inflateHeaderView(R.layout.header);
        ImageView imgvw = (ImageView) hView.findViewById(R.id.imageView);
        TextView tv = (TextView) hView.findViewById(R.id.textView);
        //checkPermissions();
        mToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);

        drawerLayout.addDrawerListener(mToggle);

        mToggle.syncState();
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_drawer);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        textView.setText("Satyam");

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();

        transaction = manager.beginTransaction();
        transaction.replace(R.id.framelayout, new HomeFragment());
        transaction.commit();
       // framelayout.setVisibility(View.VISIBLE);


        onNavigationClick();

        toolbarSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //done
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });


        toolbarCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myCartIntent = new Intent(MainActivity.this, MyCartActivity.class);
                startActivity(myCartIntent);
            }
        });

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
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();

                switch (id) {

                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.myorderlist:
                        drawerLayout.closeDrawers();
                        Intent intent = new Intent(MainActivity.this, MyOrderListActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.cart:
                        drawerLayout.closeDrawers();
                        Intent myCartIntent = new Intent(MainActivity.this, MyCartActivity.class);
                        startActivity(myCartIntent);
                        break;
                    case R.id.my_profile:
                        drawerLayout.closeDrawers();
                        Intent intent1 = new Intent(MainActivity.this, ProfileActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.my_logout:
                        drawerLayout.closeDrawers();
                        Dialog dialog = UtilDialog.dialog(MainActivity.this, getResources().getString(R.string.logout), "Logout");
                        dialog.show();
                        break;

                    case R.id.term:
                        drawerLayout.closeDrawers();
                        Intent intent2 = new Intent(MainActivity.this, TermsActivity.class);
                        intent2.putExtra("T", "T");
                        startActivity(intent2);
                        break;
                    case R.id.customer:
                        drawerLayout.closeDrawers();
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
        if (getFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        } else {
            super.onBackPressed();
        }
    }
}
