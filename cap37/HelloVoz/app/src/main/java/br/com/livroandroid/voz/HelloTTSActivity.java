package br.com.livroandroid.voz;

import android.media.MediaPlayer;
import android.os.Environment;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.HashMap;
import java.util.Locale;

import livroandroid.lib.activity.BaseActivity;
import livroandroid.lib.utils.SDCardUtils;


public class HelloTTSActivity extends BaseActivity implements TextToSpeech.OnInitListener {

    private static final String TAG = "livroandroid";
    private TextView tMsg;
    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_tts);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tMsg = (TextView) findViewById(R.id.tMsg);

        tts = new TextToSpeech(this, this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hello_tts, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onInit(int status) {
        Log.d(TAG,"Engine TTS inicializada com sucesso.");
        Locale locale = new Locale("pt","BR");
        int cod = tts.isLanguageAvailable(locale);
        if(cod == TextToSpeech.LANG_AVAILABLE) {
            //tts.setLanguage(locale);
        } else {
            toast("Idioma não suportado: " + locale);
        }
    }

    public void onClickFalarTexto(View view) {
        String s = tMsg.getText().toString();
        tts.speak(s, TextToSpeech.QUEUE_FLUSH, null);
        Log.d(TAG,"Speak: " + s);
    }

    public void onClickSalvar(View view) {
        String s = tMsg.getText().toString();
        // Sintetiza a voz para arquivo
        File file = SDCardUtils.getPublicFile("falando.wav", Environment.DIRECTORY_MUSIC);
        HashMap<String, String> params = new HashMap<String,String>();
        tts.synthesizeToFile(s, params, file.getAbsolutePath());
        toast("Voz salva em arquivo: " + file);
    }

    public void onClickFalarArquivo(View view) {
        File file = SDCardUtils.getPublicFile("falando.wav", Environment.DIRECTORY_MUSIC);
        if(file.exists()) {
            try {
                MediaPlayer mp = new MediaPlayer();
                mp.setDataSource(file.getAbsolutePath());
                mp.prepare();
                mp.start();
            } catch (Exception e) {
                Log.e(TAG,"Erro ao tocar: " + e.getMessage(), e);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Libera os recursos da engine do TTS
        tts.shutdown();
    }
}
