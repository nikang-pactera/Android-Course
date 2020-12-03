package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.LayoutAdapterDelegate;
import com.kevin.delegationadapter.DelegationAdapter;

import java.util.ArrayList;
import java.util.List;

public class LayoutActivity extends AppCompatActivity {

    private List<String> layoutList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        DelegationAdapter delegationAdapter = new DelegationAdapter();
        // 向Adapter中注册委托Adapter
        delegationAdapter.addDelegate(new LayoutAdapterDelegate(this));

        //设置Adapter
        recyclerView.setAdapter(delegationAdapter);
        //设置增加或删除条目的动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        layoutList = new ArrayList<>();
        layoutList.add("帧 布 局（FrameLayout）");
        layoutList.add("线性布局（LinearLayout）");
        layoutList.add("绝对布局（AbsoluteLayout）");
        layoutList.add("相对布局（RelativeLayout）");
        layoutList.add("表格布局（TabLayout）");
        layoutList.add("网格布局（GridLayout）");
        layoutList.add("约束布局（ConstraintLayout）");
        // 置数据
        delegationAdapter.setDataItems(layoutList);
    }


    public void onItemClick(View v, int position, String item) {
        int index = layoutList.indexOf(item);
        switch (index) {
            case 0: // 帧布局
                startActivity(new Intent(this, FrameLayoutActivity.class));
                break;
            case 1: // 线性布局
                startActivity(new Intent(this, LinearLayoutActivity.class));
                break;
            case 2: // 绝对布局
                startActivity(new Intent(this, AbsoluteLayoutActivity.class));
                break;
            case 3: // 相对布局
                startActivity(new Intent(this, RelativeLayoutActivity.class));
                break;
            case 4: // 表格布局
                startActivity(new Intent(this, TabLayoutActivity.class));
                break;
            case 5: // 网格布局
                startActivity(new Intent(this, GridLayoutActivity.class));
                break;
            case 6: // 约束布局
                startActivity(new Intent(this, ConstraintLayoutActivity.class));
                break;
            default:
                Toast.makeText(this, "触发了点击事件", Toast.LENGTH_LONG).show();
                break;
        }
    }
}