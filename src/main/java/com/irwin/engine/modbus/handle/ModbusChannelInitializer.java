package com.irwin.engine.modbus.handle;

import com.irwin.engine.modbus.constant.ModbusConstants;
import com.irwin.engine.modbus.entity.ModbusFrame;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * @Author irwin
 * @CreateAt 2020/1/2 15:16
 * @Description: modbus channel 初始化
 * @Since version-1.0
 */
public class ModbusChannelInitializer extends ChannelInitializer<SocketChannel> {
    private final SimpleChannelInboundHandler handler;
    public ModbusChannelInitializer(ModbusRequestHandler handler) {
        this.handler = handler;
    }
    public ModbusChannelInitializer(ModbusResponseHandler handler) {
        this.handler = handler;
    }
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
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        System.out.println("---"+socketChannel.remoteAddress()+"--已连接--");

        ChannelPipeline pipeline = socketChannel.pipeline();
        //数据包的最大长度、长度域的偏移量、长度域的长度
        pipeline.addLast("framer", new LengthFieldBasedFrameDecoder(ModbusConstants.ADU_MAX_LENGTH, 4, 2));
        //编码
        pipeline.addLast("encoder", new ModbusEncoder());
        //解码
        pipeline.addLast("decoder", new ModbusDecoder(handler instanceof ModbusRequestHandler));
        if (handler instanceof ModbusRequestHandler) {
            //server
            pipeline.addLast("requestHandler", handler);
        } else if (handler instanceof ModbusResponseHandler) {
            //async client
            pipeline.addLast("responseHandler", handler);
        } else {
            //sync client
            pipeline.addLast("responseHandler", new ModbusResponseHandler() {

                @Override
                public void newResponse(ModbusFrame frame) {
                    //discard in sync mode
                }
            });
        }

    }
}
