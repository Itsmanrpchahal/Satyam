package com.mandy.satyam.productDetails;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.borjabravo.readmoretextview.ReadMoreTextView;
import com.mandy.satyam.R;
import com.mandy.satyam.addressActivity.AddressActivity;
import com.mandy.satyam.commentActivity.CommentActivity;
import com.mandy.satyam.homeFragment.adapter.ViewPagerAdapter;
import com.mandy.satyam.productDetails.adapter.ColorAdapter;
import com.mandy.satyam.productDetails.adapter.SeeRelatedItemAdapter;
import com.mandy.satyam.productDetails.adapter.SizeAdapter;
import com.mandy.satyam.utils.SpacesItemDecoration;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.relex.circleindicator.CircleIndicator;

public class ProductDetailsActivity extends AppCompatActivity {

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
    @BindView(R.id.circleindecator)
    CircleIndicator circleindecator;
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

    public static ViewPager viewPager2;
    public static NestedScrollView nestedScrollView;

    ArrayList<String> array_image;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        ButterKnife.bind(this);

        viewPager2 = (ViewPager) findViewById(R.id.viewPager2);
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScoll);

        filterBt.setVisibility(View.GONE);
        searchBt.setVisibility(View.GONE);
        back.setVisibility(View.GONE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        textView.setText("Product Details");

        linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetailsActivity.this, CommentActivity.class);
                intent.putExtra("pId", id);
                startActivity(intent);
            }
        });

        ColorAdapter colorAdapter = new ColorAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerColor.setLayoutManager(linearLayoutManager);
        recyclerColor.setAdapter(colorAdapter);
        recyclerColor.addItemDecoration(new SpacesItemDecoration(10));

        SizeAdapter adapter = new SizeAdapter(this);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerSize.setLayoutManager(linearLayoutManager2);
        recyclerSize.setAdapter(adapter);
        recyclerSize.addItemDecoration(new SpacesItemDecoration(10));

        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerRelated.setLayoutManager(linearLayoutManager3);
        SeeRelatedItemAdapter adapter2 = new SeeRelatedItemAdapter(this);
        recyclerRelated.setAdapter(adapter2);
        recyclerRelated.addItemDecoration(new SpacesItemDecoration(10));

        array_image = new ArrayList<String>();
        array_image.add(String.valueOf(R.drawable.best_s));
        array_image.add(String.valueOf(R.drawable.bestseller));
        array_image.add(String.valueOf(R.drawable.image_d));

       /* PagerAdapter adapterpager = new ViewPagerAdapter(this, array_image);
        viewPager.setAdapter(adapterpager);
        circleindecator.setViewPager(viewPager);*/
    }

    @OnClick({R.id.btnAddCart, R.id.btnBuynow})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnAddCart:
                Toast.makeText(this, "Item add into cart", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnBuynow:
                Adddialog();
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

}
