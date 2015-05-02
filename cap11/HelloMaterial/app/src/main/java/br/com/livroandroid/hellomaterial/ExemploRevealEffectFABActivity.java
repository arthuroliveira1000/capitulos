package br.com.livroandroid.hellomaterial;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;


/*
*
* http://www.google.com/design/spec/components/buttons.html#buttons-flat-raised-buttons
*
* */
public class ExemploRevealEffectFABActivity extends AppCompatActivity {

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemplo_reveal_effect_fab);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void onClickFab1(View view) {
        Toast.makeText(this,"FAB 1",Toast.LENGTH_SHORT).show();
        View fab2 = findViewById(R.id.btFab2);
        RevealEffect.show(fab2, 800);
    }

    public void onClickFab2(View view) {
        Toast.makeText(this,"FAB 2",Toast.LENGTH_SHORT).show();
        RevealEffect.hide(view, 800);
    }
}
