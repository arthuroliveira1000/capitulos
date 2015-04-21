package br.com.livroandroid.camera;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.Wearable;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by ricardo on 21/04/15.
 */
public class WearBitmapUtil {
    // Cria um Assert a partir de um Bitmap
    public static Asset getAssetFromBitmap(Bitmap bitmap) {
        final ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteStream);
        return Asset.createFromBytes(byteStream.toByteArray());
    }

    public static Bitmap getBitmapFromAsset(GoogleApiClient googleApiClient,Asset asset) {
        if (asset == null) {
            return null;
        }
        // convert asset into a file descriptor and block until it's ready
        InputStream in = Wearable.DataApi.getFdForAsset(
                googleApiClient, asset).await().getInputStream();
        Bitmap bitmap = BitmapFactory.decodeStream(in);
        return bitmap;
    }

    // Envia o Asset pela Data API
    public static void putDataAsset(GoogleApiClient googleApiClient,Bitmap bitmap,String path,String assetKey) {
        Asset asset = getAssetFromBitmap(bitmap);
        PutDataMapRequest dataMap = PutDataMapRequest.create(path);
        dataMap.getDataMap().putAsset(assetKey, asset);
        dataMap.getDataMap().putLong("time", new Date().getTime());
        PutDataRequest request = dataMap.asPutDataRequest();

        Wearable.DataApi.putDataItem(googleApiClient, request);
    }
}
