package com.app.sms.thatsmsapp.service;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.telephony.SmsMessage;

import com.app.sms.thatsmsapp.activity.HomeActivity;

public class SmsBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Telephony.Sms.Intents.SMS_RECEIVED_ACTION.equals(intent.getAction())) {
            String smsSender = "";
            StringBuilder smsBody = new StringBuilder();
            for (SmsMessage message : Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
                smsSender = message.getDisplayOriginatingAddress();
                smsBody.append(message.getMessageBody());
            }

            sendNotification(context, smsSender, smsBody);
        }
    }

    private void sendNotification(Context context, String smsSender, StringBuilder smsBody) {

        Intent intent = new Intent(context, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("from_notification", true);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "CHANNEL_1")
                .setSmallIcon(android.support.compat.R.drawable.notification_icon_background)
                .setContentTitle("That SMS App")
                .setContentText(smsSender + "\n" + smsBody)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent);
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(context);

        managerCompat.notify(1, builder.build());
    }
}
