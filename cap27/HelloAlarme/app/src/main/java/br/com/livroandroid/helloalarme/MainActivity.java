package br.com.livroandroid.helloalarme;

import android.app.AlarmManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickAgendar(View view) {
        Intent intent = new Intent(LembremeDeComerReceiver.ACTION);
        // Agenda para daqui a 5 seg
        AlarmUtil.schedule(this, getIntent(), 5 * 1000);
    }

    public void onClickAgendarComRepeat(View view) {
        Intent intent = new Intent(LembremeDeComerReceiver.ACTION);
        // Agenda para daqui a 5 seg, repete a cada 30 seg
        AlarmUtil.scheduleRepeat(this, getIntent(), 5 * 1000, AlarmManager.INTERVAL_DAY);
    }

    public void onClickCancelar(View view) {
        Intent intent = new Intent(LembremeDeComerReceiver.ACTION);
        AlarmUtil.cancel(this,getIntent());
    }
}
