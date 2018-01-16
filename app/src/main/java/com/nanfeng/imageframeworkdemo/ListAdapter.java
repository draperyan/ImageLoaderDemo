package com.nanfeng.imageframeworkdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/1/14.
 *
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<String> urls;

    public ListAdapter(Context context, ArrayList<String> urls) {
        this.context = context;
        this.urls = urls;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View itemView =  LayoutInflater.from(context).inflate(R.layout.item_list, parent,
                false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
       String url =  urls.get(position);
       //LoadImageUtil.loadByImageloader(context,url,holder.img);
        LoadImageUtil.loadByGlide(context,url,holder.img);
    }

    @Override
    public int getItemCount() {
        return urls.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView img;

        public MyViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.image);
        }
    }
}
