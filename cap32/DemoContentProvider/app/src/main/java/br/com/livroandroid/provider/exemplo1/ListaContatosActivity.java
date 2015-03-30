package br.com.livroandroid.provider.exemplo1;

import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.livroandroid.provider.agenda.Agenda;
import br.com.livroandroid.provider.agenda.Contato;
import br.com.livroandroid.provider.agenda.ContatoAdapter;

/**
 * Utiliza a classe Agenda para buscar no content provider.
 */
public class ListaContatosActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private static final String TAG = "livroandroid";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(br.com.livroandroid.provider.R.layout.activity_lista_contatos);

        final ListView listView = (ListView) findViewById(br.com.livroandroid.provider.R.id.listView);
        listView.setOnItemClickListener(this);

        final Agenda a = new Agenda(this);

        new Thread(){
            @Override
            public void run() {
                // Imprime os contatos
                final List<Contato> contatos = a.getContatos();
                for (Contato c: contatos) {
                    Log.d(TAG,"Nome: " + c.nome + ", fone: " + c.fones);
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        listView.setAdapter(new ContatoAdapter(getBaseContext(),contatos));
                    }
                });
            }
        }.start();

        //printContatos();
    }

    private void printContatos() {
        // Uri: "content://com.android.contacts/contacts"
        Uri contatos = ContactsContract.Contacts.CONTENT_URI;
        Cursor cursor = getContentResolver().query(contatos, null, null, null, null);
        int count = cursor.getCount();
        Log.i(TAG,"Foram encontrados "+count+" contatos.");
        while(cursor.moveToNext()){
            String nome = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            Log.i(TAG,"Nome: " + nome);
        }
        // É importante fechar o cursor no final
        cursor.close();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Agenda a = new Agenda(this);
        Contato c = a.getContatoById(id);
        Toast.makeText(this,"Ex1: " + c.nome, Toast.LENGTH_SHORT).show();

    }
}
