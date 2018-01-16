package com.nanfeng.imageframeworkdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 * Created by Administrator on 2018/1/14.
 */

public class LoadImageUtil {
    private long startTime;

    public static void loadByGlide(Context context,String url, ImageView imageView) {

        RequestOptions options = new RequestOptions().dontTransform()
                .skipMemoryCache(false).diskCacheStrategy(DiskCacheStrategy.ALL);
       // startTime = System.currentTimeMillis();

        Glide.with(context).load(url).apply(options)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        Log.e("onLoadFailed", "onLoadFailed");
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                       // long time = System.currentTimeMillis() - startTime;
                       // Log.e("Glidetime",time + "");
                        return false;
                    }
                }).into(imageView);
    }


    public static void loadByImageloader(Context context,String url, ImageView imageView) {
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(false).cacheOnDisk(false).build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).defaultDisplayImageOptions(defaultOptions).build();
        ImageLoader loader=ImageLoader.getInstance();
        loader.init(config);

        // loader.displayImage("http://picm.photophoto.cn/006/018/030/0180300367.jpg",imageView);

       // startTime = System.currentTimeMillis();

        loader.displayImage(url, imageView, new ImageLoadingListener() {

            @Override
            public void onLoadingStarted(String imageUri, View view) {
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                Log.e("onLoadingFailed",  imageUri);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                //long time = System.currentTimeMillis() - startTime;
              //  Log.e(" ImageLoadertime",time + "");
                Log.e("onLoadingComplete",  imageUri);
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {

            }
        });
    }
}
