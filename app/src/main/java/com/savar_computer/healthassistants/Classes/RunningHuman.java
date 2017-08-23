package com.savar_computer.healthassistants.Classes;

import android.animation.ValueAnimator;
import android.content.Context;
import android.view.animation.AccelerateInterpolator;

import com.savar_computer.healthassistants.R;

public class RunningHuman extends android.support.v7.widget.AppCompatImageView implements ValueAnimator.AnimatorUpdateListener{

    private ValueAnimator animator;

    public RunningHuman(Context context) {
        super(context);
        this.setImageResource(R.drawable.h1);
    }

    private void readyAnimation() {
        //TODO: I still don't Know what this Method DO
        animator = null;
        animator = new ValueAnimator();
        animator.setDuration(1000);
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

    }
}
