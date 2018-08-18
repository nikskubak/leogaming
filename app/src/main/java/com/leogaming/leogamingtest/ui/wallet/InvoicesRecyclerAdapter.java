package com.leogaming.leogamingtest.ui.wallet;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.leogaming.leogamingtest.R;
import com.leogaming.leogamingtest.abstractions.adapters.AbstractRecyclerViewAdapter;
import com.leogaming.leogamingtest.abstractions.adapters.BindViewHolder;
import com.leogaming.leogamingtest.models.Invoice;

import java.util.List;

import butterknife.BindView;

public class InvoicesRecyclerAdapter extends AbstractRecyclerViewAdapter<Invoice> {

    public InvoicesRecyclerAdapter(List<Invoice> data) {
        super(data);
    }

    private Context context;

    @Override
    protected BindViewHolder createHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_invoice, parent, false);
        return new InvoiceViewHolder(view);
    }

    @Override
    protected void bindHolder(BindViewHolder holder, int position) {
        Invoice invoice = getData().get(position);
        InvoiceViewHolder invoiceViewHolder = (InvoiceViewHolder) holder;
        invoiceViewHolder.favoriteCheckBox.setChecked(invoice.isFavorite());
        initAccount(invoice, invoiceViewHolder);
    }

    private void initAccount(Invoice invoice, InvoiceViewHolder invoiceViewHolder) {
        if (!TextUtils.isEmpty(invoice.getName())) {
            invoiceViewHolder.accountInfo.setVisibility(View.VISIBLE);
            invoiceViewHolder.accountName.setText(invoice.getName());
            invoiceViewHolder.accountInfo.setText(invoice.getAccount());
        } else {
            invoiceViewHolder.accountInfo.setVisibility(View.GONE);
            invoiceViewHolder.accountName.setText(invoice.getAccount());
        }
    }

    public class InvoiceViewHolder extends BindViewHolder {
        @BindView(R.id.account_name)
        TextView accountName;

        @BindView(R.id.account_info)
        TextView accountInfo;

        @BindView(R.id.account_photo)
        ImageView accountPhoto;

        @BindView(R.id.favorite)
        CheckBox favoriteCheckBox;

        @BindView(R.id.item_view)
        RelativeLayout itemView;

        public InvoiceViewHolder(View view) {
            super(view);
            itemView.setOnClickListener(v -> getItemClickBus().send(getData().get(getAdapterPosition())));
        }
    }
}
