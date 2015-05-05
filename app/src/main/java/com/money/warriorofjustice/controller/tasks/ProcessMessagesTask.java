package com.money.warriorofjustice.controller.tasks;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.money.warriorofjustice.controller.AppController;
import com.money.warriorofjustice.controller.BaseTask;
import com.money.warriorofjustice.controller.ProcessMessagesResponse;
import com.money.warriorofjustice.model.Message;
import com.money.warriorofjustice.network.requests.BaseRequest;
import com.money.warriorofjustice.util.Utils;

import java.util.ArrayList;
import java.util.List;

public class ProcessMessagesTask extends BaseTask<BaseRequest, Void, ProcessMessagesResponse> {

    private final static String SMS_INBOX_URI = "content://sms/inbox";
    private AppController app;

    public ProcessMessagesTask(Context context, TaskListener<ProcessMessagesResponse> listener) {
        super(context, listener);
        app = AppController.getInstance(context);
    }

    @Override
    protected ProcessMessagesResponse doInBackground(BaseRequest... baseRequest) {

        ProcessMessagesResponse response = new ProcessMessagesResponse();

        List<Message> messages = new ArrayList<Message>();

        Cursor cursor = context.getContentResolver().query(Uri.parse(SMS_INBOX_URI), null, null, null, null);
        if (cursor.moveToFirst()) { // must check the result to prevent
            String sender = cursor.getString(cursor.getColumnIndex("address"));


            // exception
            do {
                Message message = new Message();
                message.setContent(cursor.getString(cursor.getColumnIndex("body")));
                message.setId(cursor.getLong(cursor.getColumnIndex("_id")));
                message.setSender(cursor.getString(cursor.getColumnIndex("address")));
                message.setTime(cursor.getLong(cursor.getColumnIndex("date")));

                if (app.isInSendersBlackList(sender)) {
                    message.setProcessCode(Message.MSG_PROCESS_CODE_SPAM);
                    messages.add(message);

                } else {
                    if (!Utils.contactExists(context, message.getSender())) {
                        if (app.containsBlackListKeywords(message.getContent())) {
                            message.setProcessCode(Message.MSG_PROCESS_CODE_SPAM_POTENTIAL);
                            messages.add(message);
                        }
                    }

                }


//                for (int idx = 0; idx < cursor.getColumnCount(); idx++) {
//                    String msgData = " " + cursor.getColumnName(idx) + ":" + cursor.getString(idx);
//                    Log.d("MSG_DATA", msgData);
//                }
                // use msgData
            }
            while (cursor.moveToNext());

            response.setMessages(messages.toArray(new Message[0]));

        }
        return response;

    }
}






