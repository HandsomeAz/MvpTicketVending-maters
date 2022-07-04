package com.geo.mvpframe_maters.activity.LoginActivity;

import com.geo.mvpframe_maters.bean.LoginInfo;
import com.geo.mvpframe_maters.mvp.IBaseView;
import com.geo.mvpframe_maters.network.RequestBean;

import io.reactivex.Observer;

public interface LoginContract {
    interface IModel {
        void Login(String accountNum, String password, Observer<RequestBean<LoginInfo>> observer);
    }

    interface IView extends IBaseView{


        void LoginSuccess();

        void LoginFailure();
    }

    interface IPresenter {


        void Login(String accountNum, String password);
    }


}
