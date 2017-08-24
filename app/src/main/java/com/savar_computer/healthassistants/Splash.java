package com.savar_computer.healthassistants;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.savar_computer.healthassistants.Classes.ActivityBlackBoard;
import com.savar_computer.healthassistants.Classes.BlackBoard;
import com.savar_computer.healthassistants.Classes.RunningHuman;
import com.savar_computer.healthassistants.Classes.Notification;

public class Splash extends Activity {

    private Handler handler;

    private RelativeLayout layout;
    private TextView txt;
    private RunningHuman human;

    public static SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        ActivityBlackBoard activityBlackBoard = new ActivityBlackBoard(getApplicationContext());
        new BlackBoard(getApplicationContext());

        Notification nt=new Notification(getApplicationContext());
        nt.sendNotification("سلام!مدتی بود به ما سر نزده بودی!!!","همیار سلامت",1);

        layout = (RelativeLayout) findViewById(R.id.splash_layout);

        final MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.sound);
        mediaPlayer.start();

        human = new RunningHuman(getApplicationContext());
        layout.addView(human);

        txt = (TextView) findViewById(R.id.splash_txtview);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "Font/font.ttf");
        txt.setTypeface(typeface);

        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(Splash.this);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=null;
                if (sharedPreferences.getBoolean("flag",true))
                {
                    intent = new Intent(Splash.this, Profile.class);
                }
                else
                    intent=new Intent(Splash.this,Menu.class);
                startActivity(intent);

                mediaPlayer.stop();
                finish();
                human.stopAnim();
            }
        }, 2000);


    }
}
