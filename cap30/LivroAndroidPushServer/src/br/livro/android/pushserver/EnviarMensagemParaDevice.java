package br.livro.android.pushserver;

import java.io.IOException;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Sender;

/**
 * @author Ricardo Lecheta
 */
public class EnviarMensagemParaDevice {
	
	// Registration id do dispositivo
	private static final String DEVICE_REGISTRATION_ID = "APA91bHCwRE2l4dmPDkAUmPzu1Cm9_8T1TAgkwjE_xwATzbsZ5fTTHzgNMDc6jHin2IN7LWW1e6cMKMJ2RPQWqbq7JTgJABthjik6gG34mzNqQkSY3Oqw0DdqPqoBCKPGZqTG4-eCh3eH4Pra4GQSm8rualUpLH0AQ";

	// Chave criada no Console. Menu > API Access > (create new server key)
	private static final String API_KEY = "AIzaSyCMCGxjvjeoAmI8jllDH4388EJI4qJFZsU";

	public static void main(String[] args) throws IOException {
		Sender sender = new Sender(API_KEY);
		Message message = new Message.Builder().collapseKey("livro").addData("msg", "Olá 3twe").build();
		sender.send(message, DEVICE_REGISTRATION_ID, 5);
		System.out.println("Mensagem enviada: " + message.getData().get("msg"));
	}
}