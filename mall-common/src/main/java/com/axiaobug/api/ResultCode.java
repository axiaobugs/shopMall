package com.axiaobug.api;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 05 2021
 */
public enum ResultCode implements IErrorCode{
    // success
    SUCCESS(200, "操作成功"),
    // failed
    FAILED(500, "操作失败"),
    // validate failed
    VALIDATE_FAILED(404, "参数检验失败"),
    // unauthorized
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    // forbidden
    FORBIDDEN(403, "没有相关权限");

    private long code;
    private String message;

    ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
