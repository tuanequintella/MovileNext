package com.movile.next.seriestracker.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.movile.next.seriestracker.model.ShowUpdate;
import com.movile.next.seriestracker.remote.ApiConfiguration;
import com.movile.next.seriestracker.remote.client.FetchRemoteUpdatesClient;


public class UpdatesService extends IntentService {

    private static final String UPDATES_RECEIVED = "com.movile.next.seriestracker.action.UPDATES_RECEIVED";
    private static final String EXTRA_SHOW_UPDATE = "showUpdate";

    public UpdatesService() {
        super("UpdatesService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        ShowUpdate update = new FetchRemoteUpdatesClient(ApiConfiguration.UPDATE_URL_BASE).getUpdates();

        Intent updateIntent = new Intent(UPDATES_RECEIVED);
        updateIntent.putExtra(EXTRA_SHOW_UPDATE, update);
        sendBroadcast(updateIntent);
    }
}
