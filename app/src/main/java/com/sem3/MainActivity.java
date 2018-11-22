package com.sem3;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options, menu);
        return true;
    }
@Override
    public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {

        case R.id.Settings:
            Toast.makeText(this,"Settings",Toast.LENGTH_LONG).show();
            return true;

        case R.id.About:
            Toast.makeText(this,"About",Toast.LENGTH_LONG).show();

        case R.id.Contact:
            Toast.makeText(this,"Contact",Toast.LENGTH_LONG).show();
            return true;
        default:
            return super.onOptionsItemSelected(item);
    }

    }

}
