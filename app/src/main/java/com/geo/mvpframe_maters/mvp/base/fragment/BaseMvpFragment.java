package com.geo.mvpframe_maters.mvp.base.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.geo.mvpframe_maters.mvp.inject.ViewInject;
import com.geo.mvpframe_maters.mvp.proxy.ProxyFragment;
import com.geo.mvpframe_maters.utils.NetWatchdogUtils;

import butterknife.ButterKnife;

public abstract class BaseMvpFragment extends BaseFragment implements NetWatchdogUtils.NetChangeListener {

    private ProxyFragment mProxyFragment;
    protected NetWatchdogUtils NetWatchdogUtils;
    protected boolean IS_USER_NETWORK =false;
    private View  mView = null;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewInject annotation = this.getClass().getAnnotation(ViewInject.class);
        if (annotation != null){
            int mainLayoutId = annotation.mainLayoutId();
            if (mainLayoutId > 0){
                mView = initFragmentView(inflater,mainLayoutId);
//                setContentView(mainLayoutId);
                bindView(mView);
                mProxyFragment = createProxyFragment();
                mProxyFragment.bindPresenter();
                NetWatchdogUtils = new NetWatchdogUtils(getContext());
                NetWatchdogUtils.setNetChangeListener(this);
                NetWatchdogUtils.startWatch();
                afterBindView();
                processData();

            }else {
                throw new RuntimeException("mainLayoutId 加载<0");
            }
        }else {
            throw new RuntimeException("mainLayoutId is null");
        }
        return mView;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }
    @SuppressWarnings("unchecked")
    private ProxyFragment createProxyFragment() {
        if (mProxyFragment == null) {
            return new ProxyFragment(this);
        }
        return mProxyFragment;
    }

    private View initFragmentView(LayoutInflater inflater, int mainLayoutId) {

        return  inflater.inflate(mainLayoutId,null);
    }


    /**
     * 初始化
     */
    private void init() {

        addListeners();
    }
    /**
     * 初始化监听器
     */
    protected void addListeners() {
    }


    //view的依赖绑定注入
    private void bindView(View view) {
        ButterKnife.bind(this,view);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mProxyFragment.unbindPresenter();
        NetWatchdogUtils.stopWatch();
    }
    public abstract void afterBindView();
    public abstract void processData();
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
