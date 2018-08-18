package com.leogaming.leogamingtest.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.leogaming.leogamingtest.R;
import com.leogaming.leogamingtest.abstractions.mvp.BasePresenter;
import com.leogaming.leogamingtest.abstractions.mvp.activities.BaseMVPActivity;
import com.leogaming.leogamingtest.ui.views.DialogFragment;
import com.leogaming.leogamingtest.ui.wallet.WalletFragment;

import butterknife.BindView;

public class MainActivity extends BaseMVPActivity implements BottomNavigationView.OnNavigationItemSelectedListener,
        MainView {

    @InjectPresenter
    MainPresenter mMainPresenter;

    @BindView(R.id.bottom_navigation_view)
    AHBottomNavigation navigationView;

    @BindView(R.id.container)
    FrameLayout containerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        initListeners();
        mMainPresenter.initContext(this);
        mMainPresenter.openWallet();
    }

    private void initListeners() {
        navigationView.setOnTabSelectedListener((position, wasSelected) -> {
            switch (position) {
                case BottomNavigationItem.WALLET:
                    if (!wasSelected)
                        mMainPresenter.openWallet();
                    break;
                case BottomNavigationItem.BONUSES:
                    if (!wasSelected)
                        mMainPresenter.openBonuses();
                    break;
                case BottomNavigationItem.HISTORY:
                    if (!wasSelected)
                        mMainPresenter.openHistory();
                    break;
                case BottomNavigationItem.PROFILE:
                    if (!wasSelected)
                        mMainPresenter.openProfile();
                    break;
            }
            return true;
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public BasePresenter setBasePresenter() {
        return null;
    }

    private void initViews() {
        initBottomNavigationView();
    }

    private void initBottomNavigationView() {
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.wallet, R.drawable.wallet, R.color.bottom_navigation);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.bonuses, R.drawable.gift, R.color.bottom_navigation);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.history, R.drawable.history, R.color.bottom_navigation);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.profile, R.drawable.account, R.color.bottom_navigation);
        navigationView.addItem(item1);
        navigationView.addItem(item2);
        navigationView.addItem(item3);
        navigationView.addItem(item4);
        navigationView.setDefaultBackgroundColor(getResources().getColor(R.color.bottom_navigation));
        navigationView.setBehaviorTranslationEnabled(false);
        navigationView.setForceTint(true);
        navigationView.setTranslucentNavigationEnabled(true);
        navigationView.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        navigationView.setColored(true);
        navigationView.setCurrentItem(0);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void showWallet() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, WalletFragment.newInstance(), WalletFragment.TAG);
        transaction.commit();
    }

    @Override
    public void showInfoDialog(String title, String description) {
        FragmentManager fm = getSupportFragmentManager();
        DialogFragment dialogFragment = DialogFragment.newInstance(title, description);
        dialogFragment.show(fm, DialogFragment.TAG);
    }
}
