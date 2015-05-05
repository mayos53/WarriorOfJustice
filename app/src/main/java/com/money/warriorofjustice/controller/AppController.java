package com.money.warriorofjustice.controller;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.money.warriorofjustice.SplashActivity;
import com.money.warriorofjustice.WarriorOfJusticeApp;
import com.money.warriorofjustice.model.Message;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Yossef on 5/3/15.
 */
public class AppController
{

    private final static String SHARED_PREFERENCES = "SHARED_PREFERENCES";
    private final static String PREFERENCES_KEYWORDS = "PREFERENCES_KEYWORDS";
    private final static String PREFERENCES_SENDERS  = "PREFERENCES_SENDERS";



    private static AppController instance;
    private Context context;
    private WarriorOfJusticeApp app;
    private SharedPreferences prefs;
    private Gson gson;

    private String [] keywords;
    private Set senders;




    public static AppController getInstance(Context context){
        if(instance == null){
            instance = new AppController(context);
        }
        return instance;
    }

    private AppController(Context context){
        this.context = context;

        prefs = context.getSharedPreferences(SHARED_PREFERENCES, 0);
        gson = new GsonBuilder().create();
        initMemoryArrays();
    }

    private void initMemoryArrays(){
        loadKeywords();
        loadSenders();
    }

    private void loadKeywords(){
        keywords = (String[])getObject(PREFERENCES_KEYWORDS,String[].class);
    }

    private void loadSenders(){
        senders  = new HashSet<String>(Arrays.asList((String[])getObject(PREFERENCES_SENDERS,
                String[].class)));    }

    public boolean containsBlackListKeywords(String message){
        for(String keyword:keywords){
            if(message.contains(keyword)){
                return true;
            }
        }
        return false;
  }

    public boolean isInSendersBlackList(String sender){
        return senders.contains(sender);
    }




    public void saveKeywords(String [] keywords){
        putObject(PREFERENCES_KEYWORDS,keywords);
        loadKeywords();
    }

    public void saveSenders(String [] senders){
        putObject(PREFERENCES_SENDERS,senders);
        loadSenders();

    }

    //<editor-fold desc="SharedPrefs Utils">

    private void putObject(String key,Object value){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, gson.toJson(value));
        editor.commit();
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private Object getObject(String key,Class _class){
        String str = prefs.getString(key,null);
        if(str != null){
            return gson.fromJson(str, _class);
        }
        return null;
    }

    public void reportPotentialSpamMessage(Message message) {
    }

    public void receivedSpamMessage(Message message) {
    }

    public void setMessageRead(SplashActivity splashActivity, long id) {
    }

    //</editor-fold>


}
