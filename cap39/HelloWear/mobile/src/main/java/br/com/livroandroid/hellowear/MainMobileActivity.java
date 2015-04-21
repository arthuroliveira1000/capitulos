package br.com.livroandroid.hellowear;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.Wearable;

import java.util.Date;

import br.com.livroandroid.shared.WearUtil;
import livroandroid.lib.activity.BaseActivity;
import livroandroid.lib.wear.WearBitmapUtil;


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

    public void onClickSendAsset(View view) {
        count++;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ferrari_ff);
        Asset asset = WearBitmapUtil.getAssetFromBitmap(bitmap);
//        PutDataMapRequest dataMapRequest = PutDataMapRequest.create("/foto");
//        dataMapRequest.getDataMap().putAsset("foto", asset);
//        dataMapRequest.getDataMap().putLong("time", new Date().getTime());
//        PutDataRequest request = dataMapRequest.asPutDataRequest();
//        Wearable.DataApi
//                .putDataItem(wearUtil.getGoogleApiClient(), request);

        PutDataMapRequest dataMap = PutDataMapRequest.create("/foto");
        dataMap.getDataMap().putAsset("foto", asset);
        dataMap.getDataMap().putLong("time", new Date().getTime());
        PutDataRequest request = dataMap.asPutDataRequest();
        GoogleApiClient googleApiClient = wearUtil.getGoogleApiClient();
        Wearable.DataApi.putDataItem(googleApiClient, request)
                .setResultCallback(new ResultCallback<DataApi.DataItemResult>() {
                    @Override
                    public void onResult(DataApi.DataItemResult dataItemResult) {
                        Log.d("wear", "Sending image was successful: " + dataItemResult.getStatus()
                                .isSuccess());
                    }
                });
        Log.d("wear", "Put asset");
    }
}
