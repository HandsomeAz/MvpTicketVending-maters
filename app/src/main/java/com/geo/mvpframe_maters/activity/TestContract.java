package com.geo.mvpframe_maters.activity;

import com.geo.mvpframe_maters.bean.UpdateInfo;
import com.geo.mvpframe_maters.mvp.BaseMvpModel;
import com.geo.mvpframe_maters.mvp.IBaseView;
import com.geo.mvpframe_maters.network.RequestBean;
import com.geo.mvpframe_maters.network.UserNetWork;

import io.reactivex.Observer;

public interface TestContract {
    interface IModel {



        void getUpdateInfo(Observer<RequestBean<UpdateInfo>> observer);
    }

    interface IView extends IBaseView{

        void setTestData(String s);
    }

    interface IPresenter {

        void getTestData();
    }


}
