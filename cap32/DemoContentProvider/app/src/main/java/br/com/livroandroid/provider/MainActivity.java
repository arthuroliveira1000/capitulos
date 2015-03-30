package br.com.livroandroid.provider;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import br.com.livroandroid.provider.exemplo1.ListaContatosActivity;

/**
 * Exemplos de Layouts
 *
 * @author rlecheta
 *
 */
public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        setContentView(R.layout.activity_main);

        String[] items = new String[] {
                "Lista de Contatos - simples",
                "Lista de Contatos - SimpleCursorAdapter",
                "Lista de Contatos - Custom CursorAdapter",
                "Lista de Contatos - CursorLoader",
        };

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items));
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        try {
            switch (position) {
                case 0:
                    startActivity(new Intent(getBaseContext(), br.com.livroandroid.provider.exemplo1.ListaContatosActivity.class));
                    break;
                case 1:
                    startActivity(new Intent(getBaseContext(), br.com.livroandroid.provider.exemplo2.ListaContatosActivity.class));
                    break;
                case 2:
                    startActivity(new Intent(getBaseContext(), br.com.livroandroid.provider.exemplo3.ListaContatosActivity.class));
                    break;
                case 3:
                    startActivity(new Intent(getBaseContext(), br.com.livroandroid.provider.exemplo4.ListaContatosActivity.class));
                    break;
                default:
                    finish();
                    break;
            }
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), "Erro :" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}