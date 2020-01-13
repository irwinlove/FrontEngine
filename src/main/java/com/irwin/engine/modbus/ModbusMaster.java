package com.irwin.engine.modbus;

import com.irwin.engine.modbus.entity.exception.ModbusInitException;

/*
 * @Author irwin
 * @CreateAt 2020/1/2 11:26
 * @Description:
 * @Since version-1.0
 */
abstract public class ModbusMaster extends Modbus{
    private int timeout = 500;//连接超时
    private int retries = 2;//连接重试次数
    /**
     * Should we validate the responses:
     *  - ensure that the requested slave id is what is in the response
     */
    protected boolean validateResponse;
    /**
     * If connection is established with slave/slaves
     */
    protected boolean connected = false;
    public boolean isConnected() {
        return connected;
    }
    public void setConnected(boolean connected) {
        this.connected = connected;
    }
    /**
     * If the slave equipment only supports multiple write commands, set this to true. Otherwise, and combination of
     * single or multiple write commands will be used as appropriate.
     * 是否支持多寄存器写
     */
    private boolean multipleWritesOnly;

    abstract public void init() throws ModbusInitException;
    protected boolean initialized;
    public boolean isInitialized() {
        return initialized;
    }
//    public final ModbusResponse send(ModbusRequest request) throws ModbusTransportException {
//        request.validate(this);
//        return sendImpl(request);
//    }
//
//    abstract public ModbusResponse sendImpl(ModbusRequest request) throws ModbusTransportException;
//    public int getTimeout() {
//        return timeout;
//    }

    public void setTimeout(int timeout) {
        if (timeout < 1)
            this.timeout = 1;
        else
            this.timeout = timeout;
    }

    public int getRetries() {
        return retries;
    }

    public void setRetries(int retries) {
        if (retries < 0)
            this.retries = 0;
        else
            this.retries = retries;
    }

}
