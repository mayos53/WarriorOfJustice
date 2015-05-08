package com.money.warriorofjustice.model;

import android.database.Cursor;
import android.telephony.SmsMessage;

import com.money.warriorofjustice.util.Utils;

/**
 * Created by Yossef on 5/3/15.
 */
public class Message {

    public final static int MSG_PROCESS_CODE_OK = 1;
    public final static int MSG_PROCESS_CODE_SPAM = 2;
    public final static int MSG_PROCESS_CODE_PERSONAL_SPAM = 3;
    public final static int MSG_PROCESS_CODE_SUSPECT = 4;




    private long id;
    private int processCode;
    private String sender;
    private String content;
    private long time;


    public String getSender() {
        return sender;
    }

    public String getContent() {
        return content;
    }

    public long getTime() {
        return time;
    }

    public long getId() {
        return id;
    }

    public int getProcessCode() {
        return processCode;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setProcessCode(int processCode) {
        this.processCode = processCode;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTime(long time) {
        this.time = time;
    }



    public static Message fromCursor(Cursor cursor) {
        Message message = new Message();
        message.setContent(cursor.getString(cursor.getColumnIndex("body")));
        message.setId(cursor.getLong(cursor.getColumnIndex("_id")));
        message.setSender(cursor.getString(cursor.getColumnIndex("address")));
        message.setTime(cursor.getLong(cursor.getColumnIndex("date")));
        return message;

    }
}
