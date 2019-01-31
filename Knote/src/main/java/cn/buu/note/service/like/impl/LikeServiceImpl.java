package cn.buu.note.service.like.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import cn.buu.note.dao.LikeDaoMapper;
import cn.buu.note.entity.LikeDao;
import cn.buu.note.exception.CustomException;
import cn.buu.note.exception.ErrorEnum;
import cn.buu.note.service.like.LikeService;
@Service
public class LikeServiceImpl implements LikeService{
	@Resource
	private LikeDaoMapper likeDaoMapper;
	@Resource
	private HttpSession session;
	@Override
	public int likeNote(Long noteId) throws Exception {
		LikeDao like = new LikeDao();
		like.setNoteId(noteId);
		int rows = 0;
		try {
			session.setAttribute("phone", 123);
			Integer phone =	Integer.parseInt(session.getAttribute("phone").toString());
			like.setPhone(phone);
		}catch(Exception e) {
			e.printStackTrace();
			throw new CustomException(ErrorEnum.USER_LOSE_ERROR);
		}
		try {
			rows = likeDaoMapper.insertSelective(like);
		}catch(Exception e) {
			e.printStackTrace();
			throw new CustomException(ErrorEnum.DB_CONNECT_ERROR);
		}
		return rows;
	}
	@Override
	public void cancelLike(Long noteId) throws Exception {
		Integer phone  ;
		try {
			session.setAttribute("phone", 123);
			phone =	Integer.parseInt(session.getAttribute("phone").toString());
		}catch(Exception e) {
			e.printStackTrace();
			throw new CustomException(ErrorEnum.USER_LOSE_ERROR);
		}
		try {
			System.out.println("phone:"+phone+", noteId:"+noteId);
			
			likeDaoMapper.deleteByPhoneAndNoteId(phone,noteId);
		}catch(Exception e) {
			e.printStackTrace();
			throw new CustomException(ErrorEnum.DB_CONNECT_ERROR);
		}
	}
	@Override
	public boolean getLike(Long noteId) throws Exception {
		Integer phone  ;
		try {
			session.setAttribute("phone", 123);
			phone =	Integer.parseInt(session.getAttribute("phone").toString());
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		try {
			System.out.println("phone:"+phone+", noteId:"+noteId);
			LikeDao like = likeDaoMapper.selectIfLike(phone,noteId);
			if(like!=null) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

}
