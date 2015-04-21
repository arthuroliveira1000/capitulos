package br.com.livroandroid.wear;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataMapItem;

import livroandroid.lib.wear.WearBitmapUtil;
import livroandroid.lib.wear.WearUtil;

public class MainActivity extends Activity implements DataApi.DataListener {

    private WearUtil wearUtil;
    private ImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wearUtil = new WearUtil(this);
        wearUtil.setDataListener(this);

        img = (ImageView) findViewById(R.id.img);
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
    public void onDataChanged(DataEventBuffer dataEvents) {
        Log.d("wear","onDataChanged");
        for (DataEvent event : dataEvents) {
            Log.d("wear","onDataChanged: " + event.getDataItem().getUri().getPath());
            if (event.getType() == DataEvent.TYPE_CHANGED &&
                    event.getDataItem().getUri().getPath().equals("/foto")) {
                DataMapItem dataMapItem = DataMapItem.fromDataItem(event.getDataItem());
                Asset asset = dataMapItem.getDataMap().getAsset("foto");
                Bitmap bitmap = WearBitmapUtil.getBitmapFromAsset(wearUtil.getGoogleApiClient(),asset);
                img.setImageBitmap(bitmap);
            }
        }
    }
}
