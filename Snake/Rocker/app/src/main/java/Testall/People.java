package Testall;

public class People extends baba{
    String name;
    public static void main(String[] args){
        People p=new People("People");
    }
    public People(String name){
       this.name=name;
       System.out.println(this.name);
       System.out.println(super.name);
    }
}

class baba{
    String name="baba";
}
