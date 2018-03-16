package ru.tele2.mur51.terranova.mymoney.entities;

/**
 * Created by Allteran on 03.03.2018.
 */

public class WorkDay {
    private int posId;
    private String day;
    private String date;
    private String seller;
    private String currentMonth;

    public WorkDay() {
    }

    public WorkDay(int posId, String day, String date, String seller, String currentMonth) {
        this.posId = posId;
        this.day = day;
        this.date = date;
        this.seller = seller;
        this.currentMonth = currentMonth;
    }

    public String getCurrentMonth() {
        return currentMonth;
    }

    public void setCurrentMonth(String currentMonth) {
        this.currentMonth = currentMonth;
    }

    public int getPosId() {
        return posId;
    }

    public void setPosId(int posId) {
        this.posId = posId;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }
}
