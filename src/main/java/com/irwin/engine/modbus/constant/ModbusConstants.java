package com.irwin.engine.modbus.constant;

/*
 * @Author irwin
 * @CreateAt 2020/1/2 21:44
 * @Description: modbus常量
 * @Since version-1.0
 */
public class ModbusConstants {
    public static final int ERROR_OFFSET = 0x80;

    public static final int SYNC_RESPONSE_TIMEOUT = 2000; //milliseconds
    public static final int TRANSACTION_IDENTIFIER_MAX = 100; //affects memory usage of library

    public static final int ADU_MAX_LENGTH = 260;//应用数据单元长度（地址域、功能码、数据、差错校验CRC16）
    public static final int MBAP_LENGTH = 7;//MBAP报文头的长度
    /**
     * Modbus TCP 报文帧格式描述
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
    //默认端口
    public static final int DEFAULT_MODBUS_PORT = 502;
    //默认协议标识(0:modbus协议)
    public static final short DEFAULT_PROTOCOL_IDENTIFIER = 0;
    //默认单元标识，即从设备地址(0-0xff)
    public static final short DEFAULT_UNIT_IDENTIFIER = 255;
}
