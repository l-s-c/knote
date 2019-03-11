package cn.buu.note.service.note.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import cn.buu.note.dao.NoteDaoMapper;
import cn.buu.note.dao.RedisOperator;
import cn.buu.note.dao.remindDaoMapper;
import cn.buu.note.entity.NoteDao;
import cn.buu.note.entity.remindDao;
import cn.buu.note.exception.CustomException;
import cn.buu.note.exception.ErrorEnum;
import cn.buu.note.service.note.NoteService;
import cn.buu.note.utils.BaiDuSDK;
import cn.buu.note.utils.CronUtils;
import cn.buu.note.utils.UUIDUtils;
import cn.buu.note.utils.quartz.MyScheduler;
@Service
public class NoteServiceImpl implements NoteService {
	@Resource
	private NoteDaoMapper noteDaoMapper;
	@Resource
	private remindDaoMapper remindDaoMapper;
	@Resource
	private MyScheduler myScheduler;			//定时任务操作类
	@Resource
	HttpSession session;
	@Resource
	private RedisOperator redisOperator;
	@Override
	public List<NoteDao> loadNote(Integer prefix, Integer suffix) throws Exception {
		List<NoteDao> list = new ArrayList<>();
		if(prefix==null) 	prefix=0;
		if(suffix==null) 	suffix = 20;
		try {
			list = noteDaoMapper.selectByLimit(prefix,suffix);
		}catch(Exception e) {
			e.printStackTrace();
			throw new CustomException(ErrorEnum.DB_CONNECT_ERROR);
		}
		return list;
	}
	@Override
	public List<NoteDao> loadMyNote() throws Exception {
		
		List<NoteDao> list = new ArrayList<>();
		Integer phone = null;
		try {
			 phone =Integer.parseInt(redisOperator.get(session.getId()));
		}catch(Exception e) {
			e.printStackTrace();
		}
		try {
			list = noteDaoMapper.selectByCreateUser(phone);
		}catch(Exception e) {
			e.printStackTrace();
			throw new CustomException(ErrorEnum.DB_CONNECT_ERROR);
		}
		return list;
	}
	@Override
	public NoteDao loadNoteDesc(long id) throws Exception {
		NoteDao note = new NoteDao();
		try {
			note = noteDaoMapper.selectByPrimaryKey(id);
		}catch(Exception e) {
			e.printStackTrace();
			throw new CustomException(ErrorEnum.DB_CONNECT_ERROR);
		}
		return note;
	}
	@Override
	public void insertNote(String noteTitle, String noteText,int label) throws Exception {
		List<NoteDao> list = new ArrayList<>();
		Integer phone = null;
		try {
			 phone =Integer.parseInt(redisOperator.get(session.getId()));
		}catch(Exception e) {
			e.printStackTrace();
			throw new CustomException(ErrorEnum.USER_LOSE_ERROR);
		}
		NoteDao note = new NoteDao();
		note.setNoteTitle(noteTitle);
		note.setNoteText(noteText);
		note.setLabel(label);
		note.setPhone(phone);
		note.setCreateTime(new Date());
		note.setModifyTime(new Date());
		try {
			noteDaoMapper.insertSelective(note);
		}catch(Exception e) {
			e.printStackTrace();
			throw new CustomException(ErrorEnum.DB_CONNECT_ERROR);
		}
	}
	/**
	 * 保存备忘录
	 * @throws Exception 
	 */
	@Override
	public void insertReminder(remindDao remind) throws Exception {
		Integer phone = null;
		try {
			 phone =Integer.parseInt(redisOperator.get(session.getId()));
			 remind.setPhone(phone);
		}catch(Exception e) {
			e.printStackTrace();
		}
		//设置标签---备忘录
		remind.setLabel(2);
		
		System.out.println(remind);
		if(remind.getStartTime().equals("")||remind.getStartTime().length()==0) {
			//不用提醒
			//保存
			try {
				remindDaoMapper.insertSelective(remind);
			}catch(Exception e) {
				e.printStackTrace();
				throw new CustomException(ErrorEnum.DB_CONNECT_ERROR);
			}
		}else {
			//格式化时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				remind.setStartTime(sdf.format(sdf.parse(remind.getStartTime().replace("T", " ")+":00")));
				if(remind.getEndTime().equals("")||remind.getEndTime().length()==0) {
					remind.setEndTime(null);
				}else {
					remind.setEndTime(sdf.format(sdf.parse(remind.getEndTime().replace("T", " ")+":00")));
				}
				//根据时间和频率获取cron表达式
				String cron = CronUtils.getCron(sdf.parse(remind.getStartTime()),remind.getRate());
				remind.setCron(cron);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			//设置提醒
			//为提醒的定时任务起名
			//String jobName = UUIDUtils.getUUID()+phone.toString().substring(9);
			String jobName = "job"+UUIDUtils.getUUID()+phone.toString();
			System.out.println("jobName:"+jobName);
			String triggerName = "trigger"+UUIDUtils.getUUID();
			System.out.println("triggerName:"+triggerName);
			remind.setJobName(jobName);
			remind.setTriggerName(triggerName);
			//保存
			try {
				remindDaoMapper.insertSelective(remind);
			}catch(Exception e) {
				e.printStackTrace();
				throw new CustomException(ErrorEnum.DB_CONNECT_ERROR);
			}
			//设置定时任务
			myScheduler.setQuartz(jobName,remind.getText(), triggerName, sdf.parse(remind.getStartTime()),remind.getEndTime()==null?null:sdf.parse(remind.getEndTime()), remind.getCron());
		}

	}
	/**
	 * 利用百度API识别文字，并按格式生成笔记
	 * @throws Exception 
	 */
	@Override
	public boolean analyzeBase64(String base64) throws Exception {
		/**转换为byte[]*/
		if(base64==null) {
			return false;
		}
		 sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();   
		 JSONObject json = null;
		 String noteHtml = "";
		try {
			//解密
			base64 = base64.replaceAll("data:image/png;base64,", "");
			byte[] b = decoder.decodeBuffer(base64);
			/**调用接口识别文字*/
			json = BaiDuSDK.getOcrResult(b);
			System.out.println(json);
		}catch(Exception e) {
			throw e;
		}
		/**根据结果整理笔记*/
		 noteHtml = putInNote(json);
		//保存笔记   label = 3
		try {
			NoteDao note = new NoteDao();
			note.setCreateTime(new Date());
			note.setLabel(3);
			note.setModifyTime(new Date());
			note.setPhone(123);
			note.setNoteTitle("截取摘要");
			note.setNoteText(noteHtml);
			noteDaoMapper.insertSelective(note);
		}catch(Exception e) {
			e.printStackTrace();
			throw new CustomException(ErrorEnum.DB_CONNECT_ERROR);
		}
		return true;
	}
	/**
	 * 整理笔记
	 * @param str
	 * @return
	 */
	private  String putInNote(JSONObject json) {
		if(json==null) {
			return "";
		}
		boolean flag = false;		//本行是否在标记内，在标记内为true，否则为false
		String text = "";			//内容
		String noteHtml = "";		//生成的结果
		
		JSONArray arr = json.getJSONArray("words_result");
		int length = json.getJSONArray("words_result").length();
		for(int o=0;o<length;o++) {
			String word = arr.get(o).toString().replace("{\"words\":\"", "").replace("\"}", "");
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
	@Override
	public remindDao loadReminderDesc(long id) throws Exception {
		remindDao remind = new remindDao();
		try {
			 remind = remindDaoMapper.selectByPrimaryKey(id);	
		}catch(Exception e) {
			e.printStackTrace();
			throw new CustomException(ErrorEnum.DB_CONNECT_ERROR);
		}
		return remind;
	}
	@Override
	public void updateReminder(remindDao remind) throws Exception {
		Integer phone = null;
		try {
			 phone =Integer.parseInt(redisOperator.get(session.getId()));
			 remind.setPhone(phone);
			 remind.setLabel(2);
		}catch(Exception e) {
			e.printStackTrace();
			throw new CustomException(ErrorEnum.USER_LOSE_ERROR);
		}
		try {
			remindDaoMapper.updateByPrimaryKey(remind);
		}catch(Exception e) {
			e.printStackTrace();
			throw new CustomException(ErrorEnum.DB_CONNECT_ERROR);
		}
	}
	@Override
	public void updateNote(NoteDao note) throws Exception {
		Integer phone = null;
		try {
			 phone =Integer.parseInt(redisOperator.get(session.getId()));
			 note.setPhone(phone);
		}catch(Exception e) {
			e.printStackTrace();
			throw new CustomException(ErrorEnum.USER_LOSE_ERROR);
		}
		try {
			noteDaoMapper.updateByPrimaryKey(note);
		}catch(Exception e) {
			e.printStackTrace();
			throw new CustomException(ErrorEnum.DB_CONNECT_ERROR);
		}
		
	}
}
