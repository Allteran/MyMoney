package ru.tele2.mur51.terranova.mymoney.entities;

import io.realm.RealmObject;

/**
 * Created by Allteran on 11.02.2018.
 */

public class Salary extends RealmObject {
    private String seller;
    private int pointOfSales;
    private String reportDate;

    private int ratePay;
    private int simPay;
    private int kpiPay;
    private int servicesPay;
    private int equipPay;
    private int bonusPay;


    public Salary() {
    }

    public Salary(String seller, int pointOfSales, String reportDate) {
        this.seller = seller;
        this.pointOfSales = pointOfSales;
        this.reportDate = reportDate;
    }

    public int getWholeSalary() {
        return ratePay + simPay + kpiPay + servicesPay + equipPay + bonusPay;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public int getPointOfSales() {
        return pointOfSales;
    }

    public void setPointOfSales(int pointOfSales) {
        this.pointOfSales = pointOfSales;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public int getRatePay() {
        return ratePay;
    }

    public void setRatePay(int ratePay) {
        this.ratePay = ratePay;
    }

    public int getSimPay() {
        return simPay;
    }

    public void setSimPay(int simPay) {
        this.simPay = simPay;
    }

    public int getKpiPay() {
        return kpiPay;
    }

    public void setKpiPay(int kpiPay) {
        this.kpiPay = kpiPay;
    }

    public int getServicesPay() {
        return servicesPay;
    }

    public void setServicesPay(int servicesPay) {
        this.servicesPay = servicesPay;
    }

    public int getEquipPay() {
        return equipPay;
    }

    public void setEquipPay(int equipPay) {
        this.equipPay = equipPay;
    }

    public int getBonusPay() {
        return bonusPay;
    }

    public void setBonusPay(int bonusPay) {
        this.bonusPay = bonusPay;
    }
}
