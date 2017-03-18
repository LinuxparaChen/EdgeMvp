package com.tcl.smarthome.mvpapp.api;


import com.tcl.smarthome.mvpapp.model.bean.PicClassifyBean;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.POST;

/**
 * Description: 天狗网络图片接口
 * Auther: 陈占洋
 * Data: 2017/3/17.
 */

public interface TianGouPicApi {
    /**
     * 获取图片类别
     * @return
     */
    @POST("tnfs/api/classify")
    Observable<PicClassifyBean> getClassify();

    /**
     * 获取图片列表
     * @param pageNum
     * @param itemNum
     * @param id
     * @return
     */
    @POST("tnfs/api/list")
    Observable<?> getPicList(@Field("page") int pageNum, @Field("rows") int itemNum, @Field("id") int id);
}
