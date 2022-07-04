package com.geo.mvpframe_maters.mvp.base.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.geo.mvpframe_maters.mvp.inject.ViewInject;
import com.geo.mvpframe_maters.mvp.proxy.ProxyActivity;
import com.geo.mvpframe_maters.utils.ActivityManageUtil;
import com.geo.mvpframe_maters.utils.NetWatchdogUtils;
import com.geo.mvpframe_maters.utils.SharePreUtil;

import butterknife.ButterKnife;

public abstract class BaseMvpActivity extends BaseActivity {


    private ProxyActivity mProxyActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext =this;
        ActivityManageUtil.getInstance().addActivity(this);
        sp = SharePreUtil.getInstance(mContext);

        ViewInject annotation = this.getClass().getAnnotation(ViewInject.class);
        if (annotation != null){
            int mainLayoutId = annotation.mainLayoutId();
            if (mainLayoutId > 0){
                setContentView(mainLayoutId);
                NetWatchdogUtils = new NetWatchdogUtils(this);
                NetWatchdogUtils.setNetChangeListener(this);
                NetWatchdogUtils.startWatch();
                bindView();
                mProxyActivity = createProxyActivity();
                mProxyActivity.bindPresenter();
                afterBindView();
                processData();

            }else {
                throw new RuntimeException("mainLayoutId 加载<0");
            }
        }else {
            throw new RuntimeException("mainLayoutId is null");
        }
        init();
    }

    @SuppressWarnings("unchecked")
    private ProxyActivity createProxyActivity() {
        if (mProxyActivity == null) {
            return new ProxyActivity(this);
        }
        return mProxyActivity;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mProxyActivity.unbindPresenter();
        NetWatchdogUtils.stopWatch();
        ActivityManageUtil.getInstance().removeActivity(this);
    }
    public abstract void afterBindView();
    public abstract void processData();

    /**
     * 初始化
     */
    private void init() {

        addListeners();


    }



    //view的依赖绑定注入
    private void bindView() {
        ButterKnife.bind(this);
    }
    /**
     * 初始化监听器
     */
    protected void addListeners() {
    }


}

