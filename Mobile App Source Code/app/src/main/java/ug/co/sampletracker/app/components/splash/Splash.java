package ug.co.sampletracker.app.components.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ug.co.sampletracker.app.components.auth.signin.SignIn;
import ug.co.sampletracker.app.database.PreferencesDb;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PreferencesDb preferencesDb = new PreferencesDb(Splash.this);
        loadStartPage(preferencesDb);

    }

    private void loadStartPage(PreferencesDb preferencesDb) {

        startActivity(new Intent(Splash.this, SignIn.class));
        finish();

    }

}
