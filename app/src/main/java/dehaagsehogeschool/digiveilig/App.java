package dehaagsehogeschool.digiveilig;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by Thomas on 29-Mar-18.
 */

public class App extends Application {
    private static App instance;

    public static App get() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        Stetho.initializeWithDefaults(this);
    }
}
