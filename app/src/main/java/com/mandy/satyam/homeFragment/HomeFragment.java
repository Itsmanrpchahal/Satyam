package com.mandy.satyam.homeFragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.mandy.satyam.MainActivity;
import com.mandy.satyam.R;
import com.mandy.satyam.allCategory.AllCategory;
import com.mandy.satyam.baseclass.BaseFrag;
import com.mandy.satyam.baseclass.Constants;
import com.mandy.satyam.controller.Controller;
import com.mandy.satyam.filterScreen.FilterActivity;
import com.mandy.satyam.filterScreen.response.FilterResponse;
import com.mandy.satyam.homeFragment.adapter.CategoryAdapter;
import com.mandy.satyam.homeFragment.adapter.SectionAdapter;
import com.mandy.satyam.homeFragment.adapter.ViewPagerAdapter;
import com.mandy.satyam.homeFragment.response.HomePageResponse;
import com.mandy.satyam.myProfile.ProfileActivity;
import com.mandy.satyam.myProfile.UpdateProfile;
import com.mandy.satyam.productDetails.ProductDetailsActivity;
import com.mandy.satyam.productDetails.adapter.ViewPagerProductImageAdapter;
import com.mandy.satyam.productDetails.response.ProductDetailResponse;
import com.mandy.satyam.productList.ProductsActivity;
import com.mandy.satyam.productList.interface_.GetSubCate_IF;
import com.mandy.satyam.productList.response.GetProductList;
import com.mandy.satyam.utils.Util;
import com.viewpagerindicator.CirclePageIndicator;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Response;


//Manpreet Work
public class HomeFragment extends BaseFrag implements Controller.HomePage {

    View view;
    ArrayList<String> array_image;
    ViewPager viewPager;
    FragmentManager manager;
    Context context;
    Dialog dialog;
    Unbinder unbinder;
    public static ArrayList<HomePageResponse.Data.Category> categories = new ArrayList<>();
    ArrayList<HomePageResponse.Data.Banner> banners = new ArrayList<>();
    ArrayList<HomePageResponse.Data.Section> sections = new ArrayList<>();
    Controller controller;
    RecyclerView home_products_recyler,recyclerViewCategory;
    Dialog progressDialog;
    private Handler handler;
    TextView allcategoryBT;
    private static int currentPage;
    private static int NUM_PAGES;
    int page = 0;
Dialog updateDialog;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        Util.checkPermissions(getContext() );
        progressDialog = Util.showDialog(getContext());
        progressDialog.show();

        categories.clear();
        init();
        Log.d("CONSUMER",getStringVal(Constants.CONSUMER_SECRET)+"  "+getStringVal(Constants.CONSUMER_KEY));

        controller = new Controller((Controller.HomePage)HomeFragment.this);
        if (Util.isOnline(getContext()) != false) {
            controller.setHomePage(getStringVal(Constants.USERTOKEN));
            Log.d("USERTOKEN",getStringVal(Constants.USERTOKEN));
        } else {
            Util.showToastMessage(getContext(), "No Internet connection", getResources().getDrawable(R.drawable.ic_nointernet));
        }

        unbinder = ButterKnife.bind(this, view);

        if (getStringVal(Constants.LOGIN_STATUS).equals("login"))
        {
            if (getStringVal(Constants.MOBILE).equals(""))
            {
               dialog();
            }
        }

        listeners();

        return view;
    }

    private void dialog() {
        updateDialog = new Dialog(getContext());
        Window window = updateDialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        updateDialog.setContentView(R.layout.updateprofile);
        updateDialog.setCancelable(false);
        updateDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        updateDialog.show();

        Button OK;
        OK = updateDialog.findViewById(R.id.ok_exit);

        OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), UpdateProfile.class);
                startActivity(intent);
            }
        });
    }

    private void listeners() {
        allcategoryBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AllCategory.class);
                startActivity(intent);
            }
        });
    }

    private void init() {

        array_image = new ArrayList<String>();
        array_image.add(String.valueOf(R.drawable.best_s));
        array_image.add(String.valueOf(R.drawable.bestseller));
        array_image.add(String.valueOf(R.drawable.ic_satyamplaceholder));
        allcategoryBT = view.findViewById(R.id.allcategoryBT);
        viewPager = (ViewPager) view.findViewById(R.id.recyclerOffer);
        recyclerViewCategory = view.findViewById(R.id.recyclerCategory);
        home_products_recyler = view.findViewById(R.id.home_products_recyler);

        manager = getActivity().getSupportFragmentManager();
    }


    //set image into view pager
    private void setOfferImage(ArrayList<HomePageResponse.Data.Banner> banner) {
        final PagerAdapter adapter;

        CirclePageIndicator tabLayout;
        tabLayout = view.findViewById(R.id.indicator);

        adapter = new ViewPagerAdapter(getContext(), banner);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(1);
        tabLayout.setViewPager(viewPager);
        NUM_PAGES =banner.size();
        //Set circle indicator radius
//        tabLayout.setRadius(5 * density);
//        indicator.setVisibility(View.GONE);
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void onSucessHome(Response<HomePageResponse> homePageResponseResponse) {
        progressDialog.dismiss();
        if (homePageResponseResponse.isSuccessful())
        {
            if (homePageResponseResponse.body().getStatus()==200)
            {
                allcategoryBT.setVisibility(View.VISIBLE);
                setStringVal(Constants.CART_COUNT,homePageResponseResponse.body().getCart_total());
                for (int i=0;i<homePageResponseResponse.body().getData().getCategories().size();i++)
                {
                    HomePageResponse.Data.Category category = homePageResponseResponse.body().getData().getCategories().get(i);
                    categories.add(category);

                    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                    recyclerViewCategory.setLayoutManager(layoutManager);
                    CategoryAdapter categoryAdapter = new CategoryAdapter(getActivity(),categories);
                    recyclerViewCategory.setAdapter(categoryAdapter);
                    categoryAdapter.notifyDataSetChanged();
                }


                for (int i=0;i<homePageResponseResponse.body().getData().getBanners().size();i++)
                {
                    HomePageResponse.Data.Banner banner = homePageResponseResponse.body().getData().getBanners().get(i);
                    banners.add(banner);
                    setOfferImage(banners);
                }


                for (int i=0;i<homePageResponseResponse.body().getData().getSections().size();i++)
                {
                    HomePageResponse.Data.Section section = homePageResponseResponse.body().getData().getSections().get(i);
                    sections.add(section);
                    setSections(sections);
                }
            }else {
                Util.showToastMessage(getContext(),homePageResponseResponse.body().getMessage(),getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
            }
        }

    }

    private void setSections(ArrayList<HomePageResponse.Data.Section> sections) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        home_products_recyler.setLayoutManager(layoutManager);
        SectionAdapter sectionAdapter = new SectionAdapter(getActivity(),sections);
        home_products_recyler.setAdapter(sectionAdapter);
        sectionAdapter.notifyDataSetChanged();
    }


    @Override
    public void onError(String error) {
        progressDialog.dismiss();
       Util.showToastMessage(getContext(),error,getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
    }

    // timer for change image
    private class SliderTimer extends TimerTask {
        @Override
        public void run() {
            if (getActivity() != null) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (viewPager.getCurrentItem() < array_image.size() - 1) {
                            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                        } else {
                            viewPager.setCurrentItem(0);
                        }
                    }
                });
            }
        }
    }

    @Override
    public void onAttach(Context context1) {
        super.onAttach(context1);
        context = context1;
    }

}
