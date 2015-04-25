package br.com.livroandroid.helloviews.ex7;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.wearable.view.DismissOverlayView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;

import br.com.livroandroid.helloviews.R;

public class FullScreenActivity extends Activity {
    private GestureDetector mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);

<<<<<<< HEAD
        SharedPreferences mPrefs = getSharedPreferences("android.support.wearable.DismissOverlay", 0);
            mPrefs.edit().putBoolean("first_run", false).apply();

        // Obtain the DismissOverlayView element
        mDismissOverlay = (DismissOverlayView) findViewById(R.id.dismiss_overlay);
        mDismissOverlay.setIntroText("Para sair, clique e segure.");
        mDismissOverlay.showIntroIfNecessary();
=======
        final DismissOverlayView  d = (DismissOverlayView) findViewById(R.id.dismiss_overlay);
        d.setIntroText("Para sair, clique e segure.");
        d.showIntroIfNecessary();
>>>>>>> 772f29e14d12320c2d1c0d9fb10b96cbdd05f487

        // Configure a gesture detector
        mDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            public void onLongPress(MotionEvent ev) {
                d.show();
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return mDetector.onTouchEvent(ev) || super.onTouchEvent(ev);
    }
}
