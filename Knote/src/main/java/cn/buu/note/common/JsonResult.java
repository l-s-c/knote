package cn.buu.note.common;

public class JsonResult {
	private int code;
	private String msg;
	private Object data;
	/**成功*/
	public JsonResult(Object data) {
		super();
		this.code = 1;
		this.data = data;
	}
	
	public JsonResult() {
		super();
		this.code = 1;
	}
	public JsonResult(String msg) {
		super();
		this.code = 1;
		this.msg = msg;
	}
	/**失败--报错提示时使用---配合枚举返回错误代码及其错误信息*/
	public JsonResult(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "JsonResult [code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}
	
}
