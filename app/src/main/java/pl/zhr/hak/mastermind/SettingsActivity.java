package pl.zhr.hak.mastermind;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.ListPreference;
import androidx.preference.PreferenceManager;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


public class SettingsActivity extends AppCompatActivity {

    ListPreference listPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                if(preferences.getString("difficulty","easy").equals(getString(R.string.easy))){
                    Toast.makeText(SettingsActivity.this, getString(R.string.easy_message), Toast.LENGTH_LONG).show();
                }
                if(preferences.getString("difficulty","easy").equals(getString(R.string.medium))){
                    Toast.makeText(SettingsActivity.this, getString(R.string.medium_message), Toast.LENGTH_LONG).show();
                }
                if(preferences.getString("difficulty","easy").equals(getString(R.string.hard))){
                    Toast.makeText(SettingsActivity.this, getString(R.string.hard_message), Toast.LENGTH_LONG).show();
                }
            }
        };
        preferences.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);

    }
}
