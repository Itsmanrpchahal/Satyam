package com.satyam.allCategory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.satyam.MainActivity;
import com.satyam.R;
import com.satyam.homeFragment.HomeFragment;
import com.satyam.myCart.MyCartActivity;

public class AllCategory extends AppCompatActivity {

    RecyclerView allcategory;
    ImageButton backAllCategotyBT;
    TextView cart_count;
    ImageButton product_cart;
    RecyclerView searchitemrecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_category);

        allcategory = findViewById(R.id.allcategory);
        backAllCategotyBT= findViewById(R.id.backAllCategotyBT);
        cart_count = findViewById(R.id.cart_count);
        product_cart = findViewById(R.id.product_cart);
        searchitemrecycler = findViewById(R.id.searchitemrecycler);


        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        allcategory.setLayoutManager(layoutManager);
        AllCategoryAdapter adapter = new AllCategoryAdapter(this, HomeFragment.categories);
        allcategory.setAdapter(adapter);


        product_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myCartIntent = new Intent(AllCategory.this, MyCartActivity.class);
                startActivity(myCartIntent);
            }
        });

        backAllCategotyBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        cart_count.setText(MainActivity.count);
    }
}
