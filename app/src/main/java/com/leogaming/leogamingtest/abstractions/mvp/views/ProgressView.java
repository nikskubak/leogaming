package com.leogaming.leogamingtest.abstractions.mvp.views;

import com.arellomobile.mvp.MvpView;

public interface ProgressView extends MvpView {
    void showProgress();
    void hideProgress();
}
