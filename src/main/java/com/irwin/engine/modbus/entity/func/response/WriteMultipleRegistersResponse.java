package com.irwin.engine.modbus.entity.func.response;

import com.irwin.engine.modbus.Modbus;
import com.irwin.engine.modbus.entity.exception.ModbusTransportException;
import com.irwin.engine.modbus.entity.func.AbstractFunction;

import static com.irwin.engine.modbus.code.FunctionCode.WRITE_MULTIPLE_REGISTERS;

/*
 * @Author irwin
 * @CreateAt 2020/1/9 10:58
 * @Description: 写多个寄存器响应
 * @Since version-1.0
 */
public class WriteMultipleRegistersResponse extends AbstractFunction {
    //startingAddress = 0x0000 to 0xFFFF
    //quantityOfRegisters = 1 - 123 (0x07D0)
    public WriteMultipleRegistersResponse() {
        super(WRITE_MULTIPLE_REGISTERS);
    }

    public WriteMultipleRegistersResponse(int startingAddress, int quantityOfRegisters) {
        super(WRITE_MULTIPLE_REGISTERS, startingAddress, quantityOfRegisters);
    }

    public int getStartingAddress() {
        return address;
    }

    public int getQuantityOfRegisters() {
        return value;
    }

    @Override
    public void validate(Modbus modbus) throws ModbusTransportException {
        //to do
    }

    @Override
    public String toString() {
        return "WriteMultipleRegistersResponse{" + "startingAddress=" + address + ", quantityOfRegisters=" + value + '}';
    }
}
