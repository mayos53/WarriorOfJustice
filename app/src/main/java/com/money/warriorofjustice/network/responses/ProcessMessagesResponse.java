package com.money.warriorofjustice.network.responses;

import com.money.warriorofjustice.model.Message;
import com.money.warriorofjustice.network.responses.BaseResponse;

import java.util.Map;

public class ProcessMessagesResponse extends BaseResponse{


	private Message[] messages;

    public Message[] getMessages() {
        return messages;
    }



}
