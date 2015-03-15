package br.com.livroandroid.playermp3;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Environment;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    private static final String TAG = "livro";
    // Classe que encapsula o MediaPlayer

    private EditText text;
    private InterfaceMp3 interfaceMp3;
    private ServiceConnection conexao = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            // Recupera a interface para interagir com o serviço
            Mp3Service.Mp3ServiceBinder conexao = (Mp3Service.Mp3ServiceBinder) service;
            interfaceMp3 = conexao.getInterface();
            Log.d(TAG, "onServiceConnected, interfaceMp3 conectada: " + interfaceMp3);
        }
        public void onServiceDisconnected(ComponentName className) {
            Log.d(TAG, "onServiceDisconnected, liberando recursos.");
            interfaceMp3 = null;
        }
    };

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_main);
        // /storage/emulated/0/Android/data/br.com.livroandroid.playermp3/files/DCIM
        // /storage/sdcard/Music/linkin_park1.mp3
        text = (EditText) findViewById(R.id.tArquivo);

        Intent intent = new Intent(this,Mp3Service.class);
        Log.d(TAG, "Iniciando o service");
        startService(intent);
        // Faz o bind/ligação
        boolean b = bindService(intent, conexao, Context.BIND_AUTO_CREATE);
        Log.d(TAG,"Service conectado: " + b);
    }
    public void onClickPlay(View view) {
        if(interfaceMp3 != null) {
            String mp3 = text.getText().toString();
            Log.d(TAG,"play: " +  mp3);
            interfaceMp3.start(mp3);
        }
    }
    public void onClickPause(View view) {
        if(interfaceMp3 != null) {
            Log.d(TAG,"pause");
            interfaceMp3.pause();
        }
    }
    public void onClickStop(View view) {
        if(interfaceMp3 != null) {
            Log.d(TAG, "stop");
            interfaceMp3.stop();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(interfaceMp3 != null && interfaceMp3.isPlaying()) {
            Log.d(TAG, "Activity destruida. A música continua.");
            unbindService(conexao);
        } else {
            Log.d(TAG, "Activity destruida. Para o serviço, pois não existe música tocando.");
            unbindService(conexao);
            stopService(new Intent(this, Mp3Service.class));
        }
    }
}
