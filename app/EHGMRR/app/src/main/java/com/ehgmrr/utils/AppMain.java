package com.ehgmrr.utils;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import androidx.room.Room;

import com.ehgmrr.db.AppDatabase;

import java.util.Locale;

public class AppMain extends Application {

    public static AppMain mInstance;

    private static Context context;

    boolean verif = false;

    SharedPref sharedPref;

    public static String sDefSystemLanguage;

    public static final String TAG = AppMain.class.getSimpleName();

    AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();
        mInstance = this;
        sharedPref = new SharedPref(SharedPref.MAIN_FILENAME, this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    public static synchronized AppMain getInstance() {
        return mInstance;
    }

    public static Context getAppContext() {
        return mInstance.getApplicationContext();
    }


    public SharedPref getSharedPref() {
        if (sharedPref != null)
            return sharedPref;
        else
            return new SharedPref(SharedPref.MAIN_FILENAME, this);
    }


}
