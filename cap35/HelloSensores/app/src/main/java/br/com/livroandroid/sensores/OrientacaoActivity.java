package br.com.livroandroid.sensores;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.widget.Toast;

/**
 * @author Ricardo Lecheta
 * 
 */
public class OrientacaoActivity extends ActionBarActivity implements
		SensorEventListener {

	/**
	 * Deprecado no Android 2.2 e agora falam para utilizar o método getOrientation()
	 * 
	 * Mas este sensor continua sendo muito bom e simples para implementar bússulas
	 */
	private static final int TIPO_SENSOR = Sensor.TYPE_ORIENTATION;
	private SensorManager sensorManager;
	private Sensor sensor;
	private BussulaView bussula;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		bussula = new BussulaView(this);
		setContentView(bussula);

		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

		if (sensorManager.getDefaultSensor(TIPO_SENSOR) != null) {
			sensor = sensorManager.getDefaultSensor(TIPO_SENSOR);
		} else {
			Toast.makeText(this, "Sensor TYPE_ORIENTATION não disponível", Toast.LENGTH_SHORT).show();
			
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

//		((TextView) findViewById(R.id.tX)).setText("Luz: " + valor);
		
//		progress.setProgress((int)valor);
		
		bussula.update((int) valor);
	}
}
