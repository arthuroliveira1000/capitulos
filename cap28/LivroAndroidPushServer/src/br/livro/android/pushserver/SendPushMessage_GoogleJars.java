package br.livro.android.pushserver;

import java.io.IOException;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Sender;

/**
 * Faz POST com os antigos jards do Google que eram disponibilizados antes.
 * 
 * Agora temos que fazer na mão.
 * 
 * @author Ricardo Lecheta
 */
public class SendPushMessage_GoogleJars {
	
	// Registration id do dispositivo
	private static final String DEVICE_REGISTRATION_ID = 
			"APA91bEq6-RpN-ULVI1-J5gxhRjqchSJo3InhHGkyPgbdsXWumsLOs3eOw8Gjtp5Ww-nS25PwGWy76LQezcpeFrcabXAanuar3JOfhHmMCvbEvpX7ILlpDyXYKvon07Tnt-tnng8XPkSUk8I5j4JYg9jhL45uEa5DQ";

	// Chave criada no Console. Menu > API Access > (create new server key)
	private static final String API_KEY = "AIzaSyCMCGxjvjeoAmI8jllDH4388EJI4qJFZsU";

	public static void main(String[] args) throws IOException {
		Sender sender = new Sender(API_KEY);
		Message message = new Message.Builder().collapseKey("livro").addData("msg", "Olá leitor :-).").build();
		sender.send(message, DEVICE_REGISTRATION_ID, 5);
		System.out.println("Mensagem enviada: " + message.getData().get("msg"));
	}
}