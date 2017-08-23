package com.savar_computer.healthassistants;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class Menu extends Activity {

    private RelativeLayout stepBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        stepBar=(RelativeLayout)findViewById(R.id.menu_progressStep_bar);

    }
}
