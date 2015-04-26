package br.com.livroandroid.wearnotification;

import android.util.Log;

import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.WearableListenerService;

import livroandroid.lib.utils.NotificationUtil;

/**
 * Created by ricardo on 21/04/15.
 */
public class HelloListenerService extends WearableListenerService {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("wear", "HelloListenerService.onCreate()");
    }

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        super.onMessageReceived(messageEvent);
        String item = new String(messageEvent.getData());
        Log.d("wear","onMessageReceived: " + item);
        if("Simples".equals(item)) {
            NotificationUtil.create(this,R.mipmap.ic_launcher,"Olá","Notificação disparada pelo WearableListenerService!");
        }
    }

    @Override
    public void onDataChanged(DataEventBuffer dataEvents) {
        super.onDataChanged(dataEvents);
    }

    @Override
    public void onPeerConnected(Node peer) {
        super.onPeerConnected(peer);
    }

    @Override
    public void onPeerDisconnected(Node peer) {
        super.onPeerDisconnected(peer);
    }
}
