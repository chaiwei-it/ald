package com.mood.common;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
public enum HttpCode {

    /** 200请求成功  */
    SUCCESS(200, "操作成功"),
    /** 207频繁操作  */
    MULTI_STATUS(207, "频繁操作"),
    /** 303登录失败  */
    LOGIN_FAIL(303, "登录失败"),
    /** 400请求参数出错  */
    BAD_REQUEST(400, "请求参数出错"),
    /** 401没有登录  */
    UNAUTHORIZED(401, "没有登录"),
    /** 403账号已登录  */
    HAD_LOGIN(402, "账号已登录"),
    /** 403没有权限  */
    FORBIDDEN(403, "没有权限"),
    /** 404找不到页面  */
    NOT_FOUND(404, "找不到页面"),
    /** 408请求超时  */
    REQUEST_TIMEOUT(408, "请求超时"),
    /** 500服务器出错  */
    INTERNAL_SERVER_ERROR(500, "服务器出错"),

    /**
     * 登录code
     */
    LOGIN_ERROR(20001, "用户名或密码错误"),
    LOGIN_DISABLED(20002, "登录失败次数超过5次，请15分钟后重新登录"),
    INDEX_VIDEO_SETTING_ERROR(20003, "首页视频未配置"),
    LOGIN_USER_ERROR(20004, "用户名不存在"),
    LOGIN_USER_STATE(20005, "用户为禁用状态"),
    ;

    private int code;
    private String msg;

    private HttpCode(int code, String msg) {
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

