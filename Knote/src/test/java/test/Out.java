package test;

public class Out {
	private String name = "lsc";
	private static int age = 5;
	static class inner{
		//静态内部类
		private int a =  age;
		//private String s = name;
	}
	class inner1{
		//成员内部类
		private int a =  age;
		private String s = name;
	}
	public void start() {
		//局部内部类
		class inner2{
			private int a0 =  age;
			private String s = name;
		}
	}
	
	public static void main(String[] args) {
        // 实现runnable借口，创建多线程并启动
     new Thread(new Runnable() {

		@Override
		public void run() {
			//匿名内部类
		}
    	 
     }).start();
	}

}

