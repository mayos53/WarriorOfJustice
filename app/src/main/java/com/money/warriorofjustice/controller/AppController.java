package com.money.warriorofjustice.controller;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.money.warriorofjustice.OperationListener;
import com.money.warriorofjustice.WarriorOfJusticeApp;
import com.money.warriorofjustice.controller.tasks.BaseTask;
import com.money.warriorofjustice.controller.tasks.ProcessMessagesTask;
import com.money.warriorofjustice.controller.tasks.SyncBlackListTask;
import com.money.warriorofjustice.model.Message;
import com.money.warriorofjustice.network.requests.ProcessMessagesRequest;
import com.money.warriorofjustice.network.requests.SyncBlackListRequest;
import com.money.warriorofjustice.network.responses.ProcessMessagesResponse;
import com.money.warriorofjustice.network.responses.SyncBlackListResponse;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Yossef on 5/3/15.
 */
public class AppController
{

    private final static String SHARED_PREFERENCES = "SHARED_PREFERENCES";
    private final static String PREFERENCES_KEYWORDS = "PREFERENCES_KEYWORDS";
    private final static String PREFERENCES_SENDERS  = "PREFERENCES_SENDERS";
    private final static String PREFERENCES_MESSAGES  = "PREFERENCES_MESSAGES";




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



    public void saveKeywords(String [] keywords){
        putObject(PREFERENCES_KEYWORDS,keywords);
        loadKeywords();
    }

    public void saveSenders(String [] senders){
        putObject(PREFERENCES_SENDERS,senders);
        loadSenders();

    }

    public void updateMessageProcessCode(int id){

    }

    public void updateMessages(Message[] messages){

    }

    public void getMessages(int processCode){

    }

    //<editor-fold desc="SharedPrefs utils">

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




    //</editor-fold>

    //<editor-fold desc="Init data">
    private void initMemoryArrays(){
        loadKeywords();
        loadSenders();
    }

    private void loadKeywords(){
        keywords = (String[])getObject(PREFERENCES_KEYWORDS,String[].class);
    }

    private void loadSenders(){
        senders  = new HashSet<String>(Arrays.asList((String[])getObject(PREFERENCES_SENDERS,
                String[].class)));
    }


    //</editor-fold>

    //<editor-fold desc="Operations">

    public void processMessages(String sender,long time, final OperationListener listener) {

        ProcessMessagesTask task = new ProcessMessagesTask(context,new BaseTask.TaskListener<ProcessMessagesResponse>() {
            @Override
            public void onSuccess(ProcessMessagesResponse response) {
                updateMessages(response.getMessages());
                if(listener != null){
                    listener.onSuccess();
                }

            }

            @Override
            public void onError(int code) {
                if(listener != null){
                    listener.onError(code);
                }
            }
        });
        ProcessMessagesRequest request = new ProcessMessagesRequest();
        request.setPhoneNumber(sender);
        request.setTime(time);
        task.execute(request);

    }
    public void syncBlackList(final OperationListener listener) {
        SyncBlackListTask task = new SyncBlackListTask(context,
        new BaseTask.TaskListener<SyncBlackListResponse>() {
            @Override
            public void onSuccess(SyncBlackListResponse response) {
                saveSenders(response.getSenders());
                saveKeywords(response.getSenders());
                if(listener != null){
                    listener.onSuccess();
                }
            }

            @Override
            public void onError(int code) {
                if(listener != null){
                    listener.onError(code);
                }

            }
        });
        SyncBlackListRequest request = new SyncBlackListRequest();
        task.execute(request);

    }






    //</editor-fold>

    public void setMessageRead(long id) {

    }


    public boolean containsBlackListKeywords(String message) {
        for (String keyword : keywords) {
            if (message.contains(keyword)) {
                return true;
            }
        }
        return false;
    }

    public boolean isInSendersBlackList(String sender) {
        return senders.contains(sender);
    }






}
