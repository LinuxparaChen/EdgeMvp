package com.chenzhanyang.mvpsample;

import android.app.Application;

import com.chenzhanyang.mvpsample.api.TianGouApi;
import com.chenzhanyang.mvpsample.api.ApiUrl;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Description:
 * Auther: chen_zhanyang.
 * Emaol: zhanyang.chen@gmail.com
 * Data: 2017/4/1.
 */

public class MainApp extends Application {

    private static Retrofit sRetrofit;

    @Override
    public void onCreate() {
        super.onCreate();
        sRetrofit = buildRetrofit();
    }

    /**
     * 构建Retrofit
     */
    private Retrofit buildRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrl.TIAN_GOU_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }

    /**
     * 获取Retrofit
     * @return
     */
    public static Retrofit getRetrofit(){
        return sRetrofit;
    }

    /**
     * 获取天狗api接口服务
     * @return
     */
    public static TianGouApi getTianGouApi(){
        return sRetrofit.create(TianGouApi.class);
    }
}
