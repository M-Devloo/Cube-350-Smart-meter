package com.github.mdevloo.cube.modbus.service;

import com.github.mdevloo.cube.modbus.communication.result.ModbusResult;
import com.github.mdevloo.cube.modbus.register.energy.ModbusRegister;

import java.util.List;
import java.util.concurrent.ScheduledFuture;

public interface ModbusService {

    ScheduledFuture<List<ModbusResult>> startSchedulingService();

    void stopSchedulingService();

    void subscribeRegisters(final ModbusRegister... modbusRegisterList);
}