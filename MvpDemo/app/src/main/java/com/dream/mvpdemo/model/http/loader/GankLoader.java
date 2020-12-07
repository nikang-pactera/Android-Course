package com.dream.mvpdemo.model.http.loader;

import com.dream.mvpdemo.model.bean.GankEntry;
import com.dream.mvpdemo.model.bean.GankResp;
import com.dream.mvpdemo.model.http.ApiConfig;
import com.dream.mvpdemo.model.http.ObjectLoader;
import com.dream.mvpdemo.model.http.RetrofitServiceManager;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

public class GankLoader extends ObjectLoader {

    private GankService mGankService;

    public GankLoader() {
        mGankService = RetrofitServiceManager.getInstance().create(GankService.class);
    }

    /**
     * 获取干货列表
     *
     * @return return
     */
    public Observable<List<GankEntry>> getGankList() {
        return observe(mGankService.getGank(ApiConfig.BASE_URL + ApiConfig.GANK_URL)).map(gankResp -> gankResp.results);
    }

    public interface GankService {
        /**
         * @param url 请求地址
         * @return 返回值
         */
        @GET
        Observable<GankResp> getGank(@Url String url);
    }
}
