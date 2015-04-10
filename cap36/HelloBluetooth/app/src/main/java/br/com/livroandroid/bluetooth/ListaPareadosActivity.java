package br.com.livroandroid.bluetooth;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Lista os devices pareados
 * 
 * @author Ricardo Lecheta
 * 
 */
public class ListaPareadosActivity extends BluetoothCheckActivity implements OnItemClickListener {
	protected List<BluetoothDevice> lista;
	private ListView listView;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		setContentView(R.layout.activity_listview);
		listView = (ListView) findViewById(R.id.listView);
	}

	@Override
	protected void onResume() {
		super.onResume();

		// Note que o BluetoothAdapter � iniciado na classe m�e
		if (btfAdapter != null) {
			// Lista de aparelhos pareados
			Set<BluetoothDevice> pareados = btfAdapter.getBondedDevices();
			lista = new ArrayList<BluetoothDevice>(pareados);

			updateLista();
		}
	}

	protected void updateLista() {
		// Cria o array com o nome de cada device
		List<String> nomes = new ArrayList<String>();
		for (BluetoothDevice device : lista) {
			boolean pareado = device.getBondState() == BluetoothDevice.BOND_BONDED;
			nomes.add(device.getName() + " - " + device.getAddress() + (pareado ? " *pareado" : ""));
		}

		// Cria o adapter para popular o ListView
		int layout = android.R.layout.simple_list_item_1;
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, layout, nomes);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
	}

	@Override
	// Implements android.widget.AdapterView.OnItemClickListener
	public void onItemClick(AdapterView<?> adapterView, View view, int idx, long id) {
		// Recupera o device selecionado
		BluetoothDevice device = lista.get(idx);
		String msg = device.getName() + " - " + device.getAddress();
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

		// // Vai para a tela para enviar a mensagem
		Intent intent = new Intent(this, BluetoothClienteActivity.class);
		intent.putExtra(BluetoothDevice.EXTRA_DEVICE, device);
		startActivity(intent);
	}
}
