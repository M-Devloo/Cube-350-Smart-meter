package com.github.mdevloo.cube.modbus.service;

import com.github.mdevloo.cube.modbus.register.energy.ModbusRegister;

import java.util.concurrent.ScheduledFuture;

public interface ModbusService {

    ScheduledFuture startSchedulingService();

    void stopSchedulingService();

    void subscribeRegisters(final ModbusRegister... modbusRegisterList);
}