package br.com.livroandroid.provider.exemplo4;


import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import br.com.livroandroid.provider.R;
import br.com.livroandroid.provider.agenda.Agenda;
import br.com.livroandroid.provider.agenda.Contato;

/**
 * Mostra como utilizar o CursorLoader da API de Loaders
 */
public class ListaContatosActivity extends ActionBarActivity implements AdapterView.OnItemClickListener, LoaderManager.LoaderCallbacks<Cursor> {

    private static final String TAG = "livroandroid";
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_contatos);

        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);

        // Imprime os contatos
        Uri contatos = ContactsContract.Contacts.CONTENT_URI;
        Cursor cursor = getContentResolver().query(contatos, null, "has_phone_number=1", null, null);

        getSupportLoaderManager().initLoader(1, null, this);

        /*CursorAdapter adapter = new SimpleCursorAdapter(
                this,
                R.layout.adapter_contato,
                cursor,
                new String[]{ContactsContract.Contacts.DISPLAY_NAME},
                new int[]{R.id.tNome},
                0);
        listView.setAdapter(adapter);*/
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Agenda a = new Agenda(this);
        Contato c = a.getContatoById(id);
        Toast.makeText(this,"Ex4: " + c.nome, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri contatos = ContactsContract.Contacts.CONTENT_URI;
        return new CursorLoader(getApplicationContext(),contatos,
                null,"has_phone_number=1",null,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        Toast.makeText(this,"F: " + data.getCount(), Toast.LENGTH_SHORT).show();
        listView.setAdapter(adapter);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
