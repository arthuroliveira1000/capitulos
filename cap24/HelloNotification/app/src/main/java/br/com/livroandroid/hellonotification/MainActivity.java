package br.com.livroandroid.hellonotification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity {

    private BroadcastReceiver customActionReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "CLICOU NA AÇÂO!", Toast.LENGTH_SHORT).show();
            NotificationUtil.cancell(context, 3);
        }
    };

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(customActionReceiver, new IntentFilter(NotificationUtil.ACTION_VISUALIZAR));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(customActionReceiver);
    }

    public void onClickNotificacaoSimples(View view) {
        int id = 1;
        String contentTitle = "Nova mensagem";
        String contentText = "Você possui 3 novas mensagens";
        Intent intent = new Intent(this,MensagemActivity.class);
        intent.putExtra("msg","Olá Leitor, como vai?");
        NotificationUtil.create(this,intent,contentTitle,contentText,1);
    }

    public void onClickNotificacaoBig(View view) {
        int id = 2;
        String contentTitle = "Nova mensagem";
        String contentText = "Você possui 3 novas mensagens";
        Intent intent = new Intent(this,MensagemActivity.class);
        intent.putExtra("msg","Olá Leitor, como vai?");
        List<String> lines = new ArrayList<>();
        lines.add("Mensagem 1");
        lines.add("Mensagem 2");
        lines.add("Mensagem 3");
        NotificationUtil.createBig(this, intent, contentTitle, contentText, lines, id);
    }

    public void onClickNotificacaoComAcao(View view) {
        int id = 3;
        String contentTitle = "Nova mensagem";
        String contentText = "Você possui 3 novas mensagens";
        Intent intent = new Intent(this,MensagemActivity.class);
        intent.putExtra("msg","Olá Leitor, como vai?");
        NotificationUtil.createWithAction(this, intent, contentTitle, contentText, 1);
    }
}