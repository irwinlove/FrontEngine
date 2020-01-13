package com.irwin.engine.modbus;

import com.irwin.engine.modbus.entity.exception.ModbusTransportException;

/*
 * @Author irwin
 * @CreateAt 2020/1/2 9:47
 * @Description: modbus基类
 * @Since version-1.0
 */
public class Modbus {
    //最大读取二进制数量
    public static final int DEFAULT_MAX_READ_BIT_COUNT = 2000;
    //最大读取寄存器地址数量
    public static final int DEFAULT_MAX_READ_REGISTER_COUNT = 125;
    //最大写寄存器地址数量
    public static final int DEFAULT_MAX_WRITE_REGISTER_COUNT = 120;

    private int maxReadBitCount = DEFAULT_MAX_READ_BIT_COUNT;
    private int maxReadRegisterCount = DEFAULT_MAX_READ_REGISTER_COUNT;
    private int maxWriteRegisterCount = DEFAULT_MAX_WRITE_REGISTER_COUNT;

    public void validateNumberOfRegisters(int registers) throws ModbusTransportException {
        if (registers < 1 || registers > maxReadRegisterCount) {
            throw new ModbusTransportException("Invalid number of registers: " + registers);
        }
    }
}
