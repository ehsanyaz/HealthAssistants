package com.savar_computer.healthassistants.Classes;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.animation.AccelerateInterpolator;
import android.widget.Toast;

import com.savar_computer.healthassistants.R;

import java.io.File;

public class RunningHuman extends android.support.v7.widget.AppCompatImageView implements ValueAnimator.AnimatorUpdateListener{

    private ValueAnimator animator;

    private int c=1;

    private Bitmap bm;

    public RunningHuman(Context context) {
        super(context);
        this.setImageResource(R.drawable.h1);
        readyAnimation();
    }

    private void readyAnimation() {
        animator = null;
        animator = new ValueAnimator();
        animator.setDuration(100);
        animator.setFloatValues(1);
        animator.setStartDelay(0);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.setTarget(this);
        //animator.addListener(this);
        animator.addUpdateListener(this);
        animator.start();
    }

    @Override
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        String img = "//res//drawable//h" +c + ".jpg"; // create the file name
        //icon.setScaleType(ImageView.ScaleType.CENTER_CROP);

        // check to see if the file exists
        File file = new File(img);
        if (file.exists()){

            bm = BitmapFactory.decodeFile(img);
        }
        else{// use the default icon
            Toast.makeText(getContext(),"HIIIIIIII",Toast.LENGTH_LONG).show();
        }

        // set the image and text
        this.setImageBitmap(bm);
        if(c!=5)
            c++;
        else
            c=1;
    }
}
