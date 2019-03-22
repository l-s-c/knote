package cn.buu.note.utils.quartz;

import java.io.IOException;


import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.annotation.Import;

import cn.buu.note.utils.SpringUtil;
import cn.buu.note.utils.push.AppPush;

/**
 * quartz 任务类
 * @author ABC
 *
 */
@Import(SpringUtil.class)
public class Task implements Job{
	
	private String msg;
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * 处理业务逻辑方法
	 */
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("quarz 提醒: "+msg);
		//调用推送接口，推送提示备忘录
		try {
			AppPush appPush = SpringUtil.getBean(AppPush.class);
			appPush.push("备忘录", msg, "http://www.baidu.com");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
