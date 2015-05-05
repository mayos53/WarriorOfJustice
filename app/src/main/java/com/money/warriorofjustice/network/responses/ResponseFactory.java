package com.money.warriorofjustice.network.responses;

import java.util.List;

import com.parse.ParseObject;

public class ResponseFactory {
	
	public final static int RESPONSE_TYPE_BASE = 1;
	public final static int RESPONSE_TYPE_SYNC_BLACKLIST_KEYWORDS = 2;
    public final static int RESPONSE_TYPE_SYNC_BLACKLIST_SENDERS = 3;



    public static BaseResponse fromParseResponse(List<ParseObject> list, int responseType){
		switch(responseType){
			case RESPONSE_TYPE_SYNC_BLACKLIST_KEYWORDS:
				return new SyncBlackListKeywordsResponse(list);

            case RESPONSE_TYPE_SYNC_BLACKLIST_SENDERS:
                return new SyncBlackListSendersResponse(list);
			
		}
		return null;
	}
}
