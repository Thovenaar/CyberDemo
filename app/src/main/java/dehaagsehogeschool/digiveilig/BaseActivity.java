package dehaagsehogeschool.digiveilig;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import dehaagsehogeschool.digiveilig.spel.DigiveiligSpelActivity;

/**
 * Created by Thomas on 29-Mar-18.
 */

public abstract class BaseActivity extends AppCompatActivity {
    public final String TAG = this.getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "I am created!");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "I am paused!");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "I am stopped!");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "I am destroyed!");
    }
}
