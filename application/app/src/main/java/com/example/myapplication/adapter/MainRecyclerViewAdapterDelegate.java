package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.ui.MainActivity;
import com.kevin.delegationadapter.extras.ClickableAdapterDelegate;

public class MainRecyclerViewAdapterDelegate extends ClickableAdapterDelegate<String, MainRecyclerViewAdapterDelegate.ViewHolder> {
    private MainActivity mActivity;

    public MainRecyclerViewAdapterDelegate(MainActivity activity) {
        this.mActivity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_recycler_view, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position, final String item) {
        super.onBindViewHolder(holder, position, item);
        holder.tvName.setText(item);
    }

    @Override
    public void onItemClick(View view, String item, int position) {
        mActivity.onItemClick(position, item);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.item_main_recycler_view_tv_title);
        }
    }
}