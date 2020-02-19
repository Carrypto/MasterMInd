package pl.zhr.hak.mastermind;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.ListPreference;


import android.os.Bundle;
import android.view.View;


public class SettingsActivity extends AppCompatActivity {

    ListPreference listPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();

    }
}
