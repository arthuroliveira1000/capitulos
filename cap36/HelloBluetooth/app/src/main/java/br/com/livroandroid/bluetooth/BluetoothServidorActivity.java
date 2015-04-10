package br.com.livroandroid.bluetooth;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Fica aguardando alguém se conectar
 * 
 * @author Ricardo Lecheta
 * 
 */
public class BluetoothServidorActivity extends BluetoothCheckActivity {
	private static final UUID uuid = UUID.fromString("fa87c0d0-afac-11de-8a39-0800200c9a66");
	private boolean running;
	private BluetoothSocket socket;
	private InputStream in;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.activity_receber_msg);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	protected void onResume() {
		super.onResume();

		// Se o bluetooth est� ligado, vamos iniciar a Thread do servidor
		if (btfAdapter != null && btfAdapter.isEnabled()) {
			new ThreadServidor().start();
			running = true;
		}
	}

	// Thread para controlar a conex�o, e n�o travar a tela
	class ThreadServidor extends Thread {

		@Override
		public void run() {
			try {
				// Abre o socket servidor (Quem for conectar, precisa utilizar o
				// mesmo UUID)
				BluetoothServerSocket serverSocket = btfAdapter
						.listenUsingRfcommWithServiceRecord("LivroAndroid", uuid);
				// Fica aguardando algu�m conectar (Esta chamada � bloqueante)
				try {
					socket = serverSocket.accept();
				} catch (Exception e) {
					// Se deu erro, vamos encerrar aqui
					return;
				}

				if (socket != null) {
					// Algu�m conectou, ent�o encerrar o socket server
					serverSocket.close();

					// Depois que algu�m conectou, pega a InputStream para ler as mensagens
					in = socket.getInputStream();

					// Recupera o dispostivo cliente que fez a conex�o
					BluetoothDevice device = socket.getRemoteDevice();
					updateViewConectou(device);

					byte[] bytes = new byte[1024];
					int length;

					// Fica em loop, para receber as mensagens
					while (running) {
						// L� a mensagem
						length = in.read(bytes);
						String mensagemRecebida = new String(bytes, 0, length);
						TextView tMsg = (TextView) findViewById(R.id.tMsg);
						final String s = tMsg.getText().toString() + mensagemRecebida + "\n";

						updateViewMensagem(s);
					}
				}

			} catch (IOException e) {
				error(e);
				running = false;
			}
		}
	}

	private void error(final IOException e) {
		Log.e(TAG, "Erro no servidor: " + e.getMessage(), e);
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(getBaseContext(), "Erro: " + e.getMessage() + " - " + e.getClass(), Toast.LENGTH_SHORT)
						.show();
			}
		});
	}

	private void error(final String s) {
		Log.e(TAG, "Erro : " + s);
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(getBaseContext(), "Erro: " + s, Toast.LENGTH_SHORT).show();
			}
		});
	}

	// Exibe a mensagem na tela, informando o nome do dispositivo que fez a
	// conex�o
	private void updateViewConectou(final BluetoothDevice device) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				try {
					TextView tNome = (TextView) findViewById(R.id.tNomeDevice);
					tNome.setText(device.getName() + " - " + device.getAddress());
				} catch (Exception e) {
					error(e.getMessage());
				}
			}
		});
	}

	// Exibe a mensagem recebida na tela (que foi enviada de outro dispositivo)
	private void updateViewMensagem(final String s) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				TextView tMsg = (TextView) findViewById(R.id.tMsg);
				tMsg.setText(s);
			}
		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		running = false;

		try {
			if (in != null) {
				in.close();
			}
		} catch (IOException e1) {
		}
		try {
			if (socket != null) {
				socket.close();
			}
		} catch (IOException e) {
		}

	}
}
