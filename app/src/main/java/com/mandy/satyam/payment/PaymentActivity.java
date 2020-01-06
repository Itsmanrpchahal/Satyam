package com.mandy.satyam.payment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mandy.satyam.MainActivity;
import com.mandy.satyam.commonActivity.NoInternetActivity;
import com.mandy.satyam.GetMeesageApi;
import com.mandy.satyam.controller.Controller;
import com.mandy.satyam.myCart.CartAdapter;
import com.mandy.satyam.R;
import com.mandy.satyam.utils.SpacesItemDecoration;
import com.mandy.satyam.termsandcondition.TermsActivity;
import com.mandy.satyam.utils.CheckInternet;
import com.mandy.satyam.utils.Config;
import com.mandy.satyam.utils.ProgressBarClass;
import com.mandy.satyam.utils.SharedToken;
import com.mandy.satyam.utils.Snack;
import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPGService;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;

import org.json.JSONArray;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class PaymentActivity extends AppCompatActivity {

    @BindView(R.id.tooolbar)
    Toolbar toolbar;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.textPolicy)
    TextView textPolicy;
    @BindView(R.id.txtItem)
    TextView txtItem;
    @BindView(R.id.txtDelivery)
    TextView txtDelivery;
    @BindView(R.id.txtOrderTotal)
    TextView txtOrderTotal;
    @BindView(R.id.reyclerPayment)
    RecyclerView reyclerPayment;
    @BindView(R.id.scrool_view)
    NestedScrollView scroolView;
    @BindView(R.id.txtAddress)
    TextView txtAddress;
    @BindView(R.id.btnPayment)
    Button btnPayment;
    @BindView(R.id.txtItemQuantity)
    TextView txtItemQuantity;

    Controller controller;
    SharedToken sharedToken;
    String token, address, Cid, userId, totalPrice, website, callbackUrl, orderId, checkSomeCode;
    Dialog dialog;
    String transationId;
    JSONArray jsonArray;
    ArrayList<HashMap> arrayList = new ArrayList<>();
    ArrayList<PaymentProductApi.TotalCartProduct> products = new ArrayList<>();
    String color = "", size = "", quan = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        ButterKnife.bind(this);
        controller = new Controller((Controller.BuyItemsList) this, (Controller.IncreseItemQuantity) this, (Controller.DeleteItems) this, (Controller.GetCheckSome) this, (Controller.SaveOrder) this, (Controller.ClearCart) this);

        sharedToken = new SharedToken(this);
        dialog = ProgressBarClass.showProgressDialog(this);
        dialog.show();

        token = "Bearer " + sharedToken.getShared();
        userId = sharedToken.getUserId();
        address = getIntent().getStringExtra("Address");
        Cid = getIntent().getStringExtra("Cid");
        txtAddress.setText(address);

        if (!Cid.equals("0")) {
            color = getIntent().getStringExtra("color");
            size = getIntent().getStringExtra("size");
            quan = getIntent().getStringExtra("quan");
        }

        if (CheckInternet.isInternetAvailable(this)) {
            controller.getBuyItemsList(token, Cid, color, size, quan);
        } else {
            startActivity(new Intent(this, NoInternetActivity.class));
        }
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        textView.setText("Payment");

        //get terms and condition
        passIntent();

        PaymentItemsAdapter adapter = new PaymentItemsAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        reyclerPayment.setLayoutManager(linearLayoutManager);
        reyclerPayment.setAdapter(adapter);
        reyclerPayment.addItemDecoration(new SpacesItemDecoration(10));



    }

    //click event on textView click
    private void passIntent() {
        SpannableString ss = new SpannableString(getResources().getString(R.string.policy));

        //click for privicy
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Intent intent = new Intent(PaymentActivity.this, TermsActivity.class);
                intent.putExtra("T", "T");
                startActivity(intent);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(true);
            }
        };


        // click for condition
        ClickableSpan privacy = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                Intent intent = new Intent(PaymentActivity.this, TermsActivity.class);
                intent.putExtra("T", "C");
                startActivity(intent);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(true);
            }
        };

        ss.setSpan(clickableSpan, 45, 59, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(privacy, 64, 81, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textPolicy.setHighlightColor(Color.TRANSPARENT);
        textPolicy.setText(ss, TextView.BufferType.SPANNABLE);
        textPolicy.setText(ss);
        textPolicy.setMovementMethod(LinkMovementMethod.getInstance());

    }



    @OnClick(R.id.btnPayment)
    public void onViewClicked(View view) {

        HashMap<String, String> hashMap = new HashMap<>();
        for (int i = 0; i < products.size(); i++) {
            hashMap.put("product_id", products.get(i).getProductId().toString());
            hashMap.put("product_order_quantity", products.get(i).getProductOrderQuantity().toString());
            hashMap.put("product_name", products.get(i).getProductName());
            hashMap.put("special_price", products.get(i).getSpecialPrice().toString());
            hashMap.put("size", products.get(i).getSize());
            hashMap.put("color", products.get(i).getColor());

            arrayList.add(hashMap);
            jsonArray = new JSONArray(arrayList);
        }


        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("'DS'-DyykmsS", Locale.ENGLISH);
        orderId = df.format(c.getTime());
        website = "WEBSTAGING";
        callbackUrl = "https://securegw-stage.paytm.in/theia/paytmCallback?ORDER_ID=" + orderId;
        if (CheckInternet.isInternetAvailable(this)) {
            dialog.show();
            controller.setGetCheckSome(token, Config.GET_MID, orderId, userId, "Retail", "WAP", totalPrice, website, callbackUrl);
        } else {
            startActivity(new Intent(this, NoInternetActivity.class));
        }
    }



    // open paytm get way
    private void openPaytm(String checkSomeCode) {
        PaytmPGService service = PaytmPGService.getStagingService();
        HashMap<String, String> paraMap = new HashMap<>();
        paraMap.put("MID", Config.GET_MID);
        paraMap.put("ORDER_ID", orderId);
        paraMap.put("CUST_ID", userId);
        paraMap.put("CHANNEL_ID", "WAP");
        paraMap.put("TXN_AMOUNT", totalPrice);
        paraMap.put("WEBSITE", website);
        // This is the staging value. Production value is available in your dashboard
        paraMap.put("INDUSTRY_TYPE_ID", "Retail");
        // This is the staging value. Production value is available in your dashboard
        paraMap.put("CALLBACK_URL", callbackUrl);
        paraMap.put("CHECKSUMHASH", checkSomeCode);

        PaytmOrder Order = new PaytmOrder(paraMap);
        service.initialize(Order, null);

        service.startPaymentTransaction(this, true, true, new PaytmPaymentTransactionCallback() {
            @Override
            public void onTransactionResponse(Bundle inResponse) {
                dialog.show();
                transationId = inResponse.getString("TXNID");
                controller.setSaveOrder(token, orderId, address, totalPrice, transationId, jsonArray);

            }

            @Override
            public void networkNotAvailable() {

            }

            @Override
            public void clientAuthenticationFailed(String inErrorMessage) {

            }

            @Override
            public void someUIErrorOccurred(String inErrorMessage) {

            }

            @Override
            public void onErrorLoadingWebPage(int iniErrorCode, String inErrorMessage, String inFailingUrl) {

            }

            @Override
            public void onBackPressedCancelTransaction() {

            }

            @Override
            public void onTransactionCancel(String inErrorMessage, Bundle inResponse) {

            }
        });


    }
}


