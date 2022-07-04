package com.geo.mvpframe_maters.activity.MainActivity.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.Observer;

import com.bumptech.glide.Glide;
import com.geo.mvpframe_maters.R;
import com.geo.mvpframe_maters.activity.StationActivity.StationActivity;
import com.geo.mvpframe_maters.bean.ConfigInfo;
import com.geo.mvpframe_maters.bean.DataBean;
import com.geo.mvpframe_maters.bean.PictureInfo;
import com.geo.mvpframe_maters.constant.Constants;
import com.geo.mvpframe_maters.db.DBManager;
import com.geo.mvpframe_maters.mvp.base.fragment.BaseMvpFragment;
import com.geo.mvpframe_maters.mvp.inject.InjectPresenter;
import com.geo.mvpframe_maters.mvp.inject.ViewInject;
import com.geo.mvpframe_maters.network.RequestBean;
import com.geo.mvpframe_maters.utils.TransformationUtils;
import com.geo.mvpframe_maters.widget.CustomerVideoView;
import com.jeremyliao.liveeventbus.LiveEventBus;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.RoundLinesIndicator;
import com.youth.banner.util.BannerUtils;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;


/**
 * @package: com.geo.mvpframe_maters.activity.MainActivity.fragment
 * 创建人： created by zlj
 * 时间：2022/06/20 21
 */
