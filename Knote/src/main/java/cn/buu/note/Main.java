package cn.buu.note;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import com.baidu.aip.ocr.AipOcr;

public class Main {
	public static void main(String[] args) throws Exception {
/*		File file = new File("");
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		br.readLine();*/
/*		int [] arr = {1,2,3,4,5,8,9,10,5,56,7};
		for(int i=0;i<arr.length-1;i++) {
			for(int j=0;j<arr.length-1-i;j++) {
				if(arr[j]<arr[j+1]) {
					int t = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = t;
				}
			}
		}
		for(int i : arr) {
			System.out.print(i+" , ");
		}*/
/*		byte a = 127;
		byte b = 127;
		a += b;
		System.out.println(a);*/
		// dispathServlet  handlerMapping  controller  ModelAndView  ViewResolver
		String d = handlerStr();
		System.out.println(d);
	/*	String s  =  "4慕课end网";
		System.out.println(s.indexOf("end"));*/
	}
	public static String handlerStr() {
		String noteText  = "";
		String noteHtml = "";
		String[] str = {"标题1Spring Boot异步执行程序",
						"标题2十使用注解@ EnableAsync开启异步,会自动扫描",
						"start◆定义@ Component@ Async作为组件被容器扫描执行",
						"4慕课end网"};
		noteHtml = putInNote(str);
		return noteHtml;
	}
	/**
	 * 整理笔记
	 * @param str
	 * @return
	 */
	private static String putInNote(String[] str) {
		if(str.length==0) {
			return "";
		}
		boolean flag = false;		//本行是否在标记内，在标记内为true，否则为false
		String text = "";			//内容
		String noteHtml = "";
		for(int o=0;o<str.length;o++) {
			String word = str[o];
			System.out.println(word);
		
		//是不是一标题
		if(word.startsWith("标题1")) {
			noteHtml += "<h3 class='titleone'>"+word.replace("标题1", "")+"</h3>";
		}
		if(word.startsWith("标题2")) {
			noteHtml += "<h4 class='titletwo'>"+word.replace("标题2", "")+"</h4>";
		}
		//text
		int start = word.indexOf("start");
		int end = word.indexOf("end");
		if(start>=0) {
			//开始与结束标记在同一行
			if(end>0&&end>start) {
				word = word.substring(start+1, end);
				text += word;
				noteHtml += "<p class='noteDesc'>"+text+"</p>";
			}
			//只有开始标记没有结束标记
			if(end<0) {
				word = word.substring(start+1);
				text += word;
				flag = true;
			}
			if(end == 0) {
				flag = false;
			}
		}else {						//没有开始标记
			if(end>=0) {
				if(flag) {
					word = word.substring(0,end);
					text += word;
					flag = false;
					noteHtml += "<p class='noteDesc'>"+text+"</p>";
				}
			}else {
				if(flag) {
					text += word;
					flag = false;
				}
			}
		}
		}
		 return noteHtml;
	}
	public static void sample(AipOcr client) {
	    // 传入可选参数调用接口
	    HashMap<String, String> options = new HashMap<String, String>();
	    options.put("language_type", "CHN_ENG");
	    options.put("detect_direction", "true");
	    options.put("detect_language", "true");
	    options.put("probability", "true");
	    
	    
	    // 参数为本地路径
	    String image = "C:/Users/lsc/Desktop/immg/201.png";
	    JSONObject res = client.basicGeneral(image, options);
	    System.out.println(res.toString(2));
	    // 参数为二进制数组
	 /*   byte[] file = readFile("test.jpg");
	    res = client.basicGeneral(file, options);
	    System.out.println(res.toString(2));*/
	    
	    // 通用文字识别, 图片参数为远程url图片
	    /*JSONObject res = client.basicGeneralUrl(url, options);
	    System.out.println(res.toString(2));*/

	}
}
