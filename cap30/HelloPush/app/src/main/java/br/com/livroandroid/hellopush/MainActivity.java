package br.com.livroandroid.hellopush;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private Context getContext() {
        return this;
    }

    // Mostra msg de debug na tela
    private void setText(final String s) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView text = (TextView) findViewById(R.id.text);
                text.setText(s);
            }
        });
    }

    // Faz o registro no GCM
    public void onClickRegistrar(View view) {
        new Thread(){
            @Override
            public void run() {
                super.run();

                // Faz o registro e pega o registration id
                String regId = GCM.register(getContext(), Constants.PROJECT_NUMBER);
                setText("Registrado com sucesso.");
            }
        }.start();
    }

    // Cancela o registro no GCM
    public void onClickCancelar(View view) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                GCM.unregister(getContext());
                setText("Cancelado com sucesso!");
            }
        }.start();
    }
}
