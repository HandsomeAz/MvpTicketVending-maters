package com.geo.mvpframe_maters.activity;

import android.util.Log;

import com.geo.mvpframe_maters.bean.UpdateInfo;
import com.geo.mvpframe_maters.mvp.BaseMvpModel;
import com.geo.mvpframe_maters.network.BaseObserver;
import com.geo.mvpframe_maters.network.RequestBean;
import com.geo.mvpframe_maters.network.UserNetWork;
import com.geo.mvpframe_maters.utils.MD5Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import static com.geo.mvpframe_maters.constant.Constants.ACCOUNT_KEY;
import static com.geo.mvpframe_maters.constant.Constants.PASSWORD_KEY;

public class TestModel extends BaseMvpModel implements TestContract.IModel {
    @Override
    public void getUpdateInfo(Observer<RequestBean<UpdateInfo>> observer){
        String timestamp =String.valueOf(System.currentTimeMillis()/1000);
        String nonce=String.valueOf(getRand());
        JSONObject obj = new JSONObject();
        try {
            obj.put("device",4);
            obj.put("deviceCode",ACCOUNT_KEY);
            obj.put("version","97");
            obj.put("mobileType","Android");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String str = ACCOUNT_KEY + timestamp + nonce+ obj + PASSWORD_KEY;
        String sign  = MD5Util.encode(str);
        Map<String, String> map = getHeader(timestamp, nonce, sign);
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody formBody=RequestBody.create(JSON,obj.toString());
        setSubscribe(service.getUpdateData(map,formBody),observer);
    }



}
