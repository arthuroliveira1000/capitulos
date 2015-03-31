package br.com.livroandroid.contatos.exemplo1;

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

import br.com.livroandroid.contatos.adapter.ContatoAdapter;
import br.com.livroandroid.contatos.agenda.Agenda;
import br.com.livroandroid.contatos.agenda.Contato;

/**
 * Utiliza a classe Agenda para buscar no content contatos.
 */
public class ListaContatosActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private static final String TAG = "livroandroid";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(br.com.livroandroid.contatos.R.layout.activity_lista_contatos);

        final ListView listView = (ListView) findViewById(br.com.livroandroid.contatos.R.id.listView);
        listView.setOnItemClickListener(this);

        // Lista os contatos
        final Agenda a = new Agenda(this);
        final List<Contato> contatos = a.getContatos();
        final ContatoAdapter adapter = new ContatoAdapter(getBaseContext(), contatos);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Agenda a = new Agenda(getBaseContext());
                a.delete(id);
                adapter.notifyDataSetChanged();
                Toast.makeText(getBaseContext(), "Contato excluído com sucesso.", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        //printContatos();
    }

    private void printContatos() {
        // Uri: "content://com.android.contacts/contacts"
        Uri contatos = ContactsContract.Contacts.CONTENT_URI;
        Cursor cursor = getContentResolver().query(contatos, null, ContactsContract.Contacts.HAS_PHONE_NUMBER +" = 1 ", null, null);
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
        c.show(this);
    }
}
