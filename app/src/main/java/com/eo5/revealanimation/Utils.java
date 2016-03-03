package com.eo5.revealanimation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.support.annotation.ColorRes;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * Created by Sriram on 2/26/2016.
 */
public class Utils {
    public static void animateRevealShow(final Context ctx, final View view, final int startRadius,
                                         @ColorRes final int color, int x, int y,final OnRevealAnimationListener listener) {
        float finalRadius = (float) Math.hypot(view.getWidth(), view.getHeight());
        Log.v("REVEAL","finalradius:"+finalRadius);
        Animator anim =
                ViewAnimationUtils.createCircularReveal(view, x, y, startRadius, finalRadius);
        anim.setDuration(300);
        anim.setStartDelay(100);
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                view.setBackgroundColor(ctx.getResources().getColor(color));
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.VISIBLE);
                listener.onRevealShow();
            }
        });
        anim.start();
    }
}
