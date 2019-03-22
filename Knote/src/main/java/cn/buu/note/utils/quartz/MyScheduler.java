package cn.buu.note.utils.quartz;

import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Service;
/**
 * 定时任务调度器
 * 增加，删除定时任务
 * @author ABC
 *
 */
@Service
public class MyScheduler {

	 SchedulerFactory sfact = new StdSchedulerFactory();
	 Scheduler scheduler = null;

	/**
	 * 增加定时任务
	 * @param myJob			job name
	 * @param msg			参数  提醒内容
	 * @param myTrigger		触发器name
	 * @param startTime		
	 * @param endTime
	 * @param cron			
	 * @throws SchedulerException
	 */
		public  void setQuartz(String myJob,String msg,
				String myTrigger,Date startTime,Date endTime,String cron) throws SchedulerException {
			try {
				//创建一个JobDetial 实例，并且绑定 Job     //任务
				JobDetail jobDetail = 
						JobBuilder.newJob(Task.class)
						.withIdentity(myJob, "group1")
						.usingJobData("msg",msg) 
						.build();
					
				/**CronTrigger*/					 //触发器
				CronTrigger trigger = 
						(CronTrigger)TriggerBuilder.newTrigger()
						.withIdentity(myTrigger, "group1")
						.startAt(startTime)                      //startTime
						.endAt(endTime)							//endTime
						.withSchedule(CronScheduleBuilder.cronSchedule(cron) 
									.withMisfireHandlingInstructionDoNothing())	//不立即执行按照cron表达式执行
						.build();
				
				//创建 Scheduler实例            //调度
				scheduler = sfact.getScheduler();
				scheduler.start();
				Date date = scheduler.scheduleJob(jobDetail, trigger);  //返回值为即将执行任务的时间
			//	scheduler.standby();         //暂时挂起，再次调用时会重新启动
			//	scheduler.shutdown(false);        //停止              参数默认为false,  如果为true, 会等待所有正在执行的job执行之后关闭

			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		   /** 
	     * @Description: 移除一个任务 
	     *  
	     * @param jobName 
	     * @param jobGroupName 
	     * @param triggerName 
	     * @param triggerGroupName 
		 * @throws SchedulerException 
	     */  
		   //删除任务
	    public  void deleteJob(String jobName,String triggerName){
	    	try {
		    	SchedulerFactory sfact = new StdSchedulerFactory();
				Scheduler scheduler = sfact.getScheduler();
		        if(scheduler==null){
		            return;
		        }
		        //JobKey对象
		        JobKey jobKey = new JobKey(jobName);
		        //触发器
		        TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, "group1");
		        try {
		            //停止触发器
		            scheduler.pauseTrigger(triggerKey);
		            //移除触发器
		            scheduler.unscheduleJob(triggerKey);
		            //删除任务
		            scheduler.deleteJob(jobKey);
		        } catch (SchedulerException e) {
		            e.printStackTrace();
		        } 
	    	}catch(Exception e) {
	    		e.printStackTrace();
	    	}
      
	    }
	    
/*	    public static void main(String[] args) throws Exception {
	    	MyScheduler myScheduler = new MyScheduler();
	    	myScheduler.setQuartz("myJob00", "hello w", "myTrigger00", new Date(), null, "* * * * * ?");
		}*/
}
