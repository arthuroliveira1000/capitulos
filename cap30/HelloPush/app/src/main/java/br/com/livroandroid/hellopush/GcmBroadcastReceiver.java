package br.com.livroandroid.hellopush;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

/**
 * Created by Usuário on 25/03/2015.
 */
public class GcmBroadcastReceiver extends WakefulBroadcastReceiver {
    private static final String TAG = "gcm";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "GcmBroadcastReceiver.onReceive: " + intent.getExtras());
        // Informa que o "GcmIntentService" vai tratar essa intent.
        ComponentName comp = new ComponentName(context.getPackageName(), GcmIntentService.class.getName());
        // Inicia o service com WAKE LOCK.
        startWakefulService(context, (intent.setComponent(comp)));
        setResultCode(Activity.RESULT_OK);
    }
}
