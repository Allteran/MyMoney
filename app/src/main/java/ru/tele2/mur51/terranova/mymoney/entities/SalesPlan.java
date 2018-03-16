package ru.tele2.mur51.terranova.mymoney.entities;

import io.realm.RealmObject;

/**
 * Created by Allteran on 28.02.2018.
 */

public class SalesPlan extends RealmObject{
    private int posId;
    private String date;

    // Tele2 KPI
    private int giPlan;
    private int aoPlan;
    private int mnpPlan;
    private int upSalePlan;
    private int insurancePlan;
    private int yaddPlan;

    private int giFact;
    private int aoFact;
    private int mnpFact;
    private int upSaleFact;
    private int insuranceFact;
    private int yaddFact;

    //Dealer's KPI
    private int servicesPlan;
    private int accessPlan;
    private int gsmPlan;

    private int servicesFact;
    private int accessFact;
    private int gsmFact;

    public SalesPlan(int giPlan, int aoPlan, int mnpPlan, int upSalePlan, int insurancePlan,
                     int yaddPlan, int servicesPlan, int accessPlan, int gsmPlan) {
        this.giPlan = giPlan;
        this.aoPlan = aoPlan;
        this.mnpPlan = mnpPlan;
        this.upSalePlan = upSalePlan;
        this.insurancePlan = insurancePlan;
        this.yaddPlan = yaddPlan;
        this.servicesPlan = servicesPlan;
        this.accessPlan = accessPlan;
        this.gsmPlan = gsmPlan;
    }

    public SalesPlan() {
    }

    public int getPosId() {
        return posId;
    }

    public void setPosId(int posId) {
        this.posId = posId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getGiPlan() {
        return giPlan;
    }

    public void setGiPlan(int giPlan) {
        this.giPlan = giPlan;
    }

    public int getAoPlan() {
        return aoPlan;
    }

    public void setAoPlan(int aoPlan) {
        this.aoPlan = aoPlan;
    }

    public int getMnpPlan() {
        return mnpPlan;
    }

    public void setMnpPlan(int mnpPlan) {
        this.mnpPlan = mnpPlan;
    }

    public int getUpSalePlan() {
        return upSalePlan;
    }

    public void setUpSalePlan(int upSalePlan) {
        this.upSalePlan = upSalePlan;
    }

    public int getInsurancePlan() {
        return insurancePlan;
    }

    public void setInsurancePlan(int insurancePlan) {
        this.insurancePlan = insurancePlan;
    }

    public int getYaddPlan() {
        return yaddPlan;
    }

    public void setYaddPlan(int yaddPlan) {
        this.yaddPlan = yaddPlan;
    }

    public int getGiFact() {
        return giFact;
    }

    public void setGiFact(int giFact) {
        this.giFact = giFact;
    }

    public int getAoFact() {
        return aoFact;
    }

    public void setAoFact(int aoFact) {
        this.aoFact = aoFact;
    }

    public int getMnpFact() {
        return mnpFact;
    }

    public void setMnpFact(int mnpFact) {
        this.mnpFact = mnpFact;
    }

    public int getUpSaleFact() {
        return upSaleFact;
    }

    public void setUpSaleFact(int upSaleFact) {
        this.upSaleFact = upSaleFact;
    }

    public int getInsuranceFact() {
        return insuranceFact;
    }

    public void setInsuranceFact(int insuranceFact) {
        this.insuranceFact = insuranceFact;
    }

    public int getYaddFact() {
        return yaddFact;
    }

    public void setYaddFact(int yaddFact) {
        this.yaddFact = yaddFact;
    }

    public int getServicesPlan() {
        return servicesPlan;
    }

    public void setServicesPlan(int servicesPlan) {
        this.servicesPlan = servicesPlan;
    }

    public int getAccessPlan() {
        return accessPlan;
    }

    public void setAccessPlan(int accessPlan) {
        this.accessPlan = accessPlan;
    }

    public int getGsmPlan() {
        return gsmPlan;
    }

    public void setGsmPlan(int gsmPlan) {
        this.gsmPlan = gsmPlan;
    }

    public int getServicesFact() {
        return servicesFact;
    }

    public void setServicesFact(int servicesFact) {
        this.servicesFact = servicesFact;
    }

    public int getAccessFact() {
        return accessFact;
    }

    public void setAccessFact(int accessFact) {
        this.accessFact = accessFact;
    }

    public int getGsmFact() {
        return gsmFact;
    }

    public void setGsmFact(int gsmFact) {
        this.gsmFact = gsmFact;
    }
}
