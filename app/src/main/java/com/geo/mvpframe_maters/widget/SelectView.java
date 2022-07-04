package com.geo.mvpframe_maters.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.Nullable;


import com.geo.mvpframe_maters.R;
import com.geo.mvpframe_maters.utils.KeyboardUtil;

import java.util.ArrayList;

public class SelectView extends LinearLayout implements View.OnClickListener {
    private ImageView iv_host;
    private EditText tv_name;
    private LinearLayout rl_select;
    private PopupWindow popupWindow = null;
    private ArrayList<String> dataList =  new ArrayList<String>();
    private View mView;

    public SelectView(Context context) {
        this(context,null);
    }

    public SelectView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    @SuppressLint("NonConstantResourceId")
    public SelectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.select_layout, this);
        iv_host =  findViewById(R.id.btn);
        tv_name =  findViewById(R.id.tv_text);
        tv_name.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    if (popupWindow!=null){
                        if (popupWindow.isShowing()){
                            closePopWindow();
                        }
                    }
                }
            }
        });
        rl_select = findViewById(R.id.rl_select);
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mView!=null){
                    if(popupWindow == null ){
                        showPopWindow();
                        iv_host.setImageResource(R.drawable.icon_pull_up);
                        KeyboardUtil.hideSoftKeyboard(context,tv_name);

                    }else{
                        closePopWindow();

                    }
                }
            }
        });
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SelectView, defStyleAttr, 0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {

                case R.styleable.SelectView_textContent:
                    String text = a.getString(attr);
                    if (!TextUtils.isEmpty(text)){
                        tv_name.setText(text);

                    }
                    break;
                case R.styleable.SelectView_isClickable:
                    boolean isClickable = a.getBoolean(attr,false);
                    tv_name.setFocusable(isClickable);
                    tv_name.setTextIsSelectable(isClickable);
                    tv_name.setCursorVisible(isClickable);
                    break;
                case R.styleable.SelectView_textHint:
                    String hint = a.getString(attr);
                    if (!TextUtils.isEmpty(hint)){
                        tv_name.setHint(hint);
                        tv_name.setGravity(Gravity.START|Gravity.CENTER_VERTICAL);
                        tv_name.setHintTextColor(context.getResources().getColor(R.color.item_text_color));
                    }
                    break;


            }
        }
        a.recycle();
    }

    /**
     * 打开下拉列表弹窗
     */
    private void showPopWindow() {
        // 加载popupWindow的布局文件
        String infServie = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater layoutInflater;
        layoutInflater =  (LayoutInflater) getContext().getSystemService(infServie);
        View contentView  = layoutInflater.inflate(R.layout.dropdownlist_popupwindow, null,false);
        ListView listView = contentView.findViewById(R.id.listView);

        listView.setAdapter(new XCDropDownListAdapter(getContext(), dataList));
        popupWindow = new PopupWindow(contentView,mView.getWidth(), LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.color.item_text_color));
        popupWindow.setOutsideTouchable(false);
        popupWindow.showAsDropDown(this);
//        popupWindow.setWidth(w);
//        popupWindow.setHeight(h);
        popupWindow.setFocusable(true);
        popupWindow.showAsDropDown(mView, mView.getWidth() * 5 / 100, 0);

    }
    /**
     * 关闭下拉列表弹窗
     */
    private void closePopWindow(){
        popupWindow.dismiss();
        popupWindow = null;
        iv_host.setImageResource(R.drawable.icon_down);
    }

    public ImageView getIvRight() {
        return iv_host;
    }

    public void setIvRight(ImageView ivRight) {
        this.iv_host = ivRight;
    }

    public EditText getTvLeft() {
        return tv_name;
    }

    public void setTvLeft(EditText tvLeft) {
        this.tv_name = tvLeft;
    }

    @Override
    public void onClick(View v) {

    }
    /**
     * 设置数据
     * @param list
     */
    public void setItemsData(ArrayList<String> list, View view){
        dataList = list;
        mView =view;

    }

    /**
     * 数据适配器
     * @author caizhiming
     *
     */
    class XCDropDownListAdapter extends BaseAdapter {

        Context mContext;
        ArrayList<String> mData;
        LayoutInflater inflater;
        public XCDropDownListAdapter(Context ctx, ArrayList<String> data){
            mContext  = ctx;
            mData = data;
            inflater = LayoutInflater.from(mContext);
        }
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return mData.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            // 自定义视图
            ListItemView listItemView = null;
            if (convertView == null) {
                // 获取list_item布局文件的视图
                convertView = inflater.inflate(R.layout.dropdown_list_item, null);

                listItemView = new ListItemView();
                // 获取控件对象
                listItemView.tv = (TextView) convertView
                        .findViewById(R.id.tv);

                listItemView.layout = (LinearLayout) convertView.findViewById(R.id.layout_container);
                // 设置控件集到convertView
                convertView.setTag(listItemView);
            } else {
                listItemView = (ListItemView) convertView.getTag();
            }

            // 设置数据
            listItemView.tv.setText(mData.get(position).toString());
            final String text = mData.get(position).toString();
            listItemView.layout.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    tv_name.setText(text);
                    if (mItemOnClickListener !=null){
                        mItemOnClickListener.itemOnClickListener(text);
                    }
                    closePopWindow();
                }
            });
            return convertView;
        }

    }
    private static class ListItemView{
        TextView tv;
        LinearLayout layout;
    }
    private ItemOnClickListener mItemOnClickListener;
    public interface ItemOnClickListener{
        /**
         * 传递点击的view
         * @param text
         */
        public void itemOnClickListener(String text);
    }
    public void setmItemOnClickListener(ItemOnClickListener listener){

        this.mItemOnClickListener = listener;
    }
}
