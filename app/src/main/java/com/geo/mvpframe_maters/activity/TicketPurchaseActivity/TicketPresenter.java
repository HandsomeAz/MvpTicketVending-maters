package com.geo.mvpframe_maters.activity.TicketPurchaseActivity;


import android.util.Log;

import com.geo.mvpframe_maters.bean.OrderInfo;
import com.geo.mvpframe_maters.bean.PassengerInfo;
import com.geo.mvpframe_maters.bean.RecordBean;
import com.geo.mvpframe_maters.mvp.BaseMvpPresenter;
import com.geo.mvpframe_maters.network.BaseObserver;
import com.geo.mvpframe_maters.network.RequestBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;


public class TicketPresenter extends BaseMvpPresenter<TicketPurchaseContract.IView, TicketModel> implements TicketPurchaseContract.IPresenter{
    private String TAG = "TicketPresenter_log";

    @Override
    public void createOrder(RecordBean recordBean, List<PassengerInfo> passengerInfoList) {
        JSONArray jsonArray =new JSONArray();
        JSONObject tmpObj = null;
        for (int i=0;i<passengerInfoList.size();i++){
            tmpObj = new JSONObject();
            try {
                tmpObj.put("address" , passengerInfoList.get(i).getAddress());
                tmpObj.put("idCard", passengerInfoList.get(i).getIdCard());
                tmpObj.put("name", passengerInfoList.get(i).getName());
                tmpObj.put("phone", passengerInfoList.get(i).getPhone());
                tmpObj.put("saleObjId", passengerInfoList.get(i).getSaleObjId());
                jsonArray.put(tmpObj);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        getModel().createOrder(recordBean, jsonArray, 0, new BaseObserver<RequestBean<OrderInfo>>() {
            @Override
            public void onDisposable(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onSuccess(RequestBean<OrderInfo> orderInfoRequestBean) {
                if (orderInfoRequestBean ==null){
                    getView().createFailure("订单出错");
                    return;
                }
                Log.d(TAG,"取消订单成功"+orderInfoRequestBean.toString());
                if (orderInfoRequestBean.getCode()==200000){
                    if (orderInfoRequestBean.getMessage().equals("SUCCESS")) {
                        getView().createSuccess(orderInfoRequestBean.getData().getOrderId());
                    }
                }else {
                    getView().createFailure(orderInfoRequestBean.getMessage());
                }


            }

            @Override
            public void onFailure(Throwable e) {
                getView().createFailure(e.toString());
            }
        });
    }

    @Override
    public void cancelOrder(String mOrderId) {
        getModel().cancelOrder(mOrderId, new BaseObserver<ResponseBody>() {
            @Override
            public void onDisposable(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onSuccess(ResponseBody responseBody) {
                try {
                    String s =responseBody.string();
                    Log.d(TAG,"取消订单成功"+s);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Throwable e) {
                Log.d(TAG,"取消订单失败"+e.toString());
            }
        });
    }
}
