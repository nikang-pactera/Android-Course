package com.example.myapplication.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.kevin.delegationadapter.AdapterDelegate;

public class USCompanyAdapterDelegate extends AdapterDelegate<String, USCompanyAdapterDelegate.ViewHolder> {

    @Override
    public boolean isForViewType(String item, int position) {
        return item.contains("🇺🇸");
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position, final String item) {
        holder.tvName.setText(item);
        holder.tvName.setTextColor(Color.BLUE);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(android.R.id.text1);
        }
    }
}