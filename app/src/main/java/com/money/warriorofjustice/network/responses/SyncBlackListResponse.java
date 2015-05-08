package com.money.warriorofjustice.network.responses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import com.parse.ParseObject;

public class SyncBlackListResponse extends BaseResponse{
	
	private String [] senders;
    private String [] keywords;

	

	
	public String [] getKeywords(){
		return keywords;
	}

    public String [] getSenders(){
        return senders;
    }




}
