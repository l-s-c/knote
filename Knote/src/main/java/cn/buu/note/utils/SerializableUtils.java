package cn.buu.note.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


/**
 * 序列化与反序列化工具类
 * @author ABC
 *
 */
public class SerializableUtils {
		public  static byte[] serializable (Object obj) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte [] b = null;
			ObjectOutputStream oos = null;
			try {
				oos = new ObjectOutputStream(baos);
				oos.writeObject(obj);
				b = baos.toByteArray();
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				try {
					baos.close();
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return b;
		}
		public static Object unserializable(byte[] bye) {
			ByteArrayInputStream bais = new ByteArrayInputStream(bye);
			ObjectInputStream ois = null;
			Object obj = null;
			try {
				 ois = new ObjectInputStream(bais);
				 obj = ois.readObject();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return obj;
		}
/*		public static void main(String[] args) {
			UserDao d = new UserDao();
			System.out.println(serializable(d));
			System.out.println(unserializable(serializable(d)));
		}*/
}
