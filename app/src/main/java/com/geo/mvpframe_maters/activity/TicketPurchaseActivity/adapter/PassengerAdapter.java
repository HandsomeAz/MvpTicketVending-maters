package com.geo.mvpframe_maters.activity.TicketPurchaseActivity.adapter;

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
import com.geo.mvpframe_maters.bean.PassengerInfo;
import com.geo.mvpframe_maters.bean.RecordBean;

import java.util.ArrayList;
import java.util.List;

public class PassengerAdapter extends RecyclerView.Adapter<PassengerAdapter.ViewHolder> {
    private Context mContext;
    private String businessType ="商务车";
    private List<PassengerInfo> dataList =new ArrayList<>();

    public PassengerAdapter(Context context){
        this.mContext =context;
    }

    public void setPassengerInfo(List<PassengerInfo> data){
        if (data ==null){
            return;
        }
        this.dataList =data;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public PassengerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= View.inflate(mContext, R.layout.item_passenger_layout,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PassengerAdapter.ViewHolder holder, int position) {
        if (dataList !=null){
            if (dataList.get(position).getSaleObjId() ==1){
                holder.iv_ticket_type.setImageResource(R.drawable.icon_adult);
                holder.tv_ticket_type.setText("成人票");
            }else if (dataList.get(position).getSaleObjId() ==2){
                holder.iv_ticket_type.setImageResource(R.drawable.icon_child);
                holder.tv_ticket_type.setText("儿童票");
            }else {
                holder.iv_ticket_type.setImageResource(R.drawable.icon_baby);
                holder.tv_ticket_type.setText("免童票");
            }

            holder.tv_id_card.setText("身份证号码:"+dataList.get(position).getIdCard());
            holder.tv_name.setText("姓名:"+dataList.get(position).getName());
            holder.tv_phone.setText("联系电话:"+dataList.get(position).getPhone());
            holder.tv_address.setText("目的地址:"+dataList.get(position).getAddress());
            holder.iv_delete.setOnClickListener(view -> {
                if (mClickAdapterListener !=null){
                    mClickAdapterListener.onDelete(dataList.get(position));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return dataList!=null?dataList.size():0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_ticket_type,iv_delete;
        TextView tv_id_card,tv_name,tv_phone,tv_address,tv_ticket_type;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_ticket_type = itemView.findViewById(R.id.iv_ticket_type);
            tv_id_card = itemView.findViewById(R.id.tv_id_card);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_phone = itemView.findViewById(R.id.tv_phone);
            tv_address = itemView.findViewById(R.id.tv_address);
            iv_delete = itemView.findViewById(R.id.iv_delete);
            tv_ticket_type = itemView.findViewById(R.id.tv_ticket_type);




        }
    }

    private onClickAdapterListener mClickAdapterListener =null;

    public void setClickAdapterListener(onClickAdapterListener mClickAdapterListener) {
        this.mClickAdapterListener = mClickAdapterListener;
    }

    public interface onClickAdapterListener{
        void onDelete(PassengerInfo passengerInfo);
    }
}
