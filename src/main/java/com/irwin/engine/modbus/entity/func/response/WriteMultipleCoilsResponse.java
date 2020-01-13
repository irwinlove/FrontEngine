package com.irwin.engine.modbus.entity.func.response;

import com.irwin.engine.modbus.Modbus;
import com.irwin.engine.modbus.entity.exception.ModbusTransportException;
import com.irwin.engine.modbus.entity.func.AbstractFunction;

import static com.irwin.engine.modbus.code.FunctionCode.WRITE_MULTIPLE_COILS;

/*
 * @Author irwin
 * @CreateAt 2020/1/9 10:56
 * @Description: 写多个线圈响应
 * @Since version-1.0
 */
public class WriteMultipleCoilsResponse extends AbstractFunction {
    //startingAddress = 0x0000 to 0xFFFF
    //quantityOfOutputs = 1 - 2000 (0x07D0)
    public WriteMultipleCoilsResponse() {
        super(WRITE_MULTIPLE_COILS);
    }

    public WriteMultipleCoilsResponse(int startingAddress, int quantityOfOutputs) {
        super(WRITE_MULTIPLE_COILS, startingAddress, quantityOfOutputs);
    }

    public int getStartingAddress() {
        return address;
    }

    public int getQuantityOfOutputs() {
        return value;
    }

    @Override
    public void validate(Modbus modbus) throws ModbusTransportException {

    }

    @Override
    public String toString() {
        return "WriteMultipleCoilsResponse{" + "startingAddress=" + address + ", quantityOfOutputs=" + value + '}';
    }
}
