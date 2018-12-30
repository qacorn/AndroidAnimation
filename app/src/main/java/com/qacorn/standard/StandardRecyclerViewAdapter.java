package com.qacorn.standard;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *  @author qacorn
 *  created at 2016/4/27 16:51 
 */
public abstract class StandardRecyclerViewAdapter<T,VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    protected Context context;
    protected LayoutInflater layoutInflater;
    protected List<T> mDataList = new ArrayList<>();
    protected OnItemClickListener onItemClickListener;

    public StandardRecyclerViewAdapter(Context context, List<T> mDataList) {
        this.context = context;
        this.mDataList = mDataList;
        layoutInflater = LayoutInflater.from(context);
    }

    public void setDataList(List<T> mDataList) {
        this.mDataList = mDataList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public abstract void onBindViewHolder(VH holder, int position);


    @Override
    public int getItemCount() {
        return mDataList == null?0:mDataList.size();
    }


}
