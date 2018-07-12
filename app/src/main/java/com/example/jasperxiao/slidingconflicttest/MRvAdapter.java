package com.example.jasperxiao.slidingconflicttest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;

/**
 * Created by jasperxiao on 2018/7/11
 */
public class MRvAdapter extends RecyclerView.Adapter {
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_LIST = 1;

    private ElementInfo mElementInfo;

    private boolean mHasTasted;

    private Context mContext;

    /**
     * LayoutManager不能重复使用，但是Adapter可以
     */
    private LinearLayoutManager mLinearLayoutManager;
//    private LinkedList<MInnerRvAdapterWrapper> mInnerRvAdapterQueue; // 使用队列来存储复用的Recyclerview对应的Adapter，以便更新数据
    private MInnerRvAdapter adapter;


    public MRvAdapter(Context context, ElementInfo elementInfo) {
        this.mContext = context;
        this.mElementInfo = elementInfo;
        mHasTasted = false;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        if (viewType == TYPE_ITEM) {
            viewHolder = getViewForItem(parent);
        } else {
            viewHolder = getViewForList(parent);
        }
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position % 5 != 0) {
            final ViewHolderItem viewHolder = (ViewHolderItem) holder;
            viewHolder.img.setImageResource(randomForImg());
            viewHolder.content.setText(mElementInfo.getElementList().get(position - (position / 5) - 1).getContent());
        } else {
          //  updateAppropriateAdapter(position / 5);
            /** adapter的存储意义不是很大，因此可以考虑每次bindView时都新建adapter，然后setAdapter更新数据 */
            final ViewHolderList viewHolder = (ViewHolderList) holder;
            adapter = new MInnerRvAdapter(mElementInfo.getElementListList().get(position / 5));
            viewHolder.recyclerViewInner.setAdapter(adapter);
        }
    }

    @Override
    public int getItemCount() {
        int count = 0;
        if (mElementInfo != null) {
            count = mElementInfo.getElementList().size() + mElementInfo.getElementListList().size();
        }
        return count;
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 5 != 0) {
            return TYPE_ITEM;
        } else {
            return TYPE_LIST;
        }
    }

    private int randomForImg() {
        if (mHasTasted) {
            mHasTasted = false;
            return getResourceID("on");
        } else {
            mHasTasted = true;
            return getResourceID("off");
        }
    }

    private int getResourceID(String imageName) {
        int resId = mContext.getResources().getIdentifier(imageName, "drawable", mContext.getPackageName());
        return resId;
    }

    private RecyclerView.ViewHolder getViewForItem(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, null);
        return new ViewHolderItem(view);
    }

    private RecyclerView.ViewHolder getViewForList(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list, null);
        ViewHolderList viewHolder = new ViewHolderList(view);
        mLinearLayoutManager = new LinearLayoutManager(mContext);
        viewHolder.recyclerViewInner.setLayoutManager(mLinearLayoutManager);
        /** 这里先设置layoutManager，而不设置adapter */
        return viewHolder;
    }

    // 修改adapter数据源，不重新setadapter
//    private void updateAppropriateAdapter(int position) {
//        // 目前仅仅模拟了下拉时Recycler缓存池的复用情况
//        MInnerRvAdapterWrapper innerRvAdapterWrapper = mAllViewHolderListCreated ? mInnerRvAdapterQueue.poll() : mInnerRvAdapterQueue.pollLast();
//        if (innerRvAdapterWrapper != null) {
//            if (!innerRvAdapterWrapper.isUsed) {
//                innerRvAdapterWrapper.setUsed(true);
//                innerRvAdapterWrapper.innerRvAdapter.updateDatas(mElementInfo.getElementListList().get(position));
//                mInnerRvAdapterQueue.offer(innerRvAdapterWrapper);
//            } else { // isUsed = true
//                if(!mAllViewHolderListCreated){
//                    mInnerRvAdapterQueue.offer(innerRvAdapterWrapper);
//                    MInnerRvAdapterWrapper adapterWrapper = mInnerRvAdapterQueue.poll();
//                    if(adapterWrapper != null){
//                        adapterWrapper.innerRvAdapter.updateDatas(mElementInfo.getElementListList().get(position));
//                        mInnerRvAdapterQueue.offer(adapterWrapper);
//                    }
//                    mAllViewHolderListCreated = true;
//                }
//                else{ // mAllViewHolderListCreated = true
//                    innerRvAdapterWrapper.innerRvAdapter.updateDatas(mElementInfo.getElementListList().get(position));
//                    mInnerRvAdapterQueue.offer(innerRvAdapterWrapper);
//                }
//            }
//        }
//    }

    class ViewHolderItem extends RecyclerView.ViewHolder {
        public ViewHolderItem(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            content = itemView.findViewById(R.id.content);
        }

        ImageView img;
        TextView content;
    }

    class ViewHolderList extends RecyclerView.ViewHolder {
        public ViewHolderList(View itemView) {
            super(itemView);
            recyclerViewInner = itemView.findViewById(R.id.recycler_view_inner);
        }

        RecyclerView recyclerViewInner;
    }

//    class MInnerRvAdapterWrapper {
//        MInnerRvAdapter innerRvAdapter;
//        boolean isUsed;
//
//        public MInnerRvAdapterWrapper(MInnerRvAdapter innerRvAdapter, boolean isUsed) {
//            this.innerRvAdapter = innerRvAdapter;
//            this.isUsed = isUsed;
//        }
//
//        public void setUsed(boolean isUsed) {
//            this.isUsed = isUsed;
//        }
//    }

}
