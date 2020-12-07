package com.dream.mvpdemo.presenter;

import android.util.Log;

import com.dream.mvpdemo.base.BasePresenter;
import com.dream.mvpdemo.contract.BluetoothContract;

public class BluetoothPresenter extends BasePresenter<BluetoothContract.View> implements BluetoothContract.Presenter {

    @Override
    public void getMpresenter() {
        Log.d("print", "我是P层的引用");
        mView.getView();
    }

    @Override
    public void db() {

    }

    @Override
    public void requestNetwork() {
    }

    @Override
    public void preference() {

    }
}
