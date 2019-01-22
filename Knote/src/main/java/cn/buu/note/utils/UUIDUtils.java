package cn.buu.note.utils;

import java.util.UUID;

/**
 * 生成UUID工具类
 * @author ABC
 *
 */
public class UUIDUtils {
	public static String getUUID(){
		String uuid = "";
		uuid = UUID.randomUUID().toString().replaceAll("-", "");
		return uuid;
	}
}
