package com.money.warriorofjustice.network;

import java.util.List;

import com.money.warriorofjustice.network.requests.SyncBlackListRequest;
import com.money.warriorofjustice.network.responses.BaseResponse;
import com.money.warriorofjustice.network.responses.ResponseFactory;
import com.money.warriorofjustice.network.responses.SyncBlackListKeywordsResponse;
import com.money.warriorofjustice.network.responses.SyncBlackListSendersResponse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class NetworkClient {

	public final static String TABLE_BLACK_LIST_KEYWORDS = "BlackListKeywords";
    public final static String TABLE_BLACK_LIST_SENDERS  = "BlackListSenders";


    public SyncBlackListKeywordsResponse syncBlackListKeywords(SyncBlackListRequest request) {
		ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(TABLE_BLACK_LIST_KEYWORDS);
		List<ParseObject> blackList = null;

		try {
			blackList = query.find();
		} catch (ParseException e) {

		}
		return (SyncBlackListKeywordsResponse)ResponseFactory.fromParseResponse(blackList, ResponseFactory.RESPONSE_TYPE_SYNC_BLACKLIST_KEYWORDS);

	}

    public SyncBlackListSendersResponse syncBlackListSenders(SyncBlackListRequest request) {
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(TABLE_BLACK_LIST_SENDERS);
        List<ParseObject> blackList = null;

        try {
            blackList = query.find();
        } catch (ParseException e) {

        }
        return (SyncBlackListSendersResponse)ResponseFactory.fromParseResponse(blackList,
                ResponseFactory.RESPONSE_TYPE_SYNC_BLACKLIST_SENDERS);

    }
}
