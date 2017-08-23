package com.savar_computer.healthassistants;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Menu extends Activity {

    private ProgressBar progressBar;

    private TextView kalleryTxt;
    private TextView sleeptime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        sleeptime=(TextView)findViewById(R.id.menu_sleep_time);
        sleeptime.setText("24 hrs");

        kalleryTxt=(TextView)findViewById(R.id.menu_txt_kallery);
        kalleryTxt.setText("100 kcal");

        Typeface typeface=Typeface.createFromAsset(getAssets(),"Font/font.ttf");
        kalleryTxt.setTypeface(typeface);
        sleeptime.setTypeface(typeface);

        progressBar=(ProgressBar)findViewById(R.id.menu_progress_bar);
        progressBar.setMax(100);
        progressBar.setProgress(50);
    }
}
