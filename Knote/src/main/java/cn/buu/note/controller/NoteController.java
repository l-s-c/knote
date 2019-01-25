package cn.buu.note.controller;

import java.util.Date;
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
import cn.buu.note.entity.remindDao;
import cn.buu.note.service.note.NoteService;

@Controller
@RequestMapping("/note")
@CrossOrigin
public class NoteController extends BaseController{
	 @Resource
	 private NoteService noteService;
	 
	 @RequestMapping("/{id}/loadReminderDesc")
	 @ResponseBody
	 public JsonResult loadReminderDesc(@PathVariable long id) throws Exception {
		remindDao remind = noteService.loadReminderDesc(id);
		return new JsonResult(remind);
	 }
	 
	 /**
	  * 获取图片base64码 转化为图片调用orc接口识别
	  * @param base64
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("/base64")
	 @ResponseBody
	 public JsonResult base64(String base64) throws Exception{
		 System.out.println(base64);
		 boolean b = noteService.analyzeBase64(base64);
		 
		 return new JsonResult();
	 }
	 
	 /**
	  * 保存备忘录
	  * @param remind
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("/saveReminder")
	 @ResponseBody
	 public JsonResult saveReminder(remindDao remind) throws Exception{
		 System.out.println("remind:"+remind);
		 if(remind.getId()==null) {
			 noteService.insertReminder(remind);
		 }else {
			 noteService.updateReminder(remind);
		 }
		 return new JsonResult();
	 }
	 
	 /**
	  * 保存筆記
	  * @param title
	  * @param text
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("/saveNote")
	 @ResponseBody
	 public JsonResult saveNote(String title,String text,int label,Long id) throws Exception{
		 if(id==null) {
			 noteService.insertNote(title,text,label);
		 }else {
			 	NoteDao note = new NoteDao();
			 	note.setId(id);
				note.setNoteTitle(title);
				note.setNoteText(text);
				note.setLabel(label);
				note.setCreateTime(new Date());
				note.setModifyTime(new Date());
			 noteService.updateNote(note);
		 }
		 return new JsonResult();
	 }
	 
	 /**
	  * 查询笔记信息
	  * @param id
	  * @return
	  * @throws Exception
	  */
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
		 System.out.println("loadMyNote");
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
