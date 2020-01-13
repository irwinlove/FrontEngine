package com.irwin.engine.modbus.entity.exception;

/*
 * @Author irwin
 * @CreateAt 2020/1/2 11:38
 * @Description:
 * @Since version-1.0
 */
public class ModbusInitException extends Exception {
    private static final long serialVersionUID = -1;

    public ModbusInitException() {
        super();
    }

    public ModbusInitException(String message, Throwable cause) {
        super(message, cause);
    }

    public ModbusInitException(String message) {
        super(message);
    }

    public ModbusInitException(Throwable cause) {
        super(cause);
    }
}
