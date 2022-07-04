package com.geo.mvpframe_maters.mvp.proxy;

import com.geo.mvpframe_maters.mvp.IBaseView;

public class ProxyActivity<V extends IBaseView> extends ProxyImpl {

    public ProxyActivity(V view) {
        super(view);
    }
}
