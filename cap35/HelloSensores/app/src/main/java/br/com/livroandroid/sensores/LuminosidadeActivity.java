package br.com.livroandroid.sensores;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Ricardo Lecheta
 * 
 */
public class LuminosidadeActivity extends ActionBarActivity implements
		SensorEventListener {

	private static final int TIPO_SENSOR = Sensor.TYPE_LIGHT;
	private SensorManager sensorManager;
	private Sensor sensor;
	private SeekBar progress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sensor_progressbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		progress = (SeekBar) findViewById(R.id.progress);

		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

		if (sensorManager.getDefaultSensor(TIPO_SENSOR) != null) {
			sensor = sensorManager.getDefaultSensor(TIPO_SENSOR);
			
			// Define o valor maximo no ProgressBar
			float max = sensor.getMaximumRange();
			progress.setMax(10000);
			
			Toast.makeText(this, "Sensor TYPE_LIGHT max " + max, Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(this, "Sensor TYPE_LIGHT não disponível", Toast.LENGTH_SHORT).show();
			
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (sensor != null) {
			sensorManager.registerListener(this, sensor,
					SensorManager.SENSOR_DELAY_NORMAL);
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

		((TextView) findViewById(R.id.tValor)).setText("Luz: " + valor);
		
		progress.setProgress((int)valor);
	}
}
