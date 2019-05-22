package Testall;

public class TestAttribute {
    static int staticInt=0;
    int nonstaticInt=0;
    public static void main(String[] args){
        //static方法可以通过类直接调用，也可以通过对象调用
        //单例模式的类只能有一个实例,构造函数被private修饰，该类提供static方法来创造对象
        Singleton si=Singleton.getInstance();

        TestAttribute t1=new TestAttribute();
        System.out.println("t1.staticInt="+t1.staticInt);
        System.out.println("TestAttribute.staticInt="+TestAttribute.staticInt);
        System.out.println("t1.nonstaticInt="+t1.nonstaticInt);
        t1.staticInt++;
        t1.nonstaticInt++;
        TestAttribute t2=new TestAttribute();
        System.out.println("t2.staticInt="+t2.staticInt);
        System.out.println("TestAttribute.staticInt="+TestAttribute.staticInt);
        System.out.println("t2.nonstaticInt="+t2.nonstaticInt);
        System.out.println("t1.nonstaticInt="+t1.nonstaticInt);
    }


//    //static方法中不能访问非static类型的变量
//    static int getint(){
//        fun();
//        return staticInt+nonstaticInt;
//    }
//    public void fun(){}
//
}

class Singleton {
    private static Singleton instance=null;
    private Singleton(){
        System.out.println("被隐藏的构造函数");
    }
    public static Singleton getInstance() {
        System.out.println("static方法创建对象");
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

}
