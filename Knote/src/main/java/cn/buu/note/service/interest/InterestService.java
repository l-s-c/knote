package cn.buu.note.service.interest;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import cn.buu.note.entity.InterestDao;

public interface InterestService {

	List<Map<String,Object>> getWhoLikeMe(HttpSession session) throws Exception;

	int interestPeople(Long id) throws Exception;

	void unInterestPeople(Long id) throws Exception;

}
