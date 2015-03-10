package br.com.livroandroid.hellonotification;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

public class MainActivity extends ActionBarActivity {

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_main);
    }


    public void onClickNotificacaoSimples(View view) {
        int id = 1;
        String contentTitle = "Nova mensagem";
        String contentText = "Você possui 5 novas mensagens";
        Intent intent = new Intent(this,MensagemActivity.class);
        intent.putExtra("msg","Olá Leitor, como vai?");
        NotificationUtil.create(this,intent,contentTitle,contentText,id);
    }

    public void onClickNotificacaoBig(View view) {
    }
}