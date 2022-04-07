package com.example.shiyan7broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.util.Log;

public class BatteryLevelReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // 当前剩余电量
        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        // 电量最大值
        int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        // 电量百分比
        float batteryPct = level / (float) scale;
        Log.d("BatteryLevelReceiver", "batteryPct = " + batteryPct);
        if(batteryPct<=0.2){
            Log.d("BatteryLevelReceiver","电量较低" );
        }
        if(batteryPct>0.2){
            Log.d("BatteryLevelReceiver","电量充足" );
        }
        Log.d("BatteryLevelReceiver","电量："+batteryPct);

    }


}
