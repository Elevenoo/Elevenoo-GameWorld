package Testall;

public class finalTest {
    public static void main(String[] args){
        final StringBuffer s= new StringBuffer("hello");
        s.append(" world");
        System.out.println(s);
//        //final 修饰的变量不能被修改
//        s = new StringBuffer("its ok");

//        final int a=2;
//        a=1;
//        System.out.println(a);
    }
}
