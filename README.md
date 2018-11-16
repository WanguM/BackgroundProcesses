# BackgroundProcesses

## Classes

1. MyReciever.java

2. MyServices.java

3. MainActivity.java

## Get Started

Create an empty MainActivity class. Add the intent filter code to register your broadcast reciever

```
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

```
Create a broadcastReciever class. Add the connection manager code to check connectivity

```
public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.



        final ConnectivityManager connMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        final android.net.NetworkInfo wifi = connMgr
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        final android.net.NetworkInfo mobile = connMgr
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if (wifi.isConnected() || mobile.isConnected()) {
            // do stuff
            context.startService(new Intent(context,MyService.class));
        }else{
            Toast.makeText(context,"offline",Toast.LENGTH_LONG).show();
        }

    }

}
```
Create service class
```
public class MyService extends Service {
    public MyService() {
    }

    @Override
    public void onCreate() {
        // Start up the thread running the service. Note that we create a
        // separate thread because the service normally runs in the process's
        // main thread, which we don't want to block. We also make it
        // background priority so CPU-intensive work doesn't disrupt our UI.
        Toast.makeText(this, "service created", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();

        // For each start request, send a message to start a job and deliver the
        // start ID so we know which request we're stopping when we finish the job


        // If we get killed, after returning from here, restart
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // We don't provide binding, so return null
        return null;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show();
    }
}
```
Create the manifest file with the permission
```
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.sem3">

    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.BLUETOOTH" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
        <activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>

        <service android:name=".MyService" />

        <receiver
            android:name=".MyReceiver">

            <intent-filter>
                <action android:name="android.net.conn.CONNECTIVITY_CHANGE"/>
            </intent-filter>

        </receiver>

    </application>

</manifest>
```
