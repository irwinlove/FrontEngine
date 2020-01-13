package com.irwin.engine.modbus.entity.func;

import com.irwin.engine.modbus.Modbus;
import com.irwin.engine.modbus.entity.exception.ModbusTransportException;

import static com.irwin.engine.modbus.code.FunctionCode.WRITE_SINGLE_REGISTER;

/*
 * @Author irwin
 * @CreateAt 2020/1/9 10:42
 * @Description: 写单个寄存器
 * @Since version-1.0
 */
public class WriteSingleRegister extends AbstractFunction{
    //registerAddress;
    //registerValue;
    public WriteSingleRegister() {
        super(WRITE_SINGLE_REGISTER);
    }

    public WriteSingleRegister(int outputAddress, int value) {
        super(WRITE_SINGLE_REGISTER, outputAddress, value);
    }

    public int getRegisterAddress() {
        return address;
    }

    public int getRegisterValue() {
        return value;
    }

    @Override
    public void validate(Modbus modbus) throws ModbusTransportException {
        //to do
    }

    @Override
    public String toString() {
        return "WriteSingleInputRegister{" + "registerAddress=" + address + ", registerValue=" + value + '}';
    }
}
