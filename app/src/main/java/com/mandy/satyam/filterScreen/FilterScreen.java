package com.mandy.satyam.filterScreen;

import android.os.Bundle;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.jaygoo.widget.RangeSeekBar;
import com.mandy.satyam.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FilterScreen extends AppCompatActivity {

    @BindView(R.id.filter_close)
    ImageButton filterClose;
    @BindView(R.id.brand_tv)
    TextView brandTv;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.category_tv)
    TextView categoryTv;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.colors_tv)
    TextView colorsTv;
    @BindView(R.id.view3)
    View view3;
    @BindView(R.id.rangeseekbar)
    RangeSeekBar rangeseekbar;
    @BindView(R.id.filter_startprice)
    TextView filterStartprice;
    @BindView(R.id.filter_endprice)
    TextView filterEndprice;
    @BindView(R.id.applyfilterbt)
    Button applyfilterbt;
    @BindView(R.id.brand_spinner)
    Spinner brandSpinner;
    @BindView(R.id.size_spinner)
    Spinner sizeSpinner;
    @BindView(R.id.colors_spinner)
    Spinner colorsSpinner;
    String[] bramnds = {"Nike","Puma","LV"};
    String[] colors = {"Red","Green","White","Black"};
    String[] sizes = {"M","L","X","XXL","XXXL"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_screen);
        ButterKnife.bind(this);

        listeners();
    }

    private void listeners() {
        filterClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //ToDo: BrandSpinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(FilterScreen.this, android.R.layout.simple_spinner_item, bramnds);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        brandSpinner.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        //ToDo: ColorSpinner
        ArrayAdapter<String> colorAdapter = new ArrayAdapter<String>(FilterScreen.this, android.R.layout.simple_spinner_item, colors);
        colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colorsSpinner.setAdapter(colorAdapter);
        colorAdapter.notifyDataSetChanged();

        //ToDo: SizeSpinner
        ArrayAdapter<String> sizeAdapter = new ArrayAdapter<String>(FilterScreen.this, android.R.layout.simple_spinner_item, sizes);
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeSpinner.setAdapter(sizeAdapter);
        sizeAdapter.notifyDataSetChanged();
    }
}