@ViewInject(mainLayoutId = R.layout.fragment_image)
public class ImageFragment extends BaseMvpFragment implements ImageContract.IView{
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.bn_home_body)
    Banner bn_home_body;
    @InjectPresenter
    ImagePresenter mPresenter;

    @BindView(R.id.video_view)
    CustomerVideoView video_view;

    @BindView(R.id.bt_go_buy)
    Button bt_go_buy;

    private int width,height;
    private ConfigInfo configInfo;
    private List<String> videoList = new ArrayList<>();
    private int posVideoIndex = 0;
    @Override
    public void processData() {

//        mPresenter.getBelowImage();
        configInfo = DBManager.getManager(mContext).queryConfig();
        if (configInfo.getMainModel() ==null){
            configInfo = new ConfigInfo().initConfigInfo();
        }
        initModel();
        bn_home_body.setAdapter(new BannerImageAdapter<String>(DataBean.getManager().getPicture2()) {
            @Override
            public void onBindView(BannerImageHolder holder, String data, int position, int size) {
                TransformationUtils transformationUtils = new TransformationUtils(holder.imageView);
                //图片加载自己实现
                Glide.with(holder.itemView)
                        .load(data)
                        .fitCenter()
                        .thumbnail(Glide.with(holder.itemView).load(R.drawable.icon_below))
//                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(1)))
                        .into(transformationUtils.getImageView());
            }
        });
        bn_home_body.setLoopTime(configInfo.getBelowPlayTime()*1000);
        bn_home_body.isAutoLoop(configInfo.getBelowAutoLoop().equals("0"));
        bn_home_body.setIndicator(new RoundLinesIndicator(getActivity()));
        bn_home_body.setIndicatorSelectedWidth((int) BannerUtils.dp2px(10));
        bn_home_body.setOnBannerListener((data, position) -> {
            Intent intent = new Intent(mContext, StationActivity.class);
            intent.putExtra("stationType",2);
            mContext.startActivity(intent);
        });

        LiveEventBus.get("banner_setting",ConfigInfo.class)
                .observe(getActivity(), new Observer<ConfigInfo>() {
                    @Override
                    public void onChanged(ConfigInfo info) {
                        if (info==null){
                            return;
                        }
                        configInfo = info;
                        initModel();
                    }
                });
    }

    @Override
    public void afterBindView() {
        DisplayMetrics outMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        int widthPixels = outMetrics.widthPixels;
        int heightPixels = outMetrics.heightPixels;
        width = widthPixels;
        height = (int) (heightPixels*0.25);

        bt_go_buy.setAlpha(0.5f);
        bt_go_buy.setBottom((int) (120));
        bt_go_buy.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, StationActivity.class);
            intent.putExtra("stationType",2);
            mContext.startActivity(intent);
        });
        banner.addBannerLifecycleObserver(getActivity());
        bn_home_body.addBannerLifecycleObserver(getActivity());
    }

    @Override
    public void getImageSuccess(RequestBean<List<PictureInfo>> requestBean) {
        if (requestBean ==null){
           hideLoading();
            return;
        }
        List<PictureInfo> data = requestBean.getData();
        if (data==null){
            hideLoading();
            return;
        }
        mPresenter.download(data,width,height);
    }

    @Override
    public void downloadFinish() {

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                hideLoading();
//                banner.setAdapter(new BannerImageAdapter<String>(imgList) {
//
//                    @Override
//                    public void onBindView(BannerImageHolder holder, String data, int position, int size) {
//                        System.out.println("hello TEST");
//                        Glide.with(holder.itemView)
//                                .load(data)
//                                .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
//                                .into(holder.imageView);
//                    }
//
//                }).setIndicator(new CircleIndicator(getContext())).setLoopTime(1000).setOnBannerListener(this);

                banner.setAdapter(new BannerImageAdapter<String>(DataBean.getManager().getPicture()) {
                    @Override
                    public void onBindView(BannerImageHolder holder, String data, int position, int size) {
                        TransformationUtils transformationUtils = new TransformationUtils(holder.imageView);
                        //图片加载自己实现
                        Glide.with(holder.itemView)
                                .load(data)
                                .fitCenter()
                                .thumbnail(Glide.with(holder.itemView).load(R.drawable.ic_svstatus_loading))
//                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(1)))
                                .into(transformationUtils.getImageView());
                    }
                });
                banner.setLoopTime(configInfo.getAbovePlayTime()*1000);
                banner.isAutoLoop(configInfo.getAboveAutoLoop().equals("0"));
                banner.setIndicator(new RoundLinesIndicator(getActivity()));
                banner.setIndicatorSelectedWidth((int) BannerUtils.dp2px(10));
                banner.setOnBannerListener((data, position) -> {
                    Intent intent = new Intent(mContext, StationActivity.class);
                    intent.putExtra("stationType",2);
                    mContext.startActivity(intent);
                });
            }
        });
    }


    private void initModel(){
        String mainModel = configInfo.getMainModel();
        if (mainModel ==null){
            mainModel = "0";
        }
        switch (mainModel){
            case "1":
                banner.setVisibility(View.GONE);
                video_view.setVisibility(View.VISIBLE);
                bn_home_body.setVisibility(View.VISIBLE);
                bt_go_buy.setVisibility(View.GONE);
                initVideo();
                bn_home_body.setLoopTime(configInfo.getBelowPlayTime()*1000);
                bn_home_body.isAutoLoop(configInfo.getBelowAutoLoop().equals("0"));
                break;
            case "2":
                banner.setVisibility(View.GONE);
                video_view.setVisibility(View.GONE);
                bt_go_buy.setVisibility(View.GONE);
                bn_home_body.setVisibility(View.VISIBLE);
                bn_home_body.setLoopTime(configInfo.getBelowPlayTime()*1000);
                bn_home_body.isAutoLoop(configInfo.getBelowAutoLoop().equals("0"));
                break;
            case "3":
                banner.setVisibility(View.GONE);
                video_view.setVisibility(View.VISIBLE);
                bn_home_body.setVisibility(View.GONE);
                bt_go_buy.setVisibility(View.VISIBLE);
                initVideo();
                break;
            default:
                mPresenter.getAboveImage(Constants.ACCOUNT_KEY);
                banner.setVisibility(View.VISIBLE);
                bn_home_body.setVisibility(View.VISIBLE);
                video_view.setVisibility(View.GONE);
                bt_go_buy.setVisibility(View.GONE);
                banner.setLoopTime(configInfo.getAbovePlayTime()*1000);
                banner.isAutoLoop(configInfo.getAboveAutoLoop().equals("0"));
                bn_home_body.setLoopTime(configInfo.getBelowPlayTime()*1000);
                bn_home_body.isAutoLoop(configInfo.getBelowAutoLoop().equals("0"));
                break;
        }

    }

    private void initVideo() {
        MediaController mediaController = new MediaController(mContext);
        mediaController.setVisibility(View.GONE);
        video_view.setMediaController(mediaController);
        mediaController.setMediaPlayer(video_view);
        mPresenter.getVideos();
    }
    @Override
    public void setVideoList(List<String> videos) {
        if (videos ==null){
            return;
        }
        if (videoList!=null && videoList.size()>0){
            videoList.clear();
        }
        this.videoList =videos;
        if (videoList.size() ==0){
            return;
        }
        startVideos();
        video_view.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Log.d("TYY_CC_wjll", "我播完了");
                posVideoIndex++;
                if(posVideoIndex >= videoList.size()){
                    posVideoIndex = 0;
                }
                startVideos();
            }
        });

        video_view.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onPrepared(MediaPlayer mp) {
                video_view.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                    @Override
                    public boolean onInfo(MediaPlayer mp, int what, int extra) {
                        video_view.setBackgroundColor(Color.TRANSPARENT);
                        return false;
                    }
                });
            }
        });
        video_view.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                video_view.stopPlayback(); //播放异常，则停止播放，防止弹窗使界面阻塞
                return true;
            }
        });
        video_view.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                if (configInfo.getVideoVoice().equals("1")){
                    mp.setVolume(1, 1);
                }else {
                    mp.setVolume(0, 0);
                }
            }
        });
    }
    /**
     * 播放视频
     */
    private void startVideos() {
        video_view.setVideoPath(videoList.get(posVideoIndex));
        video_view.start();

    }

    @Override
    public void onResume() {
        super.onResume();
        if (!video_view.isPlaying()){
            video_view.start();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (video_view.isPlaying()){
            video_view.stopPlayback();
        }

    }
}
