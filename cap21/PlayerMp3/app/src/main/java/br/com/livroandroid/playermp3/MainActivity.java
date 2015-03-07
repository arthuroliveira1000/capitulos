package br.com.livroandroid.playermp3;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    private static final String TAG = "livro";
    //Classe que encapsula o MediaPlayer
    private PlayerMp3 player = new PlayerMp3();
    private EditText text;
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        setContentView(R.layout.activity_main);

        text = (EditText) findViewById(R.id.arquivo);

        findViewById(R.id.start).setOnClickListener(this);
        findViewById(R.id.pause).setOnClickListener(this);
        findViewById(R.id.stop).setOnClickListener(this);
    }
    /**
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    public void onClick(View view) {
        try {
            if (view.getId() == R.id.start) {
                String mp3 = text.getText().toString();
                player.start(mp3);
            } else if (view.getId() == R.id.pause) {
                player.pause();
            } else if (view.getId() == R.id.stop) {
                player.stop();
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Libera recursos do MediaPlayer
        player.close();
    }
}
