package cn.buu.note.utils;

import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * 将base64转换为图片  并保存到指定路径
 * @author ABC
 *
 */
public class Base64DecoderUtils {
	public static boolean generateImage(String imgStr,String path) {
		if(imgStr==null) {
			return false;
		}
	//	BASE64Decoder decoder = new BASE64Decoder();
		 sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();    
		try {
			//解密
			byte[] b = decoder.decodeBuffer(imgStr);
			//byte[] b =new byte[1];
			for(int i=0;i<b.length;i++) {
				if(b[i]<0) {
					b[i] += 256;
				}
			}
				OutputStream os = new FileOutputStream(path);
				os.write(b);
				os.flush();
				os.close();
				return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
