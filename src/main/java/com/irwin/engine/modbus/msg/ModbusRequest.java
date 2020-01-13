package com.irwin.engine.modbus.msg;

import com.irwin.engine.modbus.constant.ModbusConstants;

/*
 * @Author irwin
 * @CreateAt 2020/1/2 12:33
 * @Description: Modbus请求报文抽象类
 * @Since version-1.0
 */
abstract public class ModbusRequest extends ModbusMessage {
    public ModbusRequest(short functionCode) {
        super(functionCode);
    }

    public static boolean isError(byte functionCode) {
        return functionCode - ModbusConstants.ERROR_OFFSET >= 0;
    }

}
