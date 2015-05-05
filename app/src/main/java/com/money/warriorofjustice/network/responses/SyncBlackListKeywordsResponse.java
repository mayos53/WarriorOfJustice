package com.money.warriorofjustice.network.responses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import com.parse.ParseObject;

public class SyncBlackListKeywordsResponse extends BaseResponse{
	
	private final static String KEYWORDS = "Keywords";
	
	private String [] keywords;
	
	
	public SyncBlackListKeywordsResponse(List<ParseObject> list){
		String keyword = list.get(0).getString(KEYWORDS);
		StringTokenizer tokenizer = new StringTokenizer(keyword, ",");
		List<String> keys = new ArrayList<String>();
		while(tokenizer.hasMoreTokens()){
			keys.add(tokenizer.nextToken());
		}


		keywords = keys.toArray(new String[0]);
        statusCode = STATUS_CODE_OK;
	}
	
	public String [] getKeywords(){
		return keywords;
	}


}
