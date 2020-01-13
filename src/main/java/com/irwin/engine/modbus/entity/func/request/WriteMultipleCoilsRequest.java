package com.irwin.engine.modbus.entity.func.request;

import com.irwin.engine.modbus.Modbus;
import com.irwin.engine.modbus.entity.exception.ModbusTransportException;
import com.irwin.engine.modbus.entity.func.AbstractFunction;
import io.netty.buffer.ByteBuf;

import java.util.BitSet;

import static com.irwin.engine.modbus.code.FunctionCode.WRITE_MULTIPLE_COILS;

/*
 * @Author irwin
 * @CreateAt 2020/1/9 10:05
 * @Description: 写多个线圈请求
 * @Since version-1.0
 */
public class WriteMultipleCoilsRequest extends AbstractFunction {
    //startingAddress = 0x0000 to 0xFFFF
    //quantityOfOutputs = 1 - 1968 (0x07B0)
    private short byteCount;
    private BitSet outputsValue;

    public WriteMultipleCoilsRequest() {
        super(WRITE_MULTIPLE_COILS);
    }

    public WriteMultipleCoilsRequest(int startingAddress, int quantityOfOutputs, BitSet outputsValue) {
        super(WRITE_MULTIPLE_COILS, startingAddress, quantityOfOutputs);

        byte[] coils = outputsValue.toByteArray();

        // maximum of 1968 bits
        if (coils.length > 246) {
            throw new IllegalArgumentException();
        }

        this.byteCount = (short) coils.length;
        this.outputsValue = outputsValue;
    }

    public short getByteCount() {
        return byteCount;
    }

    public BitSet getOutputsValue() {
        return outputsValue;
    }

    public int getQuantityOfOutputs() {
        return value;
    }

    public int getStartingAddress() {
        return address;
    }

    @Override
    public int calculateLength() {
        return super.calculateLength() + 1 + byteCount;
    }

    @Override
    public ByteBuf encode() {
        ByteBuf buf = super.encode();

        buf.writeByte(byteCount);
        buf.writeBytes(outputsValue.toByteArray());

        return buf;
    }

    @Override
    public void decode(ByteBuf data) {
        super.decode(data);

        byteCount = data.readUnsignedByte();

        byte[] coils = new byte[byteCount];
        data.readBytes(coils);

        outputsValue = BitSet.valueOf(coils);
    }

    @Override
    public void validate(Modbus modbus) throws ModbusTransportException {
    //to do
    }

    @Override
    public String toString() {
        return "WriteMultipleCoilsRequest{" + "startingAddress=" + address + ", quantityOfOutputs=" + value + ", byteCount=" + byteCount + ", outputsValue=" + outputsValue + '}';
    }
}
