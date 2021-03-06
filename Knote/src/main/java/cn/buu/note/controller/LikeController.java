package cn.buu.note.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.buu.note.common.BaseController;
import cn.buu.note.common.JsonResult;
import cn.buu.note.entity.NoteDao;
import cn.buu.note.service.like.LikeService;

@Controller
@RequestMapping("/like")
@CrossOrigin(allowCredentials="true",allowedHeaders="*")       //处理跨域请求
public class LikeController extends BaseController{
//	private static Logger logger = Logger.getLogger(LikeController.class);
		@Resource
		private LikeService likeService;
	
		/**
		 * 保存收藏
		 * @param noteId
		 * @return
		 * @throws Exception
		 */
		@RequestMapping("/likeNote")
		@ResponseBody
		public JsonResult likeNote(Long noteId) throws Exception {
			likeService.likeNote(noteId);
			return new JsonResult();
		}
		/**
		 * 取消收藏
		 * @param noteId
		 * @return
		 * @throws Exception
		 */
		@RequestMapping("/cancelLike")
		@ResponseBody
		public JsonResult cancelLike(Long noteId) throws Exception {
			likeService.cancelLike(noteId);
			return new JsonResult();
		}
		
		/**
		 * 判断当前用户是否收藏笔记
		 * @param noteId
		 * @return
		 * @throws Exception
		 */
		@RequestMapping("/getLike")
		@ResponseBody
		public JsonResult getLike(Long noteId) throws Exception {
			boolean b = likeService.getLike(noteId);
			return new JsonResult(b);
		}
		/**
		 * 我的收藏--加载收藏笔记
		 * @return
		 * @throws Exception
		 */
		@RequestMapping("/loadLikeNote")
		@ResponseBody
		public JsonResult loadLikeNote() throws Exception {
			List<NoteDao> list = likeService.loadLikeNote();
			return new JsonResult(list);
			
		}

}
