package cn.buu.note.utils.id;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 全局唯一ID  数字
 * @author ABC
 *
 */
public class IdUtils {
	/**当前ID*/
	 private static String currentId;
	
	 public static synchronized String getId() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = sdf.format(new Date());
		Integer random = (int)(Math.random()*1000);
		String strId = date+random;
		if(strId.equals(currentId)) {
			getId();
		}else {
			currentId = strId;
			return currentId;
		}
		return "00000000";
	 }
}
