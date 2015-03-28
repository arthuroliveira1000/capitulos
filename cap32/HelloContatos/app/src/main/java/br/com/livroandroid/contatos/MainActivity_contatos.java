package br.com.livroandroid.contatos;

import android.content.Intent;
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

public class MainActivity_contatos extends ActionBarActivity implements AdapterView.OnItemClickListener, LoaderManager.LoaderCallbacks<Cursor> {

    private static final String TAG = "livroandroid";

    private final static String[] COLUNAS = {
            ContactsContract.Contacts._ID,
            ContactsContract.Contacts.LOOKUP_KEY,
            ContactsContract.Contacts.DISPLAY_NAME};

    private CursorAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.listView);
        //mAdapter = new ContatoAdapter(this, null);

        mAdapter = new SimpleCursorAdapter(this,
                R.layout.adapter_contato, null,
                new String[] { ContactsContract.Contacts.DISPLAY_NAME },
                new int[] { R.id.tNome }, 0);

        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(this);

        getSupportLoaderManager().initLoader(0, null, this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Cursor cursor = mAdapter.getCursor();
        cursor.moveToPosition(position);

        long idContato = cursor.getLong(
                cursor.getColumnIndex(ContactsContract.Contacts._ID));
        String lookupKey = cursor.getString(
                cursor.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY));

        Uri uriContato = ContactsContract.Contacts.getLookupUri(idContato, lookupKey);

        Intent it = new Intent(Intent.ACTION_VIEW, uriContato);
        startActivity(it);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(
                this,
                ContactsContract.Contacts.CONTENT_URI,
                COLUNAS,
                ContactsContract.Contacts.HAS_PHONE_NUMBER +" = 1 ",
                null,
                ContactsContract.Contacts.DISPLAY_NAME
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }
}
