package com.irwin.engine.modbus.entity.func.request;

import com.irwin.engine.modbus.Modbus;
import com.irwin.engine.modbus.entity.exception.ModbusTransportException;
import com.irwin.engine.modbus.entity.func.AbstractFunction;
import io.netty.buffer.ByteBuf;

import static com.irwin.engine.modbus.code.FunctionCode.WRITE_MULTIPLE_REGISTERS;

/*
 * @Author irwin
 * @CreateAt 2020/1/9 10:34
 * @Description: 写多个寄存器请求
 * @Since version-1.0
 */
public class WriteMultipleRegistersRequest extends AbstractFunction {
    //startingAddress = 0x0000 to 0xFFFF
    //quantityOfRegisters = 1 - 123 (0x07D0)
    private short byteCount;
    private int[] registers;

    public WriteMultipleRegistersRequest() {
        super(WRITE_MULTIPLE_REGISTERS);
    }

    public WriteMultipleRegistersRequest(int startingAddress, int quantityOfRegisters, int[] registers) {
        super(WRITE_MULTIPLE_REGISTERS, startingAddress, quantityOfRegisters);

        // maximum of 125 registers
        if (registers.length > 125) {
            throw new IllegalArgumentException();
        }

        this.byteCount = (short) (registers.length * 2);
        this.registers = registers;
    }

    public short getByteCount() {
        return byteCount;
    }

    public int getQuantityOfRegisters() {
        return value;
    }

    public int getStartingAddress() {
        return address;
    }

    public int[] getRegisters() {
        return registers;
    }

    @Override
    public int calculateLength() {
        return super.calculateLength() + 1 + byteCount;
    }

    @Override
    public ByteBuf encode() {
        ByteBuf buf = super.encode();

        buf.writeByte(byteCount);

        for (int i = 0; i < registers.length; i++) {
            buf.writeShort(registers[i]);
        }

        return buf;
    }

    @Override
    public void decode(ByteBuf data) {
        super.decode(data);

        byteCount = data.readUnsignedByte();

        registers = new int[byteCount / 2];
        for (int i = 0; i < registers.length; i++) {
            registers[i] = data.readUnsignedShort();
        }
    }

    @Override
    public void validate(Modbus modbus) throws ModbusTransportException {
        //to do
    }

    @Override
    public String toString() {
        StringBuilder registersStr = new StringBuilder();
        registersStr.append("{");
        for (int i = 0; i < registers.length; i++) {
            registersStr.append("register_");
            registersStr.append(i);
            registersStr.append("=");
            registersStr.append(registers[i]);
            registersStr.append(", ");
        }
        registersStr.delete(registersStr.length() - 2, registersStr.length());
        registersStr.append("}");

        return "WriteMultipleRegistersRequest{" + "startingAddress=" + address + ", quantityOfRegisters=" + value + ", byteCount=" + byteCount + ", registers=" + registersStr + '}';
    }
}
