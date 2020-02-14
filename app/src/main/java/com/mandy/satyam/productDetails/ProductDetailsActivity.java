package com.mandy.satyam.productDetails;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
import com.mandy.satyam.R;
import com.mandy.satyam.addressActivity.AddressActivity;
import com.mandy.satyam.baseclass.BaseClass;
import com.mandy.satyam.baseclass.Constants;
import com.mandy.satyam.commentActivity.CommentActivity;
import com.mandy.satyam.controller.Controller;
import com.mandy.satyam.homeFragment.response.Categoriesroducts;
import com.mandy.satyam.productDetails.IF.product_id_IF;
import com.mandy.satyam.productDetails.adapter.ColorAdapter;
import com.mandy.satyam.productDetails.adapter.SeeRelatedItemAdapter;
import com.mandy.satyam.productDetails.adapter.SizeAdapter;
import com.mandy.satyam.productDetails.adapter.ViewPagerProductImageAdapter;
import com.mandy.satyam.productDetails.response.AddToCart;
import com.mandy.satyam.productDetails.response.ProductDetailResponse;
import com.mandy.satyam.utils.SpacesItemDecoration;
import com.mandy.satyam.utils.Util;

import java.util.ArrayList;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Response;

public class ProductDetailsActivity extends BaseClass implements Controller.ProductDetail,Controller.RelatedPrducts ,Controller.AddToCart{

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
    TabLayout indicator;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.txtMRP)
    TextView txtMRP;
    @BindView(R.id.txtPrice)
    TextView txtPrice;
    @BindView(R.id.txtColor)
    TextView txtColor;
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
    ArrayList<String> array_color = new ArrayList<String>();
    Dialog dialog;
    String token, id, catId, sizeId, colorId;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        ButterKnife.bind(this);
        controller = new Controller((Controller.ProductDetail)this,(Controller.RelatedPrducts)this,(Controller.AddToCart)this);
        progressDialog = Util.showDialog(this);
        intent = getIntent();
        if (intent != null) {
            productID = intent.getStringExtra("productID");
            if (Util.isOnline(this) != false) {
                progressDialog.show();
                controller.setProductDetail(productID, getStringVal(Constants.CONSUMER_KEY), getStringVal(Constants.CONSUMER_SECRET));
            } else {
                Util.showToastMessage(this, "No Internet connection", getResources().getDrawable(R.drawable.ic_nointernet));
            }

            progressDialog.show();

        }
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScoll);

        filterBt.setVisibility(View.INVISIBLE);
        searchBt.setVisibility(View.INVISIBLE);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        textView.setText("Product Details");

        linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetailsActivity.this, CommentActivity.class);
                intent.putExtra("pId", id);
                startActivity(intent);
            }
        });


        SizeAdapter adapter = new SizeAdapter(this);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerSize.setLayoutManager(linearLayoutManager2);
        recyclerSize.setAdapter(adapter);
        recyclerSize.addItemDecoration(new SpacesItemDecoration(10));

    }

    @OnClick({R.id.btnAddCart, R.id.btnBuynow})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnAddCart:
                progressDialog.show();
                controller.setAddToCart(getProductID,"1","",getStringVal(Constants.USERTOKEN));
