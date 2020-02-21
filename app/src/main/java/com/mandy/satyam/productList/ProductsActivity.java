package com.mandy.satyam.productList;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mandy.satyam.R;
import com.mandy.satyam.baseclass.BaseClass;
import com.mandy.satyam.baseclass.Constants;
import com.mandy.satyam.controller.Controller;
import com.mandy.satyam.filterScreen.Adapter.FilterProductAdapter;
import com.mandy.satyam.filterScreen.FilterActivity;
import com.mandy.satyam.filterScreen.response.FilterResponse;
import com.mandy.satyam.homeFragment.response.Categoriesroducts;
import com.mandy.satyam.productDetails.IF.product_id_IF;
import com.mandy.satyam.productDetails.ProductDetailsActivity;
import com.mandy.satyam.productList.adapter.ProductListAdapter;
import com.mandy.satyam.productList.adapter.SubCategoryAdapter;
import com.mandy.satyam.productList.interface_.GetSubCate_IF;
import com.mandy.satyam.productList.response.SubCategory;
import com.mandy.satyam.utils.Util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

//Manpreet Work
public class ProductsActivity extends BaseClass implements Controller.RelatedPrducts, Controller.ProductSubCategories, Controller.GetFilterProducts {
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
    ArrayList<Categoriesroducts.Datum> datumArrayList = new ArrayList<Categoriesroducts.Datum>();
    ArrayList<FilterResponse.Datum> filterDatumArraylist = new ArrayList<FilterResponse.Datum>();
    ArrayList<SubCategory.Datum> subCategories = new ArrayList<>();
    ArrayList<String> productname = new ArrayList<>();
    @BindView(R.id.seemorebt)
    Button seemorebt;
    @BindView(R.id.subcategoryrecycler)
    RecyclerView subcategoryrecycler;
    @BindView(R.id.searchProduct)
    AutoCompleteTextView searchProduct;
    @BindView(R.id.searchProducts)
    ImageButton searchProducts;
    String search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        ButterKnife.bind(this);
        progressDialog = Util.showDialog(ProductsActivity.this);
        controller = new Controller((Controller.RelatedPrducts) ProductsActivity.this, (Controller.ProductSubCategories) this, (Controller.GetFilterProducts) this);


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
                    controller.setRelatedPrducts(getStringVal(Constants.CONSUMER_KEY), getStringVal(Constants.CONSUMER_SECRET), catID, String.valueOf(pageCount));
                    controller.setSubCategory(catID);
                } else {
                    Util.showToastMessage(ProductsActivity.this, "No Internet connection", getResources().getDrawable(R.drawable.ic_nointernet));
                }
            } else if (intent.getStringExtra("isFrom").equals("main")) {
                search = intent.getStringExtra("search");
                textView.setText("Filter Products");
                progressDialog.show();
                searchProducts(search);
            } else {
                textView.setText("Filter Products");
                progressDialog.show();
                filterBt.setVisibility(View.INVISIBLE);
                maxPrice = intent.getStringExtra("endPrice");
                minPrice = intent.getStringExtra("startPrice");
                catID = intent.getStringExtra("catID");
                if (Util.isOnline(ProductsActivity.this) != false) {
                    progressDialog.show();
                    controller.setGetFilterProducts(getStringVal(Constants.CONSUMER_KEY), getStringVal(Constants.CONSUMER_SECRET), catID, minPrice, maxPrice, "");
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
                searchProducts.setVisibility(View.VISIBLE);
                filterBt.setVisibility(View.GONE);
                searchBt.setVisibility(View.GONE);
            }
        });

//        searchProducts.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                textView.setVisibility(View.VISIBLE);
//                searchProduct.setVisibility(View.GONE);
//                searchBt.setVisibility(View.VISIBLE);
//                filterBt.setVisibility(View.VISIBLE);
//                seemorebt.setVisibility(View.VISIBLE);

        int size = searchProduct.getText().length();
//        if (size < 3) {
//            searchProduct.setError("Input length must be 3 character");
//        } else {
//                    searchProducts(searchProduct.getText().toString());

