package com.money.warriorofjustice.model;

import android.telephony.SmsMessage;

/**
 * Created by Yossef on 5/3/15.
 */
public class Message {

    public final static int MSG_PROCESS_CODE_SPAM = 1;
    public final static int MSG_PROCESS_CODE_SPAM_POTENTIAL = 2;


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


    public static Message fromSmsMessage(SmsMessage smsMessage){
        Message message = new Message();
        message.setId(smsMessage.);
    }
}
