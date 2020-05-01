package com.satyam.filterScreen;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jaygoo.widget.OnRangeChangedListener;
import com.jaygoo.widget.RangeSeekBar;
import com.satyam.R;
import com.satyam.controller.Controller;
import com.satyam.filterScreen.Adapter.FilterAdapter;
import com.satyam.filterScreen.filterIF.FilterCateidIF;
import com.satyam.productList.ProductsActivity;
import com.satyam.productList.response.SubCategory;
import com.satyam.utils.Util;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

public class FilterActivity extends AppCompatActivity implements View.OnClickListener, Controller.ProductSubCategories {

    @BindView(R.id.filter_close)
    ImageButton filter_close;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.resetFilter)
    Button resetFilter;
    @BindView(R.id.rangeseekbar)
    RangeSeekBar rangeseekbar;
    @BindView(R.id.apllyFilter)
    Button apllyFilter;
    @BindView(R.id.startprice)
    TextView startprice;
    @BindView(R.id.endprice)
    TextView endprice;
    @BindView(R.id.brandRecycler)
    RecyclerView brandRecycler;
    Controller controller;
    Dialog dialog;
    ArrayList<SubCategory.Datum> subcate = new ArrayList<>();
    Intent intent;
    String catID, startPrice="0.0", endPrice="0.0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        dialog = Util.showDialog(this);
        intent = getIntent();
        dialog.show();
        controller = new Controller(this);

        if (intent != null) {
            catID = intent.getStringExtra("cateID");
            if (Util.isOnline(FilterActivity.this) != false) {
                controller.setSubCategory(catID);

            } else {
                Util.showToastMessage(FilterActivity.this, "No Internet connection", getResources().getDrawable(R.drawable.ic_nointernet));
            }
        }

        ButterKnife.bind(this);
        listeners();

    }


    private void listeners() {

        filter_close.setOnClickListener(this);

        rangeseekbar.setProgress(0,100);
        rangeseekbar.setOnRangeChangedListener(new OnRangeChangedListener() {
            @Override
            public void onRangeChanged(RangeSeekBar view, float leftValue, float rightValue, boolean isFromUser) {
                Log.d("onRangeChanged", leftValue + "  " + rightValue);
                startprice.setText("₹" + (int) leftValue);
                endprice.setText("₹" + (int) rightValue);
                startPrice = String.valueOf(leftValue);
                endPrice = String.valueOf(rightValue);
            }

            @Override
            public void onStartTrackingTouch(RangeSeekBar view, boolean isLeft) {
                Log.d("onStartTrackingTouch", String.valueOf(isLeft));
            }

            @Override
            public void onStopTrackingTouch(RangeSeekBar view, boolean isLeft) {
                Log.d("onStopTrackingTouch", String.valueOf(isLeft));
            }
        });

        apllyFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (endPrice.equals("0.0"))
                {
                    Util.showToastMessage(FilterActivity.this,"Select Price to continue",getResources().getDrawable(R.drawable.app_icon));
                }else {
                    if (Util.isOnline(FilterActivity.this) != false) {
                        Intent intent = new Intent(FilterActivity.this, ProductsActivity.class);
                        intent.putExtra("isFrom", "FilterScreen");
                        intent.putExtra("startPrice", startPrice);
                        intent.putExtra("catID", catID);
                        intent.putExtra("endPrice", endPrice);
                        startActivity(intent);
                    } else {
                        Util.showToastMessage(FilterActivity.this, "No Internet connection", getResources().getDrawable(R.drawable.ic_nointernet));
                    }
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.filter_close:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onSuccessSubcate(Response<SubCategory> subCategoryResponse) {
        dialog.dismiss();
        if (subCategoryResponse.isSuccessful()) {
            if (subCategoryResponse.body().getStatus() == 200) {
                rangeseekbar.setRange(Float.valueOf(subCategoryResponse.body().getMinPrice()), Float.valueOf(subCategoryResponse.body().getMaxPrice()));
                startprice.setText("₹" + subCategoryResponse.body().getMinPrice());
                endprice.setText("₹" + subCategoryResponse.body().getMaxPrice());
                for (int i = 0; i < subCategoryResponse.body().getData().size(); i++) {
                    SubCategory.Datum datum = subCategoryResponse.body().getData().get(i);
                    subcate.add(datum);
                    FilterAdapter filterAdapter = new FilterAdapter(this, subcate);
                    LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
                    brandRecycler.setLayoutManager(linearLayoutManager3);
                    brandRecycler.setAdapter(filterAdapter);
                    filterAdapter.FilterAdapter(new FilterCateidIF() {
                        @Override
                        public void onSuccess(String cateID) {
                            catID = cateID;
                        }
                    });
                }
            }

        }
    }

    @Override
    public void onError(String error) {
        dialog.dismiss();
        Util.showToastMessage(this, error, getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
    }
}
