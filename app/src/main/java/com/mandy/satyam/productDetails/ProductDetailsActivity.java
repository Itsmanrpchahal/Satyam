package com.mandy.satyam.productDetails;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.borjabravo.readmoretextview.ReadMoreTextView;
import com.google.android.material.tabs.TabLayout;
import com.mandy.satyam.MainActivity;
import com.mandy.satyam.R;
import com.mandy.satyam.addressActivity.addAddress.ADDAddressActivity;
import com.mandy.satyam.baseclass.BaseClass;
import com.mandy.satyam.baseclass.Constants;
import com.mandy.satyam.commentActivity.CommentActivity;
import com.mandy.satyam.controller.Controller;
import com.mandy.satyam.homeFragment.response.Categoriesroducts;
import com.mandy.satyam.login.LoginActivity;
import com.mandy.satyam.myCart.MyCartActivity;
import com.mandy.satyam.productDetails.IF.SendItemsToActivityIF;
import com.mandy.satyam.productDetails.IF.product_id_IF;
import com.mandy.satyam.productDetails.adapter.ColorAdapter;
import com.mandy.satyam.productDetails.adapter.SeeRelatedItemAdapter;
import com.mandy.satyam.productDetails.adapter.VariationsAdapter;
import com.mandy.satyam.productDetails.adapter.ViewPagerProductImageAdapter;
import com.mandy.satyam.productDetails.response.AddToCart;
import com.mandy.satyam.productDetails.response.ProductDetailResponse;
import com.mandy.satyam.productDetails.response.VariationResponse;
import com.mandy.satyam.utils.SpacesItemDecoration;
import com.mandy.satyam.utils.Util;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Response;

public class ProductDetailsActivity extends BaseClass implements Controller.ProductDetail, Controller.RelatedPrducts, Controller.AddToCart, Controller.GetVariations {

