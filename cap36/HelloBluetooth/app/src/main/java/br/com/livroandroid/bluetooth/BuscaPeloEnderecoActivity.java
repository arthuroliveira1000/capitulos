package br.com.livroandroid.bluetooth;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

/**
 * @author Ricardo Lecheta
 *
 */
public class BuscaPeloEnderecoActivity extends ActionBarActivity {
	private BluetoothAdapter btfAdapter;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		// Bluetooth adapter
		btfAdapter = BluetoothAdapter.getDefaultAdapter();

		if (btfAdapter != null && btfAdapter.isEnabled()) {
			BluetoothDevice device = btfAdapter.getRemoteDevice("40:FC:89:6D:0A:81"); // endere�o do Xoom
			String nome = device.getName();
			String endereco = device.getAddress();
			String msg = nome + " - " + endereco;
			Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
		}
	}
}
