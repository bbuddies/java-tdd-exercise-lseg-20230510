import java.util.ArrayList;
import java.util.List;

public class Test1 {

   public static List<Integer> releaseZYS(int a){
       ArrayList<Integer> retu = new ArrayList();
       releaseZYS(a, retu);
       return retu;
   }

    private static void releaseZYS(int a, ArrayList<Integer> retu) {
       for(int i = 2; i <= a ; i ++){
           if(a % i == 0 && a/i != 1){
               releaseZYS(a/i, retu);
               releaseZYS(i, retu);
               break;
           }else if (a % i == 0 && a/i == 1){
               retu.add(a);
               break;
           }
       }
    }

    public static void main(String[] args) {
       for(int i = 1; i < 100; i ++){
           System.out.println(releaseZYS(i));
       }
    }
}
