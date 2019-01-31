package cn.buu.note.exception;

public enum ErrorEnum {
	
	UN_KNOW_ERROR(101,"未知错误"),
	ILL_PARAMETER_ERROR(201,"参数错误"),
	DB_CONNECT_ERROR(301,"数据库连接异常"),
	RE_ACTIVATE_ERROR(401,"重复激活"),
	EMAIL_ERROR(501,"邮件发送失败"),
	REGISTER_ERROR(601,"注册失败"),
	USER_EXIT_ERROR(701,"用户已存在"),
	USER_LOSE_ERROR(702,"用户失效"),
	ANALYZE_ERROR(801,"图片识别错误，请检查图片"),
	
	;
	

	private int errorCode;
	private String errorMsg;
	private ErrorEnum(int errorCode,String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
}
