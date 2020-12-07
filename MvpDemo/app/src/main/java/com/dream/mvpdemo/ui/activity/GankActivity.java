package com.dream.mvpdemo.ui.activity;

import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.dream.mvpdemo.R;
import com.dream.mvpdemo.base.BaseActivity;
import com.dream.mvpdemo.contract.GankContract;
import com.dream.mvpdemo.model.http.Fault;
import com.dream.mvpdemo.model.http.loader.GankLoader;
import com.dream.mvpdemo.presenter.GankPresenter;
import com.dream.mvpdemo.ui.adapter.GankAdapterDelegate;
import com.kevin.delegationadapter.DelegationAdapter;

import rx.Subscription;

public class GankActivity extends BaseActivity<GankPresenter> implements GankContract.View {

    private GankLoader mGankLoader;
    private DelegationAdapter delegationAdapter;

    @Override
    protected void initPresenter() {
        mPresenter = new GankPresenter();
    }

    @Override
    protected void initView() {
        mGankLoader = new GankLoader();
        mPresenter.getMpresenter();
        mPresenter.db();
        mPresenter.requestNetwork();
        mPresenter.preference();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_gank;
    }

    @Override
    public void getView() {
        Toolbar toolbar = findViewById(R.id.tool_bar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setNavigationOnClickListener(v -> finish());
        toolbar.setTitle(R.string.award_list);


        RecyclerView mRecyclerView = findViewById(R.id.gank_recycler_view);
        mRecyclerView.addItemDecoration(new MyItemDecoration());
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);

        delegationAdapter = new DelegationAdapter();
        // 向Adapter中注册委托Adapter
        delegationAdapter.addDelegate(new GankAdapterDelegate());
        //设置Adapter
        mRecyclerView.setAdapter(delegationAdapter);
        //设置增加或删除条目的动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        getGankList();
    }

    private void getGankList() {
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

    public static class MyItemDecoration extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            outRect.set(0, 0, 20, 20);
        }
    }
}