package com.geo.mvpframe_maters.activity.StationActivity;

import com.geo.mvpframe_maters.bean.CityInfo;
import com.geo.mvpframe_maters.bean.DivideStation;
import com.geo.mvpframe_maters.bean.StationInfo;
import com.geo.mvpframe_maters.mvp.IBaseView;
import com.geo.mvpframe_maters.network.RequestBean;

import java.util.List;

import io.reactivex.Observer;

public interface StationContract {
    interface IModel {


        void getCity(String business, Observer<RequestBean<List<CityInfo>>> observer);
        void getStationList(int cityId, int type,Observer<RequestBean<List<StationInfo>>> observer);

        List<DivideStation> divideStation(List<StationInfo> data);
    }


    interface IView extends IBaseView{


        void getCityFailure(String message);

        void getCitySuccess(List<CityInfo> cityInfoList);

        void getStationFailure(String message);

        void getStationSuccess(List<StationInfo> data);

        void getDivideStations(List<DivideStation> divideStationList);
    }

    interface IPresenter {


        void getCityInfo();

        void getStationInfo(int cityId,int type);

        void divideStation(List<StationInfo> data);
    }


}
