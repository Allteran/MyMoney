package ru.tele2.mur51.terranova.mymoney.entities;

import io.realm.RealmObject;

/**
 * Created by Allteran on 24.02.2018.
 */

public class DealerBonus extends RealmObject {
    private String seller;
    private int paidMonth;
    private int paidYear;
    private int amount;
    private boolean isPaid;

    public DealerBonus() {

    }

    public DealerBonus(String seller, int paidMonth, int paidYear, int amount, boolean isPaid) {
        this.seller = seller;
        this.paidMonth = paidMonth;
        this.paidYear = paidYear;
        this.amount = amount;
        this.isPaid = isPaid;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public int getPaidMonth() {
        return paidMonth;
    }

    public void setPaidMonth(int paidMonth) {
        this.paidMonth = paidMonth;
    }

    public int getPaidYear() {
        return paidYear;
    }

    public void setPaidYear(int paidYear) {
        this.paidYear = paidYear;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public String dateParcer(int month) {
        switch (month) {
            case 1:
                return "Январь";
            case 2:
                return "Февраль";
            case 3:
                return "Март";
            case 4:
                return "Апрель";
            case 5:
                return "Май";
            case 6:
                return "Июнь";
            case 7:
                return "Июль";
            case 8:
                return "Август";
            case 9:
                return "Сентябрь";
            case 10:
                return "Октябрь";
            case 11:
                return "Ноябрь";
            case 12:
                return "Декабрь";
        }
        return "Hmmm";
    }
}
