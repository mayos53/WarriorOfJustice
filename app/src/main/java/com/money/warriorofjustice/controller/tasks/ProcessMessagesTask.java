package com.money.warriorofjustice.controller.tasks;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.money.warriorofjustice.WarriorOfJusticeApp;
import com.money.warriorofjustice.controller.AppController;
import com.money.warriorofjustice.network.responses.ProcessMessagesResponse;
import com.money.warriorofjustice.model.Message;
import com.money.warriorofjustice.network.requests.ProcessMessagesRequest;
import com.money.warriorofjustice.util.Utils;

import java.util.ArrayList;
import java.util.List;

public class ProcessMessagesTask extends BaseTask<ProcessMessagesRequest, Void, ProcessMessagesResponse> {

    private final static String SMS_INBOX_URI = "content://sms/inbox";
    private AppController app;

    public ProcessMessagesTask(Context context, TaskListener<ProcessMessagesResponse> listener) {
        super(context, listener);
        app = AppController.getInstance(context);
    }

    @Override
    protected ProcessMessagesResponse doInBackground(ProcessMessagesRequest... request) {


        String phoneNumber = request[0].getPhoneNumber();
        String selection = null;
        String[] selectionArgs = null;
        String orderBy = null;

        if (phoneNumber != null) {
            selection = " address = ?";
            selectionArgs = new String[]{phoneNumber};
            orderBy = "date DESC";
        }

        Cursor cursor = context.getContentResolver().query(Uri.parse(SMS_INBOX_URI),
                null, selection, selectionArgs, orderBy);

        List<Message> spamMessages = new ArrayList<Message>();
        List<Message> suspectMessages = new ArrayList<Message>();


        while (cursor.moveToNext()) {
            Message message = Message.fromCursor(cursor);
            WarriorOfJusticeApp app = (WarriorOfJusticeApp) context.getApplicationContext();
            int code = getMessageProcessCode(message);
            message.setProcessCode(code);
            switch (code){
                case Message.MSG_PROCESS_CODE_SPAM:
                    spamMessages.add(message);
                break;

                case Message.MSG_PROCESS_CODE_SUSPECT:
                    suspectMessages.add(message);
                break;


            }

        }
        cursor.close();

        ProcessMessagesResponse processMessagesResponse = networkClient.reportMessages(spamMessages,
                suspectMessages);
        return processMessagesResponse;
    }


    //<editor-fold desc="Message Utils">

    public int getMessageProcessCode(Message message) {
        if (app.isInSendersBlackList(message.getSender())) {
            return Message.MSG_PROCESS_CODE_SPAM;

        } else {
            if (!Utils.contactExists(context, message.getSender())) {
                if (app.containsBlackListKeywords(message.getContent())) {
                    return Message.MSG_PROCESS_CODE_SUSPECT;
                }
            }
        }
        return Message.MSG_PROCESS_CODE_OK;
    }



    //</editor-fold>

}







