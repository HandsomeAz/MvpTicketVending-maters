package com.geo.mvpframe_maters.network.api;



import com.geo.mvpframe_maters.bean.CityInfo;
import com.geo.mvpframe_maters.bean.LoginInfo;
import com.geo.mvpframe_maters.bean.OrderInfo;
import com.geo.mvpframe_maters.bean.PictureInfo;
import com.geo.mvpframe_maters.bean.ShiftInfo;
import com.geo.mvpframe_maters.bean.StationInfo;
import com.geo.mvpframe_maters.bean.UpdateInfo;
import com.geo.mvpframe_maters.network.RequestBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * @package: com.example.mymvpnetwork_master.Api
 * 创建人： created by zlj
 * 时间：2022/03/27 17
 */
public interface ServiceApi {
    @GET("/version-data/update?")
    Observable<RequestBean<String>> getUpdateInfo(@QueryMap HashMap<String, Object> map);

    @GET
    @Streaming
    Observable<ResponseBody> downloadApk(@Url String url);

    @GET("/staff-data/login")
    Observable<RequestBean<String>> UserLogin(@HeaderMap Map<String ,String> map, @Query("accountNum") String accountNum, @Query("password") String password);


    //新版本检测
    @POST("openapi-wrapper/hbc/b-device-config/version")
    Observable<RequestBean<UpdateInfo>> getUpdateData(@HeaderMap Map<String ,String> map, @Body RequestBody requestBody);

    //登录接口
    @POST("security/login")
    Observable<RequestBean<LoginInfo>> Login(@Body RequestBody requestBody);

    //获取售票城市
    @GET("openapi-wrapper/base/city/options/")
    Observable<RequestBean<List<CityInfo>>> getCityInfo(@HeaderMap Map<String ,String> map, @Query("business") String business);

    //获取上下车站点
    @POST("openapi-wrapper/hbc/station/list")
    Observable<RequestBean<List<StationInfo>>> getStationList(@HeaderMap Map<String ,String> map, @Body RequestBody requestBody);

    //获取下屏图片
    @GET("openapi-wrapper/base/b-rotation-group-manage/group/{code}")
    Observable<RequestBean<List<PictureInfo>>> getAboveImage(@HeaderMap Map<String ,String> map, @Path("code") String code);

    //获取上下车站点
    @POST("openapi-wrapper/hbc/realtime-shift/list")
    Observable<RequestBean<ShiftInfo>> getShiftList(@HeaderMap Map<String ,String> map, @Body RequestBody requestBody);

    //提交订单
    @POST("openapi-wrapper/hbc/ticket-order/equipment/createOrder")
    Observable<RequestBean<OrderInfo>> postCreateOrder(@HeaderMap Map<String ,String> map, @Body RequestBody requestBody);

    //获取下屏图片
    @POST("openapi-wrapper/hbc/ticket-order/equipment/cancelOrder/{orderId}")
    Observable<ResponseBody> postCancelOrder(@HeaderMap Map<String ,String> map, @Path("orderId") String orderId);

}

