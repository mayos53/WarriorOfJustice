package com.money.warriorofjustice.network.requests;

import com.parse.ParseObject;

public class ProcessMessagesRequest extends BaseRequest{

    public String phoneNumber;
    public long time;


    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public void setTime(long time){
        this.time = time;
    }

    public long getTime(){
        return time;
    }


}
