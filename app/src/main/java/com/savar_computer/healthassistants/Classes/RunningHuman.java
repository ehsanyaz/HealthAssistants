package com.savar_computer.healthassistants.Classes;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.animation.AccelerateInterpolator;
import android.widget.RelativeLayout;



public class RunningHuman extends android.support.v7.widget.AppCompatImageView implements ValueAnimator.AnimatorUpdateListener {

    private ValueAnimator animator;
    private Long time=(long)0;
    private int c = 1;

    public RunningHuman(Context context) {
        super(context);
        //this.setImageResource(R.drawable.h);

        RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(400,1000);
        this.setLayoutParams(params);

        readyAnimation();
    }

    private void readyAnimation() {
        animator = null;
        animator = new ValueAnimator();
        animator.setDuration(10000);
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
        if (System.currentTimeMillis() - time > 100) {
            Resources res = getResources();
            String mDrawableName = "h" + c;
            int resID = res.getIdentifier(mDrawableName, "drawable", getContext().getPackageName());
            Drawable drawable = res.getDrawable(resID);
            //this.setImageDrawable(drawable);
            if (c < 5)
                c++;
            else
                c = 1;
            time=System.currentTimeMillis();
        }

    }

    public void stopAnim(){
        animator.cancel();

    }
}
