package com.mandy.satyam.welcomeScreens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mandy.satyam.MainActivity;
import com.mandy.satyam.R;
import com.mandy.satyam.baseclass.BaseClass;
import com.mandy.satyam.baseclass.Constants;
import com.mandy.satyam.welcomeScreens.adapter.SliderAdapter;

import java.util.ArrayList;
import java.util.List;

public class WelcomeScreens extends BaseClass {

    RelativeLayout layout;
    Button mSkip;
    ViewPager viewPager;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    SliderAdapter sliderAdapter;
    RelativeLayout buttonBC;
    ImageButton welcomeNextRound;
    int currentPage, mBackPressCount;
    private static final int MULTIPLE_PERMISSIONS = 10;
    String[] permissions = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_CONTACTS};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screens);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        init();

        checkPermissions();

        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);
        addBottomDots(0);
        viewPager.addOnPageChangeListener(viewListener);


        mSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeScreens.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        welcomeNextRound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(getItem(+1), true);
            }
        });


    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            if (mBackPressCount++ > 0) {
                // if you pressed back for the second time this line will terminate the activity
                super.onBackPressed();
            }
        } else
            super.onBackPressed();
    }

    //find ids here
    private void init() {
        mSkip = findViewById(R.id.mainActivity_skip);
        viewPager = findViewById(R.id.main_viewpager);
        dotsLayout = findViewById(R.id.layoutDots);
        layout = findViewById(R.id.mainActivity_layout);
        welcomeNextRound = findViewById(R.id.welcomeNextRound);
        buttonBC = findViewById(R.id.buttonBC);
    }


    //add dots at bottom
    private void addBottomDots(int currentPage) {
        dots = new TextView[3];
        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {

            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(50);
            dots[i].setTextColor(getResources().getColor(R.color.gray));
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[currentPage].setTextColor(getResources().getColor(R.color.colorAccent));
        }

    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {
        }

        @Override
        public void onPageSelected(int i) {

            addBottomDots(i);

        }

        @Override
        public void onPageScrollStateChanged(int i) {

            if (i == 0) {
                welcomeNextRound.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPager.setCurrentItem(getItem(+1), true);
                    }
                });
            }


            if (currentPage == 0) {
                mSkip.setText("Skip");

            }

            if (currentPage == 1) {
                mSkip.setText("Skip");

            }

            if (i == ViewPager.SCROLL_STATE_IDLE) {
                int curr = viewPager.getCurrentItem();
                int lastReal = viewPager.getAdapter().getCount() - 1;

                if (curr < lastReal) {
                    currentPage = 0;
                    welcomeNextRound.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_chevron_right_white64dp));
                    buttonBC.setBackgroundDrawable(getResources().getDrawable(R.drawable.roundbutton));
                } else if (curr == lastReal) {
                    currentPage++;

                    if (currentPage == 2) {
                        setWelComeString(Constants.WELCOMESTATUS, "2");
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    }

                    if (currentPage == 1) {
                        mSkip.setText("Start");
                        welcomeNextRound.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_done_wite32dp));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            buttonBC.setBackgroundDrawable(getResources().getDrawable(R.drawable.roundgreen));
                        }

                            welcomeNextRound.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    setWelComeString(Constants.WELCOMESTATUS, "2");
                                    Intent intent = new Intent(WelcomeScreens.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            });

                    }
                }
            }
        }
    };

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private boolean checkPermissions() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p : permissions) {
            result = ContextCompat.checkSelfPermission(WelcomeScreens.this, p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(WelcomeScreens.this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), MULTIPLE_PERMISSIONS);
            return false;
        } else {
        }
        return true;
    }
}
