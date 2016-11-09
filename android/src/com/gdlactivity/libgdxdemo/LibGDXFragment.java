package com.gdlactivity.libgdxdemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.backends.android.AndroidFragmentApplication;

public class LibGDXFragment extends AndroidFragmentApplication {

    public LibGDXFragment() {

    }
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        int screenId = getArguments().getInt("screen");

        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

        return initializeForView(new GDLActivity(screenId), config);
    }
}