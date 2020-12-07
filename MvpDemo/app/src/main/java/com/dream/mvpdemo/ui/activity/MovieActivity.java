package com.dream.mvpdemo.ui.activity;

import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dream.mvpdemo.R;
import com.dream.mvpdemo.base.BaseActivity;
import com.dream.mvpdemo.contract.MovieContract;
import com.dream.mvpdemo.model.http.Fault;
import com.dream.mvpdemo.model.http.loader.MovieLoader;
import com.dream.mvpdemo.presenter.MoviePresenter;
import com.dream.mvpdemo.ui.adapter.MovieAdapterDelegate;
import com.kevin.delegationadapter.DelegationAdapter;

public class MovieActivity extends BaseActivity<MoviePresenter> implements MovieContract.View {
    private MovieLoader mMovieLoader;
    private DelegationAdapter delegationAdapter;

    @Override
    protected void initPresenter() {
        mPresenter = new MoviePresenter();
    }

    @Override
    protected void initView() {
        mMovieLoader = new MovieLoader();
        mPresenter.getMpresenter();
        mPresenter.db();
        mPresenter.requestNetwork();
        mPresenter.preference();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_movie;
    }

    @Override
    public void getView() {
        Toolbar toolbar = findViewById(R.id.main_toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());
        toolbar.setTitle(R.string.movie_list);

        RecyclerView mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.addItemDecoration(new MovieDecoration());
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        delegationAdapter = new DelegationAdapter();
        // 向Adapter中注册委托Adapter
        delegationAdapter.addDelegate(new MovieAdapterDelegate());
        //设置Adapter
        mRecyclerView.setAdapter(delegationAdapter);
        //设置增加或删除条目的动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        getMovieList();
    }

    private void getMovieList() {
        mMovieLoader.getMovie(0, 20)
                .subscribe(movies -> {
                    delegationAdapter.setDataItems(movies);
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

    }

    public static class MovieDecoration extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            outRect.set(0, 0, 0, 20);
        }
    }
}