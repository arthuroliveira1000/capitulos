package br.com.livroandroid.helloalarme;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by Ricardo Lecheta on 08/03/2015.
 */
public class AlarmUtil {
    private static final String TAG = "livroandroid-alarm";

    // Agenda o alarme
    public static void schedule(Context context, Intent intent, long time) {
        schedule(context, intent, time, -1);
    }

    // Agenda o alarme e repete a cada x millisegundos
    public static void schedule(Context context, Intent intent, long time, long repeat) {
        // Intent para disparar o broadcast
        PendingIntent p = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Agenda o alarme
        AlarmManager alarme = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if (repeat > 0) {
            alarme.setRepeating(AlarmManager.RTC_WAKEUP, time, repeat, p);
        } else {
            alarme.set(AlarmManager.RTC_WAKEUP, time, p);
        }

        Log.d(TAG, "Alarme agendado com sucesso.");
    }

    public static void cancel(Context context, Intent intent) {
        AlarmManager alarme = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent p = PendingIntent.getBroadcast(context, 0, intent, 0);
        alarme.cancel(p);
        Log.d(TAG, "Alarme cancelado com sucesso.");
    }
}
