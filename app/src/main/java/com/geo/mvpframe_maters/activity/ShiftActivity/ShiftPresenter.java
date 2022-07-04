package com.geo.mvpframe_maters.activity.ShiftActivity;


import android.util.Log;

import com.geo.mvpframe_maters.bean.RecordBean;
import com.geo.mvpframe_maters.bean.ShiftInfo;
import com.geo.mvpframe_maters.bean.StationInfo;
import com.geo.mvpframe_maters.mvp.BaseMvpPresenter;
import com.geo.mvpframe_maters.network.BaseObserver;
import com.geo.mvpframe_maters.network.RequestBean;

import java.util.List;

import io.reactivex.disposables.Disposable;


public class ShiftPresenter extends BaseMvpPresenter<ShiftContract.IView, ShiftModel> implements ShiftContract.IPresenter{


    @Override
    public void getShiftInfo(String ticketDate, int page, String offStationCode) {
        getView().showLoading();
        getModel().getShiftInfo(ticketDate, page, offStationCode, new BaseObserver<RequestBean<ShiftInfo>>() {
            @Override
            public void onDisposable(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onSuccess(RequestBean<ShiftInfo> shiftInfoRequestBean) {
                Log.d("班次获取成功",shiftInfoRequestBean.toString());
                List<RecordBean> records = shiftInfoRequestBean.getData().getRecords();
                if (records !=null && records.size()>0){
                    getView().setRecordBean(records);
                }else {
                    getView().setRecordBean(null);

                }
            }

            @Override
            public void onFailure(Throwable e) {
                Log.d("班次获取失败",e.toString());
                getView().RecordFailure(1);

            }
        });
    }
}
