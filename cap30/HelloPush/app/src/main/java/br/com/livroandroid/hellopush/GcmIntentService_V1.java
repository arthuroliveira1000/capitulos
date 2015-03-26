package br.com.livroandroid.hellopush;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by Usuário on 25/03/2015.
 */
public class GcmIntentService_V1 extends IntentService{

    public GcmIntentService_V1() {
        super(Constants.PROJECT_NUMBER);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }
}
