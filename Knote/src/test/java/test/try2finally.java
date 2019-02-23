package test;

public class try2finally {
		public static void main(String[] args) {
			int n = start();
			System.out.println(n);
		}
		public static int start() {
			try {
				System.out.println("try");
				return 1;
			}finally {
				System.out.println("finally");
				//return 2;
			}
		}
}
