package com.mandy.satyam.homeFragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.mandy.satyam.R;
import com.mandy.satyam.commonActivity.NoInternetActivity;
import com.mandy.satyam.controller.Controller;
import com.mandy.satyam.dashboardproducts.ProductsActivity;
import com.mandy.satyam.homeFragment.adapter.BestSellAdapter;
import com.mandy.satyam.homeFragment.adapter.CategoryAdapter;
import com.mandy.satyam.homeFragment.adapter.DiscountedAdapter;
import com.mandy.satyam.homeFragment.adapter.NewArrivalAdapter;
import com.mandy.satyam.homeFragment.adapter.ViewPagerAdapter;
import com.mandy.satyam.homeFragment.apis.BannerApi;
import com.mandy.satyam.homeFragment.apis.CategoryApi;
import com.mandy.satyam.productList.GetProductList;
import com.mandy.satyam.utils.CheckInternet;
import com.mandy.satyam.utils.ProgressBarClass;
import com.mandy.satyam.utils.Snack;
import com.mandy.satyam.utils.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Response;


public class HomeFragment extends Fragment {

    View view;
    ArrayList<CategoryApi.Datum> arrayCategory = new ArrayList<>();
    ArrayList<GetProductList.Datum> arrayNewArrivals = new ArrayList<>();
    ArrayList<GetProductList.Datum> arrayDiscounted = new ArrayList<>();
    ArrayList<String> array_image;

    ViewPager viewPager;
    RecyclerView recyclerViewCategory, recyclerViewNew, recyclerViewDiscount, recyclerViewBestSell;
    FragmentManager manager;
    Context context;
    Controller controller;
    Dialog dialog;

    @BindView(R.id.txtSeeNew)
    TextView txtSeeNew;
    @BindView(R.id.txtSeeDiscount)
    TextView txtSeeDiscount;
    Unbinder unbinder;
    @BindView(R.id.txtSeeBest)
    TextView txtSetxtSeeNeweBest;
    @BindView(R.id.linearNew)
    LinearLayout linearNew;
    @BindView(R.id.linearDiscount)
    LinearLayout linearDiscount;
    @BindView(R.id.linearBest)
    LinearLayout linearBest;
    Bundle bundle;
    String token;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        dialog = ProgressBarClass.showProgressDialog(context);

        init();
            if (bundle!=null)
            {
                token = bundle.getString("token");
            }
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    private void init() {

        array_image = new ArrayList<String>();
        array_image.add(String.valueOf(R.drawable.best_s));
        array_image.add(String.valueOf(R.drawable.bestseller));
        array_image.add(String.valueOf(R.drawable.image_d));

        recyclerViewCategory = (RecyclerView) view.findViewById(R.id.recyclerCategory);
        recyclerViewNew = (RecyclerView) view.findViewById(R.id.recyclerNew);
        recyclerViewDiscount = (RecyclerView) view.findViewById(R.id.recyclerDiscount);
        recyclerViewBestSell = (RecyclerView) view.findViewById(R.id.recyclerBestSell);
        viewPager = (ViewPager) view.findViewById(R.id.recyclerOffer);


        manager = getActivity().getSupportFragmentManager();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategory.setLayoutManager(layoutManager);
        CategoryAdapter categoryAdapter = new CategoryAdapter(getActivity());
        recyclerViewCategory.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();

        GridLayoutManager layoutManager2 = new GridLayoutManager(getContext(), 2);
        recyclerViewNew.setLayoutManager(layoutManager2);
        NewArrivalAdapter arrivalAdapter = new NewArrivalAdapter(getContext());
        recyclerViewNew.setAdapter(arrivalAdapter);
        recyclerViewNew.addItemDecoration(new SpacesItemDecoration(10));

        GridLayoutManager layoutManager3 = new GridLayoutManager(getContext(), 2);
        recyclerViewDiscount.setLayoutManager(layoutManager3);
        DiscountedAdapter discountedAdapter = new DiscountedAdapter(getContext());
        recyclerViewDiscount.setAdapter(discountedAdapter);
        recyclerViewDiscount.addItemDecoration(new SpacesItemDecoration(10));

        GridLayoutManager layoutManager4 = new GridLayoutManager(getContext(), 2);
        recyclerViewBestSell.setLayoutManager(layoutManager4);
        BestSellAdapter bestSellAdapter = new BestSellAdapter(context);
        recyclerViewBestSell.setAdapter(bestSellAdapter);
        recyclerViewBestSell.addItemDecoration(new SpacesItemDecoration(10));
        setOfferImage();

    }


    //set image into view pager
    private void setOfferImage() {
        final PagerAdapter adapter;

        TabLayout tabLayout;
        tabLayout = view.findViewById(R.id.indicator);

        adapter = new ViewPagerAdapter(context, array_image);
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

    @OnClick({R.id.txtSeeNew, R.id.txtSeeDiscount})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txtSeeNew:
                Intent intent = new Intent(context, ProductsActivity.class);
                intent.putExtra("ProductType","New Arrivals");
                startActivity(intent);
                break;
            case R.id.txtSeeDiscount:
                Intent intent1 = new Intent(context, ProductsActivity.class);
                intent1.putExtra("ProductType","Discounted Items");
                startActivity(intent1);
                break;
        }
    }

    @OnClick(R.id.txtSeeBest)
    public void onViewClicked() {
        Intent intent1 = new Intent(context, ProductsActivity.class);
        intent1.putExtra("ProductType","Best Sell");
        startActivity(intent1);

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
