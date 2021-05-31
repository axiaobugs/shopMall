package com.axiaobug.common;


/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 05 2021
 */

public enum ResultCode implements IErrorCode{
    // OK [GET]
    SUCCESS(200, "GET SUCCESS"),
    //CREATED [POST/PUT/PATCH]
    CREATED(201,"THE USER CREATED OR MODIFIED THE DATA SUCCESSFULLY."),
    //ACCEPTED
    ACCEPTED(202,"A REQUEST HAS ENTERED THE BACKGROUND QUEUE (ASYNCHRONOUS TASK)"),
    //NO CONTENT
    DELETED(204,"DELETED"),
    // FAILED
    FAILED(500, "操作失败"),
    // VALIDATE_FAILED
    VALIDATE_FAILED(404, "参数检验失败"),
    // UNAUTHORIZED
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    // FORBIDDEN
    FORBIDDEN(403, "没有相关权限"),
    // NOT ACCEPTABLE
    NOTACCEPTABLE(406,"THE FORMAT REQUESTED BY THE USER IS NOT AVAILABLE"),
    // UNPROCESABLE
    ERRORVERIFICATION(422,"A VERIFICATION ERROR OCCURRED");
    private final long code;
    private final String message;


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
