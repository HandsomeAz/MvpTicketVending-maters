package com.geo.mvpframe_maters.network;




import com.geo.mvpframe_maters.bean.UpdateInfo;
import com.geo.mvpframe_maters.network.api.ServiceApi;
import com.geo.mvpframe_maters.utils.MD5Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import io.reactivex.Observer;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * @package: com.example.mymvpnetwork_master.baseFile
 * 创建人： created by zlj
 * 时间：2022/03/27 18
 */
public class UserNetWork extends BaseNetwork {
    protected final ServiceApi service = getRetrofit().create(ServiceApi.class);


    public void  toGetUpdateInfo(Observer<RequestBean<String>> observer) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("versionCode",33);
        map.put("type",3);
        setSubscribe(service.getUpdateInfo(map), observer);
    }


    public void toDownloadApk(Observer<ResponseBody> observer, String url) {
        setSubscribe(service.downloadApk(url), observer);
    }

    public void toLogin(String account,String paw,Observer<RequestBean<String>> observer){
//        setSubscribe(service.UserLogin(account,paw),observer);
    }






}
