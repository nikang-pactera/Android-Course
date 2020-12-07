package com.dream.mvpdemo.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dream.mvpdemo.ui.activity.BluetoothActivity;
import com.kevin.delegationadapter.extras.ClickableAdapterDelegate;

public class BluetoothAdapterDelegate extends ClickableAdapterDelegate<String, BluetoothAdapterDelegate.ViewHolder> {
    private BluetoothActivity mActivity;

    public BluetoothAdapterDelegate(BluetoothActivity activity) {
        this.mActivity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position, final String str) {
        holder.time.setText(str);
    }

    @Override
    public void onItemClick(@NonNull View view, String item, int position) {
        mActivity.onItemClick(position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView time;

        public ViewHolder(View itemView) {
            super(itemView);
            time = itemView.findViewById(android.R.id.text1);
        }
    }
}