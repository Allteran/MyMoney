package ru.tele2.mur51.terranova.mymoney.entities;

import io.realm.RealmObject;

/**
 * Created by Allteran on 12.01.2018.
 */

public class PointOfSales extends RealmObject {
    private int id;
    private String region;
    private String city;
    private String street;
    private int buildingNumber;

    public PointOfSales() {
    }

    public PointOfSales(int id, String region, String city, String street, int buildingNumber) {
        this.id = id;
        this.region = region;
        this.city = city;
        this.street = street;
        this.buildingNumber = buildingNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(int buildingNumber) {
        this.buildingNumber = buildingNumber;
    }
}
