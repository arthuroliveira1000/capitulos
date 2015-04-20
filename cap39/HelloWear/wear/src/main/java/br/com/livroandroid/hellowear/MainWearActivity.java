package br.com.livroandroid.hellowear;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataItem;
import com.google.android.gms.wearable.DataMap;
import com.google.android.gms.wearable.DataMapItem;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.MessageEvent;

import br.com.livroandroid.shared.WearUtil;

public class MainWearActivity extends Activity implements DataApi.DataListener, MessageApi.MessageListener {

    private static final String TAG = "wear";
    private TextView mTextView;
    private WearUtil wearUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_wear);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.text);
            }
        });

        wearUtil = new WearUtil(this);
        wearUtil.setDataListener(this);
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
    public void onDataChanged(DataEventBuffer dataEventBuffer) {
        Log.d(TAG, "onDataChanged()");
        for (DataEvent event : dataEventBuffer) {
            if (event.getType() == DataEvent.TYPE_CHANGED) {
                // DataItem changed
                DataItem item = event.getDataItem();
                if (item.getUri().getPath().compareTo("/msg") == 0) {
                    // Lê a mensagem enviad pela Data API
                    DataMap dataMap = DataMapItem.fromDataItem(item).getDataMap();
                    final String msg = dataMap.getString("msg");
                    final int count = dataMap.getInt("count");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mTextView.setText(msg + "\nCount: " + count);
                        }
                    });
                }
            } else if (event.getType() == DataEvent.TYPE_DELETED) {
                // DataItem deleted
            }
        }
    }

    @Override
    public void onMessageReceived(final MessageEvent messageEvent) {
        Log.d(TAG, "onMessageReceived(): " + messageEvent.getPath());
        // Lê a mensagem
        String path = messageEvent.getPath();
        String nodeId = messageEvent.getSourceNodeId();
        byte[] bytes = messageEvent.getData();
        // Atualiza a view
        final int count = bytes[0];
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTextView.setText("Count: " + count);
            }
        });
    }
}
