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
import com.example.myapplication.adapter.UIAdapterDelegate;
import com.kevin.delegationadapter.DelegationAdapter;

import java.util.ArrayList;
import java.util.List;

public class UIActivity extends AppCompatActivity {

    private List<String> layoutList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        DelegationAdapter delegationAdapter = new DelegationAdapter();
        // 向Adapter中注册委托Adapter
        delegationAdapter.addDelegate(new UIAdapterDelegate(this));

        //设置Adapter
        recyclerView.setAdapter(delegationAdapter);
        //设置增加或删除条目的动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        layoutList = new ArrayList<>();
        layoutList.add("文本视图（TextView）");
        layoutList.add("按    钮（Button）");
        layoutList.add("输 入 框（EditText）");
        layoutList.add("警告弹窗（AlterDialog）");
        layoutList.add("进 度 条（ProgressBar）");
        layoutList.add("列    表（RecyclerView）");
        layoutList.add("视图翻页（ViewPager）");
        // 置数据
        delegationAdapter.setDataItems(layoutList);
    }


    public void onItemClick(View v, int position, String item) {
        int index = layoutList.indexOf(item);
        switch (index) {
            case 0: // TextView
                startActivity(new Intent(this, TextViewActivity.class));
                break;
            case 1: // Button
                startActivity(new Intent(this, ButtonActivity.class));
                break;
            case 2: // EditText
                startActivity(new Intent(this, EditTextActivity.class));
                break;
            case 3: // AlterDialog
                startActivity(new Intent(this, AlterDialogActivity.class));
                break;
            case 4: // ProgressBar
                startActivity(new Intent(this, ProgressBarActivity.class));
                break;
            case 5: // RecyclerView
                startActivity(new Intent(this, RecyclerViewActivity.class));
                break;
            case 6: // ViewPager
                startActivity(new Intent(this, ViewPagerActivity.class));
                break;
            default:
                Toast.makeText(this, "触发了点击事件", Toast.LENGTH_LONG).show();
                break;
        }
    }
}