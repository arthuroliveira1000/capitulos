package br.com.livroandroid.camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.Wearable;

import java.io.File;
import java.util.Date;

import livroandroid.lib.activity.BaseActivity;
import livroandroid.lib.utils.ImageResizeUtils;
import livroandroid.lib.utils.SDCardUtils;
import livroandroid.lib.wear.*;


public class MainActivity extends BaseActivity {
    // Caminho para salvar o arquivo
    private File file;
    private ImageView imgView;
    private WearUtil wearUtil;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wearUtil = new WearUtil(this);

        imgView = (ImageView) findViewById(R.id.imagem);

        findViewById(R.id.btAbrirCamera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cria o caminho do arquivo no sdcard
                // /storage/sdcard/Android/data/br.com.livroandroid.multimidia/files/Pictures/foto.jpg
                file = SDCardUtils.getPrivateFile(getBaseContext(), "foto.jpg", Environment.DIRECTORY_PICTURES);
                // Chama a intent informando o arquivo para salvar a foto
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(i, 0);
            }
        });

        findViewById(R.id.btEnviarFoto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wearUtil.sendMessage("/oi", new byte[]{1});

                if (file != null && file.exists()) {
                    Log.d("foto", file.getAbsolutePath());

                    int w = 320;
                    int h = 320;
                    Bitmap bitmap = ImageResizeUtils.getResizedImage(Uri.fromFile(file), w, h, false);

                    Asset asset = livroandroid.lib.wear.WearBitmapUtil.getAssetFromBitmap(bitmap);

                    Bundle bundle = new Bundle();
                    bundle.putParcelable("foto", asset);
                    wearUtil.putData("/foto",bundle);

                    //WearBitmapUtil.putDataAsset(wearUtil.getGoogleApiClient(),bitmap,"/foto","foto");
                }
            }
            private void sendBitmap(Bitmap bitmap) {
                Asset asset = livroandroid.lib.wear.WearBitmapUtil.getAssetFromBitmap(bitmap);
                PutDataMapRequest dataMap = PutDataMapRequest.create("/foto");
                dataMap.getDataMap().putAsset("foto", asset);
                dataMap.getDataMap().putLong("time", new Date().getTime());
                PutDataRequest request = dataMap.asPutDataRequest();
                GoogleApiClient googleApiClient = wearUtil.getGoogleApiClient();
                Wearable.DataApi.putDataItem(googleApiClient, request)
                        .setResultCallback(new ResultCallback<DataApi.DataItemResult>() {
                            @Override
                            public void onResult(DataApi.DataItemResult dataItemResult) {
                                Log.d("camera", "Sending image was successful: " + dataItemResult.getStatus()
                                        .isSuccess());
                            }
                        });
            }
        });

        if (savedInstanceState != null) {
            // Se girou a tela recupera o estado
            file = (File) savedInstanceState.getSerializable("file");
            showImage(file);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Salvar o estado caso gire a tela
        outState.putSerializable("file", file);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && file != null) {
            showImage(file);
        }
    }

    // Atualiza a imagem na tela
    private void showImage(File file) {
        if (file != null && file.exists()) {
            Log.d("foto", file.getAbsolutePath());

            int w = imgView.getWidth();
            int h = imgView.getHeight();
            Bitmap bitmap = ImageResizeUtils.getResizedImage(Uri.fromFile(file), w, h, false);
            Toast.makeText(this, "w/h:" + imgView.getWidth() + "/" + imgView.getHeight() + " > " + "w/h:" + bitmap.getWidth() + "/" + bitmap.getHeight(), Toast.LENGTH_SHORT).show();

            imgView.setImageBitmap(bitmap);
        }
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
}