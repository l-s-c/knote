package test;

public class Single {
	
	//饿汉式
	/*private static Single s = new Single();
		private Single() {
			
		}
		public static Single get() {
			
			return s;
		}*/
		
	//懒汉式+同步锁（synchronized）
/*	private static Single s = null;
	private Single() {}
	public static synchronized Single get() {
		if(s==null) {
			s = new Single();
		}
		return s;
	}*/
	//懒汉式+静态内部类
	static class inner {
		private static Single s = new Single();
	}
	private Single () {}
	public static Single get() {
		return inner.s;
	}
	
	
	
}
