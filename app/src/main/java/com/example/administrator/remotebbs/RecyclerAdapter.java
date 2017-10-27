package com.example.administrator.remotebbs;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.remotebbs.model.Data;

/**
 * Created by Administrator on 2017-10-26.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    Data[] datas = new Data[0];

    public void setData(Data[] newData) {
        if (datas.length == 0) {
            this.datas = newData;
            notifyDataSetChanged();
        } else {
            Data[] temp = new Data[datas.length + newData.length];
            // 1. 이놈을 2.여기서부터 3.temp배열에 4.temp의 0부터 5.datas의 길이만큼 복사한다
            // 먼저 기존의 배열 복사
            System.arraycopy(datas, 0, temp, 0, datas.length);
            // 나중에 들어온 배열 복사
            System.arraycopy(newData, 0, temp, datas.length, newData.length);
            datas = temp;
            notifyDataSetChanged();
        }
        Log.e("크기", datas.length+"");
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Data data = datas[position];
        holder.no.setText(data.get_id());
        holder.title.setText(data.getTitle());
        holder.date.setText(data.getDate());
    }

    @Override
    public int getItemCount() {
        return datas.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView date, title, no;

        public ViewHolder(View itemView) {
            super(itemView);
            date = (TextView) itemView.findViewById(R.id.txtDate);
            title = (TextView) itemView.findViewById(R.id.txtTitle);
            no = (TextView) itemView.findViewById(R.id.txtNo);
        }
    }
}
