package com.movile.next.seriestracker.receiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.movile.next.seriestracker.service.UpdatesService;

public class BootReceiver extends BroadcastReceiver {
    private static final int UPDATE_INTERVAL = 10000;

    public BootReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        //MADE WITH POOLING, do note do this
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, new Intent(context, UpdatesService.class), 0);
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        manager.setRepeating(AlarmManager.RTC_WAKEUP, 0, UPDATE_INTERVAL, pendingIntent);
    }
}
