package com.geo.mvpframe_maters.widget;

import android.content.Context;
import android.content.res.TypedArray;

import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import androidx.annotation.Nullable;

import com.geo.mvpframe_maters.R;
import com.geo.mvpframe_maters.utils.DateUtil;
import com.geo.mvpframe_maters.utils.ToastUtils;

import java.text.SimpleDateFormat;
import java.util.Date;


public class DateView extends LinearLayout implements View.OnClickListener {

    public static final long DAY_SECEND =24*60*60*1000;

    private TextView tvDate;
    private ImageView ivDateLeft,ivDateRight;
    private int day=0;
    private long timeSpan;
    private Context mContext;

    private RelativeLayout rl_add,rl_minus;

    public DateView(Context context) {
        this(context,null);
    }

    public DateView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext =context;
        LayoutInflater.from(context).inflate(R.layout.view_date, this);
        ivDateLeft = findViewById(R.id.vd_dateLeft);
        ivDateRight = findViewById(R.id.vd_dateRight);
        rl_add = findViewById(R.id.rl_add);
        rl_minus = findViewById(R.id.rl_minus);
        tvDate = findViewById(R.id.vd_date);
        rl_minus.setOnClickListener(this);
        rl_add.setOnClickListener(this);
        setDate(System.currentTimeMillis());


    }

    public ImageView getIvDateLeft() {
        return ivDateLeft;
    }

    public ImageView getIvDateRight() {
        return ivDateRight;
    }

    public TextView getTvDate() {
        return tvDate;
    }

    private void setDate(long time) {
        Date curDate = new Date(time);//获取当前时间 //System.currentTimeMillis()
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = formatter1.format(curDate);
        tvDate.setText(dateStr+"");
    }

    public void setDateText(long time) {
        Date curDate = new Date(time);//获取当前时间 //System.currentTimeMillis()
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = formatter1.format(curDate);
        tvDate.setText(dateStr+"");
    }
    public void setDateText(String time) {
        tvDate.setText(time+"");
    }

    @Override
    public void onClick(View v) {
        if (v==rl_minus){
            day--;
            long timeSpan = day * DateView.DAY_SECEND;
            Date date = new Date(System.currentTimeMillis() + timeSpan);
            String selectDate = DateUtil.formatYYYYMMDD(date);
            if (DateUtil.getDayNum(selectDate)<=0){
                if (null!=dateChangeListener)
                    dateChangeListener.minus(day);
            }else {
                day++;
                ToastUtils.toast(mContext,"不能购买已过时间的班次");
                return;
            }

//            timeSpan = day*24*60*60*1000;
//            setDate(System.currentTimeMillis()+ timeSpan);
//            timeSpan = day*24*60*60*1000;

        }else if (v==rl_add){
            day++;
            long timeSpan = day * DateView.DAY_SECEND;
            Date date = new Date(System.currentTimeMillis() + timeSpan);
            String selectDate = DateUtil.formatYYYYMMDD(date);
            if (Math.abs(DateUtil.getDayNum(selectDate))<=7){
                if (null!=dateChangeListener)
                    dateChangeListener.add(day);
            }else {
                day--;
                ToastUtils.toast(mContext,"不能购买超过七天的班次");
                return;
            }

        }
        timeSpan = day*DAY_SECEND;
        setDate(System.currentTimeMillis()+ timeSpan);

    }

    public long getTimeSpan() {
        return timeSpan;
    }

    public interface DateChangeListener{
        void add(int day);
        void minus(int day);
    }

    private DateChangeListener dateChangeListener;

    public DateChangeListener getDateChangeListener() {
        return dateChangeListener;
    }

    public void setDateChangeListener(DateChangeListener dateChangeListener) {
        this.dateChangeListener = dateChangeListener;
    }


    public static void main(String ...args){
        testDate(System.currentTimeMillis()-24*60*60*1000);
        testDate(System.currentTimeMillis()+24*60*60*1000);
    }

    private static void testDate(long time) {
        Date curDate = new Date(time);//获取当前时间 //System.currentTimeMillis()
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy年MM月dd日");
        String dateStr = formatter1.format(curDate);
        System.out.println("-------->>> "+dateStr);
    }
}
