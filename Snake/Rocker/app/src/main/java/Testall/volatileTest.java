package Testall;

public class volatileTest{
    public static void main(String[] args){
        sub2 s=new sub2();
        s.stop();
    }

}

class sub2 implements Runnable{
    public volatile Boolean flag1=true;
    public  Boolean flag2=true;
    public void stop(){
        flag1=false;
        flag2=false;
    }
    @Override
    public void run() {
        System.out.println("flag1="+flag1+"   flag2="+flag2);
    }
}
