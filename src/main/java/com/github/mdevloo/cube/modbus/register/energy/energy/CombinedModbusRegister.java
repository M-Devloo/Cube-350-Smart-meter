package com.github.mdevloo.cube.modbus.register.energy.energy;

import com.github.mdevloo.cube.modbus.register.energy.ModbusRegister;

public interface CombinedModbusRegister {

    ModbusRegister getLowModbusRegister();

    ModbusRegister getHighModbusRegister();
}