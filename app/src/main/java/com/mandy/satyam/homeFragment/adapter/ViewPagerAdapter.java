package com.mandy.satyam.homeFragment.adapter;


import android.content.Context;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.mandy.satyam.R;
import com.mandy.satyam.homeFragment.response.HomePageResponse;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

public class ViewPagerAdapter extends PagerAdapter {

    ArrayList<HomePageResponse.Data.Banner> array_image;
    Context context;
    private int pos = 0;


    public ViewPagerAdapter(Context context, ArrayList<HomePageResponse.Data.Banner> array_image) {
        this.context = context;
        this.array_image = array_image;

    }

    @Override
    public int getCount() {
        return array_image.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, final int position) {
        ImageView trailimg;
        final AVLoadingIndicatorView avLoadingIndicatorView;
        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemview = inflater.inflate(R.layout.item1, container, false);
        trailimg = itemview.findViewById(R.id.trailImage);
        Glide.with(context).load(array_image.get(position).getImage()).into(trailimg);
        Log.d("Images",array_image.get(position).getImage().toString());
        ((ViewPager) container).addView(itemview);


        return itemview;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        (container).removeView((View) object);
    }
}