package com.leogaming.leogamingtest.ui.wallet;

import com.arellomobile.mvp.MvpView;
import com.leogaming.leogamingtest.models.Invoice;

import java.util.List;

public interface WalletView extends MvpView {

    void showProfileData(String phone, String balanceBills, String balanceCoins);

    void showInvoices(List<Invoice> invoices);

    void showFavorites(List<Invoice> invoices);

    void showLast(List<Invoice> invoices);

    void showInfoDialog(String title, String description);
}
