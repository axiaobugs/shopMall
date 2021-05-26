package com.axiaobug.common;

/**
 * @Discription: 封装API的错误码 (Encapsulate the error code of the API)
 * @author Yanxiao
 * @version 0.1.0
 * @date 05 2021
 */
public interface IErrorCode {
    long getCode();

    String getMessage();
}
