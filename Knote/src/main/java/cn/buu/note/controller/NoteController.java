package cn.buu.note.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.buu.note.common.BaseController;
import cn.buu.note.common.JsonResult;
import cn.buu.note.entity.NoteDao;
import cn.buu.note.service.note.NoteService;

@Controller
@RequestMapping("/note")
@CrossOrigin
public class NoteController extends BaseController{
	 @Resource
	 private NoteService noteService;
	 
	 @RequestMapping("/{id}/loadNoteDesc")
	 @ResponseBody
	 public JsonResult loadNoteDesc(@PathVariable long id) throws Exception {
		NoteDao note = noteService.loadNoteDesc(id);
		return new JsonResult(note);
	 }
	 /**
	  * 加载自己笔记
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("/loadMyNote")
	 @ResponseBody
	 public JsonResult loadMyNote () throws Exception {
		List<NoteDao> list = noteService.loadMyNote();
		return new JsonResult(list);
	 }
	 /**
	  * 初始加载
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("/loadNote")
	 @ResponseBody
	 public JsonResult loadNote () throws Exception {
		 Integer prefix = 0;
		 Integer suffix = 20;
		List<NoteDao> list = noteService.loadNote(prefix,suffix);
		return new JsonResult(list);
	 }
	 /**
	  * 下拉加载
	  * @param prefix
	  * @param suffix
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("/loadAfterNote")
	 @ResponseBody
	 public JsonResult loadAfterNote (Integer prefix,Integer suffix) throws Exception {
		List<NoteDao> list = noteService.loadNote(prefix,suffix);
		return new JsonResult(list);
	 }
}
