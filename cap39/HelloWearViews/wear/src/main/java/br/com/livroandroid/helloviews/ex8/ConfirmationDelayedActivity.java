package br.com.livroandroid.helloviews.ex8;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.os.Bundle;
import android.support.wearable.view.DelayedConfirmationView;
import android.view.View;

import br.com.livroandroid.helloviews.R;
import livroandroid.lib.utils.NotificationUtil;

/**
 * Created by ricardo on 19/04/15.
 */
public class ConfirmationDelayedActivity extends Activity implements DelayedConfirmationView.DelayedConfirmationListener {
    private DelayedConfirmationView mDelayedView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_delayed);

        mDelayedView =
                (DelayedConfirmationView) findViewById(R.id.delayed_confirmation);
        mDelayedView.setTotalTimeMs(5 * 1000);
        mDelayedView.start();
        mDelayedView.setListener(this);
    }

    @Override
    public void onTimerFinished(View view) {
        NotificationUtil.create(this,R.mipmap.ic_launcher,"Timer","TimerFinished");
        finish();
    }

    @Override
    public void onTimerSelected(View view) {
        //NotificationUtil.create(this,R.mipmap.ic_launcher,"Timer","TimerSelected");
        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("Timer")
                .setContentText("TimerSelected")
                .build();
        ((NotificationManager) getSystemService(NOTIFICATION_SERVICE)).notify(0, notification);
        finish();
    }
}
