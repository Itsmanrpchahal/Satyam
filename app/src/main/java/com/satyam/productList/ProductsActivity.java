package com.satyam.productList;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.satyam.R;
import com.satyam.SearchItemAdapter;
import com.satyam.baseclass.BaseClass;
import com.satyam.baseclass.Constants;
import com.satyam.controller.Controller;
import com.satyam.filterScreen.Adapter.FilterProductAdapter;
import com.satyam.filterScreen.FilterActivity;
import com.satyam.filterScreen.response.FilterResponse;
import com.satyam.homeFragment.response.Categoriesroducts;
import com.satyam.mainIF.getProductName;
import com.satyam.productDetails.IF.product_id_IF;
import com.satyam.productDetails.ProductDetailsActivity;
import com.satyam.productList.adapter.BannerAdapter;
import com.satyam.productList.adapter.ProductListAdapter;
import com.satyam.productList.adapter.SubCategoryAdapter;
import com.satyam.productList.interface_.GetSubCate_IF;
import com.satyam.productList.response.GetSearchProductsResponse;
import com.satyam.productList.response.SubCategory;
import com.satyam.utils.Util;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

//Manpreet Work
public class ProductsActivity extends BaseClass implements Controller.RelatedPrducts, Controller.ProductSubCategories, Controller.GetFilterProducts ,Controller.GetSerchProducts{
    @BindView(R.id.tooolbar)
    Toolbar toolbar;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.recyclerProduct)
    RecyclerView recyclerProduct;
    Dialog dialog;
    Intent intent;
    String ProductType, maxPrice, minPrice;
    @BindView(R.id.filter_bt)
    ImageButton filterBt;
    @BindView(R.id.search_bt)
    ImageButton searchBt;
    @BindView(R.id.back)
    ImageButton back;
    String token, catID;
    Controller controller;
    Dialog progressDialog;
    int pageCount = 1;
    int headerList;
    ArrayList<Categoriesroducts.Datum.Image> images = new ArrayList<>();
    ArrayList<FilterResponse.Datum.Image> filterImages = new ArrayList<>();
    ArrayList<Categoriesroducts.CatgoryBanner> catgoryBanners = new ArrayList<>();
    ArrayList<Categoriesroducts.Datum> datumArrayList = new ArrayList<Categoriesroducts.Datum>();
    ArrayList<FilterResponse.Datum> filterDatumArraylist = new ArrayList<FilterResponse.Datum>();
    ArrayList<SubCategory.Datum> subCategories = new ArrayList<>();
    ArrayList<String> productname = new ArrayList<>();
    ArrayList<GetSearchProductsResponse.Datum> searchProducts = new ArrayList<>();
