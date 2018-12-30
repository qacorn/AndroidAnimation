package com.qacorn.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;

/**
 * AndroidAnimation
 *
 * @author qacorn
 */
public class SplashView extends View{

    private float mRotationRadius = 130;
    private float mCircleRadius = 25;
    private float mCenterX,mCenterY;
    private Paint paint,mBackgroundPaint;
    private int[] mCircleColors = new int[]{Color.BLUE,Color.CYAN,Color.GREEN,Color.YELLOW,Color.RED,Color.BLACK};
    private SplashState splashState = null;
    private int mRotationDuration = 1200;
    private float mCurrentRotationAngle = 0;
    private float mCurrentRotationRadius = mRotationRadius;
    private float mDiagonalDist;
    private float mHoleRadius = 0;


    public SplashView(Context context) {
        super(context);
        initialize();
    }

    public SplashView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }


    private void initialize(){

        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);

        mBackgroundPaint = new Paint();
        mBackgroundPaint.setAntiAlias(true);
        mBackgroundPaint.setColor(Color.WHITE);
        mBackgroundPaint.setStyle(Paint.Style.STROKE);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCenterX = w/2f;
        mCenterY = w/2f;

        mDiagonalDist = (float) (Math.sqrt(w*w+h*h)/2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (splashState == null){
            splashState = new RotationState();
        }

        drawBackground(canvas);

        drawCircle(canvas);
    }

    private void drawBackground(Canvas canvas) {
        if (mHoleRadius>0){
            float strokeWidth = mDiagonalDist - mHoleRadius;

            mBackgroundPaint.setStrokeWidth(strokeWidth);
            float radius = mHoleRadius + strokeWidth/2;
            canvas.drawCircle(mCenterX,mCenterY,radius,mBackgroundPaint);
        }else {
            canvas.drawColor(Color.WHITE);
        }
    }

    private void drawCircle(Canvas canvas) {
        float rotationAngle = (float) (2*Math.PI/mCircleColors.length);
        for (int i = 0; i < mCircleColors.length; i++) {
            double angle = i*rotationAngle +mCurrentRotationAngle;
            float cx = (float) (mCurrentRotationRadius*Math.cos(angle)+mCenterX);
            float cy = (float) (mCurrentRotationRadius*Math.sin(angle)+mCenterX);
            paint.setColor(mCircleColors[i]);
            canvas.drawCircle(cx,cy,mCircleRadius,paint);
        }
    }



    private abstract class SplashState{
       public abstract void drawState(Canvas canvas);
    }

    private class RotationState extends SplashState{

        private ValueAnimator valueAnimator;

        public RotationState() {
            valueAnimator = ValueAnimator.ofFloat(0,(float) Math.PI*2);
//            valueAnimator.setInterpolator(new AnticipateOvershootInterpolator(10f));
            valueAnimator.setInterpolator(new LinearInterpolator());
            valueAnimator.setDuration(mRotationDuration);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mCurrentRotationAngle = (float) animation.getAnimatedValue();
                    invalidate();
                }
            });
            valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
            valueAnimator.start();
        }

        @Override
        public void drawState(Canvas canvas) {
//            drawBackground(canvas);
//
//            drawCircle(canvas);
        }

        public void stop(){
            valueAnimator.cancel();
        }
    }

    private class MergingState extends SplashState{

        private ValueAnimator valueAnimator;

        public MergingState() {
            valueAnimator = ValueAnimator.ofFloat(0,mRotationRadius);
            valueAnimator.setInterpolator(new OvershootInterpolator(10f));
            valueAnimator.setDuration(mRotationDuration/2);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mCurrentRotationRadius = (float) animation.getAnimatedValue();
                    invalidate();
                }
            });
            valueAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    splashState = new ExpandState();
                }
            });
            valueAnimator.reverse();
        }

        @Override
        public void drawState(Canvas canvas) {
//            drawBackground(canvas);
//
//            drawCircle(canvas);
        }
    }

    private class ExpandState extends SplashState{
        private ValueAnimator valueAnimator;

        public ExpandState() {
            valueAnimator = ValueAnimator.ofFloat(0,mDiagonalDist);
            valueAnimator.setInterpolator(new LinearInterpolator());
            valueAnimator.setDuration(mRotationDuration/2);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mHoleRadius = (float) animation.getAnimatedValue();
                    invalidate();
                }
            });
            valueAnimator.start();
        }

        @Override
        public void drawState(Canvas canvas) {
            drawBackground(canvas);
        }
    }


    public void stopSplashAnimationAndJumpToMain(){

        if (splashState!=null){
            RotationState rs = (RotationState) splashState;
            rs.stop();
            post(new Runnable() {
                @Override
                public void run() {
                    splashState = new MergingState();
                }
            });
        }
    }

}
