package cn.buu.note.common;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import cn.buu.note.exception.CustomException;
import cn.buu.note.exception.ErrorEnum;

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
}
