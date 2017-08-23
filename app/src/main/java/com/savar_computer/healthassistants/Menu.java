package com.savar_computer.healthassistants;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

public class Menu extends Activity {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        progressBar.setMax(100);
        progressBar.setProgress(50);
    }
}
