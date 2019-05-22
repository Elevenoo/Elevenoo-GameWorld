package Testall;

public class SuperTest extends A {
    public static void main(String[] args){
        SuperTest t= new SuperTest();
        t.test();
    }
    public void test(){
        System.out.println(super.getClass().getSuperclass().getName());
    }
}

class A{}