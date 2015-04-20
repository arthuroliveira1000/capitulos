package br.com.livroandroid.hellowear;

import android.os.Bundle;
import android.view.View;

import br.com.livroandroid.shared.WearUtil;
import livroandroid.lib.activity.BaseActivity;


public class MainMobileActivity extends BaseActivity {

    private WearUtil wearUtil;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_mobile);

        wearUtil = new WearUtil(this);
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
        wearUtil.sendMessage("/msg", new byte[]{(byte) count});
    }

    public void onClickSendData(View view) {
        count++;
        Bundle b = new Bundle();
        b.putString("msg", "Olá Data API");
        b.putInt("count", count);
        wearUtil.sendData("/msg", b);
    }
}
