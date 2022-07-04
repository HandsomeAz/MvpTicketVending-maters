package com.geo.mvpframe_maters.activity.StationActivity;


import android.util.Log;

import com.geo.mvpframe_maters.bean.CityInfo;
import com.geo.mvpframe_maters.bean.DivideStation;
import com.geo.mvpframe_maters.bean.StationInfo;
import com.geo.mvpframe_maters.mvp.BaseMvpPresenter;
import com.geo.mvpframe_maters.network.BaseObserver;
import com.geo.mvpframe_maters.network.RequestBean;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class StationPresenter extends BaseMvpPresenter<StationContract.IView, StationModel> implements StationContract.IPresenter{


    @Override
    public void getCityInfo() {
        getModel().getCity("1", new BaseObserver<RequestBean<List<CityInfo>>>() {
            @Override
            public void onDisposable(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onSuccess(RequestBean<List<CityInfo>> cityInfoRequestBean) {
                Log.d("获取城市成功",cityInfoRequestBean.toString());
                if (cityInfoRequestBean.getCode() !=200000){
                    getView().getCityFailure(cityInfoRequestBean.getMessage());
                    return;
                }
                getView().getCitySuccess(cityInfoRequestBean.getData());
            }

            @Override
            public void onFailure(Throwable e) {
                getView().hideLoading();
                Log.d("获取城市失败",e.toString());
                getView().getCityFailure(e.toString());
            }
        });
    }

    @Override
    public void getStationInfo(int cityId ,int type) {
        getModel().getStationList(cityId, type, new BaseObserver<RequestBean<List<StationInfo>>>() {
            @Override
            public void onDisposable(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onSuccess(RequestBean<List<StationInfo>> listRequestBean) {
                if (listRequestBean ==null){
                    getView().getStationFailure("获取站点失败");
                    return;
                }
                if (listRequestBean.getCode() !=200000){
                    getView().getStationFailure(listRequestBean.getMessage());
                    return;
                }
                getView().getStationSuccess(listRequestBean.getData());
            }

            @Override
            public void onFailure(Throwable e) {
                getView().getStationFailure(e.toString());
            }
        });
    }

    @Override
    public void divideStation(List<StationInfo> data) {
        List<DivideStation> divideStationList = getModel().divideStation(data);
        getView().getDivideStations(divideStationList);
        Log.d("划分后的站点信息",divideStationList.toString());
    }
}
