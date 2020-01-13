package com.irwin.engine.modbus.handle;

import com.irwin.engine.modbus.code.FunctionCode;
import com.irwin.engine.modbus.entity.ModbusFrame;
import com.irwin.engine.modbus.entity.func.WriteSingleCoil;
import com.irwin.engine.modbus.entity.func.WriteSingleRegister;
import com.irwin.engine.modbus.entity.func.request.*;
import com.irwin.engine.modbus.entity.func.response.*;
import com.irwin.engine.modbus.msg.ModbusMessage;
import com.irwin.engine.modbus.entity.func.ModbusError;
import com.irwin.engine.modbus.entity.ModbusHeader;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

import static com.irwin.engine.modbus.constant.ModbusConstants.MBAP_LENGTH;

/*
 * @Author irwin
 * @CreateAt 2020/1/2 22:00
 * @Description: modbus 报文解码
 * @Since version-1.0
 */
public class ModbusDecoder extends ByteToMessageDecoder {
    private final boolean serverMode;

    public ModbusDecoder(boolean serverMode) {
        this.serverMode = serverMode;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.capacity() < MBAP_LENGTH + 1) {
            return;
        }
        ModbusHeader mbapHeader = ModbusHeader.decode(in);
        //功能码
        short functionCode = in.readUnsignedByte();

        ModbusMessage message = null;
        switch (functionCode) {
            case FunctionCode.READ_COILS:
                if (serverMode) {
                    message = new ReadCoilsRequest();
                } else {
                    message = new ReadCoilsResponse();
                }
                break;
            case FunctionCode.READ_DISCRETE_INPUTS:
                if (serverMode) {
                    message = new ReadDiscreteInputsRequest();
                } else {
                    message = new ReadDiscreteInputsResponse();
                }
                break;
            case FunctionCode.READ_INPUT_REGISTERS:
                if (serverMode) {
                    message = new ReadInputRegistersRequest();
                } else {
                    message = new ReadInputRegistersResponse();
                }
                break;
            case FunctionCode.READ_HOLDING_REGISTERS:
                if (serverMode) {
                    message = new ReadHoldingRegistersRequest();
                } else {
                    message = new ReadHoldingRegistersResponse();
                }
                break;
            case FunctionCode.WRITE_SINGLE_COIL:
                message = new WriteSingleCoil();
                break;
            case FunctionCode.WRITE_SINGLE_REGISTER:
                message = new WriteSingleRegister();
                break;
            case FunctionCode.WRITE_MULTIPLE_COILS:
                if (serverMode) {
                    message = new WriteMultipleCoilsRequest();
                } else {
                    message = new WriteMultipleCoilsResponse();
                }
                break;
            case FunctionCode.WRITE_MULTIPLE_REGISTERS:
                if (serverMode) {
                    message = new WriteMultipleRegistersRequest();
                } else {
                    message = new WriteMultipleRegistersResponse();
                }
                break;
        }

        if (ModbusMessage.isError(functionCode)) {
            message = new ModbusError(functionCode);
        } else if (message == null) {
            message = new ModbusError(functionCode, (short)1);
        }

        message.decode(in.readBytes(in.readableBytes()));

        ModbusFrame frame = new ModbusFrame(mbapHeader, message);

        out.add(frame);
    }
}
