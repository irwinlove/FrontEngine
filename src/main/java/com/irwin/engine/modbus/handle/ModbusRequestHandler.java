package com.irwin.engine.modbus.handle;

import com.irwin.engine.modbus.ModbusServer;
import com.irwin.engine.modbus.entity.ModbusFrame;
import com.irwin.engine.modbus.entity.ModbusHeader;
import com.irwin.engine.modbus.entity.func.WriteSingleCoil;
import com.irwin.engine.modbus.entity.func.WriteSingleRegister;
import com.irwin.engine.modbus.entity.func.request.*;
import com.irwin.engine.modbus.entity.func.response.*;
import com.irwin.engine.modbus.msg.ModbusMessage;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * @Author irwin
 * @CreateAt 2020/1/7 10:50
 * @Description: 请求报文处理器
 * @Since version-1.0
 */
public abstract class ModbusRequestHandler extends SimpleChannelInboundHandler<ModbusFrame> {
    private static final Logger logger = Logger.getLogger(ModbusRequestHandler.class.getSimpleName());
    private ModbusServer server;

    public void setServer(ModbusServer server) {
        this.server = server;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.warning(cause.getLocalizedMessage());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        server.removeClient(ctx.channel());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        server.addClient(ctx.channel());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ModbusFrame frame) throws Exception {
        Channel channel = ctx.channel();

        ModbusMessage PDUFunction = frame.getFunction();
        System.out.println(ctx.channel().remoteAddress().toString()+"--R---header::"
                +frame.getHeader().toString()
        +"---PDU::"+frame.getFunction().toString());
        ModbusMessage response;

        logger.log(Level.FINEST, PDUFunction.toString());
        if (PDUFunction instanceof WriteSingleCoil) {
            WriteSingleCoil request = (WriteSingleCoil) PDUFunction;

            response = writeSingleCoil(request);
        } else if (PDUFunction instanceof WriteSingleRegister) {
            WriteSingleRegister request = (WriteSingleRegister) PDUFunction;

            response = writeSingleRegister(request);
        } else if (PDUFunction instanceof ReadCoilsRequest) {
            ReadCoilsRequest request = (ReadCoilsRequest) PDUFunction;

            response = readCoilsRequest(request);
        } else if (PDUFunction instanceof ReadDiscreteInputsRequest) {
            ReadDiscreteInputsRequest request = (ReadDiscreteInputsRequest) PDUFunction;

            response = readDiscreteInputsRequest(request);
        } else if (PDUFunction instanceof ReadInputRegistersRequest) {
            ReadInputRegistersRequest request = (ReadInputRegistersRequest) PDUFunction;

            response = readInputRegistersRequest(request);
        } else if (PDUFunction instanceof ReadHoldingRegistersRequest) {
            ReadHoldingRegistersRequest request = (ReadHoldingRegistersRequest) PDUFunction;

            response = readHoldingRegistersRequest(request);
        } else if (PDUFunction instanceof WriteMultipleRegistersRequest) {
            WriteMultipleRegistersRequest request = (WriteMultipleRegistersRequest) PDUFunction;

            response = writeMultipleRegistersRequest(request);
        } else if (PDUFunction instanceof WriteMultipleCoilsRequest) {
            WriteMultipleCoilsRequest request = (WriteMultipleCoilsRequest) PDUFunction;
            response = writeMultipleCoilsRequest(request);
        } else {
            throw new UnsupportedOperationException("Function not supported!");
        }
        ModbusHeader header = new ModbusHeader(
                frame.getHeader().getTransactionIdentifier(),
                frame.getHeader().getProtocolIdentifier(),
                response.calculateLength(),
                frame.getHeader().getUnitIdentifier());

        ModbusFrame responseFrame = new ModbusFrame(header, response);
        channel.write(responseFrame);
    }
        protected abstract WriteSingleCoil writeSingleCoil(WriteSingleCoil request);

    protected abstract WriteSingleRegister writeSingleRegister(WriteSingleRegister request);

    protected abstract ReadCoilsResponse readCoilsRequest(ReadCoilsRequest request);

    protected abstract ReadDiscreteInputsResponse readDiscreteInputsRequest(ReadDiscreteInputsRequest request);

    protected abstract ReadInputRegistersResponse readInputRegistersRequest(ReadInputRegistersRequest request);

    protected abstract ReadHoldingRegistersResponse readHoldingRegistersRequest(ReadHoldingRegistersRequest request);

    protected abstract WriteMultipleRegistersResponse writeMultipleRegistersRequest(WriteMultipleRegistersRequest request);

    protected abstract WriteMultipleCoilsResponse writeMultipleCoilsRequest(WriteMultipleCoilsRequest request);
}
