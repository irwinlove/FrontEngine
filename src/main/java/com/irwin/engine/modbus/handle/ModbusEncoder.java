package com.irwin.engine.modbus.handle;

import com.irwin.engine.modbus.entity.ModbusFrame;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/*
 * @Author irwin
 * @CreateAt 2020/1/2 21:55
 * @Description: modbus编码操作 转换为二进制流
 * @Since version-1.0
 */
public class ModbusEncoder extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        if (msg instanceof ModbusFrame){
            ModbusFrame modbusFrame = (ModbusFrame) msg;
            ctx.writeAndFlush(modbusFrame.encode());
        }
        else {
            ctx.writeAndFlush(msg);
        }
    }
}
