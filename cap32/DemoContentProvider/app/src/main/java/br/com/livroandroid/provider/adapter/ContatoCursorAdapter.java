package br.com.livroandroid.provider.adapter;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.QuickContactBadge;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.livroandroid.provider.R;
import br.com.livroandroid.provider.R;
import br.com.livroandroid.provider.agenda.Agenda;
import br.com.livroandroid.provider.agenda.Contato;

public class ContatoCursorAdapter extends CursorAdapter {

    int[] index;

    public ContatoCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        index = new int[] {
                cursor.getColumnIndex(ContactsContract.Contacts._ID),
        };
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_contato, parent,false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tNome = (TextView) view.findViewById(R.id.tNome);
        QuickContactBadge img = (QuickContactBadge) view.findViewById(R.id.img);

        Long id = mCursor.getLong(index[0]);
        Contato c = new Agenda(context).getContatoById(id);

        Uri uriContato = c.getUri();
        tNome.setText(c.nome);

        img.assignContactUri(uriContato);
        Picasso.with(context).load(uriContato).into(img);
    }
}

