package br.com.livroandroid.hellomaterial;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;


public class ExemploRippleActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemplo_ripple);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
