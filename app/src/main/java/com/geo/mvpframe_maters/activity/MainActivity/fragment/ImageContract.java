package com.geo.mvpframe_maters.activity.MainActivity.fragment;

import androidx.fragment.app.Fragment;

import com.geo.mvpframe_maters.bean.CityInfo;
import com.geo.mvpframe_maters.bean.PictureInfo;
import com.geo.mvpframe_maters.mvp.IBaseView;
import com.geo.mvpframe_maters.network.RequestBean;

import java.util.List;

import io.reactivex.Observer;
import okhttp3.ResponseBody;

/**
 * @package: com.geo.mvpframe_maters.activity.MainActivity.fragment
 * 创建人： created by zlj
 * 时间：2022/06/21 20
 */
public interface ImageContract {
    interface IModel {
        void getAboveImage(String code, Observer<RequestBean<List<PictureInfo>>> observer);
        void downloadImage(String url, Observer<ResponseBody> observer);

    }

    interface IView extends IBaseView {


        void getImageSuccess(RequestBean<List<PictureInfo>> requestBean);

        void downloadFinish();

        void setVideoList(List<String> videoList);
    }

    interface IPresenter {


        void getAboveImage(String accountKey);

        void download(List<PictureInfo> data,int width,int height);

        void getVideos();
    }

}
