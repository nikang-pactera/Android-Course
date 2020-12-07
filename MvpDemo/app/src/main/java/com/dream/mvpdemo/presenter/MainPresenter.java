package com.dream.mvpdemo.presenter;

import android.util.Log;

import com.dream.mvpdemo.base.BasePresenter;
import com.dream.mvpdemo.contract.MainContract;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    @Override
    public void getMpresenter() {
        Log.d("print", "我是P层的引用");
        mView.getMview();
    }

    @Override
    public void db() {
        mDataManager.testDb();
        mDataManager.dbTest();
    }

    @Override
    public void requestNetwork() {

    }

    @Override
    public void preference() {
        mDataManager.testPreference();
    }
}
