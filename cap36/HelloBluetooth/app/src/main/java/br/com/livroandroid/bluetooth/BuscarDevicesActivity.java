package br.com.livroandroid.bluetooth;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Demonstra como buscar dispositivos bluetooth
 * 
 * @author Ricardo Lecheta
 *
 */
public class BuscarDevicesActivity extends ListaPareadosActivity {
	private ProgressDialog dialog;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		// Register for broadcasts when a device is discovered
		this.registerReceiver(mReceiver, new IntentFilter(BluetoothDevice.ACTION_FOUND));

		// Register for broadcasts when discovery has finished
		this.registerReceiver(mReceiver, new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED));

		buscar();
	}

	private void buscar() {

		// Garante que n�o existe outra busca sendo realizada
		if (btfAdapter.isDiscovering()) {
			btfAdapter.cancelDiscovery();
		}

		// Dispara a busca
		btfAdapter.startDiscovery();

		dialog = ProgressDialog.show(this, "Exemplo", "Buscando aparelhos bluetooth...", false, true);
	}

	// Receiver para receber os broadcasts do bluetooth
	private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
		// Quantidade de dispositivos encontrados
		private int count;

		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();

			// Se um device foi encontrado
			if (BluetoothDevice.ACTION_FOUND.equals(action)) {
				// Recupera o device da intent
				BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

				// Apenas insere na lista os devices que ainda n�o est�o pareados
				if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
					lista.add(device);
					Toast.makeText(context, "Encontrou: " + device.getName()+":"+device.getAddress(), Toast.LENGTH_SHORT).show();
					count++;
				}
			} else if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
				// Iniciou a busca
				count = 0;
				Toast.makeText(context, "Busca iniciada.", Toast.LENGTH_SHORT).show();
			} else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
				// Terminou a busca
				Toast.makeText(context, "Busca finalizada. " + count + " devices encontrados", Toast.LENGTH_LONG).show();
				
				dialog.dismiss();
				
				// Atualiza o listview. Agora via possuir todos os devices pareados, mais os novos que foram encontrados.
				updateLista();
			}
		}
	};

	@Override
	protected void onDestroy() {
		super.onDestroy();

		// Garante que a busca � cancelada ao sair
		if (btfAdapter != null) {
			btfAdapter.cancelDiscovery();
		}

		// Cancela o registro do receiver
		this.unregisterReceiver(mReceiver);
	}
}
