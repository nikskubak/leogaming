package com.leogaming.leogamingtest.ui.wallet;

import android.content.Context;
import android.os.Build;
import android.telephony.PhoneNumberUtils;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.google.gson.Gson;
import com.leogaming.leogamingtest.R;
import com.leogaming.leogamingtest.models.Data;
import com.leogaming.leogamingtest.models.Invoice;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

@InjectViewState
public class WalletPresenter extends MvpPresenter<WalletView> {

    private static final String CHARSET_NAME = "UTF-8";
    private static final String DEFAULT_CURRENCY_SYMBOL = "$ ";
    private static final char GROUPING_SEPARATOR = ' ';
    private Context context;
    private Data data;

    public void retrieveData(Context context) {
        this.context = context;
        initDataFromJson(loadJSONFromAsset(context));
        showProfileData();
    }

    private void showProfileData() {
        String phone = initAccountPhone(getData().getAccount());
        String balanceBills = initBillsBalance(getData().getBalance());
        String balanceCoins = initCoinsBalance(getData().getBalance());
        getViewState().showProfileData(phone, balanceBills, balanceCoins);
        getViewState().showInvoices(getData().getInvoices());
        getViewState().showLast(getData().getLast());
        showFavorites();
    }

    private void showFavorites() {
        for (Invoice invoice : getData().getFavorites()) {
            invoice.setFavorite(true);
        }
        getViewState().showFavorites(getData().getFavorites());
    }

    private String initCoinsBalance(Double balance) {
        int fractionalPart = (int) ((balance % 1) * 100);
        return "." + fractionalPart;
    }

    private String initBillsBalance(Double balance) {
        double fractionalPart = balance % 1;
        int integralPart = (int) (balance - fractionalPart);
        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
        symbols.setGroupingSeparator(GROUPING_SEPARATOR);
        formatter.setDecimalFormatSymbols(symbols);
        return DEFAULT_CURRENCY_SYMBOL + formatter.format(integralPart);
    }

    private String initAccountPhone(String account) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return PhoneNumberUtils.formatNumber(account, Locale.getDefault().getCountry());
        } else {
            return PhoneNumberUtils.formatNumber(account);
        }
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public String loadJSONFromAsset(Context context) {
        String json;
        try {
            InputStream is = context.getAssets().open("dashboard.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, CHARSET_NAME);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public void initDataFromJson(String json) {
        Gson gson = new Gson();
        setData(gson.fromJson(json, Data.class));
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public void openInvoiceInfo(Invoice invoice) {
        getViewState().showInfoDialog(invoice.getName(), invoice.getAccount());
    }

    public void transferMoney() {
        getViewState().showInfoDialog(context.getString(R.string.transfer_money), context.getString(R.string.transfer_money_description));
    }

    public void payService() {
        getViewState().showInfoDialog(context.getString(R.string.pay_service), context.getString(R.string.pay_service_description));
    }

    public void bill() {
        getViewState().showInfoDialog(context.getString(R.string.bill), context.getString(R.string.bill_description));
    }

    public void topUp() {
        getViewState().showInfoDialog(context.getString(R.string.top_up), context.getString(R.string.top_up_description));
    }

    public void withdraw() {
        getViewState().showInfoDialog(context.getString(R.string.withdraw), context.getString(R.string.withdraw_description));
    }

    public void openBalance() {
        getViewState().showInfoDialog(context.getString(R.string.your_balance), getData().getBalance().toString());
    }
}
