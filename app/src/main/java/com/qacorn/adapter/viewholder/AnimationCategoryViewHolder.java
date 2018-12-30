package com.qacorn.adapter.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.qacorn.R;

public class AnimationCategoryViewHolder extends RecyclerView.ViewHolder {

    public TextView animation_category_name;
    public CardView animation_category_root;

    public AnimationCategoryViewHolder(@NonNull View itemView) {
        super(itemView);
        animation_category_root = itemView.findViewById(R.id.animation_category_root);
        animation_category_name = itemView.findViewById(R.id.animation_category_name);
    }
}
