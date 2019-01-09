package cn.buu.note.utils;

import org.springframework.util.DigestUtils;

public class Md5Utils {
		private static String slat = "Knote";
		public static String getMd5(String str) {
			String base = str+"/"+slat;
			String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
			return md5;
		}
		public static void main(String[] args) {
			String md5 = getMd5("123");
			System.out.println(md5);
		}
}
