package com.example.propuesta8_2;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class Adaptador extends FragmentStatePagerAdapter {
    private int numTabs;

    public Adaptador(@NonNull FragmentManager fm, int numTabs) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.numTabs = numTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FragmentUno();
            case 1:
                return new FragmentDos();
            case 2:
                return new FragmentTres();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numTabs;
    }
}
