package com.example.jasperxiao.slidingconflicttest;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jasperxiao on 2018/7/12
 */
public class MInnerRvAdapter extends RecyclerView.Adapter {
    private List<Element> mElementList;

    public MInnerRvAdapter(List<Element> mElementList) {
        this.mElementList = mElementList;
    }

//    public void updateDatas(List<Element> elementList) {
//        mElementList = elementList;
//        notifyDataSetChanged();
//    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.img.setImageResource(R.drawable.img_inner);
        if (mElementList != null) {
            viewHolder.content.setText(mElementList.get(position).getContent());
        } else {
            viewHolder.content.setText("Inner RecyclerView");
        }
    }

    @Override
    public int getItemCount() {
        if (mElementList != null) {
            return mElementList.size();
        } else {
            return 0;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            content = itemView.findViewById(R.id.content);
        }

        ImageView img;
        TextView content;
    }
}
