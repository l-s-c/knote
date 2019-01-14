package cn.buu.note.service.note.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

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

}
