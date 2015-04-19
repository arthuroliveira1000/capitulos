package br.com.livroandroid.hellodataapi;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;

import org.w3c.dom.Text;

import br.com.livroandroid.shared.WearUtil;
import livroandroid.lib.activity.BaseActivity;


public class MainMobileActivity extends BaseActivity implements NodeApi.NodeListener {

    private static final String TAG = "wear";
    private WearUtil wearUtil;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_mobile);

        wearUtil = new WearUtil(this);
        wearUtil.setNodeListener(this);
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

    public void onClickSendMessage(View view) {
        count++;
        wearUtil.sendMessage("/msg",new byte[]{(byte) count});
    }

    public void onClickSendData(View view) {
        count++;
        Bundle b = new Bundle();
        b.putString("msg","Olá Data API");
        b.putInt("count",count);
        wearUtil.sendData("/msg",b);
    }

    @Override
    public void onPeerConnected(Node node) {
        Log.d(TAG, "Node Connected: " + node.getId() + ":" + node.getDisplayName());
    }

    @Override
    public void onPeerDisconnected(Node node) {
        Log.d(TAG, "Node Disconnected: " + node.getId() + ":" + node.getDisplayName());
    }
}
