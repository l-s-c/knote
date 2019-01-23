package cn.buu.note.utils.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * quartz 任务类
 * @author ABC
 *
 */
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
		System.out.println("hello world : "+msg);
		//调用推送接口，推送提示备忘录
	}

}
