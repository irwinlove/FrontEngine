package com.irwin.engine.modbus.value;

import com.irwin.engine.modbus.code.DataType;
import com.irwin.engine.modbus.entity.exception.InvalidDataConversionException;

import java.math.BigInteger;

/*
 * @Author irwin
 * @CreateAt 2020/1/2 11:13
 * @Description: modbus各种数据类型实际值
 * @Since version-1.0
 */
abstract public class ModbusValue {
    private final DataType type;
    private final Object value;

    public ModbusValue(DataType type, Object value) {
        this.type = type;
        this.value = value;
    }

    public DataType getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }
    //布尔
    public boolean booleanValue() {
        if (value instanceof Boolean)
            return ((Boolean) value).booleanValue();
        throw new InvalidDataConversionException("Can't convert " + value.getClass() + " to boolean");
    }
    //整型 integer short
    public int intValue() {
        if (value instanceof Integer)
            return ((Integer) value).intValue();
        if (value instanceof Short)
            return ((Short) value).shortValue() & 0xffff;
        throw new InvalidDataConversionException("Can't convert " + value.getClass() + " to int");
    }
    //长整形long
    public long longValue() {
        if (value instanceof Long)
            return ((Long) value).longValue();
        if (value instanceof Integer)
            return ((Integer) value).intValue() & 0xffffffff;
        if (value instanceof Short)
            return ((Short) value).shortValue() & 0xffff;
        throw new InvalidDataConversionException("Can't convert " + value.getClass() + " to long");
    }
    public BigInteger bigIntegerValue() {
        if (value instanceof BigInteger)
            return (BigInteger) value;
        if (value instanceof Long)
            return BigInteger.valueOf(((Long) value).longValue());
        if (value instanceof Integer)
            return BigInteger.valueOf(((Integer) value).intValue() & 0xffffffff);
        if (value instanceof Short)
            return BigInteger.valueOf(((Short) value).shortValue() & 0xffff);
        throw new InvalidDataConversionException("Can't convert " + value.getClass() + " to BigInteger");
    }
    //浮点型float
    public float floatValue() {
        if (value instanceof Float)
            return ((Float) value).floatValue();
        throw new InvalidDataConversionException("Can't convert " + value.getClass() + " to float");
    }
    //双精度double
    public double doubleValue() {
        if (value instanceof Double)
            return ((Double) value).doubleValue();
        if (value instanceof Float)
            return ((Float) value).doubleValue();
        throw new InvalidDataConversionException("Can't convert " + value.getClass() + " to float");
    }
}
