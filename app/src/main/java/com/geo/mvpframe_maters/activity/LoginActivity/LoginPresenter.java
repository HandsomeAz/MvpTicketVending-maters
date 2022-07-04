package com.geo.mvpframe_maters.activity.LoginActivity;

import android.util.Log;

import com.geo.mvpframe_maters.bean.LoginInfo;
import com.geo.mvpframe_maters.mvp.BaseMvpPresenter;
import com.geo.mvpframe_maters.network.BaseObserver;
import com.geo.mvpframe_maters.network.RequestBean;
import com.geo.mvpframe_maters.utils.SharePreUtil;

import io.reactivex.disposables.Disposable;

import static com.geo.mvpframe_maters.constant.Constants.ACCOUNT_KEY;
import static com.geo.mvpframe_maters.constant.Constants.PASSWORD_KEY;

public class LoginPresenter extends BaseMvpPresenter<LoginContract.IView, LoginModel> implements LoginContract.IPresenter{


    @Override
    public void Login(String accountNum, String password) {
        getModel().Login(accountNum, password, new BaseObserver<RequestBean<LoginInfo>>() {
            @Override
            public void onDisposable(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onSuccess(RequestBean<LoginInfo> requestBean) {
                Log.d("登录成功",requestBean.toString());
                if (requestBean.getCode()==200000){

                    getView().LoginSuccess();
                    SharePreUtil.getInstance(getView().getContext()).putString("accessKey",accountNum);
                    SharePreUtil.getInstance(getView().getContext()).putString("secretAccessKey",password);
                    ACCOUNT_KEY =accountNum;
                    PASSWORD_KEY = password;
                }else {
                    getView().LoginFailure();
                }

            }

            @Override
            public void onFailure(Throwable e) {
                Log.d("登录失败",e.toString());
                getView().LoginFailure();
//                SharePreUtil.getInstance(getView().getContext()).putString("accessKey",accountNum);
//                SharePreUtil.getInstance(getView().getContext()).putString("secretAccessKey",password);
//                ACCOUNT_KEY =accountNum;
//                PASSWORD_KEY = password;
            }
        });
    }
}
