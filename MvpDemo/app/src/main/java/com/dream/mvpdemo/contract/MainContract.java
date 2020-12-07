package com.dream.mvpdemo.contract;

import com.dream.mvpdemo.base.IBasePresenter;
import com.dream.mvpdemo.base.IBaseView;

public interface MainContract {

    interface View extends IBaseView {
        void getMview();
    }

    interface Presenter extends IBasePresenter<View> {
        void getMpresenter();

        void db();

        void requestNetwork();

        void preference();

    }
}
