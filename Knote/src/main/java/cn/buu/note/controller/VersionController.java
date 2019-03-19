package cn.buu.note.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.buu.note.common.BaseController;
import cn.buu.note.common.JsonResult;
import cn.buu.note.entity.VersionDao;
import cn.buu.note.exception.CustomException;
import cn.buu.note.exception.ErrorEnum;
import cn.buu.note.service.version.VersionService;

@Controller
@RequestMapping("/version")
@CrossOrigin(allowCredentials="true",allowedHeaders="*") 
public class VersionController extends BaseController{
	@Resource
	private VersionService versionService;
	
	/**
	 * 检查版本，更新
	 * @param version
	 * @return
	 */
	@RequestMapping("/checkVersion")
	@ResponseBody
	public JsonResult checkVersion(String version) {
		System.out.println("version:"+version);
		String path = null;
		try {
			 path = versionService.checkVersion(version);
			 System.out.println("path:"+path);
		}catch(Exception e) {
			e.printStackTrace();
		}
			if(path!=null&&path.length()>0) {
				List<String> list = new ArrayList<>();
				list.add(path);
				return new JsonResult(list);
			}else {
				return new JsonResult();
			}
	}
	/**
	 * 加载所有版本信息
	 * @param version
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/loadVersion")
	@ResponseBody
	public JsonResult loadVersion() throws Exception {
		List<VersionDao> list = versionService.loadVersion();
		return new JsonResult(list);
	}
	
	/**
	 * 上传apk 到file/apk/
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/uploadAPK")
	public String upload(@RequestParam("file") MultipartFile file,VersionDao versionDao) throws Exception {
		
		versionService.uploadAPK(file,versionDao);

		return "redirect:../index.html";
	}
}
