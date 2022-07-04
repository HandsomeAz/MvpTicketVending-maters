package com.geo.mvpframe_maters.activity.StationActivity.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geo.mvpframe_maters.R;
import com.geo.mvpframe_maters.bean.CityInfo;

import java.util.ArrayList;
import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder>  {

    private Context mContext;
    private List<CityInfo> dataList =new ArrayList<>();
    private int mCurrentPosition =0;

    public CityAdapter(Context context){
        this.mContext =context;
    }

    public void setCityData(List<CityInfo> data){
        if (data ==null){
            return;
        }
        this.dataList =data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= View.inflate(mContext, R.layout.city_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (dataList !=null){
            if (mCurrentPosition ==position){
                holder.tv_city_name.setBackgroundResource(R.drawable.text_focus);
                holder.tv_city_name.setTextColor(mContext.getResources().getColor(R.color.teal_200));
            }else {
                holder.tv_city_name.setBackgroundResource(R.drawable.text_unfocus);
                holder.tv_city_name.setTextColor(mContext.getResources().getColor(R.color.black));
            }
            holder.tv_city_name.setText(dataList.get(position).getName());
            holder.tv_city_name.setOnClickListener(view -> {
                if (mCityListener !=null){
                    mCityListener.CityInfo(dataList.get(position).getId(),dataList.get(position).getName());
                }
                if (mCurrentPosition != position){
                    mCurrentPosition =position;
                    notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return dataList!=null?dataList.size():0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_city_name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_city_name = itemView.findViewById(R.id.tv_city_name);
        }
    }

    private CityListener mCityListener =null;

    public void setCityListener(CityListener mCityListener) {
        this.mCityListener = mCityListener;
    }

    public interface CityListener {
        void CityInfo(int cityId,String cityName );
    }
}