    @BindView(R.id.tooolbar)
    Toolbar toolbar;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.txtproductName)
    TextView txtproductName;
    @BindView(R.id.txtSubProductName)
    TextView txtSubProductName;
    @BindView(R.id.ratingbar)
    RatingBar ratingbar;
    @BindView(R.id.txtratingNumber)
    TextView txtratingNumber;
    @BindView(R.id.linear)
    LinearLayout linear;
    @BindView(R.id.indicator)
    CirclePageIndicator indicator;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.txtMRP)
    TextView txtMRP;
    @BindView(R.id.txtPrice)
    TextView txtPrice;
    @BindView(R.id.recyclerColor)
    RecyclerView recyclerColor;
    @BindView(R.id.txtSize)
    TextView txtSize;
    @BindView(R.id.recyclerSize)
    RecyclerView recyclerSize;
    @BindView(R.id.perview_description)
    ReadMoreTextView perviewDescription;
    @BindView(R.id.txtFeture)
    ReadMoreTextView txtFeture;
    @BindView(R.id.btnAddCart)
    Button btnAddCart;
    @BindView(R.id.btnBuynow)
    Button btnBuynow;
    @BindView(R.id.recyclerRelated)
    RecyclerView recyclerRelated;
    Intent intent;
    public static NestedScrollView nestedScrollView;
    ArrayList<ProductDetailResponse.Data.Image> array_image = new ArrayList<>();
    @BindView(R.id.filter_bt)
    ImageButton filterBt;
    @BindView(R.id.nestedScoll)
    NestedScrollView nestedScoll;
    @BindView(R.id.search_bt)
    ImageButton searchBt;
    @BindView(R.id.back)
    ImageButton back;
    String productID;
    Controller controller;
    Dialog progressDialog;
    @BindView(R.id.layout)
    RelativeLayout layout;
    @BindView(R.id.pricelayout)
    LinearLayout pricelayout;
    @BindView(R.id.desc_tv)
    TextView descTv;
    @BindView(R.id.colorlayout)
    LinearLayout colorlayout;
    @BindView(R.id.stocktexttv)
    TextView stocktexttv;
    ArrayList<ProductDetailResponse.Data.RelatedId> relatedIDs = new ArrayList<>();
    @BindView(R.id.seerelatedTV)
    TextView seerelatedTV;
    String getProductID;
    @BindView(R.id.searchProduct)
    AutoCompleteTextView searchProduct;
    @BindView(R.id.close)
    ImageButton close;
    @BindView(R.id.variationname)
    RecyclerView variationname;
    ArrayList<ProductDetailResponse.Data.CustomVariation> customVariations = new ArrayList<>();
    public static ArrayList<String> TypeVariations = new ArrayList<>();
    public static String getPosItems;
    VariationsAdapter variationsAdapter;
    String variation_id;
    TextView cart_count;
    RelativeLayout cartlayout;
    int count;
    ImageView no_image;
    private static int currentPage;
    private static int NUM_PAGES;
     PagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        ButterKnife.bind(this);
        cartlayout = findViewById(R.id.cartlayout);
        cartlayout.setVisibility(View.VISIBLE);
        cart_count = findViewById(R.id.cart_count);
        no_image = findViewById(R.id.no_image);
        cart_count.setText(getStringVal(Constants.CART_COUNT));
        count = Integer.parseInt(cart_count.getText().toString());
        controller = new Controller((Controller.ProductDetail) this, (Controller.RelatedPrducts) this, (Controller.AddToCart) this, (Controller.GetVariations) this);
        progressDialog = Util.showDialog(this);
        intent = getIntent();


        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScoll);

        filterBt.setVisibility(View.INVISIBLE);
        searchBt.setVisibility(View.INVISIBLE);
        setSupportActionBar(toolbar);
        textView.setText("Product Details");



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        cartlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetailsActivity.this,MyCartActivity.class);
                intent.putExtra("isFrom","ProductDetail");
                startActivity(intent);
            }
        });

        cart_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetailsActivity.this,MyCartActivity.class);
                intent.putExtra("isFrom","ProductDetail");
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (intent != null) {
            productID = intent.getStringExtra("productID");
            if (Util.isOnline(this) != false) {
                progressDialog.show();
                controller.setProductDetail(productID,getStringVal(Constants.USERTOKEN), getStringVal(Constants.CONSUMER_KEY), getStringVal(Constants.CONSUMER_SECRET));
            } else {
                Util.showToastMessage(this, "No Internet connection", getResources().getDrawable(R.drawable.ic_nointernet));
            }
            progressDialog.show();
        }
    }

    @OnClick({R.id.btnAddCart, R.id.btnBuynow})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnAddCart:

                /*variationsAdapter.VariationsAdapter(new SendItemsToActivityIF() {
                    @Override
                    public void getData(int pos, ArrayList<String> arrayList) {

                        getPosItems = String.valueOf(pos);
                        TypeVariations.addAll(arrayList);
                    }
                });*/
                HashSet<String> hashSet = new HashSet<String>();

                hashSet.addAll(TypeVariations);
                TypeVariations.clear();
                TypeVariations.addAll(hashSet);
                Log.d("PRODUCTDATA", "" + TypeVariations.toString());

                if (getStringVal(Constants.USER_ID).equals("")) {

                    if (getStringVal(Constants.USER_ID).equals("")) {
                        Intent intent = new Intent(ProductDetailsActivity.this, LoginActivity.class);
                        intent.putExtra("productID",productID);
                        intent.putExtra("isFrom","ProductDetail");
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(ProductDetailsActivity.this, ADDAddressActivity.class);
                        intent.putExtra("productID", productID);
                        intent.putExtra("quantity", "1");
                        startActivity(intent);
                    }


                } else {

                    if (btnAddCart.getText().toString().equals("Go to cart"))
                    {
                        Intent intent = new Intent(ProductDetailsActivity.this, MyCartActivity.class);
                        intent.putExtra("isFrom","ProductDetail");
                        intent.putExtra("productID",productID);
                        startActivity(intent);
                    }else {
                        progressDialog.show();

                        if (Util.isOnline(ProductDetailsActivity.this) != false) {
                            if (variation_id.equals("0")) {
                                controller.setAddToCart(getProductID, "1", "", getStringVal(Constants.USERTOKEN));
                            } else {
                                controller.setGetVariations(productID, TypeVariations.toString());
                            }

                        } else {
                            Util.showToastMessage(ProductDetailsActivity.this, "No Internet connection", getResources().getDrawable(R.drawable.ic_nointernet));
                        }
                    }

                }


