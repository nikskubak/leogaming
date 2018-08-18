package com.leogaming.leogamingtest.abstractions.mvp;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BasePresenter<T extends MvpView> extends MvpPresenter<T> {

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private CompositeDisposable disposables = new CompositeDisposable();

    public void addDisposable(Disposable disposable){
        disposables.add(disposable);
    }

    public void disposeAll(){
        disposables.clear();
    }

    public CompositeDisposable getDisposables() {
        return disposables;
    }

}
