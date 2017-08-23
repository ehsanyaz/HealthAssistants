package com.savar_computer.healthassistants;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class Splash extends Activity {

    private Handler handler;

    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        final MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.sound);
        mediaPlayer.start();

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
            }
        }, 2000);


    }
}
