package com.dream.mvpdemo.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dream.mvpdemo.R;
import com.dream.mvpdemo.model.bean.GankEntry;
import com.kevin.delegationadapter.AdapterDelegate;
import com.squareup.picasso.Picasso;

public class GankAdapterDelegate extends AdapterDelegate<GankEntry, GankAdapterDelegate.ViewHolder> {

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gank_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position, final GankEntry gankEntry) {
        Picasso.get() // 初始化
                .load(gankEntry.url) // 加载地址
                .placeholder(R.mipmap.ic_launcher) // 占位符
                .error(R.mipmap.ic_launcher) // 错误图片
                .into(holder.mImageView); // 加载控件
        holder.descText.setText(gankEntry.desc);
        holder.authorText.setText(gankEntry.who);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView descText;
        public TextView authorText;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.gank_iamge);
            descText = itemView.findViewById(R.id.desc);
            authorText = itemView.findViewById(R.id.author);
        }
    }
}