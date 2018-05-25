package com.mood.base;

public enum ErrorEnum {

	SUCCESS(200, "SUCCESS"),//操作成功
	SERVER_ERROR(500,"SERVER ERROR!"),//操作失败
	NO_AUT_ERROR(403,"NO_AUT_ERROR!"),//认证失败
	PARAM_ERROR(400,"PARAM ERROR!"),//参数异常
	NOT_EXIST(500,"data not exist"),//数据不存在
	DUPLICATE(500,"数据已存在！"),//数据不存在
	NOT_ACCOUNT(1001,"账号不存在"),//数据不存在
	PASSWORD_ERROR(1002,"密码错误"),
	ACCOUNT_EXIST(1003,"账号已存在"),
	ROLE_NOT_HAVE(1004,"用户没有设置角色")
	;

	private int code;
	private String msg;

	private ErrorEnum(int code, String msg) {
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
	
}
