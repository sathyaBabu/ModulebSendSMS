package com.example.module2b;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.util.Arrays;


public class MyReceiver extends BroadcastReceiver {

  private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
  // private static final String SMS_RECEIVED = " Telephony.Sms.Intents.DATA_SMS_RECEIVED_ACTION";

    private static final String TAG = "SMSBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "Intent recieved: " + intent.getAction());

        if (intent.getAction() == SMS_RECEIVED) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                Object[] pdus = (Object[])bundle.get("pdus");
                final SmsMessage[] messages = new SmsMessage[pdus.length];
                for (int i = 0; i < pdus.length; i++) {
                    messages[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                }
                if (messages.length > -1) {
                    Log.i(TAG, "Message received: " + messages[0].getMessageBody());
                    Toast.makeText(context, "Message received: " + messages[0].getMessageBody(), Toast.LENGTH_SHORT).show();
                   // Toast.makeText(this,"i",Toast.LENGTH_LONG).show();


                }
            }
        }
    }
}
