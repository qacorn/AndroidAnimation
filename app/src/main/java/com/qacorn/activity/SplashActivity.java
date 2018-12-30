package com.qacorn.activity;

import android.os.Handler;
import android.widget.FrameLayout;

import com.qacorn.standard.StandardActivity;
import com.qacorn.view.ContentView;
import com.qacorn.view.SplashView;

public class SplashActivity extends StandardActivity {

    private FrameLayout frameLayout;
    private SplashView splashView;

    @Override
    protected void initializeView() {
        splashView = new SplashView(this);
        ContentView contentView = new ContentView(this);
        frameLayout = new FrameLayout(this);

        frameLayout.addView(contentView);
        frameLayout.addView(splashView);
        setContentView(frameLayout);
    }

    Handler handler = new Handler();

    @Override
    protected void initializeData() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                splashView.stopSplashAnimationAndJumpToMain();
            }
        },3000);
    }

    @Override
    protected void registerClickListener() {

    }
}
