package cn.buu.note.service.interest.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import cn.buu.note.dao.InterestDaoMapper;
import cn.buu.note.dao.RedisOperator;
import cn.buu.note.exception.CustomException;
import cn.buu.note.exception.ErrorEnum;
import cn.buu.note.service.interest.InterestService;

@Service
public class InterestServiceImpl implements InterestService{
	@Resource
	private RedisOperator redisOperator;
	@Resource
	private InterestDaoMapper interestDaoMapper;
	@Override
	public List<Map<String,Object>> getWhoLikeMe(HttpSession session) throws Exception {
		Integer phone;
		try {
			phone = Integer.parseInt(redisOperator.get(session.getId()));
		}catch(Exception e) {
			e.printStackTrace();
			throw new CustomException(ErrorEnum.USER_LOSE_ERROR);
		}
		List<Map<String,Object>> list = interestDaoMapper.selectWhoLikeMe(phone);
		return list;
	}
	@Override
	public int interestPeople(Long id) throws Exception {
		int row = 0;
		try {
			row = interestDaoMapper.updataStateById(id,2);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomException(ErrorEnum.DB_CONNECT_ERROR);
		}
		return row;
	}
	@Override
	public void unInterestPeople(Long id) throws Exception {
		try {
			interestDaoMapper.updataStateById(id,1);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomException(ErrorEnum.DB_CONNECT_ERROR);
		}
	}
	
}
