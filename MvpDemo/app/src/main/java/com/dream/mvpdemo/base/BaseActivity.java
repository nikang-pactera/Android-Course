package com.dream.mvpdemo.base;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity<P extends IBasePresenter> extends AppCompatActivity implements IBaseView {

    protected P mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutId());

        //初始化mPresenter
        initPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }

        //初始化
        initView();
    }

    /**
     * 初始化mPresenter
     */
    protected abstract void initPresenter();

    /**
     * 初始化
     */
    protected abstract void initView();

    /**
     * 获取布局id
     *
     * @return id
     */
    protected abstract int getLayoutId();

}
