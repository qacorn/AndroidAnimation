package com.qacorn.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.qacorn.R;
import com.qacorn.tools.MeasureTool;

import java.util.Random;

/**
 * Created by qacorn on 10/12/2016.
 */

public class MusicSymbol extends RelativeLayout {

    private Context context;
    private int mWidth,mHeight;
    private int symbolWidth,symbolHeight;
    private Drawable symbol01,symbol02,symbol03,symbol04;
    private Drawable[] symbolDrawables;
    private LayoutParams layoutParams01,layoutParams02;
    private LayoutParams[] symbolParams;
    private Random random = new Random();

    public MusicSymbol(Context context) {
        super(context);
        this.context = context;
        initialize();
    }

    public MusicSymbol(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initialize();
    }

    public MusicSymbol(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initialize();
    }


    private void initialize(){
        symbol01 = getResources().getDrawable(R.mipmap.playing_img_music1);
        symbol02 = getResources().getDrawable(R.mipmap.playing_img_music2);
        symbol03 = getResources().getDrawable(R.mipmap.playing_img_music3);
        symbol04 = getResources().getDrawable(R.mipmap.playing_img_music4);
        symbolDrawables = new Drawable[]{symbol01,symbol02,symbol03,symbol04};
        symbolWidth = MeasureTool.dip2px(context,20);
        symbolHeight = MeasureTool.dip2px(context,20);
        layoutParams01 = new LayoutParams(symbolWidth,symbolHeight);
        layoutParams01.setMargins(0, MeasureTool.dip2px(context,30),0,0);

        layoutParams02 = new LayoutParams(symbolWidth,symbolHeight);
        layoutParams02.setMargins(MeasureTool.dip2px(context,10), MeasureTool.dip2px(context,90),0,0);
        symbolParams = new LayoutParams[]{layoutParams01,layoutParams02};
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void addMusicSymbol() {
        final ImageView imageView = new ImageView(getContext());
        int symbolIndex = random.nextInt(2);
        imageView.setLayoutParams(symbolParams[symbolIndex]);
        imageView.setImageDrawable(symbolDrawables[random.nextInt(4)]);
        addView(imageView);

        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(imageView,"alpha",1f,0.2f);
        ObjectAnimator mAnimatorTranslateX = null;
        ObjectAnimator mAnimatorTranslateY = null;
        if (symbolIndex == 0) {
            mAnimatorTranslateX = ObjectAnimator.ofFloat(imageView, "translationX", 0, MeasureTool.dip2px(context, 90));
            mAnimatorTranslateY = ObjectAnimator.ofFloat(imageView, "translationY", MeasureTool.dip2px(context, 30), 0);
        }else{
            mAnimatorTranslateX = ObjectAnimator.ofFloat(imageView, "translationX", MeasureTool.dip2px(context, 10), MeasureTool.dip2px(context, 90));
            mAnimatorTranslateY = ObjectAnimator.ofFloat(imageView, "translationY", MeasureTool.dip2px(context, 90), 0);
        }

        AnimatorSet symbolSet = new AnimatorSet();
        symbolSet.setDuration(3000);
        symbolSet.playTogether(alphaAnimator,mAnimatorTranslateX,mAnimatorTranslateY);
        symbolSet.setTarget(imageView);
        symbolSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                removeView(imageView);
            }
        });
        symbolSet.start();
    }

    public void addMusicSymbol2(){
        final ImageView imageView = new ImageView(getContext());
        imageView.setLayoutParams(layoutParams02);
        imageView.setImageDrawable(symbolDrawables[random.nextInt(4)]);
        addView(imageView);

        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(imageView,"alpha",1f,0.2f);
        ObjectAnimator mAnimatorTranslateX = null;
        ObjectAnimator mAnimatorTranslateY = null;
        mAnimatorTranslateX = ObjectAnimator.ofFloat(imageView, "translationX", MeasureTool.dip2px(context, 20), MeasureTool.dip2px(context, 100));
        mAnimatorTranslateY = ObjectAnimator.ofFloat(imageView, "translationY", MeasureTool.dip2px(context, 60), 0);

        AnimatorSet symbolSet = new AnimatorSet();
        symbolSet.setDuration(4000);
        symbolSet.playTogether(alphaAnimator,mAnimatorTranslateX,mAnimatorTranslateY);
        symbolSet.setTarget(imageView);
        symbolSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                removeView(imageView);
            }
        });
        symbolSet.start();
    }



}
