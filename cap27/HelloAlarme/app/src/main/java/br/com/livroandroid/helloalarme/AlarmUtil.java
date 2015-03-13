package br.com.livroandroid.helloalarme;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by ricardo on 13/03/15.
 */
public class AlarmUtil {
    private static final String TAG = "livroandroid-alarm";

    // Agenda o alarme
    public static void schedule(Context context, Intent intent, long triggerAtMillis) {
        scheduleRepeat(context, intent, triggerAtMillis, -1);
    }

    // Agenda o alarme com repeat
    public static void scheduleRepeat(Context context, Intent intent, long triggerAtMillis, long intervalMillis) {
        PendingIntent p = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarme = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        if(intervalMillis > 0) {
            alarme.setInexactRepeating(AlarmManager.RTC_WAKEUP, triggerAtMillis, intervalMillis, p);
        } else {
            alarme.set(0, triggerAtMillis, p);
        }

        Log.d("livroandroid-alarm", "Alarme agendado com sucesso.");
    }

    public static void cancel(Context context, Intent intent) {
        AlarmManager alarme = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent p = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarme.cancel(p);
        Log.d("livroandroid-alarm", "Alarme cancelado com sucesso.");
    }
}
