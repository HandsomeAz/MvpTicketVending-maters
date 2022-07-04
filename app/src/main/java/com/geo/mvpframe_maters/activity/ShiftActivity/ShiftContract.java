package com.geo.mvpframe_maters.activity.ShiftActivity;

import com.geo.mvpframe_maters.bean.RecordBean;
import com.geo.mvpframe_maters.bean.ShiftInfo;
import com.geo.mvpframe_maters.bean.StationInfo;
import com.geo.mvpframe_maters.bean.UpdateInfo;
import com.geo.mvpframe_maters.mvp.IBaseView;
import com.geo.mvpframe_maters.network.RequestBean;

import java.util.List;

import io.reactivex.Observer;

public interface ShiftContract {
    interface IModel {
        void getShiftInfo(String ticketDate,int page,String offStationCode, Observer<RequestBean<ShiftInfo>> observer);

    }

    interface IView extends IBaseView {


        void setRecordBean(List<RecordBean> recordBeanList);

        void RecordFailure(int i);
    }

    interface IPresenter {


        void getShiftInfo(String ticketDate,int page,String offStationCode);
    }
}
