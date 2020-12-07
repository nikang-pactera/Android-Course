package com.dream.mvpdemo.contract;

import com.dream.mvpdemo.base.IBasePresenter;
import com.dream.mvpdemo.base.IBaseView;

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

        void requestNetwork();

        void preference();

    }
}
