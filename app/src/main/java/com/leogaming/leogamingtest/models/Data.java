
package com.leogaming.leogamingtest.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName("account")
    @Expose
    private String account;
    @SerializedName("balance")
    @Expose
    private Double balance;
    @SerializedName("invoices")
    @Expose
    private List<Invoice> invoices = null;
    @SerializedName("favorites")
    @Expose
    private List<Invoice> favorites = null;
    @SerializedName("last")
    @Expose
    private List<Invoice> last = null;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    public List<Invoice> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Invoice> favorites) {
        this.favorites = favorites;
    }

    public List<Invoice> getLast() {
        return last;
    }

    public void setLast(List<Invoice> last) {
        this.last = last;
    }

}
