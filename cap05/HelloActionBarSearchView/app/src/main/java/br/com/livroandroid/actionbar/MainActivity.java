package br.com.livroandroid.actionbar;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.ShareActionProvider;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getActionBar();
        actionBar.setTitle("Capítulo 5");

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Infla o menu com os botões da action bar
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // SearchView
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(onSearch());

        // ShareActionProvider
        MenuItem shareItem = menu.findItem(R.id.action_share);
        ShareActionProvider share = (ShareActionProvider) shareItem.getActionProvider();
        share.setShareIntent(getDefaultIntent());

        return true;
    }

    private Intent getDefaultIntent() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/*");
        intent.putExtra(Intent.EXTRA_TEXT, "Texto para compartilhar");
        return intent;
    }

    private SearchView.OnQueryTextListener onSearch() {
        return new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String query) {
                toast("Buscar o texto: " + query);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        };
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            toast("Clicou no Search!");
            return true;
        } else if (id == R.id.action_refresh) {
            toast("Clicou no Refresh!");
            return true;
        } else if (id == R.id.action_settings) {
            toast("Clicou no Settings!");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
