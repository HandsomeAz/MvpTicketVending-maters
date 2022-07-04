package com.geo.mvpframe_maters.activity.MainActivity.fragment;


import com.geo.mvpframe_maters.bean.PictureInfo;
import com.geo.mvpframe_maters.mvp.BaseMvpModel;
import com.geo.mvpframe_maters.network.RequestBean;
import com.geo.mvpframe_maters.utils.MD5Util;

import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import okhttp3.ResponseBody;

import static com.geo.mvpframe_maters.constant.Constants.ACCOUNT_KEY;
import static com.geo.mvpframe_maters.constant.Constants.PASSWORD_KEY;

/**
 * @package: com.geo.mvpframe_maters.activity.MainActivity.fragment
 * 创建人： created by
 * 时间：2022/06/21 20
 */
public class ImageModel extends BaseMvpModel implements ImageContract.IModel {
    @Override
    public void getAboveImage(String code, Observer<RequestBean<List<PictureInfo>>> observer) {
        String timestamp =String.valueOf(System.currentTimeMillis()/1000);
        String nonce=String.valueOf(getRand());
        String str = ACCOUNT_KEY + timestamp + nonce + PASSWORD_KEY;
        String sign  = MD5Util.encode(str);
        Map<String, String> map = getHeader(timestamp, nonce,sign);
        setSubscribe(service.getAboveImage(map,code),observer);
    }

    @Override
    public void downloadImage(String url, Observer<ResponseBody> observer) {
        setSubscribe(service.downloadApk(url),observer);
    }
}
