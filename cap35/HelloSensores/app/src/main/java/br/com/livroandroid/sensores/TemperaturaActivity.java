package br.com.livroandroid.sensores;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Ricardo Lecheta
 * 
 */
public class TemperaturaActivity extends ActionBarActivity implements SensorEventListener {

	private static final String TAG = TemperaturaActivity.class.getName();
	private SensorManager sensorManager;
	private Sensor sensor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sensor_simples);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

		if (sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) != null) {
			sensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
		} else {
			Toast.makeText(this, "Sensor TYPE_AMBIENT_TEMPERATURE não disponível", Toast.LENGTH_SHORT).show();
			
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		if(sensor != null) {
			sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
		}
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
		float valor = event.values[0];

		((TextView) findViewById(R.id.tValor)).setText("Temperatura: " + valor);
	}
}
