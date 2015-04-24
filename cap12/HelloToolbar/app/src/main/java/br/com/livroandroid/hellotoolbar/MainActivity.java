package br.com.livroandroid.hellotoolbar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    public void onCreate(Bundle icicle) {

        super.onCreate(icicle);

        String[] items = new String[]{
                "Toolbar 1 - como action bar",
                "Toolbar 2 - standalone pela API"
        };

        ListView listView = new ListView(this);
        setContentView(listView);
        listView.setOnItemClickListener(this);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        try {
            switch (position) {
                case 0:
                    startActivity(new Intent(this, ExemploToolbar1Activity.class));
                    break;
                case 1:
                    startActivity(new Intent(this, ExemploToolbar2Activity.class));
                    break;


                default:
                    finish();
                    break;
            }
        } catch (Exception e) {
            Toast.makeText(this, "Erro :" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}