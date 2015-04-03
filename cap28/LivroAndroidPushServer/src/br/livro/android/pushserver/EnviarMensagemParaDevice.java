package br.livro.android.pushserver;

import java.io.IOException;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Sender;

/**
 * @author Ricardo Lecheta
 */
public class EnviarMensagemParaDevice {
	
	// Registration id do dispositivo
	private static final String DEVICE_REGISTRATION_ID = 
			"APA91bGZNbjA2zeXelrPA26Pe3kP7wHAu6pYw23fp6vNuHYwroGHn5mTvRqEJVVPOhqX4f0UeIBcbs-Hoi0KAr5lBkRUrLPAiBsy4-5Bb2ByF1zadScjpDwBdBe49tVDDeIyX9ROeek3KsuZwZQ94ziO3NtpQEtAQA";

	// Chave criada no Console. Menu > API Access > (create new server key)
	private static final String API_KEY = "AIzaSyCMCGxjvjeoAmI8jllDH4388EJI4qJFZsU";

	public static void main(String[] args) throws IOException {
		Sender sender = new Sender(API_KEY);
		Message message = new Message.Builder().collapseKey("livro").addData("msg", "Olá leitor.").build();
		sender.send(message, DEVICE_REGISTRATION_ID, 5);
		System.out.println("Mensagem enviada: " + message.getData().get("msg"));
	}
}