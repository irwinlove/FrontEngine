package com.irwin.engine.modbus;

import com.irwin.engine.modbus.entity.exception.ModbusInitException;

/*
 * @Author irwin
 * @CreateAt 2020/1/2 14:58
 * @Description: modbus从站类
 * @Since version-1.0
 */
abstract public class ModbusSlaveSet extends Modbus {
    abstract public void start() throws ModbusInitException;
    abstract public void stop();
}