//                    images.clear();
//                    datumArrayList.clear();
//                    filterDatumArraylist.clear();
//                    filterImages.clear();
//        }
//            }
//        });

        searchProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchProduct.setText("");
            }
        });

        searchProduct.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String intent = searchProduct.getText().toString();
                String product = "";
                searchProduct(String.valueOf(product.equalsIgnoreCase(intent)));
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {

                }
            }
        });


    }

    private void searchProduct(String productType) {

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ProductsActivity.this, android.R.layout.simple_list_item_1, productname);
        searchProduct.setAdapter(arrayAdapter);


        searchProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String message = String.valueOf(arrayAdapter.getItem(position).substring(0,7));
                searchProducts(message);
            }
        });



    }

    private void searchProducts(String s) {
        if (Util.isOnline(ProductsActivity.this) != false) {
            progressDialog.show();
            controller.setGetFilterProducts(getStringVal(Constants.CONSUMER_KEY), getStringVal(Constants.CONSUMER_SECRET), "", "", "", s);
            controller.setSubCategory(catID);
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
        progressDialog.dismiss();
        seemorebt.setVisibility(View.VISIBLE);
        if (homePageResponseResponse.isSuccessful()) {
            if (homePageResponseResponse.body().getStatus() == 200) {
                headerList = Integer.parseInt(homePageResponseResponse.headers().get("X-WP-TotalPages"));
                for (int i = 0; i < homePageResponseResponse.body().getData().size(); i++) {

                    if (homePageResponseResponse.body().getData().get(i).getImages().size() >= 1) {
                        Categoriesroducts.Datum.Image image = homePageResponseResponse.body().getData().get(i).getImages().get(0);
                        images.add(image);
                    }
                    //Categoriesroducts.Datum productname = homePageResponseResponse.body().getData().get(i).getName();
                    datumArrayList.add(homePageResponseResponse.body().getData().get(i));
                    productname.add(homePageResponseResponse.body().getData().get(i).getName());
                }


                GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
                recyclerProduct.setLayoutManager(layoutManager);
                ProductListAdapter adapter = new ProductListAdapter(this, images, datumArrayList);
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
        }


        seemorebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                if (pageCount < headerList) {
                    pageCount = 1 + pageCount;
                    if (Util.isOnline(ProductsActivity.this) != false) {
                        progressDialog.show();
                        controller.setRelatedPrducts(getStringVal(Constants.CONSUMER_KEY), getStringVal(Constants.CONSUMER_SECRET), catID, String.valueOf(pageCount));
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
                    controller.setRelatedPrducts(getStringVal(Constants.CONSUMER_KEY), getStringVal(Constants.CONSUMER_SECRET), id, String.valueOf(pageCount));
                } else {
                    Util.showToastMessage(ProductsActivity.this, "No Internet connection", getResources().getDrawable(R.drawable.ic_nointernet));
                }

            }
        });
    }

    @Override
    public void onSuccessGetFilterProducts(Response<FilterResponse> responseResponse) {
        progressDialog.dismiss();
        if (responseResponse.isSuccessful()) {
            if (responseResponse.body().getStatus() == 200) {
                headerList = Integer.parseInt(responseResponse.headers().get("X-WP-TotalPages"));

                for (int i = 0; i < responseResponse.body().getData().size(); i++) {

                    if (responseResponse.body().getData().get(i).getImages().size() >= 1) {
                        FilterResponse.Datum.Image image = responseResponse.body().getData().get(i).getImages().get(0);
                        filterImages.add(image);
                    }

                    //Categoriesroducts.Datum productname = homePageResponseResponse.body().getData().get(i).getName();
                    filterDatumArraylist.add(responseResponse.body().getData().get(i));
                }
                GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
                recyclerProduct.setLayoutManager(layoutManager);
                FilterProductAdapter adapter = new FilterProductAdapter(this, filterImages, filterDatumArraylist);
                recyclerProduct.setAdapter(adapter);
                adapter.ProductListAdapter(new product_id_IF() {
                    @Override
                    public void getProductID(String id) {
                        Intent intent = new Intent(ProductsActivity.this, ProductDetailsActivity.class);
                        intent.putExtra("productID", id);
                        startActivity(intent);
                    }
                });

                seemorebt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        progressDialog.show();
                        if (pageCount < headerList) {
                            pageCount = 1 + pageCount;
                            if (Util.isOnline(ProductsActivity.this) != false) {
                                progressDialog.show();
                                controller.setGetFilterProducts(getStringVal(Constants.CONSUMER_KEY), getStringVal(Constants.CONSUMER_SECRET), catID, minPrice, maxPrice, "");
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


            } else {
                Util.showToastMessage(this, responseResponse.body().getMessage(), getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
            }
        } else {
            Util.showToastMessage(this, responseResponse.message(), getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
        }


    }

    @Override
    public void onError(String error) {
        progressDialog.dismiss();
        Util.showToastMessage(ProductsActivity.this, error, getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));

    }
}
