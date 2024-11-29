package android.example.act7_1;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

public class PreferenciasFragmento extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }
}