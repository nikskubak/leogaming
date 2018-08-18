package com.leogaming.leogamingtest.abstractions.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.leogaming.leogamingtest.abstractions.utils.RxBus;

import java.util.List;

import io.reactivex.Observable;

public abstract class AbstractRecyclerViewAdapter<T> extends RecyclerView.Adapter<BindViewHolder> {

    public List<T> data;

    private RxBus<T> itemClickBus = new RxBus<>();

    public AbstractRecyclerViewAdapter(List<T> data) {
        this.data = data;
    }

    @Override
    public BindViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return createHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(BindViewHolder holder, int position) {
        bindHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public Observable<T> getItemClickBusObs() {
        return itemClickBus.toObservable();
    }

    public RxBus<T> getItemClickBus() {
        return itemClickBus;
    }

    public void setItemClickBus(RxBus<T> itemClickBus) {
        this.itemClickBus = itemClickBus;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    protected abstract BindViewHolder createHolder(ViewGroup parent, int viewType);

    protected abstract void bindHolder(BindViewHolder holder, int position);

}
