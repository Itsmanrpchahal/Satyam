package com.mandy.satyam.productDetails.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.mandy.satyam.R;
import com.mandy.satyam.productDetails.response.ProductDetailResponse;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

public class ViewPagerProductImageAdapter extends PagerAdapter {


    Context context;
    boolean abc = false;
    ArrayList<ProductDetailResponse.Data.Image> array_image = new ArrayList<>();

    public ViewPagerProductImageAdapter(Context context, ArrayList<ProductDetailResponse.Data.Image> array_image1) {
        this.context = context;
        this.array_image = array_image1;

    }

    @Override
    public int getCount() {
        return array_image.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((RelativeLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemview = inflater.inflate(R.layout.item, container, false);
        ImageView trailimg = (ImageView) itemview.findViewById(R.id.trailImage);
//        trailimg.setImageResource(Integer.parseInt(array_image.get(position).getSrc()));
        Glide.with(context).load(array_image.get(position).getSrc()).into(trailimg);
        Log.d("IMAGES",array_image.get(position).getSrc());
        ViewPager vp = (ViewPager)container;
        vp.addView(itemview,0);
        return itemview;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager) container).removeView((RelativeLayout) object);
    }

}
