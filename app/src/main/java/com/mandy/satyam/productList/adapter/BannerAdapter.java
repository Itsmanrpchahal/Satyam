package com.mandy.satyam.productList.adapter;

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
import com.mandy.satyam.homeFragment.response.Categoriesroducts;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

public class BannerAdapter extends PagerAdapter {

    ArrayList<Categoriesroducts.CatgoryBanner> catgoryBanners ;
    Context context;

    public BannerAdapter(ArrayList<Categoriesroducts.CatgoryBanner> catgoryBanners, Context context) {
        this.catgoryBanners = catgoryBanners;
        this.context = context;
    }

    @Override
    public int getCount() {
        return catgoryBanners.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((RelativeLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView trailimg;
        final AVLoadingIndicatorView avLoadingIndicatorView;
        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemview = inflater.inflate(R.layout.item1, container, false);
        trailimg = itemview.findViewById(R.id.trailImage);
        Glide.with(context).load(catgoryBanners.get(position).getBanner()).into(trailimg);
        Log.d("Images",catgoryBanners.get(position).getBanner().toString());

        ((ViewPager) container).addView(itemview);

        return itemview;
    }
}
