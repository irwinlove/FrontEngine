package com.irwin.engine.modbus.msg;

import com.irwin.engine.modbus.Modbus;
import com.irwin.engine.modbus.constant.ModbusConstants;
import com.irwin.engine.modbus.entity.exception.ModbusTransportException;
import io.netty.buffer.ByteBuf;

/*
 * @Author irwin
 * @CreateAt 2020/1/6 20:26
 * @Description: modbus报文抽象类
 * @Since version-1.0
 */
abstract public class ModbusMessage {

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
    private final short functionCode;
    public ModbusMessage(short functionCode) {
        this.functionCode = functionCode;
    }

    public short getFunctionCode() {
        return functionCode;
    }
    public static boolean isError(short functionCode) {
        return functionCode - ModbusConstants.ERROR_OFFSET >= 0;
    }
    abstract public void validate(Modbus modbus) throws ModbusTransportException;
    public abstract int calculateLength();//计算PDU报文长度

    public abstract ByteBuf encode();//编码

    public abstract void decode(ByteBuf data);//解码

}
