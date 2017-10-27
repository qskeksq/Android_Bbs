package com.example.administrator.remotebbs;

import android.support.v7.widget.RecyclerView;
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

    public void setData(Data[] datas){
        this.datas = datas;
        notifyDataSetChanged();
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
