package ru.tele2.mur51.terranova.mymoney.entities;

import io.realm.RealmObject;

/**
 * Created by Allteran on 10/23/2017.
 */

public class Employee extends RealmObject {
    private String login;
    private String firstName;
    private String secondName;
    private int dealerId;

    public Employee() {
    }

    public Employee(String login, String firstName, String secondName) {
        this.login = login;
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public int getDealerId() {
        return dealerId;
    }

    public void setDealerId(int dealerId) {
        this.dealerId = dealerId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

}
