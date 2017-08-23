package com.savar_computer.healthassistants;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class Menu extends Activity {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        progressBar=(ProgressBar)findViewById(R.id.menu_progress_bar);
        progressBar.setMax(100);
        progressBar.setProgress(50);
    }
}
