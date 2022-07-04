package com.geo.mvpframe_maters.widget;

import android.content.Context;

import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.geo.mvpframe_maters.R;
import com.geo.mvpframe_maters.utils.DialogManage;
import com.geo.mvpframe_maters.utils.ReadIDUtil;
import com.geo.mvpframe_maters.utils.ReadIdClickListener;
import com.geo.mvpframe_maters.utils.ToastUtils;


public class SelectTicketView extends LinearLayout implements View.OnClickListener {


    private Context mContext;

    private ImageView iv_person_type;
    private ImageView iv_add,iv_minus;
    private TextView tv_name_type;
    private TextView tv_ticket_num;
    private int number =0;
    private int MAX_NUMBER=0;
    private boolean needRead =false;
    private boolean takeFreeChild =false;

    public SelectTicketView(Context context) {
        this(context,null);
    }

    public SelectTicketView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SelectTicketView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SelectTicketView, defStyleAttr, 0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.SelectTicketView_img:
                    int resourceId = a.getResourceId(attr, -1);
                    if (-1 != resourceId)
                        iv_person_type.setImageResource(resourceId);
                    break;
                case R.styleable.SelectTicketView_textContent:
                    String text = a.getString(attr);
                    if (!TextUtils.isEmpty(text))
                        tv_name_type.setText(text);
                    break;
            }
        }
        a.recycle();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.view_select_tickets, this);
        iv_add = findViewById(R.id.iv_add);
        iv_minus = findViewById(R.id.iv_minus);

        iv_person_type = findViewById(R.id.iv_person_type);
        tv_name_type = findViewById(R.id.tv_name_type);
        tv_ticket_num = findViewById(R.id.tv_ticket_num);

        iv_minus.setOnClickListener(this);
        iv_add.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if (view ==iv_minus){
            if (number ==0){
                ToastUtils.toast(mContext,"不能小于0");
                return;
            }
            number--;
            updateNumber(number);
            if (mSelectTicketListener !=null){
                mSelectTicketListener.minusPassenger(number);
            }

        }else if (view == iv_add){
            if (number >=MAX_NUMBER){
                ToastUtils.toast(mContext,"余票不足");
                return;
            }
            if (needRead){
                ReadIDUtil.getInstance().readIDCard(mContext);
                DialogManage.getInstance().ReadIDTisDialog("请刷身份证", new ReadIdClickListener() {
                    @Override
                    public void Cancel() {

                    }

                    @Override
                    public void Submit(String name, String cardNum, String address, String phoneNumber) {
                        number++;
                        updateNumber(number);
                        if (mSelectTicketListener !=null){
                            mSelectTicketListener.addPassenger(name,cardNum,address,phoneNumber,number);
                        }
                    }
                });
            }else {
                if (takeFreeChild){
                    number++;
                    updateNumber(number);
                    if (mSelectTicketListener !=null){
                        mSelectTicketListener.addPassenger("","","","",number);
                    }
                }else {
                    ToastUtils.toast(mContext,"儿童票需要有成人携带，请先添加成人票");
                }
            }

        }
    }

    private void updateNumber(int number) {
        iv_minus.setVisibility(number>0? VISIBLE:INVISIBLE);
        tv_ticket_num.setText(number+"");
    }

    public ImageView getIv_person_type() {
        return iv_person_type;
    }

    public ImageView getIv_add() {
        return iv_add;
    }

    public ImageView getIv_minus() {
        return iv_minus;
    }

    public TextView getTv_name_type() {
        return tv_name_type;
    }

    public TextView getTv_ticket_num() {
        return tv_ticket_num;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
        updateNumber(number);
    }

    public void setMaxNum(int MAX_NUMBER) {
        this.MAX_NUMBER = MAX_NUMBER;
    }

    public void setNeedRead(boolean needRead) {
        this.needRead = needRead;
    }

    private onSelectTicketListener mSelectTicketListener =null;

    public void setSelectTicketListener(onSelectTicketListener mSelectTicketListener) {
        this.mSelectTicketListener = mSelectTicketListener;
    }

    public void setTakeFreeChild(boolean takeFreeChild) {
        this.takeFreeChild = takeFreeChild;
    }

    public interface onSelectTicketListener{
        void addPassenger(String name,String cardNum,String address,String phone,int ticketNum);
        void minusPassenger(int number);
    }
}
