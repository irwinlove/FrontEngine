package com.irwin.engine.modbus.code;

/*
 * @Author irwin
 * @CreateAt 2020/1/2 10:14
 * @Description: 定义modbus寄存器寻址范围
 * @Since version-1.0
 */
public class RegisterRange {
    //输出线圈 读写
    public static final int COIL_STATUS = 1;
    //输入绕圈 只读
    public static final int INPUT_STATUS = 2;
    //保持寄存器 读写
    public static final int HOLDING_REGISTER = 3;
    //内部（输入）寄存器 只读
    public static final int INPUT_REGISTER = 4;
    //各类型寄存器起始地址
    public static int getFrom(int id) {
        switch (id) {
            case COIL_STATUS:
                return 0;
            case INPUT_STATUS:
                return 0x10000;
            case HOLDING_REGISTER:
                return 0x40000;
            case INPUT_REGISTER:
                return 0x30000;
        }
        return -1;
    }
    public static int getTo(int id) {
        switch (id) {
            case COIL_STATUS:
                return 0xffff;
            case INPUT_STATUS:
                return 0x1ffff;
            case HOLDING_REGISTER:
                return 0x4ffff;
            case INPUT_REGISTER:
                return 0x3ffff;
        }
        return -1;
    }
    public static int getReadFunctionCode(int id) {
        switch (id) {
            case COIL_STATUS:
                return FunctionCode.READ_COILS;
            case INPUT_STATUS:
                return FunctionCode.READ_DISCRETE_INPUTS;
            case HOLDING_REGISTER:
                return FunctionCode.READ_HOLDING_REGISTERS;
            case INPUT_REGISTER:
                return FunctionCode.READ_INPUT_REGISTERS;
        }
        return -1;
    }
}
