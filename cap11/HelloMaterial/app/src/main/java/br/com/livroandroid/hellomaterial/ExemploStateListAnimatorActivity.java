package br.com.livroandroid.hellomaterial;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;


public class ExemploStateListAnimatorActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemplo_state_list_animator);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
