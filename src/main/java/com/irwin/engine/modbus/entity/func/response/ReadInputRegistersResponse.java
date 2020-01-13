package com.irwin.engine.modbus.entity.func.response;

import com.irwin.engine.modbus.Modbus;
import com.irwin.engine.modbus.entity.exception.ModbusTransportException;
import com.irwin.engine.modbus.msg.ModbusMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import static com.irwin.engine.modbus.code.FunctionCode.READ_INPUT_REGISTERS;

/*
 * @Author irwin
 * @CreateAt 2020/1/9 10:53
 * @Description: 读输入寄存器响应
 * @Since version-1.0
 */
public class ReadInputRegistersResponse extends ModbusMessage {
    private short byteCount;
    private int[] inputRegisters;

    public ReadInputRegistersResponse() {
        super(READ_INPUT_REGISTERS);
    }

    public ReadInputRegistersResponse(int[] inputRegisters) {
        super(READ_INPUT_REGISTERS);

        // maximum of 125 registers
        if (inputRegisters.length > 125) {
            throw new IllegalArgumentException();
        }

        this.byteCount = (short) (inputRegisters.length * 2);
        this.inputRegisters = inputRegisters;
    }

    public int[] getInputRegisters() {
        return inputRegisters;
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

        for (int i = 0; i < inputRegisters.length; i++) {
            buf.writeShort(inputRegisters[i]);
        }

        return buf;
    }

    @Override
    public void decode(ByteBuf data) {
        byteCount = data.readUnsignedByte();

        inputRegisters = new int[byteCount / 2];
        for (int i = 0; i < inputRegisters.length; i++) {
            inputRegisters[i] = data.readUnsignedShort();
        }
    }

    @Override
    public void validate(Modbus modbus) throws ModbusTransportException {

    }

    @Override
    public String toString() {
        StringBuilder registers = new StringBuilder();
        registers.append("{");
        for (int i = 0; i < inputRegisters.length; i++) {
            registers.append("register_");
            registers.append(i);
            registers.append("=");
            registers.append(inputRegisters[i]);
            registers.append(", ");
        }
        registers.delete(registers.length() - 2, registers.length());
        registers.append("}");

        return "ReadInputRegistersResponse{" + "byteCount=" + byteCount + ", inputRegisters=" + registers + '}';
    }
}
