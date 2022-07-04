package com.geo.mvpframe_maters.activity.LoginActivity;

import com.geo.mvpframe_maters.activity.SplashActivity.SplashContract;
import com.geo.mvpframe_maters.bean.LoginInfo;
import com.geo.mvpframe_maters.mvp.BaseMvpModel;
import com.geo.mvpframe_maters.network.RequestBean;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observer;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class LoginModel extends BaseMvpModel implements LoginContract.IModel {

    @Override
    public void Login(String accountNum, String password, Observer<RequestBean<LoginInfo>> observer){
        JSONObject obj = new JSONObject();
        try {
            obj.put("username",accountNum);
            obj.put("password",password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody formBody=RequestBody.create(JSON,obj.toString());
        setSubscribe(service.Login(formBody),observer);
    }
}
