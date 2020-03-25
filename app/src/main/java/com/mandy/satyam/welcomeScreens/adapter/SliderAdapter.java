package com.mandy.satyam.welcomeScreens.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.mandy.satyam.R;

public class SliderAdapter extends PagerAdapter {


    public int[] slide_images = {
            R.color.white,
            R.color.white,
            R.color.white
    };
    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    // Arrays


    public int[] logo_ = {
            R.drawable.s1,
            R.drawable.s2,
            R.drawable.s3
    };

    public int[] slide_descs = {
            R.string.slide1,
            R.string.slide2,
            R.string.slide3

    };

    public int[] slide_text = {

            R.string.slide11,
            R.string.slide22,
            R.string.slide33
    };


    @Override
    public int getCount() {
        return slide_images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);
        ImageView slide_image = view.findViewById(R.id.slide_image);
        TextView textView = view.findViewById(R.id.slide_text);
        TextView textView1 = view.findViewById(R.id.slide_text1);
        ImageView logo = view.findViewById(R.id.logo);


        slide_image.setImageResource(slide_images[position]);
        textView.setText(slide_descs[position]);
        textView1.setText(slide_text[position]);
        logo.setImageResource(logo_[position]);


        container.addView(view);
        return view;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout) object);


    }
}