package br.com.livroandroid.helloalarme;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import livroandroid.lib.utils.AlarmUtil;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btAgendar).setOnClickListener(this);
        findViewById(R.id.btCancelar).setOnClickListener(this);

        if(getIntent().getAction().equals("AHAHAHA")) {
            NotificationUtil.cancel(this,1);
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent("br.com.livroandroid.helloalarme.LEMBREME_DE_COMER");
        if(v.getId() == R.id.btAgendar) {
            AlarmUtil.scheduleAfterXSeconds(this,intent,5,5);
        } else {
            AlarmUtil.cancel(this,intent);
        }
    }
}
