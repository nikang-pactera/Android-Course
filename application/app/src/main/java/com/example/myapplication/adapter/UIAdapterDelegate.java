package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.ui.LayoutActivity;
import com.example.myapplication.ui.UIActivity;
import com.kevin.delegationadapter.extras.ClickableAdapterDelegate;

public class UIAdapterDelegate extends ClickableAdapterDelegate<String, UIAdapterDelegate.ViewHolder> {
    private UIActivity mActivity;

    public UIAdapterDelegate(UIActivity activity) {
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
        mActivity.onItemClick(view, position, item);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.item_main_recycler_view_tv_title);
        }
    }
}