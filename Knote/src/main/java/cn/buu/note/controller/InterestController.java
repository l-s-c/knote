package cn.buu.note.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.buu.note.common.BaseController;
import cn.buu.note.common.JsonResult;
import cn.buu.note.service.interest.InterestService;

@Controller
@RequestMapping("/interest")
@CrossOrigin(allowCredentials="true",allowedHeaders="*") 
public class InterestController extends BaseController{
	
	@Resource
	private InterestService interestService;
	/**
	 * 获取关注用户的人
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getWhoLikeMe")
	@ResponseBody
	public JsonResult getWhoLikeMe(HttpSession session) throws Exception {
		List<Map<String,Object>> list = interestService.getWhoLikeMe(session);
		return new JsonResult(list);
	}
	/**
	 * 互相关注
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/interestPeople")
	@ResponseBody
	public JsonResult interestPeople(String id) throws Exception {
		interestService.interestPeople(Long.parseLong(id));
		return new JsonResult();
	}
	
	/**
	 * 取消互相关注
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/unInterestPeople")
	@ResponseBody
	public JsonResult unInterestPeople(String id) throws Exception {
		interestService.unInterestPeople(Long.parseLong(id));
		return new JsonResult();
	}
}
