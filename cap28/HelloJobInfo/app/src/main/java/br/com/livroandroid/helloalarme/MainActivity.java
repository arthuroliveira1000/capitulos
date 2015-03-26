package br.com.livroandroid.helloalarme;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    public void onClickAgendar(View view) {
        int id = 1;
        JobUtil.schedule(this, HelloJobService.class, id);

        Toast.makeText(this,"Job agendado" ,Toast.LENGTH_SHORT).show();
    }

    public void onClickCancelar(View view) {
        JobUtil.cancel(this,1);
    }
}
