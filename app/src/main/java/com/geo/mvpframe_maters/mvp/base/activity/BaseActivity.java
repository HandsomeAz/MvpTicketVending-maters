package com.geo.mvpframe_maters.mvp.base.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.geo.mvpframe_maters.mvp.IBaseView;
import com.geo.mvpframe_maters.mvp.inject.ViewInject;
import com.geo.mvpframe_maters.utils.NetWatchdogUtils;
import com.geo.mvpframe_maters.utils.SharePreUtil;
import com.geo.mvpframe_maters.utils.ToastUtils;
import com.geo.mvpframe_maters.widget.LoadDialog;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity implements IBaseView, NetWatchdogUtils.NetChangeListener {
    /**
     * 加载弹框
     */
    protected LoadDialog loadingDialog;
    protected boolean IS_USER_NETWORK =false;
    protected SharePreUtil sp;
    protected  Context mContext;
    protected  NetWatchdogUtils NetWatchdogUtils;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /**
     * 吐司
     */
    @Override
    public void showToast(String message) {
        ToastUtils.toast(mContext,message);
//        ToastUtil.getInstance().showToast(message, R.layout.toast_message);
    }

    @Override
    public void showLoading(String message) {
        hideLoading();
        loadingDialog = new LoadDialog(this);
        loadingDialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                hideLoading();
            }
        },3000);
    }

    @Override
    public void showLoading() {
        hideLoading();
        loadingDialog = new LoadDialog(this);
        loadingDialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                hideLoading();
            }
        },3000);
    }

    @Override
    public void hideLoading() {
        if (null != loadingDialog && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
            WindowManager.LayoutParams attributes = getWindow().getAttributes();
            attributes.alpha=1.0f;
            getWindow().setAttributes(attributes);
        }
    }

    /**
     * 控制物理返回键是否可用
     */
    protected boolean backKeyEnable() {
        return false;
    }

//    @Override
//    public boolean dispatchKeyEvent(KeyEvent event) {
//        //处理返回按键 统一关闭Activity的方法
//        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN && event.getRepeatCount() == 0) {
//            if (backKeyEnable()) {
//                backEvent();
//            }
//            return true;
//        }
//        return super.dispatchKeyEvent(event);
//    }

    @Override
    public Context getContext() {
        return this;
    }



    @Override
    public void on4GToWifi() {
        IS_USER_NETWORK =true;
    }

    @Override
    public void onWifiTo4G() {
        IS_USER_NETWORK =true;
    }

    @Override
    public void onReNetConnected(boolean isReconnect) {
        IS_USER_NETWORK =true;
    }

    @Override
    public void onNetUnConnected() {
        IS_USER_NETWORK =false;
    }
}

