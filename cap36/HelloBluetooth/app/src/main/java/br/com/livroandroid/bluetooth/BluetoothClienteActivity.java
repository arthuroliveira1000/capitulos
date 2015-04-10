package br.com.livroandroid.bluetooth;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Conecta no servidor para enviar mensagens
 * 
 * @author Ricardo Lecheta
 * 
 */
public class BluetoothClienteActivity extends ActionBarActivity {
	private static final String TAG = "livroandroid";
	private static final UUID uuid = UUID.fromString("fa87c0d0-afac-11de-8a39-0800200c9a66");
	private BluetoothDevice device;
	private TextView tMsg;
	private OutputStream out;
	private BluetoothSocket socket;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.activity_enviar_msg);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		tMsg = (TextView) findViewById(R.id.tMsg);

		device = getIntent().getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
		TextView tNome = (TextView) findViewById(R.id.tNomeDevice);
		tNome.setText(device.getName() + " - " + device.getAddress());

		findViewById(R.id.btConectar).setOnClickListener(onClickConectar());

		findViewById(R.id.btEnviar).setOnClickListener(onClickEnviar());
	}

	private OnClickListener onClickConectar() {
		return new OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					socket = device.createRfcommSocketToServiceRecord(uuid);
					socket.connect();

					out = socket.getOutputStream();
					
					// Se chegou aqui � porque conectou
					if(out != null) {
						// Habilita o bot�o para enviar mensagens
						findViewById(R.id.btConectar).setEnabled(false);
						findViewById(R.id.btEnviar).setEnabled(true);
					}
					
				} catch (IOException e) {
					error(e);
					Log.e(TAG, "Erro ao conectar: " + e.getMessage(), e);
				}
			}
		};
	}

	private void error(final IOException e) {
		Log.e(TAG, "Erro no client: " + e.getMessage(), e);
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(getBaseContext(), "Erro: " + e.getMessage() + " - " + e.getClass(), Toast.LENGTH_SHORT)
						.show();
			}
		});
	}

	private OnClickListener onClickEnviar() {
		return new OnClickListener() {
			@Override
			public void onClick(View v) {
				String msg = tMsg.getText().toString();

				try {
					if (out != null) {
						out.write(msg.getBytes());
					}
				} catch (IOException e) {
					Log.e(TAG, "Erro ao escrever: " + e.getMessage(), e);
					error(e);
				}
			}
		};
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		try {
			if (out != null) {
				out.close();
			}
		} catch (IOException e) {
		}
		try {
			if (socket != null) {
				socket.close();
			}
		} catch (IOException e) {
		}
	}
}
