package br.com.livroandroid.helloalarme;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/**
 * Classe utilitária para disparar notifications
 */
public class NotificationUtil {

    private static final String TAG = "livroandroid";

    public static void notify(Context context, int id, Intent intent) {
        NotificationManager manager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Intent para disparar o broadcast
        PendingIntent p = PendingIntent.getActivity(context, 0, intent, 0);

        // Cria a notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentIntent(p)
                .setContentTitle("Hora de comer algo.")
                .setContentText("Que tal uma fruta?")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.drawable.maca))
                .setNumber(99);

        // Dispara a notification
        Notification n = builder.build();
        manager.notify(id, n);

        Log.d(TAG,"Notification criada com sucesso");
    }

    public static void cancel(Context context, int id) {
        NotificationManager manager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        manager.cancel(id);

        Log.d(TAG,"Notification cancelada com sucesso");
    }
}
