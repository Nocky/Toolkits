package tarce.toolkits;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;

/**
 * Created by Daniel.Xu on 2017/4/18.
 */

public class MyApplication extends Application {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }

    }

}
