package com.geo.mvpframe_maters.activity.TicketPurchaseActivity;

import com.geo.mvpframe_maters.bean.OrderInfo;
import com.geo.mvpframe_maters.bean.PassengerInfo;
import com.geo.mvpframe_maters.bean.RecordBean;
import com.geo.mvpframe_maters.bean.ShiftInfo;
import com.geo.mvpframe_maters.mvp.IBaseView;
import com.geo.mvpframe_maters.network.RequestBean;

import org.json.JSONArray;

import java.util.List;

import io.reactivex.Observer;
import okhttp3.ResponseBody;


public interface TicketPurchaseContract {
    interface IModel {

        void createOrder(RecordBean recordBean, JSONArray jsonArray, int insurance, Observer<RequestBean<OrderInfo>> observer);
        void cancelOrder(String orderId, Observer<ResponseBody> observer);
    }

    interface IView extends IBaseView {


        void createSuccess(String orderId);

        void createFailure(String message);
    }

    interface IPresenter {


        void createOrder(RecordBean recordBean, List<PassengerInfo> passengerInfoList);

        void cancelOrder(String mOrderId);
    }
}
