package com.example.labtwo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;



public class SplashScreen extends AppCompatActivity {

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        handler=new Handler();
        handler.postDelayed(() -> {
            Intent intent=new Intent(SplashScreen.this,MainActivity.class);
            startActivity(intent);
            finish();
        },5000);

    }
}