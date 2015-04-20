package br.com.livroandroid.helloviews.ex7;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.DelayedConfirmationView;
import android.view.View;

import br.com.livroandroid.helloviews.R;

/**
 * Created by ricardo on 19/04/15.
 */
public class ConfirmationDelayedActivity extends Activity implements DelayedConfirmationView.DelayedConfirmationListener {
    private DelayedConfirmationView mDelayedView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_delayed);

        mDelayedView =
                (DelayedConfirmationView) findViewById(R.id.delayed_confirm);
        mDelayedView.setListener(this);
    }

    @Override
    public void onTimerFinished(View view) {
        // User didn't cancel, perform the action
    }

    @Override
    public void onTimerSelected(View view) {
        // User canceled, abort the action
    }
}
