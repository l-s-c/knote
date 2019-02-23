package test;

public class IntegerCompareint {
		public static void main(String[] args) {
			Integer i1 = new Integer(100);
			Integer i2 = new Integer(100);
			Integer i3 = Integer.valueOf(100);
			int i4 = 100;
			Integer i5 = 100;
			Integer i6 = 128;
			Integer i7 = 128;
			/**
			 * new 了两个对象，== 比较的是对象地址  所以为false
			 * */
			System.out.println(i1==i2); 	//false
			/**
			 * new 出来的变量指向的是堆中的对象，非new 出来的变量指向的是java常量池中的对象
			 */
			System.out.println(i1==i3); 	//false
			/**
			 * Integer 类型变量  与  int 类型变量比较时会自动拆包为int
			 */
			System.out.println(i3==i4); 	//true
			System.out.println(i1==i4);		//true
			/**
			 * 对于两个非new生成的Integer对象，进行比较时，
			 * 如果两个变量的值在区间-128到127之间，则比较结果为true，
			 * 如果两个变量的值不在此区间，则比较结果为false
			 */
			System.out.println(i3==i5); 	//true
			System.out.println(i6==i7); 	//false
			
			
			//以上比较均为 == 比较，equals 比较结果均为true
			
		}
}
