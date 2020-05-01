package com.satyam.homeFragment.adapter;


import android.app.Dialog;
import android.content.Context;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.satyam.R;
import com.satyam.homeFragment.response.HomePageResponse;
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
        if (array_image.get(position).getImage().equals(""))
        {
            trailimg.setImageResource(R.drawable.ic_satyamplaceholder);
        }else {
            Glide.with(context).load(array_image.get(position).getImage()).into(trailimg);
        }

        Log.d("Images",array_image.get(position).getImage().toString());
        ((ViewPager) container).addView(itemview);

        trailimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showImage(array_image.get(position).getImage().toString());
            }
        });

        return itemview;
    }

    private void showImage(String image_drawable) {

        Dialog dialogAlert = new Dialog(context,R.style.Theme_Dialog);
        dialogAlert.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogAlert.setContentView(R.layout.layout_image);
//        RelativeLayout relativeLayout = dialogAlert.findViewById(R.id.layout_image);
        ImageView img_selected_item = (ImageView) dialogAlert.findViewById(R.id.img_selected_item);
//        relativeLayout.setBackgroundResource(Integer.parseInt(image_drawable));
        Glide.with(context).load(image_drawable).placeholder(R.drawable.ic_satyamplaceholder).into(img_selected_item);
        dialogAlert.show();


    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        (container).removeView((View) object);
    }
}