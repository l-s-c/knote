package test;
 
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {	
    	Scanner scan = new Scanner(System.in);
    	int num = scan.nextInt();
    	int r = l(num);
    	System.out.println(r);
    }
	private static int l(int n) {
		 int re = 0;
         if(n == 0 || n == 1){
             return 1;
         }else{
             re = l(n-1)+l(n-2);
         }
         return re;
	}
}