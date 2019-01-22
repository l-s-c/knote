package cn.buu.note.common;


import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.buu.note.exception.CustomException;
import cn.buu.note.exception.ErrorEnum;
import cn.buu.note.utils.UUIDUtils;
@Controller
@CrossOrigin
public class BaseController {
	protected static final String CONTENT_TYPE_FORMED="application/x-www-form-urlencoded";
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public JsonResult ExceptionHandler(Exception e) {
		if(e instanceof CustomException) {
			CustomException ce = (CustomException)e;
			return new JsonResult(ce.getErrorEnum().getErrorCode(),ce.getErrorEnum().getErrorMsg());
		}else {
			return new JsonResult(ErrorEnum.UN_KNOW_ERROR.getErrorCode(),ErrorEnum.UN_KNOW_ERROR.getErrorMsg());
		}
	}
	
	  @RequestMapping("/upload")
	  @ResponseBody
	  public JsonResult upload(RedirectAttributes redirectAttributes,
	    HttpServletRequest request, HttpServletResponse response) throws IOException, FileUploadException {
	 System.out.println("upload");
		  CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				  request.getSession().getServletContext());
	   String image = null;
	   String fileName = null;
	   String path = null;
	   if (multipartResolver.isMultipart(request)) {
	    MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
	    Iterator<String> iter = multiRequest.getFileNames();
	    String myFileName = null;
	    String myFiledName = null;
	    while (iter.hasNext()) {
	     List<MultipartFile> file = multiRequest.getFiles(iter.next());
	     if (file != null) {
	      for (MultipartFile files : file) {
	       myFileName = files.getOriginalFilename();
	       myFiledName = files.getName();
	       if (myFileName.toString().trim() != "") {
	        path = request.getSession().getServletContext().getRealPath("file"+File.separator+"img");
	        //UUID
	        String uuid = UUIDUtils.getUUID();
	        fileName = uuid + "_" + myFileName;
	        if (myFiledName.equals("pictures")) {
	        	image = fileName;
	        }
	        File targetFile = new File(path, fileName);
	        System.out.println(fileName+"  ,   "+path);
	        files.transferTo(targetFile);
	       }
	      }
	     }
	    }
	   }
	return new JsonResult(fileName);
	   	
	  }
}
