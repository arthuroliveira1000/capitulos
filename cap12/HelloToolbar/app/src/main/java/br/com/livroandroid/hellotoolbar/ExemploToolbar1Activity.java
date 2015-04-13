package br.com.livroandroid.hellotoolbar;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


/**
 * 1) Esta activity desligou a action bar padrão pelo Tema
 *
 * android:theme="@style/AppTheme.NoActionBar"
 *
 * 2) Foi adicionado a Toolbar no layout
 *
 * 3) A toolbar é setada como a ActionBar
 *
 * setSupportActionBar(toolbar);
 *
 * 4) De resto pode usar os métodos da action bar normalmente.
 */
public class ExemploToolbar1Activity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemplo_toolbar1);

        // Aqui é a mágica (A toolbar será a action bar).
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(this, "SETTINGS", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_go) {
            Toast.makeText(this, "GO", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

}