//               controller.setAddToCart(getStringVal(Constants.CONSUMER_KEY_LOGIN),getStringVal(Constants.CONSUMER_SECRET_LOGIN),getProductID,"1");
                break;
            case R.id.btnBuynow:
                Adddialog();
                break;

            case R.id.back:
                onBackPressed();
                break;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    private void Adddialog() {
        final EditText editText;
        Button btnAdd, btnCancel;

        final Dialog quantityDialog = new Dialog(this);
        quantityDialog.setCancelable(false);
        quantityDialog.setCanceledOnTouchOutside(false);
        quantityDialog.setContentView(R.layout.custom_quantity);
        editText = quantityDialog.findViewById(R.id.edtQuantity);
        btnAdd = quantityDialog.findViewById(R.id.btnAdd);
        btnCancel = quantityDialog.findViewById(R.id.btnCancel);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(editText.getText().toString())) {
                    editText.setError("Enter the quantity");
                } else {
                    Intent intent = new Intent(ProductDetailsActivity.this, AddressActivity.class);
                    startActivity(intent);
                    quantityDialog.dismiss();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantityDialog.cancel();
            }
        });
        quantityDialog.show();

    }

    @Override
    public void onSuccessProductDetail(Response<ProductDetailResponse> productDetailResponseResponse) {
        progressDialog.dismiss();
        if (productDetailResponseResponse.body().getStatus() == 200) {
            String cat = productDetailResponseResponse.body().getData().getName().substring(0, 1);
            String small = productDetailResponseResponse.body().getData().getName().toLowerCase().substring(1);
            txtproductName.setText(cat + small);

            if (productDetailResponseResponse.body().getData().getSalePrice().equals("")) {
                txtMRP.setVisibility(View.GONE);
                txtPrice.setText("$ " + productDetailResponseResponse.body().getData().getPrice());
            } else {
                txtMRP.setText("$ " + productDetailResponseResponse.body().getData().getSalePrice());
                txtMRP.setPaintFlags(txtMRP.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                txtPrice.setText("$ " + productDetailResponseResponse.body().getData().getPrice());
            }
            getProductID = productDetailResponseResponse.body().getData().getId().toString();
            stocktexttv.setText(productDetailResponseResponse.body().getData().getStockStatus());
            perviewDescription.setText(Html.fromHtml(productDetailResponseResponse.body().getData().getDescription()));
            if (perviewDescription.getText().toString().equals("")) {
                descTv.setVisibility(View.GONE);
                perviewDescription.setVisibility(View.GONE);
            }
            ratingbar.setRating(productDetailResponseResponse.body().getData().getRatingCount());
            txtratingNumber.setText("(" + productDetailResponseResponse.body().getData().getAverageRating() + ")");

            for (int i = 0; i < productDetailResponseResponse.body().getData().getImages().size(); i++) {
                ProductDetailResponse.Data.Image image = productDetailResponseResponse.body().getData().getImages().get(i);
                array_image.add(image);
                setOfferImage(array_image);
            }


            for (int i1 = 0; i1 < productDetailResponseResponse.body().getData().getColors().size(); i1++) {
                String color = productDetailResponseResponse.body().getData().getColors().get(i1);

                if (color != null) {
                    String[] name = color.split(":#");
                    if (name.length == 2) {
                        String Fname = name[1];
                        array_color.add(Fname);

                        setColor(array_color);
                    } else {
//                        String Fname = name[0];
                        array_color.add("00E54640");
                    }


                }

            }

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
                SeeRelatedItemAdapter adapter2 = new SeeRelatedItemAdapter(this,relatedIDs);
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
        } else {
            Toast.makeText(this, "" + productDetailResponseResponse.body().getStatus(), Toast.LENGTH_SHORT).show();
        }


    }

    private void setColor(ArrayList<String> array_color) {
        ColorAdapter colorAdapter = new ColorAdapter(this, array_color);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerColor.setLayoutManager(linearLayoutManager);
        recyclerColor.setAdapter(colorAdapter);
        recyclerColor.addItemDecoration(new SpacesItemDecoration(5));
    }

    //set image into view pager
    private void setOfferImage(ArrayList<ProductDetailResponse.Data.Image> banner) {
        final PagerAdapter adapter;

        TabLayout tabLayout;
        tabLayout = findViewById(R.id.indicator);

        adapter = new ViewPagerProductImageAdapter(ProductDetailsActivity.this, banner);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager, true);

      /*  Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(), 4000, 6000);*/
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
//        if (homePageResponseResponse.body().getStatus()==200)
//        {
//            if (Util.isOnline(this) != false) {
//                progressDialog.show();
//                controller.setProductDetail(productID, getStringVal(Constants.CONSUMER_KEY), getStringVal(Constants.CONSUMER_SECRET));
//            } else {
//                Util.showToastMessage(this, "No Internet connection", getResources().getDrawable(R.drawable.ic_nointernet));
//            }
//        }

    }


    @Override
    public void onSuccessAddToCart(Response<AddToCart> response) {
        progressDialog.dismiss();

        Toast.makeText(this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String error) {
        progressDialog.dismiss();
        Util.showToastMessage(this, error, getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
    }
}
