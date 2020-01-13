package com.irwin.engine.modbus.entity.func;

import com.irwin.engine.modbus.msg.ModbusMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/*
 * @Author irwin
 * @CreateAt 2020/1/6 21:13
 * @Description: modbus报文PDU抽象类
 * @Since version-1.0
 */
public abstract class AbstractFunction extends ModbusMessage {
    /**
     * Modbus TCP 报文帧描述
     *  - max. 260 Byte (ADU = 7 Byte MBAP + 253 Byte PDU)
     *  - Length field includes Unit Identifier + PDU
     *
     * <----------------------------------------------- ADU -------------------------------------------------------->
     * <---------------------------- MBAP -----------------------------------------><------------- PDU ------------->
     * +------------------------+---------------------+----------+-----------------++---------------+---------------+
     * | Transaction Identifier | Protocol Identifier | Length   | Unit Identifier || Function Code | Data          |
     * | (2 Byte)               | (2 Byte)            | (2 Byte) | (1 Byte)        || (1 Byte)      | (1 - 252 Byte |
     * +------------------------+---------------------+----------+-----------------++---------------+---------------+
     */
    protected int address;//地址
    protected int value;//数值
    public AbstractFunction(short functionCode) {
        super(functionCode);
    }

    public AbstractFunction(short functionCode, int address, int quantity) {
        super(functionCode);
        this.address = address;
        this.value = quantity;
    }

    @Override
    public int calculateLength() {
        //function code + address + quantity
        return 1 + 2 + 2;
    }

    @Override
    public ByteBuf encode() {
        ByteBuf buf = Unpooled.buffer(calculateLength());
        buf.writeByte(getFunctionCode());
        buf.writeShort(address);
        buf.writeShort(value);
        return buf;
    }

    @Override
    public void decode(ByteBuf data) {
        address = data.readUnsignedShort();
        value = data.readUnsignedShort();
    }
}
