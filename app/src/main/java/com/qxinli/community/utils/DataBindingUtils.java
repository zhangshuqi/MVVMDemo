package com.qxinli.community.utils;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.qxinli.community.R;

/**
 * Created by Administrator on 2017/2/9 0009.
 */

public class DataBindingUtils {
    @BindingAdapter("homeTint")
    public static void setTint(final ImageView imageView, int current) {
        if (imageView.getTag().equals(current + "")) {
            imageView.setColorFilter(imageView.getResources().getColor(R.color.colorPrimary));
        } else {
            imageView.setColorFilter(imageView.getResources().getColor(R.color.ripple_color));
        }
    }
    @BindingAdapter("adapter")
    public  static void setAdapter(RecyclerView rv , RecyclerView.Adapter adapter){
        rv.setAdapter(adapter);
    }
    @BindingAdapter("layoutManager")
    public static void setLayoutManager(RecyclerView rv, RecyclerView.LayoutManager layoutManager){
        rv.setLayoutManager(layoutManager);
    }
}
