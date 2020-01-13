package com.irwin.engine.modbus.entity.func.request;

import com.irwin.engine.modbus.Modbus;
import com.irwin.engine.modbus.entity.exception.ModbusTransportException;
import com.irwin.engine.modbus.entity.func.AbstractFunction;

import static com.irwin.engine.modbus.code.FunctionCode.READ_DISCRETE_INPUTS;

/*
 * @Author irwin
 * @CreateAt 2020/1/9 10:02
 * @Description: 读离散输入量
 * @Since version-1.0
 */
public class ReadDiscreteInputsRequest extends AbstractFunction {
    //startingAddress = 0x0000 to 0xFFFF
    //quantityOfCoils = 1 - 2000 (0x07D0)
    public ReadDiscreteInputsRequest() {
        super(READ_DISCRETE_INPUTS);
    }

    public ReadDiscreteInputsRequest(int startingAddress, int quantityOfCoils) {
        super(READ_DISCRETE_INPUTS, startingAddress, quantityOfCoils);
    }

    public int getStartingAddress() {
        return address;
    }

    public int getQuantityOfCoils() {
        return value;
    }

    @Override
    public String toString() {
        return "ReadDiscreteInputsRequest{" + "startingAddress=" + address + ", quantityOfCoils=" + value + '}';
    }

    @Override
    public void validate(Modbus modbus) throws ModbusTransportException {
        //to do
    }
}
