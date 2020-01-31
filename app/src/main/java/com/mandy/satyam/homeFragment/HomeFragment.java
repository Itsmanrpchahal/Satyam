package com.mandy.satyam.homeFragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.mandy.satyam.R;
import com.mandy.satyam.baseclass.BaseFrag;
import com.mandy.satyam.baseclass.Constants;
import com.mandy.satyam.controller.Controller;
import com.mandy.satyam.homeFragment.adapter.CategoryAdapter;
import com.mandy.satyam.homeFragment.adapter.SectionAdapter;
import com.mandy.satyam.homeFragment.adapter.ViewPagerAdapter;
import com.mandy.satyam.homeFragment.response.HomePageResponse;
import com.mandy.satyam.productList.response.GetProductList;
import com.mandy.satyam.utils.Util;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Response;


//Manpreet Work
public class HomeFragment extends BaseFrag implements Controller.HomePage {

    View view;
    ArrayList<GetProductList.Datum> arrayNewArrivals = new ArrayList<>();
    ArrayList<GetProductList.Datum> arrayDiscounted = new ArrayList<>();
    ArrayList<String> array_image;

    ViewPager viewPager;
    FragmentManager manager;
    Context context;
    Dialog dialog;

    Unbinder unbinder;
    Bundle bundle;
    String token;
    ArrayList<HomePageResponse.Data.Category> categories = new ArrayList<>();
    ArrayList<HomePageResponse.Data.Banner> banners = new ArrayList<>();
    ArrayList<HomePageResponse.Data.Section> sections = new ArrayList<>();
    Controller controller;
    RecyclerView home_products_recyler,recyclerViewCategory;
    Dialog progressDialog;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        progressDialog = Util.showDialog(getContext());
        progressDialog.show();
        init();
        Log.d("CONSUMER",getStringVal(Constants.CONSUMER_SECRET)+"  "+getStringVal(Constants.CONSUMER_KEY));

        controller = new Controller((Controller.HomePage)HomeFragment.this);
        controller.setHomePage();
        unbinder = ButterKnife.bind(this, view);


        return view;
    }

    private void init() {

        array_image = new ArrayList<String>();
        array_image.add(String.valueOf(R.drawable.best_s));
        array_image.add(String.valueOf(R.drawable.bestseller));
        array_image.add(String.valueOf(R.drawable.image_d));

        viewPager = (ViewPager) view.findViewById(R.id.recyclerOffer);
        recyclerViewCategory = view.findViewById(R.id.recyclerCategory);
        home_products_recyler = view.findViewById(R.id.home_products_recyler);


        manager = getActivity().getSupportFragmentManager();
    }


    //set image into view pager
    private void setOfferImage(ArrayList<HomePageResponse.Data.Banner> banner) {
        final PagerAdapter adapter;

        TabLayout tabLayout;
        tabLayout = view.findViewById(R.id.indicator);

        adapter = new ViewPagerAdapter(context, banner);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager, true);


        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(), 4000, 6000);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void onSucessHome(Response<HomePageResponse> homePageResponseResponse) {
        progressDialog.dismiss();
        if (homePageResponseResponse.body().getStatus()==200)
        {
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

    private void setSections(ArrayList<HomePageResponse.Data.Section> sections) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        home_products_recyler.setLayoutManager(layoutManager);
        SectionAdapter sectionAdapter = new SectionAdapter(getActivity(),sections);
        home_products_recyler.setAdapter(sectionAdapter);
        sectionAdapter.notifyDataSetChanged();
        /*sectionAdapter.SectionAdapter(new IF_getCategoryID() {
            @Override
            public void catID(String ID) {
                progressDialog.show();
               controller.setRelatedPrducts("ck_548941c93a5e2e01b319e36ec5a5c242f7e3c7a5",getStringVal(Constants.CONSUMER_SECRET),ID);
            }
        });*/
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
