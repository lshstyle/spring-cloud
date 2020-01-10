package com.example.gateway.model;

public class Result<T> {
	
	private Integer code;
	private String msg;
	private boolean success;
	private T data;
	
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	private Result(){}
	
	private Result(boolean success, int code, String msg) {
		this.success = success;
		this.msg = msg;
		this.code = code;
	}
	
	private Result(T data, boolean success, int code, String msg) {
		this.data = data;
		this.success = success;
		this.code = code;
		this.msg = msg;
	}
	
	public static <T> Result getInstance(T data, boolean success, int code, String msg) {
		return new Result(data, success, code, msg);
	}
	
	public static <T> Result getInstance() {
		return new Result();
	}

}
