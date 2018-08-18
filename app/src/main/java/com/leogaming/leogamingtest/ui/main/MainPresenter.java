package com.leogaming.leogamingtest.ui.main;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.leogaming.leogamingtest.R;
import com.leogaming.leogamingtest.abstractions.mvp.BasePresenter;

@InjectViewState
public class MainPresenter extends BasePresenter<MainView> {

    private Context context;

    public void initContext(Context context){
        this.context = context;
    }

    public void openWallet() {
        getViewState().showWallet();
    }

    public void openBonuses() {
        getViewState().showInfoDialog(context.getString(R.string.bonuses), context.getString(R.string.dev_description));
    }

    public void openHistory() {
        getViewState().showInfoDialog(context.getString(R.string.history), context.getString(R.string.dev_description));
    }

    public void openProfile() {
        getViewState().showInfoDialog(context.getString(R.string.profile), context.getString(R.string.dev_description));
    }
}
