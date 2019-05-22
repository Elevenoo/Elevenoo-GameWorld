package com.example.asus.rocker;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 *Touch the movement to control the small circle movement
 * actually change the coordinates of the center of the small circle
 * be careful not to exceed the large circle range
 *
 * implement interface(RadListener)
 *
 * Modify by Elevenoo, 2019/5/20
 * Source：https://blog.csdn.net/xiaominghimi/article/details/6423983
 */
public class RockerView extends View implements RadListener{

    @Override
    public void report(double tempRad) {
        //The report method is not implemented here.
    }

    private Paint mPaint;
    private PointF bigCircleP=new PointF(150.0f, 150.0f) ; //the center of the big circle
    private PointF smallCircleP=new PointF(150.0f, 150.0f) ;  //the center of the small circle
    private float bigCircleR = 120; //the radius of the big circle
    private float smallCircleR = 60;    //the radius of the small circle
    static double tempRad=0;  //The direction of the small circle relative to the big circle

    private RadListener radListener=null;          //Create a move direction listener
    /**
     * use setRadListener method to pass radlistener
     * @param radListener
     */
    public void setRadListener(RadListener radListener) {
        this.radListener = radListener;
    }


    public RockerView(Context context) {
        this(context, null);
    }
    public RockerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public RockerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Show
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint = new Paint();
        //Painting the head
        mPaint.setAlpha(15);
        mPaint.setColor(getResources().getColor(R.color.藏青色));  //Register the color in res/values/colors.xml, and call the color via getResources in the activity.
        canvas.drawCircle(bigCircleP.x, bigCircleP.y, bigCircleR, mPaint);
        //Painting the body
        mPaint.setAlpha(30);
        mPaint.setColor(getResources().getColor(R.color.天青色));
        canvas.drawCircle(smallCircleP.x, smallCircleP.y, smallCircleR, mPaint);
    }


    /**
     * Touch the movement to control the small circle movement
     * @param motionEvent
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        //touch down , update the position of the rocker
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN ) {
            bigCircleP.x = (int) motionEvent.getX();
            bigCircleP.y = (int) motionEvent.getY();
            smallCircleP.x = bigCircleP.x;
            smallCircleP.y = bigCircleP.y;
        }
        //touch move , update the position of the small circle while the position of the big circle remains unchanged
        else if(motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
            //get the angle formed by the touch screen point and the rocker position
            tempRad = getRad(bigCircleP.x, bigCircleP.y, motionEvent.getX(), motionEvent.getY());
            //ensure that the small circle does not exceed the large circle
            float len=(float) Math.sqrt((Math.pow((int)(motionEvent.getX())-bigCircleP.x,2)+Math.pow((int)(motionEvent.getY())-bigCircleP.y,2)));
            if(len>=bigCircleR){
                getXY(bigCircleP.x, bigCircleP.y, bigCircleR, tempRad);
            }else{
                smallCircleP.x = (int) motionEvent.getX();
                smallCircleP.y = (int) motionEvent.getY();
            }
        }
        //touch up , restore the position of the small circle
        else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            smallCircleP.x = bigCircleP.x;
            smallCircleP.y = bigCircleP.y;
            invalidate();
        }

        if (radListener != null) {
            float dt_x = smallCircleP.x - bigCircleP.x;
            float dt_y = smallCircleP.y - bigCircleP.y;
            if(dt_x==0&&dt_y==0){
                radListener.report(888888);  //the stop flag data=888888
            }
            else{
                radListener.report(tempRad);
            }
        }else{
            Log.d("RockerView","mOnDirectionListener=null");
        }
        invalidate();                //重绘
        return true;
    }

    /**
     * Calculate the direction of p1 relative to p2
     * @param px1
     * @param py1
     * @param px2
     * @param py2
     * @return
     */
    public double getRad(float px1, float py1, float px2, float py2) {
        float x = px2 - px1;
        float y = py2 - py1;
        float xie = (float) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        float cosAngle = x / xie;
        float rad = (float) Math.acos(cosAngle);
        // because the starting point of the screen is in the upper left corner, that means the y-axis is the opposite
        if (py2 < py1) {
            rad = -rad;
        }
        return rad;
    }

    /**
     * When the touch point exceeds the large circle range,
     * calculate the boundary point of the large circle in the same direction,
     * that is, the value assigned to the center of the small circle
     * @param centerX
     * @param centerY
     * @param R
     * @param rad
     */
    public void getXY(float centerX, float centerY, float R, double rad) {
        smallCircleP.x = (float) (R * Math.cos(rad)) + centerX;
        smallCircleP.y = (float) (R * Math.sin(rad)) + centerY;
    }

}