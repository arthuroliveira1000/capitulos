package br.com.livroandroid.gestos;

import android.app.Activity;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.gesture.Prediction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Exemplo de gestos
 *
 * @author rlecheta
 */

public class MainActivity extends Activity implements OnGesturePerformedListener {
    private GestureLibrary gestureLib;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.text);
        // Configura o listener do GestureOverlayView
        GestureOverlayView gestureOverlayView = (GestureOverlayView) findViewById(R.id.gestureView);
        gestureOverlayView.addOnGesturePerformedListener(this);
        // Carrega a biblioteca de gestos utilizando o arquivo /res/raw/gestures
        gestureLib = GestureLibraries.fromRawResource(this, R.raw.gestures);
        if (!gestureLib.load()) {
            finish();
        }
    }

    @Override
    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
        // Faz a biblioteca de gestos reconhecer o movimento
        ArrayList<Prediction> predictions = gestureLib.recognize(gesture);
        Prediction maxScore = null;
        for (Prediction prediction : predictions) {
            // Vamos aceitar somente escores maiores que cinco
            if (prediction.score > 5.0) {
                if (maxScore == null || maxScore.score < prediction.score) {
                    maxScore = prediction;
                }
            }
        }
        // Se encontrou algum gesto com escore alto, vamos mostrar o texto
        if (maxScore != null) {
            // Se o score é maior que 5
            String desc = maxScore.name + ", score: " + maxScore.score;
            text.setText(desc);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add) {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
