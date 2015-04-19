package br.com.livroandroid.helloviews;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.MessageEvent;

import br.com.livroandroid.shared.WearUtil;

public class MainWearActivity extends Activity implements MessageApi.MessageListener {

    private static final String TAG = "wear";
    private TextView mTextView;
    private WearUtil wearUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_wear);

        // Esta activity recebe mensagens do device pela Message API
        mTextView = (TextView) findViewById(R.id.text);
        mTextView.setText("Utilize o device para abrir as activities no Wear!");

        wearUtil = new WearUtil(this);
        wearUtil.setMessageListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        wearUtil.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        wearUtil.disconnect();
    }

    @Override
    public void onMessageReceived(final MessageEvent messageEvent) {
        byte[] bytes = messageEvent.getData();
        String msg = new String(bytes);
        Log.d(TAG, "onMessageReceived(): " + messageEvent.getPath() +" : " + msg);
        if("CardView".equals(msg)) {
            startActivity(new Intent(this,CardViewActivity.class));
        }
    }
}
