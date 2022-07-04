package com.geo.mvpframe_maters.mvp.proxy;

import com.geo.mvpframe_maters.mvp.IBaseView;

public class ProxyFragment<V extends IBaseView> extends ProxyImpl {
    public ProxyFragment(V view) {
        super(view);
    }
}
