package br.com.livroandroid.hellomaterial;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


/*
* FAB
* http://www.google.com/design/spec/components/buttons.html#buttons-flat-raised-buttons
*
* */
public class ExemploFloatingButtonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemplo_floating_button);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void onClickFab1(View view) {
        Toast.makeText(this, "FAB 1", Toast.LENGTH_SHORT).show();
    }


    public void onClickFab2(View view) {
        Toast.makeText(this, "FAB 2 - Android Design Support Library", Toast.LENGTH_SHORT).show();
    }
}
