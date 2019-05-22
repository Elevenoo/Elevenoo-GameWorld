package Testall;

public class BackTest {
    public static void insertSort(int[]a,IntCompare cmp){
        if(a!=null){
            for (int i=1;i<a.length;i++){
                int temp=a[i];
                int j=i;
                while(j>=1 &&cmp.cmp(a[j-1],temp)==1){
                    a[j]=a[j-1];
                    j--;
                }
                a[j]=temp;
            }
        }
    }

    public static void main(String[] arg){
        int[] array={7,3,19,40,4,7,1};
        insertSort(array,new cmp1());
        for (int i=0;i<array.length;i++){
            System.out.print(array[i]+" ");
        }
        System.out.println();
        insertSort(array,new cmp2());
        for (int i=0;i<array.length;i++){
            System.out.print(array[i]+" ");
        }

    }

}

interface IntCompare{
    //接口中只含有方法的定义，没有方法的实现，接口中的所有方法都是抽象的，
    // 接口中成员的作用域修饰符都是public，常量都是public static final修饰
    int cmp(int a,int b);

}

class cmp1 implements IntCompare{
    public int cmp(int a,int b) {
        if (a > b)
            return 1;
        else if (a < b)
            return -1;
        else
            return 0;
    }
}

class cmp2 implements IntCompare{
    public int cmp(int a,int b) {
        if (a < b)
            return 1;
        else if (a > b)
            return -1;
        else
            return 0;
    }
}
