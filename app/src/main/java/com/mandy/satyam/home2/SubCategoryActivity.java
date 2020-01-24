package com.mandy.satyam.home2;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mandy.satyam.R;
import com.mandy.satyam.utils.SpacesItemDecoration;
import com.mandy.satyam.home2.adapter.BottomWearAdapter;
import com.mandy.satyam.home2.adapter.ExploreMoreAdapter;
import com.mandy.satyam.home2.adapter.TopWearAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SubCategoryActivity extends AppCompatActivity {

    @BindView(R.id.tooolbar)
    Toolbar toolbar;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.recyclerTop)
    RecyclerView recyclerTop;
    @BindView(R.id.recyclerBottom)
    RecyclerView recyclerBottom;
    @BindView(R.id.recyclerExploreMore)
    RecyclerView recyclerExploreMore;

    Dialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);
        ButterKnife.bind(this);



        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        textView.setText("Sub Cateogry list");

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerTop.setLayoutManager(layoutManager);
        recyclerTop.addItemDecoration(new SpacesItemDecoration(10));
        TopWearAdapter adapter = new TopWearAdapter(this);
        recyclerTop.setAdapter(adapter);


        GridLayoutManager layoutManager2 = new GridLayoutManager(this, 2);
        recyclerBottom.setLayoutManager(layoutManager2);
        recyclerBottom.addItemDecoration(new SpacesItemDecoration(10));
        BottomWearAdapter adapter2 = new BottomWearAdapter(this);
        recyclerBottom.setAdapter(adapter2);

        GridLayoutManager layoutManager3 = new GridLayoutManager(this, 2);
        recyclerExploreMore.setLayoutManager(layoutManager3);
        recyclerExploreMore.addItemDecoration(new SpacesItemDecoration(10));
        ExploreMoreAdapter adapter3 = new ExploreMoreAdapter(this);
        recyclerExploreMore.setAdapter(adapter3);

    }



    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
