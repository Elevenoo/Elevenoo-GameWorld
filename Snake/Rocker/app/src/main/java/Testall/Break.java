package Testall;

public class Break {
    public static void main(String[] args){
        //设置标识符out1,break out1跳出整个循环
        out1:
        for(int i=0;i < 5;i++){
           for(int j=0;j< 5 ;j++){
               if(j>=2)
                   break out1;
               System.out.println(j);
            }

        }
    }
}
