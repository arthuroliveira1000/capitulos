package br.com.livroandroid.camera;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataItem;
import com.google.android.gms.wearable.DataMapItem;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.MessageEvent;

import livroandroid.lib.wear.WearBitmapUtil;
import livroandroid.lib.wear.WearUtil;

public class MainActivity extends Activity implements DataApi.DataListener, MessageApi.MessageListener {

    private WearUtil wearUtil;
    private ImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wearUtil = new WearUtil(this);
        wearUtil.setDataListener(this);
        wearUtil.setMessageListener(this);

        img = (ImageView) findViewById(R.id.img);

        Log.d("camera","onCreate OK");
    }

    @Override
    protected void onResume() {
        super.onResume();
        wearUtil.connect();
        Log.d("camera", "onResume OK");
    }

    @Override
    protected void onPause() {
        super.onPause();
        wearUtil.disconnect();
    }

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        Log.d("camera","onMessageReceived: " + messageEvent.getPath());
    }

    @Override
    public void onDataChanged(DataEventBuffer dataEvents) {
        Log.d("camera","onDataChanged");
        for (DataEvent event : dataEvents) {
            Log.d("camera","onDataChanged: " + event.getDataItem().getUri().getPath());
            if (event.getType() == DataEvent.TYPE_CHANGED) {
                DataItem item = event.getDataItem();
                if (item.getUri().getPath().compareTo("/foto") == 0) {
                    DataMapItem dataMapItem = DataMapItem.fromDataItem(event.getDataItem());
                    Asset photo = dataMapItem.getDataMap().getAsset("foto");
                    GoogleApiClient googleApiClient = wearUtil.getGoogleApiClient();
                    final Bitmap bitmap = WearBitmapUtil.getBitmapFromAsset(googleApiClient, photo);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // Altera o fundo do layout com o Bitmap
                            img.setImageBitmap(bitmap);
                        }
                    });
                }

                /*DataMapItem dataMapItem = DataMapItem.fromDataItem(event.getDataItem());
                Asset asset = dataMapItem.getDataMap().getAsset("foto");
                Bitmap bitmap = WearBitmapUtil.getBitmapFromAsset(wearUtil.getGoogleApiClient(),asset);
                img.setImageBitmap(bitmap);*/
            }
        }
    }


}
