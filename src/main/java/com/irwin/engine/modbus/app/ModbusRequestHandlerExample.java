package com.irwin.engine.modbus.app;

import com.irwin.engine.modbus.entity.func.WriteSingleCoil;
import com.irwin.engine.modbus.entity.func.WriteSingleRegister;
import com.irwin.engine.modbus.entity.func.request.*;
import com.irwin.engine.modbus.entity.func.response.*;
import com.irwin.engine.modbus.handle.ModbusRequestHandler;
import io.netty.channel.ChannelHandler.Sharable;

import java.util.BitSet;

/*
 * @Author irwin
 * @CreateAt 2020/1/8 10:50
 * @Description:
 * @Since version-1.0
 */
@Sharable
public class ModbusRequestHandlerExample extends ModbusRequestHandler {
    @Override
    protected WriteSingleCoil writeSingleCoil(WriteSingleCoil request) {
        return request;
    }

    @Override
    protected WriteSingleRegister writeSingleRegister(WriteSingleRegister request) {
        return request;
    }

    @Override
    protected ReadCoilsResponse readCoilsRequest(ReadCoilsRequest request) {
        BitSet coils = new BitSet(request.getQuantityOfCoils());
        coils.set(0);
        coils.set(5);
        coils.set(8);

        return new ReadCoilsResponse(coils);
    }

    @Override
    protected ReadDiscreteInputsResponse readDiscreteInputsRequest(ReadDiscreteInputsRequest request) {
        BitSet coils = new BitSet(request.getQuantityOfCoils());
        coils.set(0);
        coils.set(5);
        coils.set(8);

        return new ReadDiscreteInputsResponse(coils);
    }

    @Override
    protected ReadInputRegistersResponse readInputRegistersRequest(ReadInputRegistersRequest request) {
        int[] registers = new int[request.getQuantityOfInputRegisters()];
        registers[0] = 0xFFFF;
        registers[1] = 0xF0F0;
        registers[2] = 0x0F0F;

        return new ReadInputRegistersResponse(registers);
    }

    @Override
    protected ReadHoldingRegistersResponse readHoldingRegistersRequest(ReadHoldingRegistersRequest request) {
        int[] registers = new int[request.getQuantityOfInputRegisters()];
        registers[0] = 0xFFFF;
        registers[1] = 0xF0F0;
        registers[2] = 0x0F0F;

        return new ReadHoldingRegistersResponse(registers);
    }

    @Override
    protected WriteMultipleRegistersResponse writeMultipleRegistersRequest(WriteMultipleRegistersRequest request) {
        return new WriteMultipleRegistersResponse(request.getStartingAddress(), request.getQuantityOfRegisters());
    }

    @Override
    protected WriteMultipleCoilsResponse writeMultipleCoilsRequest(WriteMultipleCoilsRequest request) {
        return new WriteMultipleCoilsResponse(request.getStartingAddress(), request.getQuantityOfOutputs());
    }
}
