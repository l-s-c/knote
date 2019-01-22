package cn.buu.note.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * cron生成工具类
 * @author ABC
 *
 */
public class CronUtils {
	/**
	 * 
	 * @param date
	 * @param rate	频率
	 * 0   不重复
	 * 1   每天
	 * 2   每周
	 * 3   每月
	 * 4   每年
	 * 5   每季度
	 * @return
	 */
	public static String getCron(Date date,int rate) {
		//获取星期
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		int week = cal.get(Calendar.DAY_OF_WEEK);
		//格式化时间
		SimpleDateFormat sdf = new SimpleDateFormat("ss,mm,HH,dd,MM,yyyy");
		String[] ss = sdf.format(date).split(",");
		String cron = null;
		//按频率编写cron表达式
		switch(rate){
		case 0: 
			cron = ss[0]+" "+ss[1]+" "+ss[2]+" "+ss[3]+" "+ss[4]+" ? "+ss[5];
			break;
		case 1: 
			cron = ss[0]+" "+ss[1]+" "+ss[2]+" * * ? *";
			break;
		case 2: 
			cron = ss[0]+" "+ss[1]+" "+ss[2]+" ? * "+week+" *";
			break;
		case 3: 
			cron = ss[0]+" "+ss[1]+" "+ss[2]+" "+ss[3]+" * ? *";
			break;
		case 4: 
			cron = ss[0]+" "+ss[1]+" "+ss[2]+" "+ss[3]+" "+ss[4]+" ? *";
			break;
		case 5: 
			cron = ss[0]+" "+ss[1]+" "+ss[2]+" "+ss[3]+" "+ss[4]+"/3 ? *";
			break;
		}
		return cron;
	}
}
