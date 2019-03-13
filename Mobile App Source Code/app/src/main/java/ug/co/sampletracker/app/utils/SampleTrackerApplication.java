package ug.co.sampletracker.app.utils;

import com.beardedhen.androidbootstrap.TypefaceProvider;
import com.orm.SugarApp;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 5/15/2018.
 */

public class SampleTrackerApplication extends SugarApp {

    @Override public void onCreate() {
        super.onCreate();
        TypefaceProvider.registerDefaultIconSets();
    }


}
