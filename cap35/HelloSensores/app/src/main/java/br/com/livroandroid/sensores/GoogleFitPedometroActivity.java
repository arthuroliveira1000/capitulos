package br.com.livroandroid.sensores;

import android.graphics.Color;
import android.location.Location;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.data.Value;
import com.google.android.gms.fitness.request.OnDataPointListener;
import com.google.android.gms.fitness.request.SensorRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;

import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;


public class GoogleFitPedometroActivity extends ActionBarActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private GoogleApiClient mGoogleApiClient;
    private TextView text;
    private int qtdePassos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_fit_pedometro);

        text = (TextView) findViewById(R.id.text);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Configura o objeto GoogleApiClient
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Fitness.SENSORS_API)
                .useDefaultAccount()
                .addScope(new Scope(Scopes.FITNESS_ACTIVITY_READ))
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Conecta no Google Play Services
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        // Desconecta
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnected(Bundle bundle) {
        toast("Conectado no Google Play Services!");

        startPedometer();
    }

    private void startPedometer() {
        // Listener do Fitness API que conta os passos
        OnDataPointListener listener = new OnDataPointListener() {
            @Override
            public void onDataPoint(DataPoint dataPoint) {
                for (Field field : dataPoint.getDataType().getFields()) {
                    if (dataPoint.getDataType().equals(DataType.TYPE_STEP_COUNT_DELTA)) {
                        Value val = dataPoint.getValue(field);
                        Log.d("livro", "Valor Pedometro: " + val);
                        qtdePassos += val.asInt();
                        text.setText("Passos: " + qtdePassos);
                    }
                }
            }
        };

        // Contador de passos (TYPE_STEP_COUNT_DELTA)
        SensorRequest req = new SensorRequest.Builder()
                .setDataType(DataType.TYPE_STEP_COUNT_DELTA)
                .setSamplingRate(1, TimeUnit.SECONDS)
                .build();

        // Ativa a API do Fitness
        Fitness.SensorsApi.add(mGoogleApiClient, req, listener);
    }

    @Override
    public void onConnectionSuspended(int cause) {
        toast("Conexão interrompida.");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        toast("Erro ao conectar: " + connectionResult);
    }

    private void toast(String s) {
        Toast.makeText(getBaseContext(), s, Toast.LENGTH_SHORT).show();
    }
}
