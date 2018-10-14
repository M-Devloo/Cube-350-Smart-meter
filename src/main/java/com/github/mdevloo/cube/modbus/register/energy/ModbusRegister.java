package com.github.mdevloo.cube.modbus.register.energy;

import com.github.mdevloo.cube.modbus.register.access.RegisterAccess;
import com.github.mdevloo.cube.modbus.register.scalers.RegisterScaling;

public interface ModbusRegister {

    RegisterAccess getRegisterAccess();

    int getRegisterValue();

    int getModbusRegisterValue();

    RegisterScaling getRegisterScaling();
}