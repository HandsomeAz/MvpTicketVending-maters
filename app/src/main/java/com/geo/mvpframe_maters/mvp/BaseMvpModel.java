package com.geo.mvpframe_maters.mvp;

import com.geo.mvpframe_maters.network.BaseNetwork;
import com.geo.mvpframe_maters.network.api.ServiceApi;

public abstract class BaseMvpModel  extends BaseNetwork {
    protected final ServiceApi service = getRetrofit().create(ServiceApi.class);
}

