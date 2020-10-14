package com.github.mdevloo.cube.modbus.service;

import com.github.mdevloo.cube.modbus.communication.result.ModbusResult;

import java.util.List;

public interface CubeMeterCallback {

    void notifyRegisterUpdates(final List<ModbusResult> receivedRegisters);
}