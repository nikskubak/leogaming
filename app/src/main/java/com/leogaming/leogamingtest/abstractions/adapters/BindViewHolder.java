package com.leogaming.leogamingtest.abstractions.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

public abstract class BindViewHolder extends RecyclerView.ViewHolder{
    public BindViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
