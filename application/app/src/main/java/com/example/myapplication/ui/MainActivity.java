package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.MainRecyclerViewAdapterDelegate;
import com.kevin.delegationadapter.DelegationAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> layoutList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        DelegationAdapter delegationAdapter = new DelegationAdapter();
        // 向Adapter中注册委托Adapter
        delegationAdapter.addDelegate(new MainRecyclerViewAdapterDelegate(this));

        //设置Adapter
        recyclerView.setAdapter(delegationAdapter);
        //设置增加或删除条目的动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        layoutList = new ArrayList<>();
        layoutList.add("基础布局");
        layoutList.add("常用控件");
        layoutList.add("Intent相关");
        delegationAdapter.setDataItems(layoutList);

    }

    public void onItemClick(View v, int position, String item) {
        int index = layoutList.indexOf(item);
        switch (index) {
            case 0:
                startActivity(new Intent(this, LayoutActivity.class));
                break;
            case 1:
                startActivity(new Intent(this, UIActivity.class));
                break;
            case 2:
                startActivity(new Intent(this, IntentActivity.class));
                break;
            default:
                Toast.makeText(MainActivity.this, "点击事件，点击了" + position, Toast.LENGTH_LONG).show();
                break;
        }
    }
}