package com.money.warriorofjustice.receivers;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by Yossef on 5/3/15.
 */

    public class SMSReceiver extends BroadcastReceiver {


        // Get the object of SmsManager
        final SmsManager sms = SmsManager.getDefault();

        public void onReceive(Context context, Intent intent) {

            // Retrieves a map of extended data from the intent.
            final Bundle bundle = intent.getExtras();

            try {

                if (bundle != null) {

                    final Object[] pdusObj = (Object[]) bundle.get("pdus");

                    String senderNum = null;
                    String message = null;

                    for (int i = 0; i < pdusObj.length; i++) {

                        SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                        String phoneNumber = currentMessage.getDisplayOriginatingAddress();

                        senderNum = phoneNumber;
                        message = currentMessage.getDisplayMessageBody();




                        Log.i("SmsReceiver", "senderNum: " + senderNum + "; message: " + message);

                        // Show Alert
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context,
                                "senderNum: "+ senderNum + ", message: " + message, duration);
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

        public void showSpamNotification(Context context, Spam spam){
            NotificationCompat.Builder mBuilder =   new NotificationCompat.Builder(context)
                    .setSmallIcon(R.mipmap.ic_launcher) // notification icon
                    .setContentTitle("הודעת ספאם") // title for notification
                    .setContentText(" :התקבלה הודעה חשודה מאת" + spam.title) // message for notification
                    .setAutoCancel(true); // clear notification after click
            Intent intent = new Intent(context, MainActivity.class);
            intent.putExtra("spam",true);
            PendingIntent pi = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            mBuilder.setContentIntent(pi);
            NotificationManager mNotificationManager =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.notify(0, mBuilder.build());
        }
    }


