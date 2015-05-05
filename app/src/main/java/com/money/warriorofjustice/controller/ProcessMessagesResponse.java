package com.money.warriorofjustice.controller;

import com.money.warriorofjustice.model.Message;
import com.money.warriorofjustice.network.responses.BaseResponse;

public class ProcessMessagesResponse extends BaseResponse{


	private Message [] messages;

    public Message[] getMessages() {
        return messages;
    }

    public void setMessages(Message[] messages) {
        this.messages = messages;
    }


}
