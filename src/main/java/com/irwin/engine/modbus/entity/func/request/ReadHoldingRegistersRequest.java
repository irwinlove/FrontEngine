package com.irwin.engine.modbus.entity.func.request;

import com.irwin.engine.modbus.Modbus;
import com.irwin.engine.modbus.entity.exception.ModbusTransportException;
import com.irwin.engine.modbus.entity.func.AbstractFunction;

import static com.irwin.engine.modbus.code.FunctionCode.READ_HOLDING_REGISTERS;

/*
 * @Author irwin
 * @CreateAt 2020/1/7 10:43
 * @Description: 读保持寄存器请求
 * @Since version-1.0
 */
public class ReadHoldingRegistersRequest extends AbstractFunction {
    public ReadHoldingRegistersRequest() {
        super(READ_HOLDING_REGISTERS);
    }
    /**
     * @param address 0x0000 to 0xFFFF
     * @param quantity 1 - 125
     */
    public ReadHoldingRegistersRequest(int startingAddress, int quantityOfHoldingRegisters) {
        super(READ_HOLDING_REGISTERS, startingAddress, quantityOfHoldingRegisters);
    }
    public int getStartingAddress() {
        return address;
    }

    public int getQuantityOfInputRegisters() {
        return value;
    }
    @Override
    public String toString() {
        return "ReadHoldingRegistersRequest{" + "startingAddress=" + address + ", quantityOfInputRegisters=" + value + '}';
    }

    @Override
    public void validate(Modbus modbus) throws ModbusTransportException {

    }
}
