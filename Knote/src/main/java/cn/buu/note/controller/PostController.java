package cn.buu.note.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.buu.note.common.BaseController;
import cn.buu.note.common.JsonResult;
import cn.buu.note.dao.UserDaoMapper;
import cn.buu.note.entity.PostAgain;
import cn.buu.note.entity.PostDao;
import cn.buu.note.entity.PostResult;
import cn.buu.note.entity.UserDao;
import cn.buu.note.service.post.PostService;


@Controller
@RequestMapping("/post")
@CrossOrigin       //处理跨域请求
public class PostController extends BaseController{
	//private static Logger logger = Logger.getLogger(PostController.class);
		@Resource
		private PostService postService;
		@Resource
		private UserDaoMapper userDaoMapper;
		
		@RequestMapping("/{postId}/readMore")
		@ResponseBody
		public JsonResult readMore(@PathVariable Long postId) throws Exception {
			List<PostAgain> list = postService.loadReplyByPostId(postId);
			return new JsonResult(list);
		}
		
		/**
		 * 通过笔记id查询评论
		 * @param id
		 * @return
		 * @throws Exception
		 */
		@RequestMapping("/{id}/loadPost")
		@ResponseBody
		public JsonResult loadPost(@PathVariable Long id) throws Exception {
			List<PostResult> list = postService.loadPost(id);
			return new JsonResult(list);
		}
		/**
		 * 保存回复评论
		 * @param postAgain
		 * @return
		 * @throws Exception
		 */
		@RequestMapping("/reply")
		@ResponseBody
		public JsonResult reply(PostAgain postAgain) throws Exception {
			System.out.println("postAgain:"+postAgain);
			postService.reply(postAgain);
			return new JsonResult();
		}
		
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
		/**
		 * 删除楼层子评论
		 * @param id	post_ag_info 表的唯一id
		 * @return
		 * @throws Exception
		 */
		@RequestMapping("/delPostAgFromDb")
		@ResponseBody
		public JsonResult delPostAgFromDb(String id) throws Exception {
			postService.delPostAgFromDb(id);
			return new JsonResult();
		}
}
