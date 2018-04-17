package ru.tele2.mur51.terranova.mymoney.entities;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Allteran on 12.01.2018.
 */

public class Dealer extends RealmObject {
    private String name;
    private int id;
    private RealmList<PointOfSales> posList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RealmList<PointOfSales> getPosList() {
        return posList;
    }

    public void setPosList(RealmList<PointOfSales> posList) {
        this.posList = posList;
    }
}
