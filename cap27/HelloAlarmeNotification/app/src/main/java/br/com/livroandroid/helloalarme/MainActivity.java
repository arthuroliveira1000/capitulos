package br.com.livroandroid.helloalarme;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.Calendar;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private static final String TAG = "livroandroid";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btAgendar).setOnClickListener(this);
        findViewById(R.id.btCancelar).setOnClickListener(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "onNewIntent");
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent("br.com.livroandroid.helloalarme.LEMBREME_DE_COMER");
        if (v.getId() == R.id.btAgendar) {
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(System.currentTimeMillis());
            c.add(Calendar.SECOND, 5);
            long time = c.getTimeInMillis();

            AlarmUtil.schedule(this, intent, time, 10 * 1000);
        } else {
            AlarmUtil.cancel(this, intent);
        }
    }
}
