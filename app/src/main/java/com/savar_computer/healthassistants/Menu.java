package com.savar_computer.healthassistants;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Menu extends Activity {

    private ProgressBar progressBar;

    private TextView kalleryTxt;
    private TextView sleeptime;

    private LinearLayout foodLayout,stepLayout;

    private ImageView imgEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        foodLayout=(LinearLayout)findViewById(R.id.menu_food_layout);
        foodLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),FoodMenu.class);
                startActivity(intent);
            }
        });

        stepLayout=(LinearLayout)findViewById(R.id.menu_step_layout);
        stepLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(),StepMenu.class);
                startActivity(intent);
            }
        });

        imgEdit=(ImageView) findViewById(R.id.imgEdit);
        imgEdit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(Menu.this, EditProfile.class);
                startActivity(intent);
            }
        });


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
