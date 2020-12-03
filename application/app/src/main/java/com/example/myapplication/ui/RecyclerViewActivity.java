package com.example.myapplication.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.CNCompanyAdapterDelegate;
import com.example.myapplication.adapter.USCompanyAdapterDelegate;
import com.kevin.delegationadapter.DelegationAdapter;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        DelegationAdapter delegationAdapter = new DelegationAdapter();
        // 向Adapter中注册委托Adapter
//        delegationAdapter.addDelegate(new CompanyAdapterDelegate());
        delegationAdapter.addDelegate(new CNCompanyAdapterDelegate());
        delegationAdapter.addDelegate(new USCompanyAdapterDelegate());

        //设置Adapter
        recyclerView.setAdapter(delegationAdapter);
        //设置增加或删除条目的动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        List<String> companies = new ArrayList<>();
        companies.add("🇨🇳 百度");
        companies.add("🇨🇳 阿里");
        companies.add("🇨🇳 腾讯");
        companies.add("🇺🇸 Google");
        companies.add("🇺🇸 Facebook");
        companies.add("🇺🇸 Microsoft");

        // 置数据
        delegationAdapter.setDataItems(companies);
    }
}