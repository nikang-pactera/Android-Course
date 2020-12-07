package com.dream.mvpdemo.contract;

import com.dream.mvpdemo.base.IBasePresenter;
import com.dream.mvpdemo.base.IBaseView;
import com.dream.mvpdemo.model.http.loader.GankLoader;
import com.kevin.delegationadapter.DelegationAdapter;

/**
 * MainContract
 * Created by Administrator on 2018/5/7.
 */

public interface GankContract {

    interface View extends IBaseView {
        void getView();
    }

    interface Presenter extends IBasePresenter<View> {
        void getMpresenter();

        void db();

        void requestNetwork(GankLoader mGankLoader, DelegationAdapter delegationAdapter);

        void preference();

    }
}
