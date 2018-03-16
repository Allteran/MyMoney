package ru.tele2.mur51.terranova.mymoney.applications;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by Администратор on 18.02.2018.
 */

public class MyMoneyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
