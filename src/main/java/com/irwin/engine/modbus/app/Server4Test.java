package com.irwin.engine.modbus.app;

import com.irwin.engine.modbus.ModbusServer;
import com.irwin.engine.modbus.entity.exception.ConnectionException;

import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * @Author irwin
 * @CreateAt 2020/1/8 11:05
 * @Description: 服务端测试类
 * @Since version-1.0
 */
public class Server4Test {
    private final ModbusServer modbusServer;

    public Server4Test() {
        modbusServer = new ModbusServer(30502); //ModbusConstants.MODBUS_DEFAULT_PORT);
        try {
            modbusServer.setup(new ModbusRequestHandlerExample());
        } catch (ConnectionException ex) {
            Logger.getLogger(Server4Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ModbusServer getModbusServer() {
        return modbusServer;
    }

    public static Server4Test getInstance() {
        return Server4TestHolder.INSTANCE;
    }

    private static class Server4TestHolder {

        private static final Server4Test INSTANCE = new Server4Test();
    }
}
