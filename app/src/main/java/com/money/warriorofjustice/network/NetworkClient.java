package com.money.warriorofjustice.network;

import com.money.warriorofjustice.model.Message;
import com.money.warriorofjustice.network.requests.SyncBlackListRequest;
import com.money.warriorofjustice.network.responses.ProcessMessagesResponse;
import com.money.warriorofjustice.network.responses.SyncBlackListResponse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class NetworkClient {

	//
    public final static String TABLE_BLACK_LIST_KEYWORDS = "BlackListKeywords";
    public final static String TABLE_BLACK_LIST_SENDERS  = "BlackListSenders";


    public SyncBlackListResponse syncBlackLists(SyncBlackListRequest request) {
		ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(TABLE_BLACK_LIST_KEYWORDS);
		List<ParseObject> blackList = null;

		try {
			blackList = query.find();
		} catch (ParseException e) {

		}
		return null;

	}


    public ProcessMessagesResponse reportMessages(List<Message> spamMessages, List<Message> suspectMessages) {
        return null;
    }
}
