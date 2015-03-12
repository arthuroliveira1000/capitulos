package br.com.livroandroid.helloalarme;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Date;

/**
 * Created by ricardo on 08/03/15.
 */
public class LembremeDeComerReceiver extends BroadcastReceiver {

    private static final String TAG = "livroandroid";

    @Override
    public void onReceive(Context context, Intent it) {
        Log.d(TAG,"Você precisa comer: " + new Date());

        Intent intent = new Intent(context,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        NotificationUtil.notify(context,1,intent);
    }
}
