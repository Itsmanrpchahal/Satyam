package com.mandy.satyam.myCart;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mandy.satyam.R;
import com.mandy.satyam.addressActivity.addAddress.ADDAddressActivity;
import com.mandy.satyam.baseclass.BaseClass;
import com.mandy.satyam.baseclass.Constants;
import com.mandy.satyam.controller.Controller;
import com.mandy.satyam.myCart.IF.AddCartQuantity;
import com.mandy.satyam.myCart.IF.RemoveCartIF;
import com.mandy.satyam.myCart.adapter.CartAdapter;
import com.mandy.satyam.myCart.response.GetCartProducts;
import com.mandy.satyam.myCart.response.RemoveCartItem;
import com.mandy.satyam.myCart.response.UpdateCart;
import com.mandy.satyam.utils.SpacesItemDecoration;
import com.mandy.satyam.utils.Util;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Response;

public class MyCartActivity extends BaseClass implements Controller.GetCartProducts,Controller.UpdateCart,Controller.RemoveCartItem {

    @BindView(R.id.tooolbar)
    Toolbar toolbar;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.txtItems)
    TextView txtItems;
    @BindView(R.id.txtPrice)
    TextView txtPrice;
    @BindView(R.id.btnProced)
    Button btnProced;
    @BindView(R.id.recyclerCart)
    RecyclerView recyclerCart;
    @BindView(R.id.recyclerMore)
    RecyclerView recyclerMore;
    @BindView(R.id.recyclerTopPickup)
    RecyclerView recyclerTopPickup;
    @BindView(R.id.btnProced2)
    Button btnProced2;
    @BindView(R.id.scrool_view)
    NestedScrollView scroolView;
    Dialog dialog;
    FragmentManager manager;
    @BindView(R.id.search_bt)
    ImageButton searchBt;
    @BindView(R.id.filter_bt)
    ImageButton filterBt;
    @BindView(R.id.back)
    ImageButton back;
    String user_id;
    ArrayList<GetCartProducts.Datum> getCartProductsArrayList = new ArrayList<>();
    Controller controller;
    Dialog progressdialog;
    public  ArrayList<Integer> quantity = new ArrayList<>();
    public  ArrayList<String> product_id = new ArrayList<>();
    String totalquantity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);
        ButterKnife.bind(this);
        controller = new Controller((Controller.GetCartProducts)this,(Controller.UpdateCart)this,(Controller.RemoveCartItem)this);


        manager = getSupportFragmentManager();
        searchBt.setVisibility(View.GONE);
        filterBt.setVisibility(View.GONE);


        progressdialog = Util.showDialog(this);
        progressdialog.show();
        controller.setGetCartProducts(getStringVal(Constants.USERTOKEN));

        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        textView.setText("My Cart");
        ((NestedScrollView) findViewById(R.id.scrool_view)).post(new Runnable() {
            public void run() {
                ((NestedScrollView) findViewById(R.id.scrool_view)).fullScroll(View.FOCUS_UP);
            }
        });



        listeners();


    }

    private void listeners() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    @OnClick({R.id.btnProced, R.id.btnProced2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnProced:
                sendData();
                break;
            case R.id.btnProced2:
                sendData();
                break;
        }
    }


    private void sendData() {
        Intent intent = new Intent(this, ADDAddressActivity.class);
        intent.putExtra("product_id", product_id);
        intent.putExtra("quantity",quantity);
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onSuccessGetCart(Response<GetCartProducts> response) {
        progressdialog.dismiss();
        if (response.isSuccessful()) {
            if (response.body().getStatus() == 200) {
                for (int i = 0; i < response.body().getData().size(); i++) {

                    if (response.body().getData().size()==0)
                    {
                        btnProced.setVisibility(View.GONE);
                        btnProced2.setVisibility(View.GONE);
                    }
                    GetCartProducts.Datum datum = response.body().getData().get(i);
                    getCartProductsArrayList.add(datum);
                    quantity.add(getCartProductsArrayList.get(i).getQuantity());
                    product_id.add(getCartProductsArrayList.get(i).getProductId());

                    // set data into cart
                    CartAdapter adapter3 = new CartAdapter(this, getCartProductsArrayList);
                    LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
                    recyclerCart.setLayoutManager(linearLayoutManager3);
                    recyclerCart.setAdapter(adapter3);
                    adapter3.CartAdapter(new AddCartQuantity() {
                        @Override
                        public void onSuccess(String quantity, String cartID) {
                            progressdialog.show();
                            controller.setUpdateCart(cartID,getStringVal(Constants.USERTOKEN),quantity);
                        }
                    });

                    adapter3.CartAdapter(new RemoveCartIF() {
                        @Override
                        public void cartID(String cartID) {
//                            Toast.makeText(MyCartActivity.this, ""+cartID, Toast.LENGTH_SHORT).show();
                            progressdialog.show();
                            controller.setRemoveCartItem(cartID,getStringVal(Constants.USER_ID),getStringVal(Constants.USERTOKEN));
                        }
                    });
                }
            }

        } else {
            Toast.makeText(this, "Failure " + response.message(), Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onSuccessUpdateCart(Response<UpdateCart> response) {
        progressdialog.dismiss();
            if (response.isSuccessful())
            {
                if (response.body().getStatus()==200)
                {
                    Util.showToastMessage(this, "" + response.body().getMessage(), getResources().getDrawable(R.drawable.app_icon));
                }else {
                    Util.showToastMessage(this, "" + response.body().getMessage(), getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
                }

                recreate();
            }
    }

    @Override
    public void onSuccessRemoveCartItem(Response<RemoveCartItem> response) {
            progressdialog.dismiss();
            if (response.isSuccessful())
            {
                if (response.body().getStatus()==200)
                {
                    Util.showToastMessage(this,response.body().getMessage(),getResources().getDrawable(R.drawable.app_icon));
                    recreate();
                }else {
                    Util.showToastMessage(this,response.body().getMessage(),getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
                }
            }
    }

    @Override
    public void onError(String error) {
        progressdialog.dismiss();
        Util.showToastMessage(this, "" + error, getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
    }
}
