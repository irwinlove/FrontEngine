package com.irwin.engine.modbus.entity.exception;

/*
 * @Author irwin
 * @CreateAt 2020/1/2 9:52
 * @Description: 自定义Modbus传输异常
 * @Since version-1.0
 */
public class ModbusTransportException extends Exception {
    private static final long serialVersionUID = -1;

    private final int slaveId;

    public ModbusTransportException(int slaveId) {
        this.slaveId = slaveId;
    }

    public ModbusTransportException() {
        this.slaveId = -1;
    }

    public ModbusTransportException(String message, Throwable cause, int slaveId) {
        super(message, cause);
        this.slaveId = slaveId;
    }

    public ModbusTransportException(String message) {
        super(message);
        this.slaveId = -1;
    }

    public ModbusTransportException(String message, int slaveId) {
        super(message);
        this.slaveId = slaveId;
    }

    public ModbusTransportException(Throwable cause) {
        super(cause);
        this.slaveId = -1;
    }

    public ModbusTransportException(Throwable cause, int slaveId) {
        super(cause);
        this.slaveId = slaveId;
    }
    public int getSlaveId() {
        return slaveId;
    }
}
