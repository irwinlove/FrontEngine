package com.irwin.engine.modbus.app;

import com.irwin.engine.modbus.ModbusServer;

/*
 * @Author irwin
 * @CreateAt 2020/1/8 11:14
 * @Description: application
 * @Since version-1.0
 */
public class ModbusSlaveServerApp {
    public static void main(String[] args) {
        ModbusServer modbusServer = Server4Test.getInstance().getModbusServer();
    }
}
