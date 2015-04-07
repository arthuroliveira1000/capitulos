package br.com.livroandroid.sensores;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Exibe os sensores disponiveis
 *
 * @author rlecheta
 */
public class ListaSensoresActivity extends ActionBarActivity implements SensorEventListener, AdapterView.OnItemSelectedListener {

    private SensorManager sensorManager;
    private List<Sensor> sensorList;
    private TextView text;
    private Sensor sensor;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        setContentView(R.layout.activity_lista_sensores);

        text = (TextView) findViewById(R.id.text);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);

        List<String> nomes = new ArrayList<>();
        for (Sensor s : sensorList) {
            nomes.add(s.getName() + " - " + s.getVendor() + " - " + s.getType());
        }

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        int layout = android.R.layout.simple_list_item_1;
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, layout, nomes);
        spinner.setAdapter(adaptador);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        sensor = sensorList.get(position);
        String msg = sensor.getName() + " - " + sensor.getVendor();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        // Cancela o sensor atual
        sensorManager.unregisterListener(this);
        // Registra o sensor selecionado
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Mudou o status de precisão do cursor
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // Lê os valores do sensor
        StringBuffer sb = new StringBuffer("Sensor: ").append(sensor.getName()).append("\n");
        for (int i = 0; i < event.values.length; i++) {
            sb.append(i).append(": ").append(event.values[i]).append("\n");
        }
        text.setText(sb.toString());
    }


}