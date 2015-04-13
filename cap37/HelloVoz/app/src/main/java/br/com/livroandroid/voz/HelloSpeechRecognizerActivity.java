package br.com.livroandroid.voz;

import android.content.Intent;
import android.os.Bundle;
import android.speech.SpeechRecognizer;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.ArrayList;


public class HelloSpeechRecognizerActivity extends HelloRecognizerIntentActivity  {
    // Reconhecedor de voz
    private SpeechRecognizer stt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Cria o SpeechRecognizer e configura o listener
        stt = SpeechRecognizer.createSpeechRecognizer(this);
        stt.setRecognitionListener(new BaseRecognitionListener() {
            public void onResults(Bundle results) {
                // Recupera as possíveis palavras que foram pronunciadas
                ArrayList<String> words = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                Log.d("livroandroid", "onResults: " + words);
                listView.setAdapter(new ArrayAdapter<String>(getBaseContext(),
                        android.R.layout.simple_list_item_1, words));
            }
        });
    }

    @Override
    protected View.OnClickListener onClickSpeak() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ao clicar no botão, recupera a intent e inicia o reconhecimento de voz
                Intent intent = getRecognizerIntent();
                stt.startListening(intent);
            }
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Libera os recursos e finaliza o STT
        stt.stopListening();
        stt.destroy();
    }
}
