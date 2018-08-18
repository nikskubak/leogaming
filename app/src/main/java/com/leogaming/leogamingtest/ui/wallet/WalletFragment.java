package com.leogaming.leogamingtest.ui.wallet;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.leogaming.leogamingtest.R;
import com.leogaming.leogamingtest.abstractions.adapters.AbstractRecyclerViewAdapter;
import com.leogaming.leogamingtest.abstractions.mvp.fragment.BaseMvpFragment;
import com.leogaming.leogamingtest.models.Invoice;
import com.leogaming.leogamingtest.ui.views.DialogFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.disposables.CompositeDisposable;

public class WalletFragment extends BaseMvpFragment implements WalletView {
    public static final String TAG = "WalletFragment";
    @InjectPresenter
    WalletPresenter mWalletPresenter;

    @BindView(R.id.search_view)
    SearchView searchView;

    @BindView(R.id.phone_view)
    TextView phoneView;

    @BindView(R.id.bills_balance_view)
    TextView balanceBillView;

    @BindView(R.id.coins_balance_view)
    TextView balanceCoinsView;

    @BindView(R.id.top_up_card_view)
    CardView topUpCardView;

    @BindView(R.id.withdraw_text)
    TextView withDrawTextView;

    @BindView(R.id.transfer_image_view)
    ImageView transferMoneyImageView;

    @BindView(R.id.pay_service_image_view)
    ImageView payServiceImageView;

    @BindView(R.id.bill_image_view)
    ImageView billImageView;

    @BindView(R.id.invoices_recycler_view)
    RecyclerView invoicesRecyclerView;

    @BindView(R.id.favorites_recycler_view)
    RecyclerView favoritesRecyclerView;

    @BindView(R.id.last_recycler_view)
    RecyclerView lastRecyclerView;

    private InvoicesRecyclerAdapter invoicesRecyclerAdapter;
    private InvoicesRecyclerAdapter favoritesRecyclerAdapter;
    private InvoicesRecyclerAdapter lastRecyclerAdapter;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public static WalletFragment newInstance() {
        WalletFragment fragment = new WalletFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_wallet;
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        initListeners();
        mWalletPresenter.retrieveData(getContext());
    }

    private void initListeners() {
        compositeDisposable.add(invoicesRecyclerAdapter.getItemClickBusObs()
                .subscribe(invoice -> mWalletPresenter.openInvoiceInfo(invoice), Throwable::printStackTrace));
        compositeDisposable.add(favoritesRecyclerAdapter.getItemClickBusObs()
                .subscribe(invoice -> mWalletPresenter.openInvoiceInfo(invoice), Throwable::printStackTrace));
        compositeDisposable.add(lastRecyclerAdapter.getItemClickBusObs()
                .subscribe(invoice -> mWalletPresenter.openInvoiceInfo(invoice), Throwable::printStackTrace));
        transferMoneyImageView.setOnClickListener(v -> mWalletPresenter.transferMoney());
        payServiceImageView.setOnClickListener(v -> mWalletPresenter.payService());
        billImageView.setOnClickListener(v -> mWalletPresenter.bill());
        topUpCardView.setOnClickListener(v -> mWalletPresenter.topUp());
        withDrawTextView.setOnClickListener(v -> mWalletPresenter.withdraw());
        balanceBillView.setOnClickListener(v -> mWalletPresenter.openBalance());
    }

    private void initViews() {
        EditText searchEditText = searchView.findViewById(R.id.search_src_text);
        if (searchEditText != null) {
            searchEditText.setGravity(Gravity.CENTER);
        }

        invoicesRecyclerAdapter = new InvoicesRecyclerAdapter(new ArrayList<>());
        initRecyclerView(invoicesRecyclerView, invoicesRecyclerAdapter);

        favoritesRecyclerAdapter = new InvoicesRecyclerAdapter(new ArrayList<>());
        initRecyclerView(favoritesRecyclerView, favoritesRecyclerAdapter);

        lastRecyclerAdapter = new InvoicesRecyclerAdapter(new ArrayList<>());
        initRecyclerView(lastRecyclerView, lastRecyclerAdapter);
    }

    void initRecyclerView(RecyclerView recyclerView,
                          AbstractRecyclerViewAdapter adapter) {
        DividerItemDecorator itemDecoration = new DividerItemDecorator(ContextCompat.getDrawable(getContext(),
                R.drawable.divider));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.addItemDecoration(itemDecoration);
    }


    @Override
    public void showProfileData(String phone, String balanceBills, String balanceCoins) {
        phoneView.setText(phone);
        balanceBillView.setText(balanceBills);
        balanceCoinsView.setText(balanceCoins);
    }

    @Override
    public void showInvoices(List<Invoice> invoices) {
        invoicesRecyclerAdapter.setData(invoices);
        invoicesRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void showFavorites(List<Invoice> invoices) {
        favoritesRecyclerAdapter.setData(invoices);
        favoritesRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLast(List<Invoice> invoices) {
        lastRecyclerAdapter.setData(invoices);
        lastRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void showInfoDialog(String title, String description) {
        FragmentManager fm = getFragmentManager();
        DialogFragment dialogFragment = DialogFragment.newInstance(title, description);
        dialogFragment.show(fm, DialogFragment.TAG);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        compositeDisposable.dispose();
    }
}
