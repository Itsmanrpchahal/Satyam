package com.mandy.satyam.filterScreen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mandy.satyam.R;
import com.mandy.satyam.filterScreen.Adapter.BrandAdapter;
import com.mandy.satyam.filterScreen.Adapter.ColorAdapter;
import com.mandy.satyam.filterScreen.Adapter.FilterCategory_Adapter;
import com.mandy.satyam.filterScreen.Adapter.PriceAdapter;
import com.mandy.satyam.filterScreen.Adapter.SizeAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FilterActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.filter_close)
    ImageButton filter_close;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.brand_tab)
    TextView brand_tab;
    @BindView(R.id.size_tab)
    TextView size_tab;
    @BindView(R.id.colors_tab)
    TextView colors_tab;
    @BindView(R.id.price_tab)
    TextView price_tab;
    ArrayList<String> price = new ArrayList<>();
    ArrayList<String> brand = new ArrayList<>();
    ArrayList<String> color = new ArrayList<>();
    ArrayList<String> size = new ArrayList<>();
    ArrayList<String> FilterCategories = new ArrayList<>();
    PriceAdapter priceAdapter;
    BrandAdapter brandAdapter;
    ColorAdapter colorAdapter;
    FilterCategory_Adapter filterCategory_adapter;

    SizeAdapter sizeAdapter;

    @BindView(R.id.filter_recylcer)
    RecyclerView filterRecylcer;
    @BindView(R.id.categories_recyler)
    RecyclerView categoriesRecyler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        ButterKnife.bind(this);

        FilterCategories.add("Price");
        FilterCategories.add("Brand");
        FilterCategories.add("Color");
        FilterCategories.add("Size");

        //-----------------//
        price.add("Rs. 499 and Below");
        price.add("Rs. 500 - Rs.999");
        price.add("Rs. 1000 - Rs.1499");
        price.add("Rs. 1500 - 1999");
        price.add("Rs. 2000 - Rs.2500");

        //-------------------//
        brand.add("Nike");
        brand.add("Gucci");
        brand.add("Puma");
        brand.add("HM");
        brand.add("ZARA");

        //--------------------//
        color.add("Red");
        color.add("Blue");
        color.add("Green");
        color.add("Grey");
        color.add("White");

        //----------------------//
        size.add("S");
        size.add("M");
        size.add("L");
        size.add("XL");
        size.add("XXL");
        size.add("XXXL");

        listeners();
//        setFilterCateAdapter(FilterCategories);
        setBrandAdapter(brand);

    }

    private void setFilterCateAdapter(ArrayList<String> filterCategories) {
        LinearLayoutManager manager = new LinearLayoutManager(FilterActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        categoriesRecyler.setHasFixedSize(true);
        categoriesRecyler.setLayoutManager(manager);
        filterCategory_adapter = new FilterCategory_Adapter(FilterActivity.this, filterCategories);
        categoriesRecyler.setAdapter(filterCategory_adapter);
    }


    private void setPriceAdapter(ArrayList<String> price) {
        LinearLayoutManager manager = new LinearLayoutManager(FilterActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        filterRecylcer.setHasFixedSize(true);
        filterRecylcer.setLayoutManager(manager);
        priceAdapter = new PriceAdapter(FilterActivity.this, price);
        filterRecylcer.setAdapter(priceAdapter);

    }

    private void setBrandAdapter(ArrayList<String> brand) {

        LinearLayoutManager manager = new LinearLayoutManager(FilterActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        filterRecylcer.setHasFixedSize(true);
        filterRecylcer.setLayoutManager(manager);
        brandAdapter = new BrandAdapter(FilterActivity.this, brand);
        filterRecylcer.setAdapter(brandAdapter);
    }

    private void setColorAdapter(ArrayList<String> color) {
        LinearLayoutManager manager = new LinearLayoutManager(FilterActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        filterRecylcer.setLayoutManager(manager);
        colorAdapter = new ColorAdapter(FilterActivity.this, color);
        filterRecylcer.setAdapter(colorAdapter);
    }

    private void setSizeAdapter(ArrayList<String> size) {
        LinearLayoutManager manager = new LinearLayoutManager(FilterActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        filterRecylcer.setLayoutManager(manager);
        sizeAdapter = new SizeAdapter(FilterActivity.this, size);
        filterRecylcer.setAdapter(sizeAdapter);
    }


    private void listeners() {
        brand_tab.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        brand_tab.setTextColor(getResources().getColor(R.color.grey));
        size_tab.setBackgroundColor(getResources().getColor(R.color.grey));
        colors_tab.setBackgroundColor(getResources().getColor(R.color.grey));
        price_tab.setBackgroundColor(getResources().getColor(R.color.grey));
        size_tab.setTextColor(getResources().getColor(R.color.black));
        colors_tab.setTextColor(getResources().getColor(R.color.black));
        price_tab.setTextColor(getResources().getColor(R.color.black));
        filter_close.setOnClickListener(this);
        brand_tab.setOnClickListener(this);
        size_tab.setOnClickListener(this);
        colors_tab.setOnClickListener(this);
        price_tab.setOnClickListener(this);
        filter_close.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.brand_tab:
                brand_tab.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                size_tab.setBackgroundColor(getResources().getColor(R.color.grey));
                colors_tab.setBackgroundColor(getResources().getColor(R.color.grey));
                price_tab.setBackgroundColor(getResources().getColor(R.color.grey));
                brand_tab.setTextColor(getResources().getColor(R.color.white));
                size_tab.setTextColor(getResources().getColor(R.color.black));
                colors_tab.setTextColor(getResources().getColor(R.color.black));
                price_tab.setTextColor(getResources().getColor(R.color.black));
                setBrandAdapter(brand);

                break;

            case R.id.size_tab:
                brand_tab.setBackgroundColor(getResources().getColor(R.color.grey));
                size_tab.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                colors_tab.setBackgroundColor(getResources().getColor(R.color.grey));
                price_tab.setBackgroundColor(getResources().getColor(R.color.grey));
                brand_tab.setTextColor(getResources().getColor(R.color.black));
                size_tab.setTextColor(getResources().getColor(R.color.white));
                colors_tab.setTextColor(getResources().getColor(R.color.black));
                price_tab.setTextColor(getResources().getColor(R.color.black));
                setSizeAdapter(size);
                break;

            case R.id.colors_tab:
                brand_tab.setBackgroundColor(getResources().getColor(R.color.grey));
                size_tab.setBackgroundColor(getResources().getColor(R.color.grey));
                colors_tab.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                price_tab.setBackgroundColor(getResources().getColor(R.color.grey));
                brand_tab.setTextColor(getResources().getColor(R.color.black));
                size_tab.setTextColor(getResources().getColor(R.color.black));
                colors_tab.setTextColor(getResources().getColor(R.color.white));
                price_tab.setTextColor(getResources().getColor(R.color.black));
                setColorAdapter(color);
                break;


            case R.id.price_tab:
                brand_tab.setBackgroundColor(getResources().getColor(R.color.grey));
                size_tab.setBackgroundColor(getResources().getColor(R.color.grey));
                colors_tab.setBackgroundColor(getResources().getColor(R.color.grey));
                price_tab.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                brand_tab.setTextColor(getResources().getColor(R.color.black));
                size_tab.setTextColor(getResources().getColor(R.color.black));
                colors_tab.setTextColor(getResources().getColor(R.color.black));
                price_tab.setTextColor(getResources().getColor(R.color.white));
                setPriceAdapter(price);
                break;

            case R.id.filter_close:
                onBackPressed();
                break;
        }
    }


}
