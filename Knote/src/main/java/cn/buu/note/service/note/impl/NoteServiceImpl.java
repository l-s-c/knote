package cn.buu.note.service.note.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import cn.buu.note.dao.NoteDaoMapper;
import cn.buu.note.entity.NoteDao;
import cn.buu.note.exception.CustomException;
import cn.buu.note.exception.ErrorEnum;
import cn.buu.note.service.note.NoteService;
@Service
public class NoteServiceImpl implements NoteService {
	@Resource
	private NoteDaoMapper noteDaoMapper;
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

}
