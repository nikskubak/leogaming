package com.leogaming.leogamingtest.ui.main;

import com.arellomobile.mvp.MvpView;

public interface MainView extends MvpView {
    void showWallet();
    void showInfoDialog(String title, String description);
}
