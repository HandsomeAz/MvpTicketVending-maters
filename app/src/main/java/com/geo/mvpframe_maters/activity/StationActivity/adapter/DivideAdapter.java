package com.geo.mvpframe_maters.activity.StationActivity.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.geo.mvpframe_maters.R;
import com.geo.mvpframe_maters.bean.DivideStation;
import com.geo.mvpframe_maters.bean.StationInfo;
import com.jeremyliao.liveeventbus.LiveEventBus;

import java.util.ArrayList;
import java.util.List;

public class DivideAdapter extends RecyclerView.Adapter<DivideAdapter.ViewHolder>  {

    private Context mContext;
    private List<DivideStation> dataList =new ArrayList<>();
    private int mCurrentPosition =-1;
    private int mCurrentItem =-1;
    private int itemType =0;


    public DivideAdapter(Context context){
        this.mContext =context;
    }

    public void setDivideStationData(List<DivideStation> data,int type){
        if (data ==null){
            return;
        }
        this.dataList =data;
        mCurrentPosition =-1;
        mCurrentItem =-1;
        this.itemType = type;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= View.inflate(mContext, R.layout.divide_station_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (dataList !=null){

            holder.tv_title.setText(dataList.get(position).getTitleName());
            StationAdapter stationAdapter =new StationAdapter(mContext);
            stationAdapter.setStationData(dataList.get(position).getStationList(),itemType);
            holder.rv_station.setAdapter(stationAdapter);
            stationAdapter.setChooseListener(new StationAdapter.ChooseListener() {
                @Override
                public void onClick(StationInfo stationInfo,int item) {
                    mCurrentPosition = position;
                    mCurrentItem = item;
                    Log.d("返回的站点信息",stationInfo.toString());
                    LiveEventBus.get("station_info").post(stationInfo);
                    notifyDataSetChanged();

                }
            });
            if (mCurrentPosition ==position){
                stationAdapter.setmCurrentPosition(mCurrentItem);
            }
        }
    }

    @Override
    public int getItemCount() {
        return dataList!=null?dataList.size():0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title;
        RecyclerView rv_station;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            rv_station =itemView.findViewById(R.id.rv_station);
            GridLayoutManager layoutManager = new GridLayoutManager(mContext, 4);//第二个参数为网格的列数
            rv_station.setLayoutManager(layoutManager);
        }
    }


}
