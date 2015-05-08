package com.money.warriorofjustice;

import android.app.Application;

import com.money.warriorofjustice.model.Message;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseCrashReporting;
import com.parse.ParseUser;

public class WarriorOfJusticeApp extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize Crash Reporting.
        ParseCrashReporting.enable(this);

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        // Add your initialization code here
        Parse.initialize(this, "MMHhI8itGS3mdzc6P0iKILdVz4N8qJv9mt39ys8F", "jtcVWooKlkOsSVXutka6dckBQ0CcT9EODpavn8if");


        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        // Optionally enable public read access.
        // defaultACL.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);
    }



}
