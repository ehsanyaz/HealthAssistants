package com.savar_computer.healthassistants;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.savar_computer.healthassistants.R;

public class StepMenu extends Activity
{
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView4;
    private ImageView imageView5;
    private ImageView imageView6;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.step_menu);


        imageView1=(ImageView) findViewById(R.id.imageView11);
        imageView2=(ImageView) findViewById(R.id.imageView22);
        imageView3=(ImageView) findViewById(R.id.imageView33);
        imageView4=(ImageView) findViewById(R.id.imageView44);
        imageView5=(ImageView) findViewById(R.id.imageView55);
        imageView6=(ImageView) findViewById(R.id.imageView66);

        imageView1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(StepMenu.this, PedometerActivity.class);
                startActivity(intent);
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });

        imageView3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });

        imageView4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });

        imageView5.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });

        imageView6.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });
    }// end of class
}
