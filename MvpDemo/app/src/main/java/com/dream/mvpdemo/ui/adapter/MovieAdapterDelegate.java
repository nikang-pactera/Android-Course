package com.dream.mvpdemo.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dream.mvpdemo.R;
import com.dream.mvpdemo.model.bean.Movie;
import com.kevin.delegationadapter.AdapterDelegate;
import com.nostra13.universalimageloader.core.ImageLoader;

public class MovieAdapterDelegate extends AdapterDelegate<Movie, MovieAdapterDelegate.ViewHolder> {

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.moive_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position, final Movie movie) {
        ImageLoader.getInstance().displayImage(movie.images.small, holder.mImageView);
        holder.time.setText("上映时间：" + movie.year + "年");
        holder.title.setText(movie.title);
        holder.subTitle.setText(movie.original_title);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView title;
        public TextView subTitle;
        public TextView time;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.movie_image);
            title = itemView.findViewById(R.id.movie_title);
            subTitle = itemView.findViewById(R.id.movie_sub_title);
            time = itemView.findViewById(R.id.movie_time);
        }
    }
}