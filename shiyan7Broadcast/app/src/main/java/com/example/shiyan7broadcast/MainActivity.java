package com.example.shiyan7broadcast;

import androidx.appcompat.app.AppCompatActivity;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

private NetworkChangeReceiver networkChangeReceiver;
private PowerConnectionReceiver powerConnectionReceiver;
private BatteryLevelReceiver batteryLevelReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //设置意图过滤器
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");

        networkChangeReceiver = new NetworkChangeReceiver();
        powerConnectionReceiver = new PowerConnectionReceiver();
        batteryLevelReceiver = new BatteryLevelReceiver();
        registerReceiver(powerConnectionReceiver,intentFilter);
        registerReceiver(networkChangeReceiver,intentFilter);
        registerReceiver(batteryLevelReceiver,intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkChangeReceiver);
        unregisterReceiver(powerConnectionReceiver);
        unregisterReceiver(batteryLevelReceiver);
    }

    class NetworkChangeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context , Intent intent){
            ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            boolean isConnected = activeNetwork != null &&
                    activeNetwork.isConnectedOrConnecting();
            if(isConnected == true)
            {
                Log.d("isConnection","现在有网络");
            }
            else{
                Log.d("isConnection","现在无网络");
            }
            boolean isWiFi = activeNetwork.getType() == ConnectivityManager.TYPE_WIFI;
            if(isWiFi == true){
                Log.d("iswifi","现在使用的网络是wifi");
            }
            else{
                Log.d("iswifi","现在使用的网络不是wifi");
            }
        }
    }


}