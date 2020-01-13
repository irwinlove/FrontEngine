package com.irwin.engine.modbus.entity.exception;

/*
 * @Author irwin
 * @CreateAt 2020/1/2 11:16
 * @Description: 数据类型转换异常
 * @Since version-1.0
 */
public class InvalidDataConversionException extends RuntimeException{
    private static final long serialVersionUID = -1;
    public InvalidDataConversionException(String message) {
        super(message);
    }
}
