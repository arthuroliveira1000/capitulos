package br.com.livroandroid.helloalarme;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import static android.app.job.JobInfo.*;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class MainActivity extends ActionBarActivity {

    private static final String TAG = "livroandroid";
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    public void onClickAgendar(View view) {

        try {

            int id = 1;
            JobUtil.schedule(this,TestJobService.class, id);



            Toast.makeText(this,"Job agendado " + count,Toast.LENGTH_SHORT).show();

            TextView text = (TextView) findViewById(R.id.text);
            text.setText("Job: " + count);

            Log.d(TAG,"Job agendado.");
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            Toast.makeText(this,"Erro: " + e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
}
