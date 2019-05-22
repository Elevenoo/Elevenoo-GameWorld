package Testall;

class ThisTest {
    public static void main(String[] args){
        sub ss=new sub();
    }
}

class sub extends base{
    public sub(){
        super();
        System.out.println("sub");

    }
}
class base{
    public base(){
        System.out.println("Base");
    }
}
