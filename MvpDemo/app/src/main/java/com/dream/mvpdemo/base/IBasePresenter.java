package com.dream.mvpdemo.base;

import rx.Subscription;

/**
 * Created by Administrator on 2018/5/7.
 */

public interface IBasePresenter<V extends IBaseView> {

    void attachView(V view);

    Subscription getSubscription();
}
