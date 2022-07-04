package com.geo.mvpframe_maters.activity.ShiftActivity.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.geo.mvpframe_maters.R;

import com.geo.mvpframe_maters.bean.PassStationBean;
import com.geo.mvpframe_maters.bean.RecordBean;


import java.util.ArrayList;
import java.util.List;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.ViewHolder> {
    private Context mContext;
    private String businessType ="商务车";
    private List<RecordBean> dataList =new ArrayList<>();

    public RecordAdapter(Context context){
        this.mContext =context;
    }

    public void setRecordData(List<RecordBean> data){
        if (data ==null){
            return;
        }
        this.dataList =data;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RecordAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= View.inflate(mContext, R.layout.item_record_layout,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordAdapter.ViewHolder holder, int position) {
        if (dataList !=null){
            holder.tv_get_onStationName.setText(dataList.get(position).getGetonStationName());
            holder.tv_takeoffStationName.setText(dataList.get(position).getTakeoffStationName());
            holder.tv_departure_time.setText(dataList.get(position).getGetonTime());
            String businessPropertyName = dataList.get(position).getBusinessPropertyName();
            if (businessPropertyName !=null){
                businessType =businessPropertyName;
            }
            if (businessType.equals("商务车")){
                holder.iv_bus_type.setImageResource(R.drawable.icon_car_logo);
            }else {
                holder.iv_bus_type.setImageResource(R.drawable.icon_bus_logo);
            }
            //票价
            holder.tv_person.setText("￥"+dataList.get(position).getPrice());
            holder.tv_price.setText("￥"+dataList.get(position).getPrice());
            holder.tv_childPrice.setText("￥"+dataList.get(position).getChildPrice());

            List<PassStationBean> stations = dataList.get(position).getStations();
            String stationContents ="";
            if (stations !=null &&stations.size()>0){
                for (PassStationBean bean :
                        stations) {
                    stationContents+=bean.getStationName()+"  ";
                }
            }

            //余票
            holder.tv_ticket_num.setText("剩"+dataList.get(position).getTicketNum()+"张");
            holder.tv_content.setText("路线:"+dataList.get(position).getRouteName()+"\r\r\r"
                    +"车型:"+businessType+"\r\r\r"
                    +"班次:"+dataList.get(position).getParkingSpaceCode()+"\r\r\r"
            +" 直达 "+stationContents);
            holder.rl_shift.setOnClickListener(view -> {
                if (mClickAdapterListener != null){
                    mClickAdapterListener.onClickRecordBean(dataList.get(position));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return dataList!=null?dataList.size():0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_departure_time,tv_person,tv_childPrice,tv_price,tv_ticket_num,tv_content;
        TextView tv_get_onStationName,tv_takeoffStationName;
        ImageView iv_bus_type;
        RelativeLayout rl_shift;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_departure_time = itemView.findViewById(R.id.tv_departure_time);
            tv_get_onStationName = itemView.findViewById(R.id.tv_get_onStationName);
            tv_takeoffStationName = itemView.findViewById(R.id.tv_takeoffStationName);
            tv_person = itemView.findViewById(R.id.tv_person);
            tv_childPrice = itemView.findViewById(R.id.tv_childPrice);
            tv_price = itemView.findViewById(R.id.tv_price);
            tv_ticket_num = itemView.findViewById(R.id.tv_ticket_num);
            tv_content = itemView.findViewById(R.id.tv_content);
            iv_bus_type = itemView.findViewById(R.id.iv_bus_type);
            rl_shift = itemView.findViewById(R.id.rl_shift);



        }
    }

    private onClickAdapterListener mClickAdapterListener =null;

    public void setClickAdapterListener(onClickAdapterListener mClickAdapterListener) {
        this.mClickAdapterListener = mClickAdapterListener;
    }

    public interface onClickAdapterListener{
        void onClickRecordBean(RecordBean recordBean);
    }
}
