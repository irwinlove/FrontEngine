package com.irwin.engine.modbus.msg;

/*
 * @Author irwin
 * @CreateAt 2020/1/2 22:07
 * @Description: modbus响应报文抽象类
 * @Since version-1.0
 */
abstract public class ModbusResponse extends ModbusMessage {
    protected static final byte MAX_FUNCTION_CODE = (byte) 0x80;

    public ModbusResponse(Short functionCode) {
        super(functionCode);
    }
}
