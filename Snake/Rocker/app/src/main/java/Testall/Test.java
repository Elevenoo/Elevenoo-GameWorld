package Testall;

public class Test {


    public static void main(String[] args){
        try{
         // Class c=Class.forName("Sub");
            Class c=Sub.class;
            //Class c=this.getClass();    //静态成员不绑定实例
            Base b=(Base)c.newInstance();
            b.f();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public void memberFun(){
//        Class c=this.getClass();     //非静态成员绑定实例
//    }
//
//    //非静态内部类不能定义静态成员，可以访问外部类的所有成员（函数）
//    static int a=0;
//    class innerclass{      //成员内部类
//        public static int geta(){
//            return a;
//        }
//    }

    //静态内部类不能访问外部类的非静态成员
//    static int a=0;
//    int b=1;
//    static class  innerclass{     //静态内部类
//        public static int geta(){
//            return a+b;
//        }
//    }

//    //局部内部类定义在方法内，若方法是静态，则局部内部类为局部静态内部类，特性同静态内部类；
//    //局部内部类访问方法内的局部成员，必须是final修饰的局部52成员
//    int a=0;
//    public  void memberFun(){
//        int b=1;
//        final int bf=2;
//        public class innerclass{
//            int c=a;
//            int d=b;
//            int e=bf;
//        }
//    }
}

class Base{

    public static int a(){
        return 1;
    }

    public void f(){
        System.out.println("Base");
    }
}

class Sub extends Base{
    public void f(){

        System.out.println("Sub");
    }
}


