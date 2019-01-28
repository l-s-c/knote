package cn.buu.note.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.buu.note.common.BaseController;
import cn.buu.note.common.JsonResult;
import cn.buu.note.dao.UserDaoMapper;
import cn.buu.note.entity.PostDao;
import cn.buu.note.entity.UserDao;
import cn.buu.note.service.post.PostService;


@Controller
@RequestMapping("/post")
@CrossOrigin       //处理跨域请求
public class PostController extends BaseController{
	private static Logger logger = Logger.getLogger(PostController.class);
		@Resource
		private PostService postService;
		@Resource
		private UserDaoMapper userDaoMapper;
	
		
		/**
		 * 保存楼层评论
		 * @param post
		 * @param session
		 * @return
		 * @throws Exception
		 */
		@RequestMapping("/savePostToDb")
		@ResponseBody
		public JsonResult savePostToDb(PostDao post , HttpSession session) throws Exception {
			session.setAttribute("phone", 123);
			Integer phone = Integer.parseInt(session.getAttribute("phone").toString());
			post.setPhone(phone);
			Long id = postService.savePostToDb(post);
			UserDao user = userDaoMapper.selectUserByPhone(phone);
			user.setId(id);
			System.out.println("usre:"+user);
			return new JsonResult(user);
		}
		/**
		 * 删除楼层评论
		 * @param id
		 * @return
		 * @throws Exception
		 */
		@RequestMapping("/delPostFromDb")
		@ResponseBody
		public JsonResult delPostFromDb(String id) throws Exception {
			System.out.println("idcontroller:"+id);
			postService.delPostFromDb(id);
			return new JsonResult();
		}
}
