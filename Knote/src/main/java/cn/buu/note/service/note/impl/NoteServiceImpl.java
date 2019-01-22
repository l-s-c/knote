package cn.buu.note.service.note.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import cn.buu.note.dao.NoteDaoMapper;
import cn.buu.note.dao.remindDaoMapper;
import cn.buu.note.entity.NoteDao;
import cn.buu.note.entity.remindDao;
import cn.buu.note.exception.CustomException;
import cn.buu.note.exception.ErrorEnum;
import cn.buu.note.service.note.NoteService;
import cn.buu.note.utils.CronUtils;
@Service
public class NoteServiceImpl implements NoteService {
	@Resource
	private NoteDaoMapper noteDaoMapper;
	@Resource
	private remindDaoMapper remindDaoMapper;
	@Resource
	HttpSession session;
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
		session.setAttribute("phone",123);
		List<NoteDao> list = new ArrayList<>();
		Integer phone = null;
		try {
			 phone =Integer.parseInt(session.getAttribute("phone").toString());
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
		session.setAttribute("phone",123);
		List<NoteDao> list = new ArrayList<>();
		Integer phone = null;
		try {
			 phone =Integer.parseInt(session.getAttribute("phone").toString());
		}catch(Exception e) {
			e.printStackTrace();
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
		session.setAttribute("phone",123);				//session
		Integer phone = null;
		try {
			 phone =Integer.parseInt(session.getAttribute("phone").toString());
			 remind.setPhone(phone);
		}catch(Exception e) {
			e.printStackTrace();
		}
		//设置标签---备忘录
		remind.setLabel(2);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			remind.setStartTime(sdf.format(sdf.parse(remind.getStartTime().replace("T", " ")+":00")));
			remind.setEndTime(sdf.format(sdf.parse(remind.getEndTime().replace("T", " ")+":00")));
			//根据时间和频率获取cron表达式
			String cron = CronUtils.getCron(sdf.parse(remind.getStartTime()),remind.getRate());
			remind.setCron(cron);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//保存
		try {
			remindDaoMapper.insertSelective(remind);
		}catch(Exception e) {
			e.printStackTrace();
			throw new CustomException(ErrorEnum.DB_CONNECT_ERROR);
		}
	}

}
