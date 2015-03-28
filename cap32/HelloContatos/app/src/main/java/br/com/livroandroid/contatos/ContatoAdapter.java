package br.com.livroandroid.contatos;

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

/**
 * Created by Usuário on 28/03/2015.
 */
public class ContatoAdapter extends CursorAdapter {

    int[] indices;

    public ContatoAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        indices = new int[] {
                cursor.getColumnIndex(ContactsContract.Contacts._ID),
                cursor.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY),
                cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)
        };
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_contato, parent,false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tNome = (TextView) view.findViewById(R.id.tNome);
        QuickContactBadge badge = (QuickContactBadge)
                view.findViewById(R.id.badgeFoto);

        Uri uriContato = ContactsContract.Contacts.getLookupUri(
                mCursor.getLong(indices[0]),
                mCursor.getString(indices[1]));

        tNome.setText(cursor.getString(indices[2]));
        badge.assignContactUri(uriContato);
        Picasso.with(mContext)
                .load(uriContato)
                .placeholder(R.mipmap.ic_launcher)
                .into(badge);
    }
}