package com.mandy.satyam.addressActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mandy.satyam.R;
import com.mandy.satyam.addressActivity.adapter.AddressAdapter;
import com.mandy.satyam.addressActivity.addAddress.ADDAddressActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AddressActivity extends AppCompatActivity {

    @BindView(R.id.tooolbar)
    Toolbar toolbar;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.recycler_address)
    RecyclerView recyclerAddress;
    @BindView(R.id.add_address_btn)
    Button addAddressBtn;
    @BindView(R.id.continue_btn)
    Button continueBtn;
    @BindView(R.id.linear5)
    LinearLayout linear5;
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.search_bt)
    ImageButton searchBt;
    @BindView(R.id.filter_bt)
    ImageButton filterBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        ButterKnife.bind(this);

        back.setVisibility(View.GONE);
        searchBt.setVisibility(View.GONE);
        filterBt.setVisibility(View.GONE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        textView.setText("Address list");

        AddressAdapter addressAdapter = new AddressAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerAddress.setLayoutManager(linearLayoutManager);
        recyclerAddress.setAdapter(addressAdapter);


    }

    @OnClick({R.id.add_address_btn, R.id.continue_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add_address_btn:
                Intent intent = new Intent(this, ADDAddressActivity.class);
                startActivity(intent);
                break;
            case R.id.continue_btn:

               /* Intent intent1 = new Intent(this, PaymentActivity.class);
                startActivity(intent1);*/
                break;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
