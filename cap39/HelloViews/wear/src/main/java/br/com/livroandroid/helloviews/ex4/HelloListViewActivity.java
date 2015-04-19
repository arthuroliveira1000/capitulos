package br.com.livroandroid.helloviews.ex4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.WearableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.livroandroid.helloviews.R;

/**
 * Created by ricardo on 19/04/15.
 */
public class HelloListViewActivity extends Activity implements WearableListView.ClickListener {
    // Sample dataset for the list
    private List<Carro> carros = new ArrayList<Carro>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_listview);

        WearableListView listView =
                (WearableListView) findViewById(R.id.wearable_list);

        carros.add(new Carro("Ferrari FF",R.drawable.ferrari_ff));
        carros.add(new Carro("AUDI GT Spyder",R.drawable.audi_spyder));
        carros.add(new Carro("Porsche Panamera",R.drawable.porsche_panamera));
        carros.add(new Carro("Lamborghini Aventador",R.drawable.lamborghini_aventador));
        carros.add(new Carro("Chevrolet Corvette Z06",R.drawable.chevrolet_corvette_z06));
        carros.add(new Carro("BMW M5",R.drawable.bmw));
        carros.add(new Carro("Renault Megane",R.drawable.renault_megane_trophy));
        carros.add(new Carro("Maserati Grancabrio Sport",R.drawable.maserati_grancabrio_sport));
        carros.add(new Carro("McLAREN MP4-12C",R.drawable.mclaren));
        carros.add(new Carro("MERCEDES-BENZ C63 AMG",R.drawable.mercedes_bens));

        listView.setAdapter(new HelloAdapter(this, carros));
        listView.setClickListener(this);
    }

    // WearableListView click listener
    @Override
    public void onClick(WearableListView.ViewHolder v) {
        Integer position = (Integer) v.itemView.getTag();
        Carro c = carros.get(position);
        Toast.makeText(this,"Carro: " + c.nome,Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,CarroActivity.class);
        intent.putExtra("carro",c);
        startActivity(intent);
    }

    @Override
    public void onTopEmptyRegionClick() {
    }
}