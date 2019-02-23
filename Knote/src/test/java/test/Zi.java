package test;

public class Zi extends Fu {  
    public static void main(String[] args) {  
    	//Zi zi = new Zi(); 
        Fu zi = new Zi();  
        zi.show();  
        zi.method();  
    }  
    public static void show() {  
        System.out.println("子类的静态");  
    }  
    public void method() {  
        System.out.println("子类的一般方法");  
    }  
      
} 
class Fu{
    public static void show() {  
        System.out.println("fu类的静态");  
    }  
    public void method() {  
        System.out.println("fu类的一般方法");  
    }  
}