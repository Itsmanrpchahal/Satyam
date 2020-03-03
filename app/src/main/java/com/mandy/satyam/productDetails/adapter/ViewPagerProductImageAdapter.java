package com.mandy.satyam.productDetails.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.jsibbold.zoomage.ZoomageView;
import com.mandy.satyam.R;
import com.mandy.satyam.productDetails.ProductDetailsActivity;
import com.mandy.satyam.productDetails.response.ProductDetailResponse;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

public class ViewPagerProductImageAdapter extends PagerAdapter {


    Context context;
    boolean abc = false;
    ArrayList<ProductDetailResponse.Data.Image> array_image = new ArrayList<>();
    ImageView trailimg;

    public ViewPagerProductImageAdapter(Context context, ArrayList<ProductDetailResponse.Data.Image> array_image1) {
        this.context = context;
        this.array_image = array_image1;
        notifyDataSetChanged();

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
         trailimg =  itemview.findViewById(R.id.trailImage);
        assert itemview != null;
//        trailimg.setImageResource(Integer.parseInt(array_image.get(position).getSrc()));
        if (array_image.size()>=1)
        {
            Glide.with(context).load(array_image.get(position).getSrc()).placeholder(R.drawable.ic_satyamplaceholder).override(Target.SIZE_ORIGINAL).into(trailimg);
        }else {
            Glide.with(context).load("").placeholder(R.drawable.ic_satyamplaceholder).into(trailimg);
        }

        Log.d("IMAGES",""+array_image.get(position).getSrc());
        ViewPager vp = (ViewPager)container;
        vp.addView(itemview,0);


        trailimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showImage(array_image.get(position).getSrc());
            }
        });

        return itemview;
    }

    private void showImage(String image_drawable) {

        Dialog dialogAlert = new Dialog(context,R.style.Theme_Dialog);
        dialogAlert.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogAlert.setContentView(R.layout.layout_image);
        ImageView img_selected_item = (ImageView) dialogAlert.findViewById(R.id.img_selected_item);
        Glide.with(context).load(image_drawable).placeholder(R.drawable.ic_satyamplaceholder).into(img_selected_item);
        dialogAlert.show();
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager) container).removeView((RelativeLayout) object);
    }

}
