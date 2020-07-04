package com.hj.advertiser.base.pojo;

import java.util.UUID;

public class ResultModel<T> {
	private boolean success;
	private int code;
	private String msg;
	private T data;
	private UUID operationId;
	
	public ResultModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResultModel(int code, String msg, T data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	
	public boolean getSuccess() {
		return code == 1;
	}
//	public void setSuccess(boolean success) {
//		this.success = success;
//	}
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
	public void setData(T data) {
		this.data = data;
	}
	public UUID getOperationId() {
		return operationId;
	}
	public void setOperationId(UUID operationId) {
		this.operationId = operationId;
	}
	
}
