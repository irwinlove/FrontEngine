package com.irwin.engine.modbus.entity.func.request;

import com.irwin.engine.modbus.Modbus;
import com.irwin.engine.modbus.entity.exception.ModbusTransportException;
import com.irwin.engine.modbus.entity.func.AbstractFunction;

import static com.irwin.engine.modbus.code.FunctionCode.READ_INPUT_REGISTERS;

/*
 * @Author irwin
 * @CreateAt 2020/1/9 10:04
 * @Description: 读输入寄存器请求
 * @Since version-1.0
 */
public class ReadInputRegistersRequest extends AbstractFunction {
    //startingAddress = 0x0000 to 0xFFFF
    //quantityOfInputRegisters = 1 - 125
    public ReadInputRegistersRequest() {
        super(READ_INPUT_REGISTERS);
    }

    public ReadInputRegistersRequest(int startingAddress, int quantityOfInputRegisters) {
        super(READ_INPUT_REGISTERS, startingAddress, quantityOfInputRegisters);
    }

    public int getStartingAddress() {
        return address;
    }

    public int getQuantityOfInputRegisters() {
        return value;
    }

    @Override
    public String toString() {
        return "ReadInputRegistersRequest{" + "startingAddress=" + address + ", quantityOfInputRegisters=" + value + '}';
    }

    @Override
    public void validate(Modbus modbus) throws ModbusTransportException {
        //to do
    }
}
