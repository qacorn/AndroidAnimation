package com.qacorn.activity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Handler;
import android.os.Message;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.qacorn.R;
import com.qacorn.standard.StandardActivity;
import com.qacorn.view.MusicSymbol;

public class MusicPlayActivity extends StandardActivity {


    private ImageView music_play_image;
    private ObjectAnimator musicPlayAnimator;
    private MusicSymbol musicSymbol;
    private boolean firstStart = false;

    @Override
    protected void initializeView() {
        setContentView(R.layout.activity_music_play);
        music_play_image = findViewById(R.id.music_play_image);
        musicSymbol = findViewById(R.id.musicSymbol);
    }

    @Override
    protected void initializeData() {
        ringRotate();
        musicPlayAnimator.start();
        Thread thread = new Thread(){
            @Override
            public void run() {

                    mHandler.sendEmptyMessageDelayed(1,200);
            }
        };
        thread.start();

    }

    @Override
    protected void registerClickListener() {

    }


    private void ringRotate() {

        musicPlayAnimator = ObjectAnimator.ofFloat(music_play_image,"rotation", 0f, 360.0f);
        musicPlayAnimator.setDuration(2000);
        musicPlayAnimator.setInterpolator(new LinearInterpolator());
        musicPlayAnimator.setRepeatCount(-1);
        musicPlayAnimator.setRepeatMode(ValueAnimator.RESTART);

    }

    private int messageCount ;
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    messageCount++;
                    if (messageCount%10 == 0) {
                        musicSymbol.addMusicSymbol();
                    }
                    mHandler.sendEmptyMessageDelayed(1,200);
                    break;
                case 2:
                    if (!firstStart) {
                        musicPlayAnimator.start();
                        firstStart = true;
                    }
                    break;

            }
            return true;
        }
    });

}
