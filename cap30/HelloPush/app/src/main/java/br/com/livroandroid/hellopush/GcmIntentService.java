package br.com.livroandroid.hellopush;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by Usuário on 25/03/2015.
 */
public class GcmIntentService extends IntentService{

    public GcmIntentService() {
        super(Constants.PROJECT_NUMBER);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }
}
