package com.geo.mvpframe_maters.activity.TicketPurchaseActivity;


import com.geo.mvpframe_maters.bean.OrderInfo;
import com.geo.mvpframe_maters.bean.PictureInfo;
import com.geo.mvpframe_maters.bean.RecordBean;
import com.geo.mvpframe_maters.bean.ShiftInfo;
import com.geo.mvpframe_maters.constant.Constants;
import com.geo.mvpframe_maters.mvp.BaseMvpModel;
import com.geo.mvpframe_maters.network.RequestBean;
import com.geo.mvpframe_maters.utils.MD5Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

import static com.geo.mvpframe_maters.constant.Constants.ACCOUNT_KEY;
import static com.geo.mvpframe_maters.constant.Constants.PASSWORD_KEY;


public class TicketModel extends BaseMvpModel implements TicketPurchaseContract.IModel {


    @Override
    public void createOrder(RecordBean recordBean, JSONArray jsonArray, int insurance,Observer<RequestBean<OrderInfo>> observer) {
        String timestamp =String.valueOf(System.currentTimeMillis()/1000);
        String nonce=String.valueOf(getRand());
        JSONObject obj = new JSONObject();
        try {
            obj.put("getonStationCode",recordBean.getGetonStationCode());
            obj.put("getonTime",recordBean.getGetonTime());
            obj.put("insurance",insurance);
            obj.put("operCode","L001");
            obj.put("passengerList",jsonArray);
            obj.put("shiftCode",recordBean.getCode());
            obj.put("takeoffStationCode",recordBean.getTakeoffStationCode());
            obj.put("ticketDate",recordBean.getTicketDate());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String str = ACCOUNT_KEY + timestamp + nonce+ obj + PASSWORD_KEY;
        String sign  = MD5Util.encode(str);
        Map<String, String> map = getHeader(timestamp, nonce, sign);
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody formBody=RequestBody.create(JSON,obj.toString());
        setSubscribe(service.postCreateOrder(map,formBody),observer);
    }

    @Override
    public void cancelOrder(String orderId, Observer<ResponseBody> observer) {
        String timestamp =String.valueOf(System.currentTimeMillis()/1000);
        String nonce=String.valueOf(getRand());
        String str = ACCOUNT_KEY + timestamp + nonce + PASSWORD_KEY;
        String sign  = MD5Util.encode(str);
        Map<String, String> map = getHeader(timestamp, nonce,sign);
        setSubscribe(service.postCancelOrder(map,orderId),observer);
    }


}
