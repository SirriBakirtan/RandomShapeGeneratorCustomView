package com.bakirtansirri.customview.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.Random;

public class CustomView extends View {

    private static final byte CIRCLE = 0;
    private static final byte SQUARE = 1;
    private static final byte TRIANGLE = 2;

    private static byte shape = 0;

    public int resultForLeftSize=400;
    public int resultForTopSize=450;
    public int resultForArea=250;

    private Rect mRectSquare;
    private Paint mRectPaint;

    private Paint mCirclePaint;

    private int color;
    private boolean chan=false;

    ValueAnimator vm=ValueAnimator.ofFloat(0f, 3f);

    public CustomView(Context context) {
        super(context);
        init(null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(@Nullable AttributeSet set){
        mRectSquare=new Rect();
        mRectPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        color = Color.GREEN;
        mCirclePaint =new Paint();
        mCirclePaint.setAntiAlias(true);
    }
    @Override
    protected void onDraw(Canvas canvas){
        //canvas.drawColor(Color.GREEN);

        canvas.drawColor(Color.WHITE);

        switch (shape){
            case CIRCLE:
                mRectSquare.left=resultForLeftSize;
                mRectSquare.top=resultForTopSize;
                mRectSquare.right=mRectSquare.left+resultForArea;
                mRectSquare.bottom=mRectSquare.top+resultForArea;

                mRectPaint.setColor(color);

                canvas.drawRect(mRectSquare,mRectPaint);

                mCirclePaint.setColor(color);
                break;

            case SQUARE:
                canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
                canvas.drawColor(Color.WHITE);
                canvas.drawCircle(resultForLeftSize,resultForTopSize,resultForArea,mCirclePaint);
                break;

            case TRIANGLE:

                super.onDraw(canvas);

                mCirclePaint.setStrokeWidth(4);
                mCirclePaint.setColor(color);
//                mCirclePaint.setStyle(Paint.Style.FILL_AND_STROKE);
                mCirclePaint.setAntiAlias(true);

                Point a = new Point(resultForLeftSize, resultForLeftSize);
                Point b = new Point(resultForLeftSize, mRectSquare.right);
                Point c = new Point( resultForArea, resultForTopSize);

                Path path = new Path();
                path.moveTo(a.x, a.y);
                path.lineTo(b.x, b.y);
                path.lineTo(c.x, c.y);
                path.lineTo(a.x, a.y);
                path.close();

                canvas.drawPath(path, mCirclePaint);
                break;

        }



    }
    public void swapColor(){
        Random rnd = new Random();
        color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        invalidate();
        //postInvalidate();
    }
    public void changeLocation(){
        Random r = new Random();

        int low = 10;
        int high = 600;
        resultForLeftSize = r.nextInt(high-low) + low;

        int lows = 10;
        int highs = 600;
        resultForTopSize = r.nextInt(highs-lows) + lows;

        invalidate();
    }
    public void changeArea(){
        Random rs = new Random();

        int lows = 10;
        int highs = 550;
        resultForArea = rs.nextInt(highs-lows) + lows;

        invalidate();
    }
    public void doStart() {
        int mDuration = 3000;
        vm.setDuration(mDuration);
        vm.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                changeLocation();
                changeArea();
                swapColor();
                invalidate();
            }
        });
        vm.setRepeatCount(5);
        vm.start();
    }
    public void doStop(){

        vm.cancel();
    }
    public void changeShapes(){
        Random rs = new Random();

        int lows = 0;
        int highs = 3;
        shape = (byte) (rs.nextInt(highs-lows) + lows);
        invalidate();
    }
}
