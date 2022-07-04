package com.geo.mvpframe_maters.activity.ShiftActivity;


import com.geo.mvpframe_maters.bean.CityInfo;
import com.geo.mvpframe_maters.bean.ShiftInfo;
import com.geo.mvpframe_maters.bean.StationInfo;
import com.geo.mvpframe_maters.constant.Constants;
import com.geo.mvpframe_maters.mvp.BaseMvpModel;
import com.geo.mvpframe_maters.network.RequestBean;

import com.geo.mvpframe_maters.utils.MD5Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import static com.geo.mvpframe_maters.constant.Constants.ACCOUNT_KEY;
import static com.geo.mvpframe_maters.constant.Constants.PASSWORD_KEY;


public class ShiftModel extends BaseMvpModel implements ShiftContract.IModel {


    @Override
    public void getShiftInfo(String ticketDate,int page,String offStationCode, Observer<RequestBean<ShiftInfo>> observer) {
        String timestamp =String.valueOf(System.currentTimeMillis()/1000);
        String nonce=String.valueOf(getRand());
        JSONObject obj = new JSONObject();
        try {
            obj.put("date",ticketDate);
            obj.put("current",page);
            obj.put("getonStationCode", Constants.departStationCode);
            obj.put("takeoffStationCode",offStationCode);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String str = ACCOUNT_KEY + timestamp + nonce+ obj + PASSWORD_KEY;
        String sign  = MD5Util.encode(str);
        Map<String, String> map = getHeader(timestamp, nonce, sign);
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody formBody=RequestBody.create(JSON,obj.toString());
        setSubscribe(service.getShiftList(map,formBody),observer);
    }

}
