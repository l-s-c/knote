package cn.buu.note.exception;

public class CustomException extends Exception{
		private ErrorEnum errorEnum;

		public CustomException(ErrorEnum errorEnum) {
			super();
			this.errorEnum = errorEnum;
		}
		public CustomException(ErrorEnum errorEnum,String errorMsg) {
			super();
			this.errorEnum = errorEnum;
			this.errorEnum.setErrorMsg(errorMsg);
		}
		public ErrorEnum getErrorEnum() {
			return errorEnum;
		}
		public void setErrorEnum(ErrorEnum errorEnum) {
			this.errorEnum = errorEnum;
		}
		
		
}
