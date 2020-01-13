package com.irwin.engine.modbus.entity.func;

import com.irwin.engine.modbus.Modbus;
import com.irwin.engine.modbus.entity.exception.ModbusTransportException;
import io.netty.buffer.ByteBuf;

import static com.irwin.engine.modbus.code.FunctionCode.WRITE_SINGLE_COIL;

/*
 * @Author irwin
 * @CreateAt 2020/1/9 10:39
 * @Description: 写单个线圈
 * @Since version-1.0
 */
public class WriteSingleCoil extends AbstractFunction {
    //outputAddress;
    //outputValue;
    private boolean state;

    public WriteSingleCoil() {
        super(WRITE_SINGLE_COIL);
    }

    public WriteSingleCoil(int outputAddress, boolean state) {
        super(WRITE_SINGLE_COIL, outputAddress, state ? 0xFF00 : 0x0000);

        this.state = state;
    }

    public int getOutputAddress() {
        return address;
    }

    @Override
    public void decode(ByteBuf data) {
        super.decode(data);

        state = value == 0xFF00;
    }

    public boolean isState() {
        return state;
    }

    @Override
    public void validate(Modbus modbus) throws ModbusTransportException {
        //to do
    }

    @Override
    public String toString() {
        return "WriteSingleCoil{" + "outputAddress=" + address + ", state=" + state + '}';
    }
}
