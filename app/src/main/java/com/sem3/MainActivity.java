package com.sem3;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //This is how you start a service from an activity
       // startService(new Intent(this,MyService.class));

        // This is how to start the broadcast reciever with Intent filter
        MyReceiver receiver = new MyReceiver();
        IntentFilter filter = new IntentFilter( "android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(receiver, filter);

    }
}
