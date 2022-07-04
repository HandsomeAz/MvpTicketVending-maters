package com.geo.mvpframe_maters.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.lifecycle.Observer;

import com.geo.mvpframe_maters.R;
import com.geo.mvpframe_maters.application.MyApplication;
import com.geo.mvpframe_maters.widget.MyDialog;
import com.jeremyliao.liveeventbus.LiveEventBus;

import cn.mineki.CardReaders.IDCardInfo;

/**
 * 创建人： created by zlj
 * 时间：2022/06/19 22
 */
public class DialogManage {
    private static DialogManage mDialogManage =null;
    private MyDialog mMyDialog =null;

    public static DialogManage getInstance() {
        if (mDialogManage == null){
            mDialogManage = new DialogManage();
        }
        return mDialogManage;
    }

    public void TipDialog(String content,DialogClickListener listener){
        Activity activity = MyApplication.getCurrentActivity();
        if (activity ==null){
            return;
        }

        hideDialog();
        DisplayMetrics outMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        int widthPixels = outMetrics.widthPixels;
        int heightPixels = outMetrics.heightPixels;
        View view = activity.getLayoutInflater().inflate(R.layout.message_dialog, null);

        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);

        TextView tv_content = view.findViewById(R.id.tv_content);
        Button btn_submit = view.findViewById(R.id.btn_submit);
        Button btn_cancel = view.findViewById(R.id.btn_cancel);
        if (content!=null){
            tv_content.setText(content);
        }
        btn_cancel.setOnClickListener(v -> {
            listener.Cancel();
            mMyDialog.dismiss();
        });
        btn_submit.setOnClickListener(v -> {
            listener.Submit();
            mMyDialog.dismiss();
        });
        mMyDialog = new MyDialog(activity, (int) (widthPixels*0.8), ViewGroup.LayoutParams.WRAP_CONTENT, view, R.style.custom_dialog, Gravity.CENTER);
        mMyDialog.setCancelable(false);
        mMyDialog.show();
    }

    private void hideDialog(){
        if (mMyDialog !=null){
            if (mMyDialog.isShowing()){
                mMyDialog.dismiss();
            }
        }
    }

    public void ReadIDTisDialog(String content,ReadIdClickListener listener){
        Activity activity = MyApplication.getCurrentActivity();
        if (activity ==null){
            return;
        }

        hideDialog();
        String person_address = SharePreUtil.getInstance(activity).getString("person_address", "");
        String person_phone = SharePreUtil.getInstance(activity).getString("person_phone", "");
        DisplayMetrics outMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        int widthPixels = outMetrics.widthPixels;
        int heightPixels = outMetrics.heightPixels;
        View view = activity.getLayoutInflater().inflate(R.layout.image_tip_dialog, null);

        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);

        TextView tv_content = view.findViewById(R.id.tv_content);
        Button btn_submit = view.findViewById(R.id.btn_submit);
        Button btn_cancel = view.findViewById(R.id.btn_cancel);


        RelativeLayout rl_tip = view.findViewById(R.id.rl_tip);
        LinearLayout ll_input_tip = view.findViewById(R.id.ll_input_tip);

        EditText et_name = view.findViewById(R.id.et_name);
        EditText et_card_num = view.findViewById(R.id.et_card_num);
        EditText et_address = view.findViewById(R.id.et_address);
        if (!person_address.equals("")){
            et_address.setText(person_address);
        }
        EditText et_phone_number = view.findViewById(R.id.et_phone_number);
        if (!person_phone.equals("")){
            et_phone_number.setText(person_phone);
        }
        if (content!=null){
            tv_content.setText(content);
        }
        btn_cancel.setOnClickListener(v -> {
            listener.Cancel();
            mMyDialog.dismiss();
        });
        btn_submit.setOnClickListener(v -> {
            if (rl_tip.getVisibility()== View.VISIBLE){
                rl_tip.setVisibility(View.GONE);
                ll_input_tip.setVisibility(View.VISIBLE);
                btn_submit.setText("确认");
                tv_content.setText("防控疫情人人有责，请完善信息，感谢配合！");
            }else {
                String name = et_name.getText().toString().trim();
                String cardNum = et_card_num.getText().toString().trim();
                String address = et_address.getText().toString().trim();
                String phone = et_phone_number.getText().toString().trim();
                if (name.isEmpty()){
                    ToastUtils.toast(activity,"姓名不能为空");
                    return;
                }
                if (cardNum.isEmpty()){
                    ToastUtils.toast(activity,"证件号不能为空");
                    return;
                }
                if (address.isEmpty()){
                    ToastUtils.toast(activity,"目的地址不能为空");
                    return;
                }
                if (phone.isEmpty()){
                    ToastUtils.toast(activity,"联系电话不能为空");
                    return;
                }
                mMyDialog.dismiss();
                listener.Submit(name,cardNum,address,phone);
                SharePreUtil.getInstance(activity).putString("person_address",address);
                SharePreUtil.getInstance(activity).putString("person_phone",phone);
            }
        });
        LiveEventBus.get("idRead_success", IDCardInfo.class).observeForever(new Observer<IDCardInfo>() {
            @Override
            public void onChanged(IDCardInfo idCardInfo) {
                if (idCardInfo ==null){
                    return;
                }
                rl_tip.setVisibility(View.GONE);
                ll_input_tip.setVisibility(View.VISIBLE);
                btn_submit.setText("确认");
                tv_content.setText("防控疫情人人有责，请完善信息，感谢配合！");
                et_card_num.setText(idCardInfo.getCardNum()+"");
                et_name.setText(idCardInfo.getName()+"");
            }
        });
        mMyDialog = new MyDialog(activity, (int) (widthPixels*0.8), ViewGroup.LayoutParams.WRAP_CONTENT, view, R.style.custom_dialog, Gravity.CENTER);
        mMyDialog.setCancelable(false);
        mMyDialog.show();
    }
}
