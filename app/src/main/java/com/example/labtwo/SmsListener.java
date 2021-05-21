package com.example.labtwo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SmsListener extends BroadcastReceiver {

    private String msgBody;
    private SharedPreferences preferences;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub


        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){

            Toast.makeText(context,"message received",Toast.LENGTH_SHORT).show();

            Bundle bundle = intent.getExtras();           //---get the SMS message passed in---
            SmsMessage[] msgs = null;
            String msg_from;
            if (bundle != null){
                //---retrieve the SMS message received---
                try{
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    msgs = new SmsMessage[pdus.length];
                    for(int i=0; i<msgs.length; i++){
                        msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                        msg_from = msgs[i].getOriginatingAddress();
                        msgBody = msgs[i].getMessageBody();
                        MainActivity.handleMessage(msgBody);
                    }

                    Toast.makeText(context,"message is:"+msgBody,Toast.LENGTH_SHORT).show();
                }catch(Exception e){
                    Log.d("Exception caught",e.getMessage());
                }
            }
        }
    }}