//    ArrayList<FilterResponse.Datum> searchProducts = new ArrayList<>();
    @BindView(R.id.seemorebt)
    Button seemorebt;
    @BindView(R.id.subcategoryrecycler)
    RecyclerView subcategoryrecycler;
    @BindView(R.id.searchProduct)
    AutoCompleteTextView searchProduct;
    @BindView(R.id.close)
    ImageButton close;
    String search;
    @BindView(R.id.searchitemrecycler)
    RecyclerView searchitemrecycler;
    @BindView(R.id.noitemfound)
    TextView noitemfound;
    ViewPager viewPager;
    RelativeLayout bannerlaout;
    private static int currentPage;
    private static int NUM_PAGES;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        ButterKnife.bind(this);
        viewPager = (ViewPager) findViewById(R.id.bannerrecyler);
        bannerlaout = findViewById(R.id.bannerlaout);
        progressDialog = Util.showDialog(ProductsActivity.this);
        controller = new Controller((Controller.RelatedPrducts) ProductsActivity.this, (Controller.ProductSubCategories) this, (Controller.GetFilterProducts) this,(Controller.GetSerchProducts)this);


        listerners();

        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        intent = getIntent();
        if (intent != null) {
            if (intent.getStringExtra("isFrom").equals("HomePage")) {
                ProductType = intent.getStringExtra("ProductType");
                catID = intent.getStringExtra("cateID");
                String cat = ProductType.substring(0, 1);
                String small = ProductType.toLowerCase().substring(1);
                textView.setText(cat + small);
                if (Util.isOnline(ProductsActivity.this) != false) {
                    progressDialog.show();
                    controller.setRelatedPrducts(getStringVal(Constants.CONSUMER_KEY), getStringVal(Constants.CONSUMER_SECRET), catID, String.valueOf(pageCount),30,"get_products","instock");
                    controller.setSubCategory(catID);
                } else {
                    Util.showToastMessage(ProductsActivity.this, "No Internet connection", getResources().getDrawable(R.drawable.ic_nointernet));
                }
            }
            else if (intent.getStringExtra("isFrom").equals("FilterScreen"))
            {
                viewPager.setVisibility(View.GONE);
                 textView.setText("Filter Products");
                searchitemrecycler.setVisibility(View.GONE);
                progressDialog.show();
                filterBt.setVisibility(View.INVISIBLE);
                searchBt.setVisibility(View.GONE);
                subcategoryrecycler.setVisibility(View.GONE);
                maxPrice = intent.getStringExtra("endPrice");
                minPrice = intent.getStringExtra("startPrice");
                catID = intent.getStringExtra("catID");
                if (Util.isOnline(ProductsActivity.this) != false) {
                    progressDialog.show();
                    controller.setGetFilterProducts(getStringVal(Constants.CONSUMER_KEY), getStringVal(Constants.CONSUMER_SECRET), catID, minPrice, maxPrice, "",String.valueOf(pageCount),30,"instock");
                    controller.setSubCategory(catID);
                } else {
                    Util.showToastMessage(ProductsActivity.this, "No Internet connection", getResources().getDrawable(R.drawable.ic_nointernet));
                }
            }
        }
    }


    private void listerners() {
        filterBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductsActivity.this, FilterActivity.class);
                intent.putExtra("cateID", catID);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        searchBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setVisibility(View.GONE);
                searchProduct.setVisibility(View.VISIBLE);
                close.setVisibility(View.VISIBLE);
                filterBt.setVisibility(View.GONE);
                searchBt.setVisibility(View.GONE);
            }
        });

        if (searchProduct.getText().toString().equals(""))
        {
            recyclerProduct.setVisibility(View.VISIBLE);
            searchitemrecycler.setVisibility(View.GONE);
        }

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setVisibility(View.VISIBLE);
                searchProduct.setVisibility(View.GONE);
                close.setVisibility(View.GONE);
                searchProduct.setText("");
                filterBt.setVisibility(View.VISIBLE);
                searchBt.setVisibility(View.VISIBLE);
                noitemfound.setVisibility(View.GONE);

            }
        });

        searchProduct.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0)
                {
                    recyclerProduct.setVisibility(View.VISIBLE);
                    subcategoryrecycler.setVisibility(View.VISIBLE);
                    searchitemrecycler.setVisibility(View.GONE);
                }

                if (searchProduct.getText().toString().equals(""))
                {
                    recyclerProduct.setVisibility(View.VISIBLE);
                    subcategoryrecycler.setVisibility(View.VISIBLE);
                    searchitemrecycler.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

                if (searchProduct.getText().toString().equals("")) {
                    recyclerProduct.setVisibility(View.VISIBLE);
                    subcategoryrecycler.setVisibility(View.VISIBLE);
                    searchitemrecycler.setVisibility(View.GONE);
                }


                if (s.length() > 2) {
                    String intent = searchProduct.getText().toString();
                    String product = "";
                    searchProducts(intent);
//                    searchProduct();
                } else if (s.length() == 0) {
                    searchitemrecycler.setVisibility(View.GONE);
                    recyclerProduct.setVisibility(View.VISIBLE);
                    subcategoryrecycler.setVisibility(View.VISIBLE);
                }
            }
        });
    }


    private void searchProducts(String s) {
        if (Util.isOnline(ProductsActivity.this) != false) {
//            progressDialog.show();
            controller.setGetSerchProducts(s,"instock");
//            controller.setGetSearchProducts(getStringVal(Constants.CONSUMER_KEY), getStringVal(Constants.CONSUMER_SECRET), "", "", "", s,String.valueOf(pageCount));
//            controller.setSubCategory(catID);
        } else {
            Util.showToastMessage(ProductsActivity.this, "No Internet connection", getResources().getDrawable(R.drawable.ic_nointernet));
        }

    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onSucessRelated(Response<Categoriesroducts> homePageResponseResponse) {
        progressDialog.show();
        if (homePageResponseResponse.isSuccessful()) {
            progressDialog.dismiss();
            if (homePageResponseResponse.body().getStatus() == 200) {
                headerList = Integer.parseInt(homePageResponseResponse.headers().get("X-WP-TotalPages"));
                for (int i = 0; i < homePageResponseResponse.body().getData().size(); i++) {

                   /* if (homePageResponseResponse.body().getData().get(i).getImages().size() >= 1) {
                        Categoriesroducts.Datum.Image image = homePageResponseResponse.body().getData().get(i).getImages().get(0);
                        images.add(image);
                    }*/
                    //Categoriesroducts.Datum productname = homePageResponseResponse.body().getData().get(i).getName();
                    datumArrayList.add(homePageResponseResponse.body().getData().get(i));
                }

                for (int i=0;i<homePageResponseResponse.body().getCatgoryBanners().size();i++)
                {
                    catgoryBanners.add(homePageResponseResponse.body().getCatgoryBanners().get(i));
                    setBanners(catgoryBanners);
                }
                if (catgoryBanners.size()==0)
                {
                    bannerlaout.setVisibility(View.GONE);
                    viewPager.setVisibility(View.GONE);
                }

                if (datumArrayList.size()!=0)
                {
                    seemorebt.setVisibility(View.VISIBLE);
                    noitemfound.setVisibility(View.GONE);
                }else {
                    seemorebt.setVisibility(View.GONE);
                    noitemfound.setVisibility(View.VISIBLE);
                }


                GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
                recyclerProduct.setLayoutManager(layoutManager);
                ProductListAdapter adapter = new ProductListAdapter(this,  datumArrayList);
                recyclerProduct.setAdapter(adapter);
                adapter.ProductListAdapter(new product_id_IF() {
                    @Override
                    public void getProductID(String id) {
                        Intent intent = new Intent(ProductsActivity.this, ProductDetailsActivity.class);
                        intent.putExtra("productID", id);
                        startActivity(intent);
                    }
                });
            } else if (homePageResponseResponse.body().getStatus() == 401) {
                dialog.dismiss();
                Toast.makeText(this, "" + homePageResponseResponse.body().getStatus(), Toast.LENGTH_SHORT).show();
            }
        } else {
            progressDialog.dismiss();
            seemorebt.setVisibility(View.GONE);
        }


        seemorebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                if (pageCount < headerList) {
                    pageCount = 1 + pageCount;

                    if (Util.isOnline(ProductsActivity.this) != false) {
                        progressDialog.show();
                        controller.setRelatedPrducts(getStringVal(Constants.CONSUMER_KEY), getStringVal(Constants.CONSUMER_SECRET), catID, String.valueOf(pageCount),30,"get_products","instock");
                    } else {
                        Util.showToastMessage(ProductsActivity.this, "No Internet connection", getResources().getDrawable(R.drawable.ic_nointernet));
                    }
                } else {
                    seemorebt.setVisibility(View.GONE);
                }

            }
        });

        if (pageCount == headerList) {
            seemorebt.setVisibility(View.GONE);
        } else {
            seemorebt.setVisibility(View.VISIBLE);
        }


    }

    private void setBanners(ArrayList<Categoriesroducts.CatgoryBanner> catgoryBanners) {
        final PagerAdapter adapter;

        CirclePageIndicator tabLayout;
        tabLayout = findViewById(R.id.indicator);

        adapter = new BannerAdapter( catgoryBanners,ProductsActivity.this);
        viewPager.setAdapter(adapter);
        tabLayout.setViewPager(viewPager);
        NUM_PAGES =catgoryBanners.size();

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };

        // Pager listener over indicator
        tabLayout.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });
    }


    @Override
    public void onSuccessSubcate(Response<SubCategory> subCategoryResponse) {
        progressDialog.dismiss();
        if (subCategoryResponse.body().getStatus() == 200) {

            for (int i = 0; i < subCategoryResponse.body().getData().size(); i++) {
                SubCategory.Datum datum = subCategoryResponse.body().getData().get(i);
                subCategories.add(datum);
                setSubCategory(subCategories);
            }
        }
    }

    private void setSubCategory(ArrayList<SubCategory.Datum> subCategorie) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(ProductsActivity.this, LinearLayoutManager.HORIZONTAL, false);
        subcategoryrecycler.setLayoutManager(layoutManager);
        SubCategoryAdapter categoryAdapter = new SubCategoryAdapter(this, subCategorie);
        subcategoryrecycler.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();
        categoryAdapter.SubCategoryAdapter(new GetSubCate_IF() {
            @Override
            public void getCateID(String id) {
                catID = id;
                if (Util.isOnline(ProductsActivity.this) != false) {
                    progressDialog.show();
                    images.clear();
                    datumArrayList.clear();
                    catgoryBanners.clear();
                    controller.setRelatedPrducts(getStringVal(Constants.CONSUMER_KEY), getStringVal(Constants.CONSUMER_SECRET), id, String.valueOf(pageCount),30,"get_products","instock");
                } else {
                    Util.showToastMessage(ProductsActivity.this, "No Internet connection", getResources().getDrawable(R.drawable.ic_nointernet));
                }

            }
        });
    }

    @Override
    public void onSuccessGetFilterProducts(Response<FilterResponse> responseResponse) {
        progressDialog.show();
        if (responseResponse.isSuccessful()) {
            seemorebt.setVisibility(View.GONE);
            progressDialog.dismiss();
            if (responseResponse.body().getStatus() == 200) {

                headerList = Integer.parseInt(responseResponse.headers().get("X-WP-TotalPages"));


                if (responseResponse.body().getData().size()==0)
                {
                    searchitemrecycler.setVisibility(View.GONE);
                    recyclerProduct.setVisibility(View.GONE);
                    noitemfound.setVisibility(View.VISIBLE);
                    seemorebt.setVisibility(View.GONE  );
                    subcategoryrecycler.setVisibility(View.GONE);
                }else {
                    if (headerList>0)
                    {
                        seemorebt.setVisibility(View.VISIBLE);
                    }
                    if (!searchProduct.getText().toString().equals(""))
                    {
                        searchitemrecycler.setVisibility(View.VISIBLE);
                        noitemfound.setVisibility(View.GONE);
                        recyclerProduct.setVisibility(View.GONE);
                    }else {
                        searchitemrecycler.setVisibility(View.GONE);
                        noitemfound.setVisibility(View.GONE);
                        recyclerProduct.setVisibility(View.VISIBLE);
                    }
                    for (int i = 0; i < responseResponse.body().getData().size(); i++) {
                        if (responseResponse.body().getData().get(i).getImages().size() >= 1) {
                            FilterResponse.Datum.Image image = responseResponse.body().getData().get(i).getImages().get(0);
                            filterImages.add(image);
                        }else {

                        }
                        filterDatumArraylist.add(responseResponse.body().getData().get(i));
                    }
                    GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
                    recyclerProduct.setLayoutManager(layoutManager);
                    FilterProductAdapter adapter = new FilterProductAdapter(this,  filterImages,filterDatumArraylist);
                    recyclerProduct.setAdapter(adapter);
                    adapter.FilterProductAdapter(new product_id_IF() {
                        @Override
                        public void getProductID(String id) {
                            Intent intent = new Intent(ProductsActivity.this, ProductDetailsActivity.class);
                            intent.putExtra("productID", id);
                            startActivity(intent);
                        }
                    });

                }

                seemorebt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        progressDialog.show();
                        if (pageCount < headerList) {
                            pageCount = 1 + pageCount;

                            if (Util.isOnline(ProductsActivity.this) != false) {
                                progressDialog.show();
                                controller.setGetFilterProducts(getStringVal(Constants.CONSUMER_KEY), getStringVal(Constants.CONSUMER_SECRET), catID, minPrice, maxPrice, "",String.valueOf(pageCount),30,"instock");
                            } else {
                                Util.showToastMessage(ProductsActivity.this, "No Internet connection", getResources().getDrawable(R.drawable.ic_nointernet));
                            }
                        } else {
                            seemorebt.setVisibility(View.GONE);
                        }

                    }
                });

                if (pageCount == headerList) {
                    seemorebt.setVisibility(View.GONE);
                }else if (headerList==0)
                {
                    seemorebt.setVisibility(View.GONE);
                }else {
                    seemorebt.setVisibility(View.VISIBLE);
                }
            } else {

                progressDialog.dismiss();
                Util.showToastMessage(this, responseResponse.body().getMessage(), getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
            }
        } else {

            progressDialog.dismiss();
            Util.showToastMessage(this, responseResponse.message(), getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
        }

    }


    @Override
    public void onSuccessSearch(Response<GetSearchProductsResponse> searchProductsResponseResponse) {
        productname.clear();
        searchProducts.clear();
        progressDialog.show();
        if (searchProductsResponseResponse.isSuccessful()) {
            seemorebt.setVisibility(View.GONE);
            progressDialog.dismiss();
            if (searchProductsResponseResponse.body().getStatus() == 200) {

                if (searchProductsResponseResponse.body().getData().size()==0)
                {
                    searchitemrecycler.setVisibility(View.GONE);
                    recyclerProduct.setVisibility(View.GONE);
                    noitemfound.setVisibility(View.VISIBLE);
                    seemorebt.setVisibility(View.GONE);
                    subcategoryrecycler.setVisibility(View.GONE);
                }else {
                    if (!searchProduct.getText().toString().equals(""))
                    {
                        searchitemrecycler.setVisibility(View.VISIBLE);
                        noitemfound.setVisibility(View.GONE);
                        recyclerProduct.setVisibility(View.GONE);
                    }else {
                        searchitemrecycler.setVisibility(View.GONE);
                        noitemfound.setVisibility(View.GONE);
                        recyclerProduct.setVisibility(View.VISIBLE);
                    }

                    for (int i=0;i<searchProductsResponseResponse.body().getData().size();i++)
                    {
                       GetSearchProductsResponse.Datum datum = searchProductsResponseResponse.body().getData().get(i);
                       searchProducts.add(datum);
                    }

                    LinearLayoutManager linearLayout = new LinearLayoutManager(ProductsActivity.this);
                    searchitemrecycler.setLayoutManager(linearLayout);
                    SearchItemAdapter adapter = new SearchItemAdapter(this, searchProducts);
                    searchitemrecycler.setAdapter(adapter);
                    adapter.SearchItemAdapter(new getProductName() {
                        @Override
                        public void getName(String name, String id) {
//                        Toast.makeText(ProductsActivity.this, ""+name+" "+id, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ProductsActivity.this, ProductDetailsActivity.class);
                            intent.putExtra("productID", id);
                            startActivity(intent);
                        }
                    });
                }



            } else {

                progressDialog.dismiss();
                Util.showToastMessage(this, searchProductsResponseResponse.body().getMessage(), getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
            }
        } else {

            progressDialog.dismiss();
            Util.showToastMessage(this, searchProductsResponseResponse.message(), getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
        }
    }

    @Override
    public void onError(String error) {
        progressDialog.dismiss();
        Util.showToastMessage(ProductsActivity.this, error, getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
    }
}