//               controller.setAddToCart(getStringVal(Constants.CONSUMER_KEY_LOGIN),getStringVal(Constants.CONSUMER_SECRET_LOGIN),getProductID,"1");
                break;
            case R.id.btnBuynow:
                if (getStringVal(Constants.USER_ID).equals("")) {
                    Intent intent = new Intent(ProductDetailsActivity.this, LoginActivity.class);
                    intent.putExtra("productID",productID);
                    intent.putExtra("isFrom","ProductDetail");
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(ProductDetailsActivity.this, ADDAddressActivity.class);
                    intent.putExtra("productID", productID);
                    intent.putExtra("quantity", "1");
                    intent.putExtra("isFrom", "BuyBT");
                    startActivity(intent);
                }
                break;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onSuccessProductDetail(Response<ProductDetailResponse> productDetailResponseResponse) {
        progressDialog.dismiss();
        array_image.clear();
        if (productDetailResponseResponse.isSuccessful()) {
            if (productDetailResponseResponse.body().getStatus() == 200) {
                String cat = productDetailResponseResponse.body().getData().getName().substring(0, 1);
                String small = productDetailResponseResponse.body().getData().getName().toLowerCase().substring(1);
                txtproductName.setText(cat + small);

                if (productDetailResponseResponse.body().getData().isIs_cart()==true)
                {
                    btnAddCart.setText("Go to cart");
                }else {
                    btnAddCart.setText("Add to Cart");
                }

                cart_count.setText(productDetailResponseResponse.body().getData().getCart_total());

                if (productDetailResponseResponse.body().getData().getStockStatus().equals("outofstock"))
                {
                    btnAddCart.setEnabled(false);
                    btnBuynow.setEnabled(false);
                }
                if (productDetailResponseResponse.body().getData().getSalePrice().equals("")) {
                    txtMRP.setVisibility(View.GONE);
                    txtPrice.setText("₹ " + productDetailResponseResponse.body().getData().getPrice());
                } else {
                    txtMRP.setText("₹ " + productDetailResponseResponse.body().getData().getSalePrice());
                    txtMRP.setPaintFlags(txtMRP.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    txtPrice.setText("₹ " + productDetailResponseResponse.body().getData().getPrice());
                }
                variation_id = String.valueOf(productDetailResponseResponse.body().getData().getCustomVariations().size());
                getProductID = productDetailResponseResponse.body().getData().getId().toString();
                stocktexttv.setText(productDetailResponseResponse.body().getData().getStockStatus());
                perviewDescription.setText(Html.fromHtml(productDetailResponseResponse.body().getData().getDescription()));
                if (perviewDescription.getText().toString().equals("")) {
                    descTv.setVisibility(View.GONE);
                    perviewDescription.setVisibility(View.GONE);
                }
                ratingbar.setRating(productDetailResponseResponse.body().getData().getRatingCount());
                txtratingNumber.setText("(" + productDetailResponseResponse.body().getData().getAverageRating() + ")");

                if (productDetailResponseResponse.body().getData().getImages().size()==0)
                {
                    no_image.setVisibility(View.VISIBLE);
                    viewPager.setVisibility(View.GONE);
                }
                for (int i = 0; i < productDetailResponseResponse.body().getData().getImages().size(); i++) {
                    ProductDetailResponse.Data.Image image = productDetailResponseResponse.body().getData().getImages().get(i);
                    array_image.add(image);

                }
                setOfferImage(array_image);

                for (int i = 0; i < productDetailResponseResponse.body().getData().getRelatedIds().size(); i++) {

                    relatedIDs.add(productDetailResponseResponse.body().getData().getRelatedIds().get(i));
                    if (relatedIDs.size() >= 1) {

                    } else {
                        recyclerRelated.setVisibility(View.GONE);
                        seerelatedTV.setVisibility(View.GONE);
                    }
                }


                if (productDetailResponseResponse.body().getData().getAttributes().size() == 0) {
                    colorlayout.setVisibility(View.GONE);
                    recyclerColor.setVisibility(View.GONE);

                    LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
                    recyclerRelated.setLayoutManager(linearLayoutManager3);
                    SeeRelatedItemAdapter adapter2 = new SeeRelatedItemAdapter(this, relatedIDs);
                    recyclerRelated.setAdapter(adapter2);
                    recyclerRelated.addItemDecoration(new SpacesItemDecoration(10));
                    adapter2.ProductListAdapter(new product_id_IF() {
                        @Override
                        public void getProductID(String id) {
                            Intent intent = new Intent(ProductDetailsActivity.this, ProductDetailsActivity.class);
                            intent.putExtra("productID", id);
                            startActivity(intent);
                            finish();
                        }
                    });

                }

                if (productDetailResponseResponse.body().getData().getType().equals("variable")) {

                    variationname.setVisibility(View.VISIBLE);


                    for (int i = 0; i < productDetailResponseResponse.body().getData().getCustomVariations().size(); i++) {

                        customVariations.add(productDetailResponseResponse.body().getData().getCustomVariations().get(i));
                        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
                        linearLayout.setOrientation(LinearLayout.VERTICAL);
                        variationname.setHasFixedSize(true);
                        variationname.setLayoutManager(linearLayout);
                        variationsAdapter = new VariationsAdapter(this, customVariations);
                        variationname.setAdapter(variationsAdapter);

                    }

                } else {

                }
            } else {
                Toast.makeText(this, "" + productDetailResponseResponse.body().getStatus(), Toast.LENGTH_SHORT).show();
            }

        }


    }

    private void setColor(ArrayList<String> array_color) {
        ColorAdapter colorAdapter = new ColorAdapter(this, array_color);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        variationname.setLayoutManager(linearLayoutManager);
        variationname.setAdapter(colorAdapter);
//        variationname.addItemDecoration(new SpacesItemDecoration(5));
    }

    //set image into view pager
    private void setOfferImage(ArrayList<ProductDetailResponse.Data.Image> banner) {


        CirclePageIndicator tabLayout;
        tabLayout = findViewById(R.id.indicator);

        adapter = new ViewPagerProductImageAdapter(ProductDetailsActivity.this, banner);
        viewPager.setAdapter(adapter);
        tabLayout.setViewPager(viewPager);
        NUM_PAGES =banner.size();
        final float density = getResources().getDisplayMetrics().density;
        //Set circle indicator radius
        indicator.setRadius(5 * density);
//        indicator.setVisibility(View.GONE);

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
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

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

    // timer for change image
    private class SliderTimer extends TimerTask {
        @Override
        public void run() {
            if (ProductDetailsActivity.this != null) {
                runOnUiThread(new Runnable() {
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
    public void onSucessRelated(Response<Categoriesroducts> homePageResponseResponse) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        Intent intent = new Intent(ProductDetailsActivity.this, MainActivity.class);
//        intent.putExtra("backPressed","backPressed");
//        startActivity(intent);
    }

    @Override
    public void onSuccessAddToCart(Response<AddToCart> response) {
        progressDialog.dismiss();

        if (response.isSuccessful()) {
            if (response.body().getStatus() == 200) {
                int newCount = 1+count;
                cart_count.setText(String.valueOf(newCount));
                btnAddCart.setText("Go to cart");
                setStringVal(Constants.CART_COUNT, String.valueOf(newCount));
                Util.showToastMessage(this, response.body().getMessage(), getResources().getDrawable(R.drawable.ic_shopping_cart));
            }
        } else {
            Util.showToastMessage(this, response.message(), getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
        }
    }

    @Override
    public void onSuccessVariations(Response<VariationResponse> variationResponseResponse) {
        if (variationResponseResponse.isSuccessful()) {
            progressDialog.dismiss();
            if (variationResponseResponse.body().getStatus() == 200) {
                variation_id = String.valueOf(variationResponseResponse.body().getData().getVariationId());
                controller.setAddToCart(getProductID, "1", String.valueOf(variationResponseResponse.body().getData().getVariationId()), getStringVal(Constants.USERTOKEN));
            }
        }

    }

    @Override
    public void onError(String error) {
        progressDialog.dismiss();
        Util.showToastMessage(this, error, getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
    }
}
