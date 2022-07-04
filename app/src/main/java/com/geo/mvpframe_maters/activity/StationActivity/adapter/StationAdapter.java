package com.geo.mvpframe_maters.activity.StationActivity.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geo.mvpframe_maters.R;
import com.geo.mvpframe_maters.bean.StationInfo;

import java.util.ArrayList;
import java.util.List;

public class StationAdapter extends RecyclerView.Adapter<StationAdapter.ViewHolder>  {

    private Context mContext;
    private List<StationInfo> dataList =new ArrayList<>();
    private int mCurrentPosition =-1;
    private int itemType =0;

    public StationAdapter(Context context){
        this.mContext =context;
    }

    public void setStationData(List<StationInfo> data,int type){
        if (data ==null){
            return;
        }
        this.dataList =data;
        mCurrentPosition =-1;
        this.itemType =type;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= View.inflate(mContext, R.layout.station_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (dataList !=null){
            if (itemType ==0){
                if (mCurrentPosition ==position){
                    holder.rl_station_name.setBackgroundResource(R.drawable.text_focus);
                    holder.tv_station_name.setTextColor(mContext.getResources().getColor(R.color.teal_200));
                }else {
                    holder.rl_station_name.setBackgroundResource(R.drawable.text_unfocus);
                    holder.tv_station_name.setTextColor(mContext.getResources().getColor(R.color.black));
                }
            }else {
                holder.rl_station_name.setBackgroundResource(R.drawable.text_focus);
                holder.tv_station_name.setTextColor(mContext.getResources().getColor(R.color.teal_200));
            }

            if (dataList.get(position).getHotDegree() >=100){
                holder.iv_host.setVisibility(View.VISIBLE);
            }else {
                holder.iv_host.setVisibility(View.GONE);
            }
            String name = dataList.get(position).getName();
            if (name !=null){

                if (name.length()>8){
                    holder.tv_station_name.setTextSize(14);
                    holder.tv_station_name.setSelected(true);
                }else if (name.length()>6){
                    holder.tv_station_name.setTextSize(15);
                    holder.tv_station_name.setSelected(true);
                }else {
                    holder.tv_station_name.setTextSize(16);
                }
                holder.tv_station_name.setText(dataList.get(position).getName());
                holder.rl_station_name.setOnClickListener(v -> {
                    if (mChooseListener !=null){
                        if (mCurrentPosition !=position){
                            mCurrentPosition =position;
                            notifyDataSetChanged();
                        }
                        mChooseListener.onClick(dataList.get(position),mCurrentPosition);
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return dataList!=null?dataList.size():0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout rl_station_name;
        TextView tv_station_name;
        ImageView iv_host;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rl_station_name = itemView.findViewById(R.id.rl_station_name);
            tv_station_name = itemView.findViewById(R.id.tv_station_name);
            iv_host = itemView.findViewById(R.id.iv_host);
        }
    }
    private ChooseListener mChooseListener =null;

    public void setChooseListener(ChooseListener listener) {
        this.mChooseListener = listener;
    }

    public interface ChooseListener {
        void onClick(StationInfo stationInfo,int position);
    }

    public void setmCurrentPosition(int mCurrentPosition) {
        this.mCurrentPosition = mCurrentPosition;
        notifyDataSetChanged();
    }
}
