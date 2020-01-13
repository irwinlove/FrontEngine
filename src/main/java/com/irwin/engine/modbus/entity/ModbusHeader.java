package com.irwin.engine.modbus.entity;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/*
 * @Author irwin
 * @CreateAt 2020/1/2 16:03
 * @Description: modbus报文头信息
 * @Since version-1.0
 */
public class ModbusHeader {
    private final int transactionIdentifier;//事务处理标识符,modbus请求/响应事务处理的标识符
    private final int protocolIdentifier;//协议标识符，0=modbus协议
    private final int length;//随后字节的数量
    private final short unitIdentifier;//串行链路或tcp上连接的远程从站的识别
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
    public ModbusHeader(int transactionIdentifier, int protocolIdentifier, int pduLength, short unitIdentifier) {
        this.transactionIdentifier = transactionIdentifier;
        this.protocolIdentifier = protocolIdentifier;
        this.length = pduLength + 1;
        this.unitIdentifier = unitIdentifier;
    }

    public int getTransactionIdentifier() {
        return transactionIdentifier;
    }

    public int getProtocolIdentifier() {
        return protocolIdentifier;
    }

    public int getLength() {
        return length;
    }

    public short getUnitIdentifier() {
        return unitIdentifier;
    }

    /**
     * 解码-将二进制流解码成java对象
     * @param buffer
     * @return
     */
    public static ModbusHeader decode(ByteBuf buffer) {
        return new ModbusHeader(buffer.readUnsignedShort(),
                buffer.readUnsignedShort(),
                buffer.readUnsignedShort(),
                buffer.readUnsignedByte());
    }

    /**
     * 编码-封装成二进制
     * @return
     */
    public ByteBuf encode() {
        ByteBuf buf = Unpooled.buffer();
        buf.writeShort(transactionIdentifier);
        buf.writeShort(protocolIdentifier);
        buf.writeShort(length);
        buf.writeByte(unitIdentifier);
        return buf;
    }

    @Override
    public String toString() {
        return "ModbusHeader{" +
                "transactionIdentifier=" + transactionIdentifier +
                ", protocolIdentifier=" + protocolIdentifier +
                ", length=" + length +
                ", unitIdentifier=" + unitIdentifier +
                '}';
    }
}
