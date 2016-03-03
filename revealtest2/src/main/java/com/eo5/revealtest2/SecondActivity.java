package com.eo5.revealtest2;

import android.animation.Animator;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;

public class SecondActivity extends AppCompatActivity {
    CoordinatorLayout mCLayout;
    FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mCLayout = (CoordinatorLayout)findViewById(R.id.secondRootLayout);

        setSupportActionBar(toolbar);

        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        setupEnterAnimations();
    }

    private void setupEnterAnimations(){
        Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.transition_arc);
        transition.setDuration(300);
        getWindow().setSharedElementEnterTransition(transition);
        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {
                transition.removeListener(this);
                int cx = (mCLayout.getLeft() + mCLayout.getRight())/2;
                int cy = (mCLayout.getTop() + mCLayout.getBottom())/2;
                float radius = (float)Math.hypot((double)cx, (double)cy);
                Animator anim = ViewAnimationUtils.createCircularReveal(mCLayout,cx,cy,0,radius);
                anim.setDuration(300);
                anim.setStartDelay(100);
                anim.setInterpolator(new AccelerateDecelerateInterpolator());
                anim.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        mCLayout.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                        mFab.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                anim.start();

            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });


    }


}
