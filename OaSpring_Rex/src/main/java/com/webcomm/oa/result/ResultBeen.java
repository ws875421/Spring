package com.webcomm.oa.result;

import java.io.Serializable;

/**
 * 統一回應物件
 * 
 * @author user
 *
 * @param <T>
 */
public class ResultBeen<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int SUCCESS = 0;
	public static final int ERROR = 1;

	private String msg = "success";

	private int code = SUCCESS;

	private T date;

	public ResultBeen() {
		super();
	}

	public ResultBeen(T date) {
		super();
		this.date = date;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public T getDate() {
		return date;
	}

	public void setDate(T date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "ResultBeen [msg=" + msg + ", code=" + code + ", date=" + date + "]";
	}
}
