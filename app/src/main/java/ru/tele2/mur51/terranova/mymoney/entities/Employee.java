package ru.tele2.mur51.terranova.mymoney.entities;

import io.realm.RealmObject;

/**
 * Created by Allteran on 10/23/2017.
 */

public class Employee extends RealmObject {
    private String login;
    private String password;
    private String firstName;
    private String secondName;

    public Employee() {
    }

    public Employee(String login, String password, String firstName, String secondName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
