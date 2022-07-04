package com.geo.mvpframe_maters.activity.SelectActivity;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;


import android.app.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.geo.mvpframe_maters.R;
import com.geo.mvpframe_maters.activity.ShiftActivity.ShiftActivity;
import com.geo.mvpframe_maters.activity.StationActivity.StationActivity;
import com.geo.mvpframe_maters.bean.ConfigInfo;
import com.geo.mvpframe_maters.bean.DataBean;
import com.geo.mvpframe_maters.db.DBManager;
import com.geo.mvpframe_maters.mvp.base.activity.BaseMvpActivity;
import com.geo.mvpframe_maters.mvp.inject.ViewInject;
import com.geo.mvpframe_maters.utils.DateUtil;
import com.geo.mvpframe_maters.utils.ToastUtils;
import com.geo.mvpframe_maters.utils.TransformationUtils;
import com.geo.mvpframe_maters.widget.CustomerVideoView;

import com.github.gzuliyujiang.calendarpicker.CalendarPicker;
import com.github.gzuliyujiang.calendarpicker.OnSingleDatePickListener;
import com.github.gzuliyujiang.calendarpicker.core.ColorScheme;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.RoundLinesIndicator;
import com.youth.banner.util.BannerUtils;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;

@ViewInject(mainLayoutId =R.layout.activity_select)
public class SelectActivity extends BaseMvpActivity {

    @BindView(R.id.select_banner)
    Banner banner;
    @BindView(R.id.select_video)
    CustomerVideoView video_view;

    private ConfigInfo configInfo;
    private List<String> videoList = new ArrayList<>();
    private int posVideoIndex = 0;
    private String offStationCode="";
    private String offStationName="",getOnStationName="";

    @BindView(R.id.tv_start_station)
    TextView tv_start_station;

    @BindView(R.id.ll_des_station)
    LinearLayout ll_des_station;
    @BindView(R.id.tv_destination)
    TextView tv_destination;

    @BindView(R.id.bt_select_shift)
    Button bt_select_shift;

    @BindView(R.id.ll_date)
    LinearLayout ll_date;

    private long singleTimeInMillis;
    @BindView(R.id.tv_date)
    TextView tv_date;

    @Override
    public void afterBindView() {
        getOnStationName =sp.getString("get_station_name", "未选上车站点");
        tv_start_station.setText(getOnStationName);

        Intent intent = getIntent();
        offStationCode =intent.getStringExtra("off_station_code");
        offStationName =intent.getStringExtra("off_station_name");
        tv_destination.setText(offStationName);
        String date = DateUtil.formatYYYYMMDD(new Date());
        tv_date.setText(date);

    }

    @Override
    protected void addListeners() {
        super.addListeners();
        ll_des_station.setOnClickListener(view -> {
            Intent intent = new Intent(mContext, StationActivity.class);
            intent.putExtra("stationType",2);
            startActivityForResult(intent,456);
        });

        bt_select_shift.setOnClickListener(view -> {
            if (offStationCode !=null &&!offStationCode.equals("")){
                String departure_time = tv_date.getText().toString();
                if (departure_time.isEmpty()){
                    ToastUtils.toast(mContext,"请选择出发日期！");
                    return;
                }
                Intent intent = new Intent(mContext, ShiftActivity.class);
                intent.putExtra("off_station_code",offStationCode);
                intent.putExtra("off_station_name",offStationName);
                intent.putExtra("departure_time",departure_time);
                startActivity(intent);
            }else {
                ToastUtils.toast(mContext,"请选择目的地！");
            }
        });

        ll_date.setOnClickListener(view -> {
            showDatePickerDialog(tv_date);
        });
    }

    @Override
    public void processData() {
        configInfo = DBManager.getManager(mContext).queryConfig();
        if (configInfo.getMainModel() ==null){
            configInfo = new ConfigInfo().initConfigInfo();
        }
//        initBanner();
        initModel();
    }

    private void initModel(){
        String mainModel = configInfo.getMainModel();
        if (mainModel ==null){
            mainModel = "0";
        }
        switch (mainModel){
            case "0":
            case "2":
                banner.setVisibility(View.VISIBLE);
                video_view.setVisibility(View.GONE);
                banner.setLoopTime(configInfo.getAbovePlayTime()*1000);
                banner.isAutoLoop(configInfo.getAboveAutoLoop().equals("0"));
                initBanner();
                break;
            case "1":
            case "3":
                banner.setVisibility(View.GONE);
                video_view.setVisibility(View.VISIBLE);
                initVideo();
                break;

        }

    }

    private void initBanner() {
        banner.addBannerLifecycleObserver(this);
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
        banner.setIndicator(new RoundLinesIndicator(mContext));
        banner.setIndicatorSelectedWidth((int) BannerUtils.dp2px(10));

    }

    private void initVideo() {
        MediaController mediaController = new MediaController(mContext);
        mediaController.setVisibility(View.GONE);
        video_view.setMediaController(mediaController);
        mediaController.setMediaPlayer(video_view);
        List<String> videos = DataBean.getManager().getVideoList();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==456&&resultCode==-1){
            if (data !=null){
                offStationCode =data.getStringExtra("off_station_code");
                offStationName =data.getStringExtra("off_station_name");
                tv_destination.setText(offStationName);
            }
        }
    }


    private   void showDatePickerDialog(TextView tv_date) {
        CalendarPicker picker = new CalendarPicker(this);
        picker.setRangeDateOnFuture(3);
        if (singleTimeInMillis == 0) {
            singleTimeInMillis = System.currentTimeMillis();
        }
        picker.setSelectedDate(singleTimeInMillis);
        picker.setColorScheme(new ColorScheme()
                .daySelectBackgroundColor(getResources().getColor(R.color.colorTheme))
                .dayStressTextColor(getResources().getColor(R.color.colorTheme)));
        picker.setOnSingleDatePickListener(new OnSingleDatePickListener() {
            @Override
            public void onSingleDatePicked(@NonNull Date date) {
                singleTimeInMillis = date.getTime();
                String selectDate = DateUtil.formatYYYYMMDD(date);
                if (Math.abs(DateUtil.getDayNum(selectDate))<7){
                    tv_date.setText(DateUtil.formatYYYYMMDD(date));
                    picker.dismiss();
                }else {
                    showToast("班车只售票七天内的班车");
                }
            }
        });
        picker.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}