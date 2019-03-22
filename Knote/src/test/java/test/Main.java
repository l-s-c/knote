package test;

import java.io.File;

public class Main {
	static int count =0;
    public static void main(String[] args) {	
    	
    	String str = "0";
    	boolean b = str.equals("0");
    	System.out.println("b:"+b);
    	
    	
    	
    	
    	
    	File file = new File("C:/Users/lsc/Desktop/test");
    	//getTest(file);
    }
    public static void getTest(File file) {
    	
		File[] files = file.listFiles();
		for(File f:files) {
	    	if(f.isDirectory()) {
	    		getTest(f);
	    	}else {
	    		System.out.println(f.getPath());
	    	}
			
		}
    	
    	
    	
    	
    	
    	

    }
}