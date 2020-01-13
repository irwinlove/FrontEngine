package com.irwin.engine.modbus.entity.func.response;

import com.irwin.engine.modbus.Modbus;
import com.irwin.engine.modbus.entity.exception.ModbusTransportException;
import com.irwin.engine.modbus.entity.func.AbstractFunction;
import com.irwin.engine.modbus.msg.ModbusMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.util.BitSet;

import static com.irwin.engine.modbus.code.FunctionCode.READ_COILS;

/*
 * @Author irwin
 * @CreateAt 2020/1/9 10:47
 * @Description: 读多个线圈响应
 * @Since version-1.0
 */
public class ReadCoilsResponse extends ModbusMessage {
    private short byteCount;
    private BitSet coilStatus;

    public ReadCoilsResponse() {
        super(READ_COILS);
    }

    public ReadCoilsResponse(BitSet coilStatus) {
        super(READ_COILS);

        byte[] coils = coilStatus.toByteArray();

        // maximum of 2000 bits
        if (coils.length > 250) {
            throw new IllegalArgumentException();
        }

        this.byteCount = (short) coils.length;
        this.coilStatus = coilStatus;
    }

    public BitSet getCoilStatus() {
        return coilStatus;
    }

    public short getByteCount() {
        return byteCount;
    }

    @Override
    public int calculateLength() {
        return 1 + 1 + byteCount;
    }

    @Override
    public ByteBuf encode() {
        ByteBuf buf = Unpooled.buffer(calculateLength());
        buf.writeByte(getFunctionCode());
        buf.writeByte(byteCount);
        buf.writeBytes(coilStatus.toByteArray());

        return buf;
    }

    @Override
    public void decode(ByteBuf data) {
        byteCount = data.readUnsignedByte();

        byte[] coils = new byte[byteCount];
        data.readBytes(coils);

        coilStatus = BitSet.valueOf(coils);
    }

    @Override
    public void validate(Modbus modbus) throws ModbusTransportException {
        //to do
    }

    @Override
    public String toString() {
        return "ReadCoilsResponse{" + "byteCount=" + byteCount + ", coilStatus=" + coilStatus + '}';
    }
}
