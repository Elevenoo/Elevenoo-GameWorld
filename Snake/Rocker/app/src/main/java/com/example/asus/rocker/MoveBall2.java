package com.example.asus.rocker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class MoveBall2 extends View{

    public MoveBall2(Context context) {
        this(context, null);
    }

    public MoveBall2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MoveBall2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private Paint mPaint=new Paint();   //画笔
    static int length=8;  //初始8节身体
    static float CircleR = 20.0f; //后俩个圆的半径
    static PointF CircleP=new PointF();
    static Snake snake=new Snake();
    static {
        for(int i=0;i<length;i++){
            CircleP.x=250.0f-20.0f*i;
            CircleP.y=200.0f;
            snake.AddBady(CircleP);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setAlpha(80);

        Snake snake1=snake.clone();
        int size=snake.size;

        for(int i=0;i<size;i++){
            CircleP=snake1.getNodePt(snake1.pHead);
            if(i==0)
                mPaint.setColor(getResources().getColor(R.color.青色));
            else
                mPaint.setColor(getResources().getColor(R.color.藏青色));
            canvas.drawCircle( CircleP.x, CircleP.y, CircleR, mPaint);
            snake1.pHead=snake1.getNextNode(snake1.pHead);
        }
//        if(){
//
//        }else{
//
//        }
    }

    float stepsize=10.0f;
    private PointF pt1,pt2;
    double rad;
    public  void Moveball(String direction){
        if(direction!=""&&direction!="stop"){
            try {
                Thread.sleep(500);
                Log.d("MoveBall",direction);
                Snake snake1=snake.clone();
                pt2=snake1.getNodePt(snake1.ptail);
                if(direction.equals("Up")){
                   pt2.y-=stepsize;
                }else if(direction.equals("Down")){
                    pt2.y+=stepsize;
                }
                else if(direction.equals("Left")){
                    pt2.x-=stepsize;
                }
                else if(direction.equals("Right")){
                    pt2.x+=stepsize;
                }
                for(int i=0;i<snake1.size-1;i++){
                    pt2=snake1.getNodePt(snake1.ptail);
                    pt1=snake1.getNodePt(snake1.getPreNode(snake1.ptail));
                    rad=getRad(pt2,pt1);
                    getXY(pt2,CircleR,rad);
                    snake1.ptail=snake1.getPreNode(snake1.ptail);
                }
                //重绘
                invalidate();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public double getRad(PointF pt1,PointF pt2) {
        //得到两点X的距离
        float x = pt1.x - pt2.x;
        //得到两点Y的距离
        float y = pt1.y - pt2.y;
        //算出斜边长
        float xie = (float) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        //得到这个角度的余弦值（通过三角函数中的定理 ：邻边/斜边=角度余弦值）
        float cosAngle = x / xie;
        //通过反余弦定理获取到其角度的弧度
        float rad = (float) Math.acos(cosAngle);
        //注意：当触屏的位置Y坐标<摇杆的Y坐标我们要取反值-0~-180
        if (pt2.y < pt1.y) {
            rad = -rad;
        }
        return rad;
    }

    public void getXY(PointF pt2, float R, double rad) {
        pt1.x = (float) (R * Math.cos(rad)) + pt2.x;
        pt1.y = (float) (R * Math.sin(rad)) + pt2.y;
    }
}


class Snake implements Cloneable{

    public Node pHead;
    public Node ptail=pHead;
    int size=0;

    private class Node{
        private PointF CircleP=new PointF(0,0);
        private Node NextNode;
        private Node PreNode;
        private Node(PointF CircleP){
            if(CircleP!=null)
               this.CircleP=CircleP;
        }
    }

    public Node AddBady(PointF CircleP){
        Node newBady=new Node(CircleP);
        if (size==0){
            pHead=newBady;
        }else {
            pHead.NextNode=pHead;
            newBady.PreNode=pHead;
            pHead=newBady;
        }
        size++;
        return pHead;
    }

    public PointF getNodePt(Node node){
       return node.CircleP;
    }

    public void setNodePt(PointF CircleP){

    }

    public Node getNextNode(Node node){
        return node.NextNode;
    }

    public Node getPreNode(Node node){
        return node.PreNode;
    }

    public Snake clone(){
        Snake o=null;
        try{
            o=(Snake)super.clone();
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return o;
    }
}