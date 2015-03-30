package br.com.livroandroid.provider.agenda;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;

/**
 * Busca os contatos na agenda.
 * 
 * content://com.android.contacts/contacts
 * 
 * @author ricardo
 * 
 */
public class Agenda {
	// content://com.android.contacts/contacts
	private static final Uri URI = Contacts.CONTENT_URI;
    private Context context;

    public Agenda(Context context) {
        this.context = context;
    }
	
	public List<Contato> getContatos() {

		// Recupera o Cursor para percorrer a lista de contatos
		Cursor c = context.getContentResolver().query(URI, null, "has_phone_number=1", null,null);

		return getContatos(c);
	}

    public List<Contato> getContatos(Cursor cursor) {
        List<Contato> contatos = new ArrayList<Contato>();

        try {
            while (cursor.moveToNext()) {
                Contato a = getContato(cursor);
                contatos.add(a);
            }
        } finally {
            // Fecha o Cursor
            cursor.close();
        }

        return contatos;
    }

    public Contato getContatoById(Long id) {
        Uri uri = ContentUris.withAppendedId(Contacts.CONTENT_URI, id);
        Cursor cursor = context.getContentResolver().query(uri, null, "has_phone_number=1", null,null);
        if(cursor.moveToNext()) {
            Contato c = getContato(cursor);
            return c;
        }
        return null;
    }

	public Contato getContato(Cursor cursor) {
		Contato c = new Contato();

		// Id e nome
		long id = cursor.getLong(cursor.getColumnIndexOrThrow(Contacts._ID));
		c.id = id;
		
		String nome = cursor.getString(cursor.getColumnIndexOrThrow(Contacts.DISPLAY_NAME));
		c.nome = nome;

		// Fone
		boolean temFone = "1".equals(cursor.getString(cursor.getColumnIndexOrThrow(Contacts.HAS_PHONE_NUMBER)));
		if (temFone) {
			List<String> fones = getFones(id);
			c.fones = fones;
		}
		return c;
	}

	// Busca os telefones na tabela 'ContactsContract.CommonDataKinds.Phone'
	private List<String> getFones(long id) {
		List<String> fones = new ArrayList<String>();
	
		Cursor cursor = context.getContentResolver().query(
				ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
				ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id,
				null, null);
	
		try {
			while (cursor.moveToNext()) {
				int coluna = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
				String fone = cursor.getString(coluna);
				fones.add(fone);
			}
		} finally {
			cursor.close();
		}
		
		return fones;
	}
}