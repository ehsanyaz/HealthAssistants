package com.savar_computer.healthassistants;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.savar_computer.healthassistants.Classes.RunningHuman;

public class Splash extends Activity {

    private Handler handler;

    private RelativeLayout layout;
    private TextView txt;
    private RunningHuman human;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        layout = (RelativeLayout) findViewById(R.id.splash_layout);

        final MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.sound);
        mediaPlayer.start();

        human = new RunningHuman(getApplicationContext());
        layout.addView(human);

        txt = (TextView) findViewById(R.id.splash_txtview);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "Font/font.ttf");
        txt.setTypeface(typeface);


        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash.this, Profile.class);
                startActivity(intent);
                mediaPlayer.stop();
                finish();
                human.stopAnim();
            }
        }, 2000);


    }
}
