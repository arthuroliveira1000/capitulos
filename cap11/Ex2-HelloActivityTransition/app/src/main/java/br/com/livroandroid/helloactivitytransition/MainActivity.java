package br.com.livroandroid.helloactivitytransition;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Efeito padrão de cross-fading
     * @param view
     */
    public void onClickPlaneta_Exemplo1(View view) {
        Intent intent = new Intent(getBaseContext(), PlanetaActivity.class);

        ActivityOptionsCompat opts = ActivityOptionsCompat.makeSceneTransitionAnimation(this);
        ActivityCompat.startActivity(this, intent, opts.toBundle());
    }

    /**
     * Demonstra uma animação customizada de entrada e saída
     * @param view
     */
    public void onClickPlaneta(View view) {
        Intent intent = new Intent(getBaseContext(), PlanetaActivity.class);

        // Fade-In e Fade-Out
        //ActivityOptionsCompat opts = ActivityOptionsCompat.makeCustomAnimation(this, android.R.anim.fade_in, android.R.anim.fade_out);
        //ActivityCompat.startActivity(this, intent, opts.toBundle());

        // slide_in_left e slide_out_right
        ActivityOptionsCompat opts = ActivityOptionsCompat.makeCustomAnimation(this, R.anim.slide_in_left, R.anim.slide_out_left);
        ActivityCompat.startActivity(this, intent, opts.toBundle());
    }

    public void onClickPlaneta_Exemplo_SharedElements(View view) {
        ImageView img = (ImageView) findViewById(R.id.img);
        Intent intent = new Intent(getBaseContext(), PlanetaActivity.class);
        String key = getString(R.string.transition_key);

        // Android 5.0 ou superior
        //ActivityOptions opts = ActivityOptions.makeSceneTransitionAnimation(this, img, key);
        //startActivity(intent, opts.toBundle());

        // AppCompat
        ActivityOptionsCompat opts = ActivityOptionsCompat.makeSceneTransitionAnimation(this, img, key);
        ActivityCompat.startActivity(this, intent, opts.toBundle());
    }
}
