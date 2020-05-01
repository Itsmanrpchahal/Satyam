package com.satyam.myCart;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.satyam.R;
import com.satyam.addressActivity.addAddress.ADDAddressActivity;
import com.satyam.baseclass.BaseClass;
import com.satyam.baseclass.Constants;
import com.satyam.controller.Controller;
import com.satyam.myCart.IF.AddCartQuantity;
import com.satyam.myCart.IF.RemoveCartIF;
import com.satyam.myCart.adapter.CartAdapter;
import com.satyam.myCart.response.GetCartProducts;
import com.satyam.myCart.response.RemoveCartItem;
import com.satyam.myCart.response.UpdateCart;
import com.satyam.utils.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Response;

public class MyCartActivity extends BaseClass implements Controller.GetCartProducts, Controller.UpdateCart, Controller.RemoveCartItem {

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
    @BindView(R.id.totalprice)
    TextView totalprice;
    @BindView(R.id.tax)
    TextView tax;
    @BindView(R.id.price_recycler)
    RecyclerView price_recycler;
    String user_id;
    ArrayList<GetCartProducts.Datum> getCartProductsArrayList = new ArrayList<>();
    Controller controller;
    Dialog progressdialog;
    public ArrayList<Integer> quantity = new ArrayList<>();
    public ArrayList<String> product_id = new ArrayList<>();
    String totalp;
    int P_quantity;
    float price;
    int position;
    JSONArray jsonArray;
    CartAdapter adapter3;
    Intent intent;
    String string,productID;
    static boolean outofStock;
    Dialog stockdialog;
    String is_address_update;
    float R_Quantity, R_Price, removeQuantity, removePrice, totalAmount, newP, newQ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);
        ButterKnife.bind(this);
        controller = new Controller((Controller.GetCartProducts) this, (Controller.UpdateCart) this, (Controller.RemoveCartItem) this);

        intent = getIntent();
        if (intent!=null)
        {
            string = intent.getStringExtra("isFrom");
            productID = intent.getStringExtra("productID");
        }
        manager = getSupportFragmentManager();
        searchBt.setVisibility(View.GONE);
        filterBt.setVisibility(View.GONE);


        progressdialog = Util.showDialog(this);
        progressdialog.show();

        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        textView.setText("My Cart");
        ((NestedScrollView) findViewById(R.id.scrool_view)).post(new Runnable() {
            public void run() {
                ((NestedScrollView) findViewById(R.id.scrool_view)).fullScroll(View.FOCUS_UP);
            }
        });

        if (outofStock==true)
        {
            dialog();
        }

        listeners();


    }

    @Override
    protected void onResume() {
        super.onResume();
        controller.setGetCartProducts(getStringVal(Constants.USERTOKEN));
    }

    @Override
    public void onBackPressed() {
//        if (intent.getStringExtra("isFrom").equals("ProductDetail"))
//        {
//            Intent intent = new Intent(MyCartActivity.this, ProductDetailsActivity.class);
//            intent.putExtra("productID",productID);
//            startActivity(intent);
//            finish();
//        }else {
            super.onBackPressed();
//        }

    }

    private void listeners() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MyCartActivity.this, ProductDetailsActivity.class);
