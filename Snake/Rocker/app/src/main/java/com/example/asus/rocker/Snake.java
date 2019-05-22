package com.example.asus.rocker;

/**
 *  Achieve the addition and modification of the snake's head and body
 *                (growth and movement)
 *
 *  Write by Elevenoo , 2019/5/20
 */
class Snake implements Cloneable{
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Node pHead;
    public Node ptail=pHead;
    int size=0;

    public class Node{
        private float[] bodyP=new float[2];
        private Node NextNode;
        private Node PreNode;
        private Node(float body_x,float body_y){
            this.bodyP[0]=body_x;
            this.bodyP[1]=body_y;
        }
    }

    /**
     * Achieve the addition of the snake's head and body
     * @param body_x
     * @param body_y
     * @return
     */
    public Node AddBady(float body_x,float body_y){
        Node newBady=new Node(body_x,body_y);
        if (size==0) {
            pHead = newBady;
            ptail = pHead;
        }else {
            newBady.NextNode=pHead;
            pHead.PreNode=newBady;
            pHead=newBady;
        }
        size++;
        return pHead;
    }

    public Node getPreN(Node body){
        return  body.PreNode;
    }

    public Node getNextN(Node body){
        return  body.NextNode;
    }

    public float[] getNodeP(Node bodyN){
        return  bodyN.bodyP;
    }
    /**
     * Achieve and modification of the snake's head and body
     *
     * @param index
     * @param pt
     */
    public void setNodeP(int index,float[] pt){
        Node findN=ptail;
        for (int i=0;i<index;i++){
            findN=findN.PreNode;
        }
        findN.bodyP=pt;
    }
    /**
     * Starting from the tail node (snake head), traversing the node index
     *
     * @param index
     */
    public Node getIndN(int index){
        Node IndN=ptail;
        for (int i=0;i<index;i++){
            IndN=IndN.PreNode;
        }
        return IndN;
    }
}