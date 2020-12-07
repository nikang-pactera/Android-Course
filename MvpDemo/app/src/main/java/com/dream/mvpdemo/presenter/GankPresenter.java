package com.dream.mvpdemo.presenter;

import android.util.Log;

import com.dream.mvpdemo.base.BasePresenter;
import com.dream.mvpdemo.contract.GankContract;
import com.dream.mvpdemo.model.http.net.Fault;
import com.dream.mvpdemo.model.http.loader.GankLoader;
import com.kevin.delegationadapter.DelegationAdapter;

import rx.Subscription;

public class GankPresenter extends BasePresenter<GankContract.View> implements GankContract.Presenter {

    @Override
    public void getMpresenter() {
        mView.getView();
    }

    @Override
    public void db() {
    }

    @Override
    public void requestNetwork(GankLoader mGankLoader, DelegationAdapter delegationAdapter) {
        Subscription subscription = mGankLoader.getGankList().subscribe(gankEntries -> {
            Log.i("FK", "gank size:" + gankEntries.size());
            delegationAdapter.setDataItems(gankEntries);
            delegationAdapter.notifyDataSetChanged();
        }, throwable -> {
            Log.e("TAG", "error message:" + throwable.getMessage());
            if (throwable instanceof Fault) {
                Fault fault = (Fault) throwable;
                if (fault.getErrorCode() == 404) {
                    //错误处理
                } else if (fault.getErrorCode() == 500) {
                    //错误处理
                } else if (fault.getErrorCode() == 501) {
                    //错误处理
                }
            }
        });

        addSubscription(subscription);
    }

    @Override
    public void preference() {

    }
}
