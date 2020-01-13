package com.irwin.engine.modbus.entity.exception;

/*
 * @Author irwin
 * @CreateAt 2020/1/7 10:58
 * @Description: 连接异常定义
 * @Since version-1.0
 */
public class ConnectionException extends Exception {
    public ConnectionException(String message) {
        super(message);
    }
}
