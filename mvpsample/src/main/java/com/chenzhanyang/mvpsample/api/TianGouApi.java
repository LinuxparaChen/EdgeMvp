package com.chenzhanyang.mvpsample.api;

import com.chenzhanyang.mvpsample.model.bean.PicClassifyBean;
import com.chenzhanyang.mvpsample.model.bean.PicListBean;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Description:
 * Auther: chen_zhanyang.
 * Emaol: zhanyang.chen@gmail.com
 * Data: 2017/4/1.
 */

public interface TianGouApi {
    /**
     * 获取图片类别
     *
     * @return
     */
    @POST("tnfs/api/classify")
    Observable<PicClassifyBean> getRxPicClassify();

    @POST("tnfs/api/classify")
    Call<PicClassifyBean> getPicClassify();

    /**
     * 获取图片列表
     * @param page 当前页
     * @param rows 每页返回的条数
     * @param id 分类id 不传返回全部类型
     * @return
     */
    @FormUrlEncoded
    @POST("tnfs/api/list")
    Observable<PicListBean> getRxPicList(@Field("page") int page, @Field("rows") int rows, @Field("id") int id);
}
