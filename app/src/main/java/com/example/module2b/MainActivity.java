package com.example.module2b;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.provider.Telephony;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
   public static int MY_PERMISSIONS_REQUEST_SMS_RECEIVE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        ActivityCompat.requestPermissions(this,new String[]{
                        Manifest.permission.RECEIVE_SMS},
                MY_PERMISSIONS_REQUEST_SMS_RECEIVE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            MyReceiver smsReceiver = new MyReceiver();
            IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
            intentFilter.addAction(Telephony.Sms.Intents.DATA_SMS_RECEIVED_ACTION);
            this.registerReceiver(smsReceiver, intentFilter);
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_SMS_RECEIVE) {
            Log.d("TAG", "My permission request sms received successfully");
        }
    }
}