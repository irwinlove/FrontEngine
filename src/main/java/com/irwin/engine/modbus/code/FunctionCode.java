package com.irwin.engine.modbus.code;

/*
 * @Author irwin
 * @CreateAt 2020/1/2 10:27
 * @Description: modbus功能码定义
 * @Since version-1.0
 */
public class FunctionCode {
    //读取线圈状态
    public static final short READ_COILS = 0x01;
    //读离散输入状态
    public static final short READ_DISCRETE_INPUTS = 0x02;
    //读保持寄存器
    public static final short READ_HOLDING_REGISTERS = 0x03;
    //读取内部寄存器
    public static final short READ_INPUT_REGISTERS = 0x04;
    //强制（写）单个线圈
    public static final short WRITE_SINGLE_COIL = 0x05;
    //预设（写）单个寄存器
    public static final short WRITE_SINGLE_REGISTER = 0x06;
    //读取异常状态
    public static final short READ_EXCEPTION_STATUS = 0x07;
    //强制（写）多个线圈
    public static final short WRITE_MULTIPLE_COILS = 0x0F;
    //预设（写）多个寄存器
    public static final short WRITE_MULTIPLE_REGISTERS = 0x10;
    /** Constant <code>REPORT_SLAVE_ID=17</code> */
    public static final short REPORT_SLAVE_ID = 0x11;
    //已屏蔽的写入寄存器
    public static final short WRITE_MASK_REGISTER = 0x16;
    //读写多个寄存器
    public static final short READ_WRITE_MULTIPLE_REGISTERS = 0x17;
    public static final short READ_FIFO_QUEUE = 0x18;
    public static final short ENCAPSULATED_INTERFACE_TRANSPORT = 0x2B;

    public static String toString(short code) {
        return Integer.toString(code & 0xff);
    }
}
