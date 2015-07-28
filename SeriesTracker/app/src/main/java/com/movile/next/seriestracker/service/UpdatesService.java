package com.movile.next.seriestracker.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.movile.next.seriestracker.model.ShowUpdate;
import com.movile.next.seriestracker.remote.ApiConfiguration;
import com.movile.next.seriestracker.remote.client.FetchRemoteUpdatesClient;
import com.movile.next.seriestracker.util.FormatUtil;


public class UpdatesService extends IntentService {

    private static final String UPDATES_RECEIVED = "com.movile.next.seriestracker.action.UPDATES_RECEIVED";
    private static final String EXTRA_SHOW_UPDATE = "showUpdate";
    private static final String UPDATE_PREFERENCES = "updatePreferences";
    private static final String LAST_UPDATE_KEY = "lastUpdate";

    public UpdatesService() {
        super("UpdatesService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        ShowUpdate update = new FetchRemoteUpdatesClient(ApiConfiguration.UPDATE_URL_BASE).getUpdates();

        SharedPreferences updatePreferences = this.getSharedPreferences(UPDATE_PREFERENCES, Context.MODE_PRIVATE);
        String lastUpdateDate = updatePreferences.getString(LAST_UPDATE_KEY, null);

        Log.d("UHUR", "SERVIÇO INICIADO");

            if (lastUpdateDate == null || (FormatUtil.formatDate(lastUpdateDate).before(FormatUtil.formatDate(update.date())))) {
                Log.d("UHUR", "UPDATE NOVO!");
                SharedPreferences.Editor editor = updatePreferences.edit();
                editor.putString(LAST_UPDATE_KEY, update.date());
                editor.commit();

                Intent updateIntent = new Intent(UPDATES_RECEIVED);
                updateIntent.putExtra(EXTRA_SHOW_UPDATE, update);
                sendBroadcast(updateIntent);
            }
    }
}