//                intent.putExtra("productID",productID);
//                startActivity(intent);
//                finish();
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
        intent.putExtra("isFrom", "Cart");
        intent.putExtra("product_id", product_id);
        intent.putExtra("quantity", quantity);
        intent.putExtra("is_address_update",is_address_update);
        intent.putExtra("product_id_quantity", jsonArray.toString());
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
        JSONObject obj = null;
        jsonArray = new JSONArray();
        getCartProductsArrayList.clear();
        if (response.isSuccessful()) {
            if (response.body().getStatus() == 200) {
                if (response.body().getData().size() == 0) {
                    btnProced.setVisibility(View.GONE);
                    btnProced2.setVisibility(View.GONE);
                }
                jsonArray = new JSONArray();
                for (int i = 0; i < response.body().getData().size(); i++) {


                    obj = new JSONObject();
                    try {
                        obj.put("product_id", response.body().getData().get(i).getProductId());
                        obj.put("quantity", response.body().getData().get(i).getQuantity());
                        obj.put("variation_id", response.body().getData().get(i).getVariation_id());

                        jsonArray.put(obj);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    Log.d("JSONCHECK", jsonArray.toString());

                    GetCartProducts.Datum datum = response.body().getData().get(i);
                    getCartProductsArrayList.add(datum);
                    quantity.add(getCartProductsArrayList.get(i).getQuantity());
                    product_id.add(getCartProductsArrayList.get(i).getProductId());


                    // set data into cart
                    adapter3 = new CartAdapter(this, getCartProductsArrayList);
                    LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
                    recyclerCart.setLayoutManager(linearLayoutManager3);
                    recyclerCart.setAdapter(adapter3);
                    adapter3.CartAdapter(new AddCartQuantity() {
                        @Override
                        public void onSuccess(String quantity, String cartID) {
                            progressdialog.show();
                            controller.setUpdateCart(cartID, getStringVal(Constants.USERTOKEN), quantity);
                        }
                    });

                    adapter3.CartAdapter(new RemoveCartIF() {
                        @Override
                        public void cartID(String cartID, int pos, int quatity, float price1) {
                            position = pos;
                            P_quantity = quatity;
                            price = price1;
                            progressdialog.show();
                           /* float quantity = Float.parseFloat(txtItems.getText().toString());
                            float price = Float.parseFloat(totalprice.getText().toString());*/
//                            float t = quantity*price;

                            R_Quantity = Float.parseFloat(response.body().getTotal_quantity());
                            R_Price = Float.parseFloat(response.body().getTotal());
                            removeQuantity = quatity;
                            removePrice = price1;
                            totalAmount = removeQuantity * removePrice;


//                            Toast.makeText(MyCartActivity.this, ""+, Toast.LENGTH_SHORT).show();
                            controller.setRemoveCartItem(cartID, getStringVal(Constants.USER_ID), getStringVal(Constants.USERTOKEN));
                        }
                    });


                    txtItems.setText(String.valueOf(response.body().getTotal_quantity()));
                    tax.setText("₹" + response.body().getTax());
                    totalprice.setText("₹" + response.body().getTotal());
                    setStringVal(Constants.CART_COUNT,response.body().getTotal_quantity());
                    is_address_update = response.body().getIs_address_update();
                    //set Price Items


                }

            }

        } else {
            Util.showToastMessage(this, response.body().getMessage(), getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
        }
    }


    @Override
    public void onSuccessUpdateCart(Response<UpdateCart> response) {
        progressdialog.dismiss();
        recreate();
        if (response.isSuccessful()) {

            if (response.body().getStatus() == 200) {
                txtItems.setText(String.valueOf(response.body().getData().getTotalQuantity()));
                tax.setText("₹" + response.body().getData().getTax());
                totalprice.setText("₹" + response.body().getData().getTotal());
                Util.showToastMessage(this, "" + response.body().getMessage(), getResources().getDrawable(R.drawable.app_icon));
            } else {
                outofStock = true;
//                dialog();
//                Util.showToastMessage(this, "" + response.body().getMessage(), getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
            }
        }
    }

    private void dialog() {
        stockdialog = new Dialog(MyCartActivity.this);
        Window window = stockdialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        stockdialog.setContentView(R.layout.outofstock);
        stockdialog.setCancelable(false);
        stockdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        stockdialog.show();

        Button OK;
        OK = stockdialog.findViewById(R.id.ok_);

        OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               stockdialog.dismiss();
               outofStock = false;
            }
        });
    }

    @Override
    public void onSuccessRemoveCartItem(Response<RemoveCartItem> response) {
        progressdialog.dismiss();
        recreate();
        if (response.isSuccessful()) {
            if (response.body().getStatus() == 200) {

//                getCartProductsArrayList.remove(position);
//                adapter3.notifyDataSetChanged();
//                newP = R_Price - totalAmount;
//                newQ = R_Quantity - removeQuantity;
//                txtItems.setText(String.valueOf(newQ));
//                totalprice.setText("₹" + String.valueOf(newP));
                Util.showToastMessage(this, response.body().getMessage(), getResources().getDrawable(R.drawable.app_icon));


//                    recreate();
            } else {
                Util.showToastMessage(this, "Out of stock", getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
            }
        }
    }

    @Override
    public void onError(String error) {
        progressdialog.dismiss();
        Util.showToastMessage(this, "" + error, getResources().getDrawable(R.drawable.ic_error_outline_black_24dp));
    }
}
