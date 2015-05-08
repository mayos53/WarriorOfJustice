package com.money.warriorofjustice.receivers;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import com.money.warriorofjustice.R;
import com.money.warriorofjustice.WarriorOfJusticeApp;
import com.money.warriorofjustice.controller.AppController;
import com.money.warriorofjustice.model.Message;

import java.util.Random;

/**
 * Created by Yossef on 5/3/15.
 */

    public class SMSReceiver extends BroadcastReceiver {

        private final static String SMS_INBOX_URI = "content://sms/inbox";


    // Get the object of SmsManager
        final SmsManager sms = SmsManager.getDefault();

        public void onReceive(Context context, Intent intent) {

            // Retrieves a map of extended data from the intent.
            final Bundle bundle = intent.getExtras();

            try {

                if (bundle != null) {

                    final Object[] pdusObj = (Object[]) bundle.get("pdus");

                    String senderNum = null;

                    for (int i = 0; i < pdusObj.length; i++) {

                        SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                        String phoneNumber = currentMessage.getDisplayOriginatingAddress();
                        long timeMessage = currentMessage.getTimestampMillis();

                        AppController.getInstance(context).processMessages(phoneNumber,timeMessage,null);



                        // toast.show();

                    } // end for loop

//                    Spam spam = new Spam();
//                    spam.id = new Random().nextInt(472836426) + 11;
//                    spam.title = senderNum;
//                    spam.content = message;
//
//
//                    if(spam.content.contains("הנחה")){
//                        ((App)context.getApplicationContext()).updateList(spam);
//                        showSpamNotification(context,spam);
//
//                    }
                } // bundle is null

            } catch (Exception e) {
                Log.e("SmsReceiver", "Exception smsReceiver" +e);

            }
        }


    }


