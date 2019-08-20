package com.webcomm.oa.exception;

public class CustomGenericException extends Exception {

	private static final long serialVersionUID = 1L;
	private String errCode;
	private String errMsg;

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public CustomGenericException(String errCode, String errMsg) {
		super();
		this.errCode = errCode;
		this.errMsg = errMsg;
	}

	@Override
	public String toString() {
		return "CustomGenericException [errCode=" + errCode + ", errMsg=" + errMsg + "]";
	}

}
