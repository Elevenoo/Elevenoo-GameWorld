package Testall;

public class staticTest {
    private static  int b=1;
    static  int c=1;
    static {
        int a=0;
        b=2;
        staticTest.c=3;
    }
    public static void main(String[] args){
     //   System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }

}
