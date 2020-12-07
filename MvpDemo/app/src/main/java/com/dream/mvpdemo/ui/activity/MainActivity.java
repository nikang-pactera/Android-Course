package com.dream.mvpdemo.ui.activity;

import android.content.Intent;
import android.util.Log;

import com.dream.mvpdemo.R;
import com.dream.mvpdemo.base.BaseActivity;
import com.dream.mvpdemo.contract.MainContract;
import com.dream.mvpdemo.presenter.MainPresenter;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    @Override
    protected void initPresenter() {
        mPresenter = new MainPresenter();
    }

    @Override
    protected void initView() {
        mPresenter.getMpresenter();
        mPresenter.db();
        mPresenter.requestNetwork();
        mPresenter.preference();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void getMview() {
        Log.d("print", "我是V层的引用");

        findViewById(R.id.award_btn).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, GankActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.bluetooth_btn).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, BluetoothActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.camera_btn).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CameraActivity.class);
            startActivity(intent);
        });
    }

}
