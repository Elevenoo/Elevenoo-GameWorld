package com.example.asus.rocker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 *  Role movement and growth
 *
 *  Write by Elevenoo, 2019/5/20
 */
public class MoveBall extends View {

    static int screenWidth,screenHeight;
    private Paint mPaint=new Paint();

    static Snake snake=new Snake();
    static int length=6;  //Initialize the length of the snake
    static float[] food=new float[2];

    static float CircleR = 25.0f; //The radius of the snake's body radius
    static {
        //The linked list realizes the body addition of the snake and  specify the position of each body
        float body_x,body_y;
        for(int i=0;i<length;i++){
            body_x=400.0f-50.0f*i;
            body_y=200.0f;
            snake.AddBady(body_x,body_y);
        }
        //Initialize the location of the food
        food[0]=300.0f;
        food[1]=400.0f;
    }

    public MoveBall(Context context) {
        this(context, null);
    }
    public MoveBall(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public MoveBall(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Show
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setAlpha(80);
        Snake ss= null;
        try {
            ss = (Snake)snake.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        float[] CircleP;
        for(int i=0;i<snake.size;i++){
            CircleP=ss.getNodeP(ss.ptail);
            if(i==0)
                mPaint.setColor(getResources().getColor(R.color.青色));
            else
                mPaint.setColor(getResources().getColor(R.color.藏青色));
            canvas.drawCircle( CircleP[0], CircleP[1], CircleR, mPaint);
            ss.ptail=ss.getPreN(ss.ptail);
        }
        mPaint.setColor(getResources().getColor(R.color.粉紫色));
        canvas.drawCircle( food[0], food[1], CircleR, mPaint);
    }



    float[] lastfood=new float[2];
    float[] tailP=new float[2];
    boolean eat=false;
    /**
     * Moveball
     * @param rad
     */
    public void Moveball(double rad){
        screenWidth = MainActivity.screenWidth;
        screenHeight = MainActivity.screenHeight;

        if(rad!=888888){
            try {
                float[] head=snake.getNodeP(snake.ptail);
                //calculate the target position of the snake head
                head=getXY(head[0],head[1],5.0f,rad);
                //If the target position exceeds the screen, it is judged to be hitting the wall and does not move
                if(head[0]<0||head[0]>screenWidth||head[1]<0||head[1]>screenHeight)
                    return;
                //Update the value of the head node if the target position is within the screen range
                snake.setNodeP(0,head);
                //determine if the snake eats food
                float FoodSafetyDistance=25.0f;
                if((Math.sqrt(Math.pow((head[0]-food[0]),2)+Math.pow((head[1]-food[1]),2)))<=FoodSafetyDistance){
                    eat=true;
                    lastfood=food;
                    tailP=snake.getNodeP(snake.pHead);
                    //update the position of food
                    food[0]=(float) Math.random()*screenWidth;
                    food[1]=(float) Math.random()*screenHeight;
                }
                /**update the position of body
                 * Algorithm:
                 * 1. Calculate the direction of the body relative to the body of the previous section,
                 * 2. Calculate the position(nowNP)in the direction of a diameter distance from the body of the previous section,
                 * 3. Update the value of the body node of the section to nowNP
                 */
                Snake.Node lastN;
                Snake.Node nowN;
                float[] nowNP;
                float[] lastNP;
                for (int i=1;i<snake.size;i++){
                    lastN=snake.getIndN(i-1);
                    lastNP=snake.getNodeP(lastN);
                    nowN=snake.getIndN(i);
                    nowNP=snake.getNodeP(nowN);
                    rad=getRad(lastNP[0],lastNP[1],nowNP[0],nowNP[1]);
                    nowNP=getXY(lastNP[0],lastNP[1],50.0f,rad);
                    snake.setNodeP(i,nowNP);
                }
                // Eat food, directly increase the tail body (Actually the head node)
                if(eat){
                    snake.AddBady(tailP[0],tailP[1]);
                    eat=false;
                }
                //Redraw
                invalidate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
        float y = py1 - py2;
        float xie = (float) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        float cosAngle = x / xie;
        float rad = (float) Math.acos(cosAngle);
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
    public float[] getXY(float centerX, float centerY, float R, double rad) {
        float[] xy=new float[2];
        xy[0] = (float) (R * Math.cos(rad)) + centerX;
        xy[1] = (float) (R * Math.sin(rad)) + centerY;
        return xy;

    }

}
