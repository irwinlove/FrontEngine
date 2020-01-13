package com.irwin.engine.modbus.entity.func.response;

import com.irwin.engine.modbus.Modbus;
import com.irwin.engine.modbus.entity.exception.ModbusTransportException;
import com.irwin.engine.modbus.msg.ModbusResponse;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import static com.irwin.engine.modbus.code.FunctionCode.READ_HOLDING_REGISTERS;

/*
 * @Author irwin
 * @CreateAt 2020/1/2 22:06
 * @Description: 保持寄存器响应数据处理
 * @Since version-1.0
 */
public class ReadHoldingRegistersResponse extends ModbusResponse {
    /**
     * Modbus TCP 读保持寄存器响应报文帧格式描述
     *  - max. 260 Byte (ADU = 7 Byte MBAP + 253 Byte PDU)
     *  - Length field includes Unit Identifier + PDU
     *
     * <----------------------------------------------- ADU -------------------------------------------------------->
     * <---------------------------- MBAP -----------------------------------------><------------- PDU ------------->
     * +------------------------+---------------------+----------+-----------------++-----0x03--------+---------------+
     * | Transaction Identifier | Protocol Identifier | Length   | Unit Identifier || Function Code | Data          |
     *                                                                                              +--byteCount--+--data---------+
     * | (2 Byte)               | (2 Byte)            | (2 Byte) | (1 Byte)        || (1 Byte)      | (1 Byte )   |(2~250Byte)
     * +------------------------+---------------------+----------+-----------------++---------------+---------------+
     */
    //字节数量
    private short byteCount;
    //寄存器数据
    private int[] registers;
    public ReadHoldingRegistersResponse() {
        super(READ_HOLDING_REGISTERS);
    }

    public ReadHoldingRegistersResponse(int[] registers) {
        super(READ_HOLDING_REGISTERS);
        if (registers.length > 125) {
            throw new IllegalArgumentException();
        }
        this.byteCount = (short) (registers.length * 2);
        this.registers = registers;
    }

    public short getByteCount() {
        return byteCount;
    }

    public int[] getRegisters() {
        return registers;
    }

    @Override
    public void validate(Modbus modbus) throws ModbusTransportException {

    }

    /**
     * =======================
     * |功能码     | 1个字节  |
     * |数据长度   | 1个字节  |
     * |寄存器数据 | n个字节  |
     * =======================
     */
    @Override
    public int calculateLength() {
        return 1 + 1 + byteCount;
    }

    @Override
    public ByteBuf encode() {
        ByteBuf buf = Unpooled.buffer(calculateLength());
        buf.writeByte(getFunctionCode());
        buf.writeByte(byteCount);
        for (int i = 0; i < registers.length; i++) {
            buf.writeShort(registers[i]);
        }
        //System.out.println(buf.toString());
        return buf;
    }

    @Override
    public void decode(ByteBuf data) {
        //数据长度
        byteCount = data.readUnsignedByte();
        //寄存器数据(数据长度/2 字节，每个数据高字节在前)
        registers = new int[byteCount / 2];
        for (int i = 0; i < registers.length; i++) {
            registers[i] = data.readUnsignedShort();
        }
    }

    @Override
    public String toString() {
        StringBuilder registersStr = new StringBuilder();
        registersStr.append("{");
        for (int i = 0; i < registers.length; i++) {
            registersStr.append("register_")
                    .append(i)
                    .append("=")
                    .append(registers[i])
                    .append(", ");
        }
        registersStr.delete(registersStr.length() - 2, registersStr.length());
        registersStr.append("}");
        return "ReadHoldingRegistersResponse{" + "byteCount=" + byteCount + ", inputRegisters=" + registersStr + '}';
    }
}