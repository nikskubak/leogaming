package com.leogaming.leogamingtest.abstractions.mvp.activities;

import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.leogaming.leogamingtest.abstractions.mvp.BasePresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

abstract public class BaseMVPActivity extends MvpAppCompatActivity {

    private Unbinder unbinder;
    private BasePresenter basePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        basePresenter = setBasePresenter();
        unbinder = ButterKnife.bind(this);
    }

    abstract public int getLayoutId();

    abstract public BasePresenter setBasePresenter();

    @Override
    protected void onStop() {
        super.onStop();
        if (basePresenter == null) {
            return;
        }
        basePresenter.disposeAll();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
