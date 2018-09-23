package com.github.mdevloo.cube.modbus.service;

import com.github.mdevloo.cube.modbus.communication.result.ModbusResult;

import java.util.List;
import java.util.concurrent.ScheduledFuture;

public interface ModbusService {

    ScheduledFuture<List<ModbusResult>> startSchedulingService();

    void stopSchedulingService();
}