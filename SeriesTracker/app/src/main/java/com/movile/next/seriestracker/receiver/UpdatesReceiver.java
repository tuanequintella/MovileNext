package com.movile.next.seriestracker.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.movile.next.seriestracker.R;
import com.movile.next.seriestracker.activity.ShowDetailsActivity;
import com.movile.next.seriestracker.model.ShowUpdate;

public class UpdatesReceiver extends BroadcastReceiver {

    private static final String UPDATES_RECEIVED = "com.movile.next.seriestracker.action.UPDATES_RECEIVED";
    private static final String EXTRA_SHOW_UPDATE = "showUpdate";

    public UpdatesReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            ShowUpdate showUpdate = (ShowUpdate) intent.getExtras().get(EXTRA_SHOW_UPDATE);

            if (UPDATES_RECEIVED.equals(action)) {

                Notification notification = configureNotification(context, showUpdate);

                NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

                manager.notify(0, notification);
            }
        }

    }

    private Notification configureNotification(Context context, ShowUpdate showUpdate) {
        Intent openShow = new Intent(context, ShowDetailsActivity.class);
        openShow.putExtra(ShowDetailsActivity.EXTRA_SHOW, showUpdate.show());
        openShow.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(ShowDetailsActivity.class);
        stackBuilder.addNextIntent(openShow);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(showUpdate.title())
                .setContentText(showUpdate.message())
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(showUpdate.message()));

        return builder.build();
    }
}
