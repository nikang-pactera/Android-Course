package com.dream.mvpdemo.base;

import android.util.Log;

import com.dream.mvpdemo.model.DataManager;
import com.dream.mvpdemo.model.db.AppDbHelper;
import com.dream.mvpdemo.model.db.DbHelper;
import com.dream.mvpdemo.model.http.ApiHelper;
import com.dream.mvpdemo.model.http.AppApiHelper;
import com.dream.mvpdemo.model.preference.AppPreferenceHelper;
import com.dream.mvpdemo.model.preference.PreferenceHelper;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * BasePresenter
 * Created by Administrator on 2018/5/7.
 */

public abstract class BasePresenter<V extends IBaseView> implements IBasePresenter<V> {
    private static final String TAG = "BasePresenter";

    private CompositeSubscription sCompositeSubscription;
    protected DataManager mDataManager;
    protected V mView;

    public BasePresenter() {
        AppDbHelper appDbHelper = new DbHelper();
        AppPreferenceHelper appPreferenceHelper = new PreferenceHelper();
        AppApiHelper appApiHelper = new ApiHelper();
        mDataManager = new DataManager(appDbHelper, appApiHelper, appPreferenceHelper);

        if (sCompositeSubscription == null || sCompositeSubscription.isUnsubscribed()) {
            sCompositeSubscription = new CompositeSubscription();
        }
    }

    @Override
    public void attachView(V view) {
        this.mView = view;
    }

    public void addSubscription(Subscription subscription) {
        Log.d(TAG, "add subscription");
        sCompositeSubscription.add(subscription);
    }

    @Override
    public Subscription getSubscription() {
        if (sCompositeSubscription != null) {
            return sCompositeSubscription;
        } else {
            return null;
        }
    }
}
