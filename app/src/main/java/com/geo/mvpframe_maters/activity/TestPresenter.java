package com.geo.mvpframe_maters.activity;

import android.util.Log;

import com.geo.mvpframe_maters.bean.UpdateInfo;
import com.geo.mvpframe_maters.mvp.BaseMvpPresenter;
import com.geo.mvpframe_maters.network.BaseObserver;
import com.geo.mvpframe_maters.network.RequestBean;

import io.reactivex.disposables.Disposable;

public class TestPresenter extends BaseMvpPresenter<TestContract.IView,TestModel> implements TestContract.IPresenter{

    @Override
    public void getTestData() {
        getModel().getUpdateInfo(new BaseObserver<RequestBean<UpdateInfo>>() {
            @Override
            public void onDisposable(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onSuccess(RequestBean<UpdateInfo> updateInfoRequestBean) {
                Log.d("升级数据获取成功",updateInfoRequestBean.toString());
            }

            @Override
            public void onFailure(Throwable e) {
                Log.d("升级数据获取失败",e.toString());
            }
        });
//        getView().setTestData("6666");
    }
}
