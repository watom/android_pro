package com.haitao.www.myformer.utils;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;


/**
 * 加载图片
 * Created by Administrator on 2018/3/7 0007.
 */

public class ImageUtils {
    public static void loadImage(Context context, String url, final ImageView imageView) {
        if (imageView == null) return;
        Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
    }

    public static void loadImage(Activity activity, String url, final ImageView imageView) {
        if (imageView == null) return;
        Glide.with(activity).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
    }

    public static void loadImage(Fragment fragment, String url, final ImageView imageView) {
        if (imageView == null) return;
        Glide.with(fragment).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
    }

    public static void loadImage(Activity activity,int res,ImageView imageView) {
        if (imageView == null) return;
        Glide.with(activity).load(res).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
    }

}
