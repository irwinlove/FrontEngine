package com.irwin.engine.modbus.entity;

import com.irwin.engine.modbus.msg.ModbusMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/*
 * @Author irwin
 * @CreateAt 2020/1/6 16:37
 * @Description: modbus帧报文基类
 * @Since version-1.0
 */
public class ModbusFrame {
    private final ModbusHeader header;
    private final ModbusMessage function;
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
    public ModbusFrame(ModbusHeader header, ModbusMessage function) {
        this.header = header;
        this.function = function;
    }

    public ModbusHeader getHeader() {
        return header;
    }

    public ModbusMessage getFunction() {
        return function;
    }

public  ModbusFrame decode(ByteBuf buffer){
        return  new ModbusFrame(ModbusHeader.decode(buffer),function);
}
    public ByteBuf encode() {
        ByteBuf buf = Unpooled.buffer();

        buf.writeBytes(header.encode());
        buf.writeBytes(function.encode());

        return buf;
    }

    @Override
    public String toString() {
        return "ModbusFrame{" + "header=" + header + ", function=" + function + '}';
    }
}
