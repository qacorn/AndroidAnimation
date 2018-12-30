package com.qacorn.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qacorn.R;
import com.qacorn.adapter.viewholder.AnimationCategoryViewHolder;
import com.qacorn.standard.StandardRecyclerViewAdapter;

import java.util.List;

public class AnimationCategoryAdapter extends StandardRecyclerViewAdapter<String,AnimationCategoryViewHolder> {

    private Context context;
    private List<String> animationCategory;
    private LayoutInflater layoutInflater;

    public AnimationCategoryAdapter(Context context, List<String> mDataList) {
        super(context, mDataList);
        this.context = context;
        this.animationCategory = mDataList;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public AnimationCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new AnimationCategoryViewHolder(layoutInflater.inflate(R.layout.item_animation_category,viewGroup,false));
    }


    @Override
    public void onBindViewHolder(AnimationCategoryViewHolder holder, final int position) {
        holder.animation_category_name.setText(animationCategory.get(position));
        holder.animation_category_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClickListener(v,position);
            }
        });
    }
}
