package com.money.warriorofjustice.network.responses;

import com.parse.Parse;
import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SyncBlackListSendersResponse extends BaseResponse{
    private final static String PHONE_NUMBER = "phoneNumber";

    private String [] senders;


    public SyncBlackListSendersResponse(List<ParseObject> list){
        List<String> result = new ArrayList<String>();
        for(ParseObject object:list){
            result.add(object.getString(PHONE_NUMBER));

        }
        senders =  result.toArray(new String[0]);
    }

    public String [] getSenders(){
        return senders;
    }
}
