package com.dream.mvpdemo.base;

import rx.Subscription;

public interface IBasePresenter<V extends IBaseView> {

    void attachView(V view);

    Subscription getSubscription();
}
