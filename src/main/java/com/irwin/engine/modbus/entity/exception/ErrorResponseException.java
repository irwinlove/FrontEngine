package com.irwin.engine.modbus.entity.exception;

import com.irwin.engine.modbus.entity.func.ModbusError;

/*
 * @Author irwin
 * @CreateAt 2020/1/7 10:14
 * @Description: 异常响应信息
 * @Since version-1.0
 */
public class ErrorResponseException extends Exception {
    public ErrorResponseException(ModbusError modbusError) {
        super(modbusError.toString());
    }
}
