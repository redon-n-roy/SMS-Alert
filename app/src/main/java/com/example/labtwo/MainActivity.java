package com.example.labtwo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;


   public class MainActivity extends AppCompatActivity
   {

       public static void handleMessage(String msgBody) {
       }

       @Override
       protected void onCreate(Bundle savedInstanceState) {
           super.onCreate(savedInstanceState);
           setContentView(R.layout.activity_main);


           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
               requestSmsPermission();
           else {
               SmsListener smsListener = new SmsListener();
               IntentFilter intentFilter = new IntentFilter();
               intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
               registerReceiver(smsListener, intentFilter);
           }
       }


       private void requestSmsPermission() {
           String permission = Manifest.permission.RECEIVE_SMS;
           int grant = ContextCompat.checkSelfPermission(this, permission);
           if ( grant != PackageManager.PERMISSION_GRANTED) {
               String[] permission_list = new String[1];
               permission_list[0] = permission;
               ActivityCompat.requestPermissions(this, permission_list, 1);

           }
       }

       @Override
       public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
           super.onRequestPermissionsResult(requestCode, permissions, grantResults);
           if (requestCode == 1) {
               SmsListener smsListener = new SmsListener();
               IntentFilter intentFilter = new IntentFilter();
               intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
               registerReceiver(smsListener, intentFilter);
           }
       }}