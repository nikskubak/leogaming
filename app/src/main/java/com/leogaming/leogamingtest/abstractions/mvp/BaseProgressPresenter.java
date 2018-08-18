package com.leogaming.leogamingtest.abstractions.mvp;

import com.arellomobile.mvp.MvpPresenter;
import com.leogaming.leogamingtest.abstractions.mvp.views.ProgressView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

@SuppressWarnings({"WeakerAccess", "unused"})
public class BaseProgressPresenter<T extends ProgressView> extends MvpPresenter<T> {

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @SuppressWarnings("Convert2Lambda")
    public Runnable showProgress() {
        return new Runnable() {
            @Override
            public void run() {
                getViewState().showProgress();
            }
        };
    }

    @SuppressWarnings("Convert2Lambda")
    public Runnable hideProgress() {
        return new Runnable() {
            @Override
            public void run() {
                getViewState().hideProgress();
            }
        };
    }

    public void addDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    public void disposeAll() {
        compositeDisposable.clear();
    }

    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }
}
