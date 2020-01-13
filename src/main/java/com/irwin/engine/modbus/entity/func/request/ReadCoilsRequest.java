package com.irwin.engine.modbus.entity.func.request;

import com.irwin.engine.modbus.Modbus;
import com.irwin.engine.modbus.entity.exception.ModbusTransportException;
import com.irwin.engine.modbus.entity.func.AbstractFunction;

import static com.irwin.engine.modbus.code.FunctionCode.READ_COILS;

/*
 * @Author irwin
 * @CreateAt 2020/1/9 9:56
 * @Description: 读线圈开关量（布尔）
 * @Since version-1.0
 */
public class ReadCoilsRequest extends AbstractFunction {
    //startingAddress = 0x0000 to 0xFFFF
    //quantityOfCoils = 1 - 2000 (0x07D0)

    public ReadCoilsRequest() {
        super(READ_COILS);
    }

    public ReadCoilsRequest(int startingAddress, int quantityOfCoils) {
        super(READ_COILS,startingAddress, quantityOfCoils);
    }
    public int getStartingAddress() {
        return address;
    }

    public int getQuantityOfCoils() {
        return value;
    }

    @Override
    public String toString() {
        return "ReadCoilsRequest{" + "startingAddress=" + address + ", quantityOfCoils=" + value + '}';
    }

    @Override
    public void validate(Modbus modbus) throws ModbusTransportException {
        //to do
    }
}
